package kumoh.core;

import java.io.*;
import java.net.*;

import kumoh.core.Protocol;
import kumoh.core.model.Apply;
import kumoh.core.model.DataPackage;
import kumoh.core.model.Recruit;
import kumoh.core.model.RecruitDate;
import kumoh.core.model.Schedule;
import kumoh.core.model.Selection;
import kumoh.core.model.Student;
import kumoh.core.model.SubRecruit;

public class Network {
	private Socket socket;
	private InputStream is;
	private OutputStream os;

	public Network() {
		try {
			socket = new Socket("reldn.ipdisk.co.kr", 9999);
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println("통신오류: 서버와의 연결에 실패함");
		}
	}

	private void send(Protocol protocol) throws Exception {
		try {
			os.write(protocol.getPacket());
		} catch (Exception e) {
			throw new Exception("통신오류: 데이터 송신 실패함");
		}
	}

	private Protocol recv(int type) throws Exception {
		byte[] header = new byte[Protocol.LEN_HEADER];
		Protocol protocol = new Protocol();
		try {
			int totalReceived, readSize;
			do {
				totalReceived = 0;
				readSize = 0;
				is.read(header, 0, Protocol.LEN_HEADER);
				protocol.setPacketHeader(header);
				byte[] buf = new byte[protocol.getBodyLength()];
				while (totalReceived < protocol.getBodyLength()) {
					readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
					totalReceived += readSize;
					if (readSize == -1) {
						throw new Exception("통신오류: 연결 끊어짐");
					}
				}
				protocol.setPacketBody(buf);
				if (protocol.getType() == Protocol.TYPE_ERROR) {
					throw new Exception("통신오류: 서버에서 오류 발생함");
				} else if (protocol.getType() == type) {
					return protocol;
				}
			} while (true); // 현재 필요한 응답이 아닐경우 무시하고 다음 응답을 대기
		} catch (IOException e) {
			throw new Exception("통신오류: 데이터 수신 실패함");
		}
	}

	// ---- 통신 함수

	// ## 프로그램 종료
	public void exit() throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_EXIT);

		send(protocol); // 전송
		System.exit(0);
	}

	// ## 로그인
	public boolean[] login(String id, String pw) throws Exception {
		String[] body = new String[2];
		body[0] = id;
		body[1] = pw;

		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_LOGIN_REQ);
		protocol.setBody(body);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_LOGIN_RES); // 수신

		boolean[] result = new boolean[2];
		if (protocol.getCode() == 1) { // 로그인 성공
			result[0] = true;
			result[1] = ((String) protocol.getBody()).equals("관리자");
		} else { // 로그인 실패
			result[0] = false;
			result[1] = false;
		}
		return result;
	}

	// ## 로그아웃
	public boolean logout() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_LOGOUT_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_LOGOUT_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 학생정보 조회 (자기자신)
	public Student getStudent() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_STUDENT_INFO_REQ);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_STUDENT_INFO_RES); // 수신

		return (Student) protocol.getBody();
	}

	// ## 데이터 패키지 조회 (선택한 년도학기)
	public DataPackage getDataPackage() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_DATAPACKAGE_INFO_REQ);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_DATAPACKAGE_INFO_RES); // 수신

		return (DataPackage) protocol.getBody();
	}

	// ## 데이터 패키지 생성 (선택한 년도학기)
	public boolean createDataPackage(DataPackage pkg) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_DATAPACKAGE_CREATE_REQ);
		protocol.setBody(pkg);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_DATAPACKAGE_CREATE_RES); // 수신

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 데이터 패키지 갱신
	public boolean updateDataPackage(DataPackage pkg) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_DATAPACKAGE_UPDATE_REQ);
		protocol.setBody(pkg);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_DATAPACKAGE_UPDATE_RES); // 수신

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 년도학기 조회 (전체)
	public RecruitDate[] getRecruitDates() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_RECRUITDATES_INFO_REQ);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_RECRUITDATES_INFO_RES); // 수신

		return (RecruitDate[]) protocol.getBody();
	}

	// ## 년도학기 선택 (단일)
	public boolean selectRecruitDate(RecruitDate rcd) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_RECRUITDATE_SELECT_REQ);
		protocol.setBody(rcd);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_RECRUITDATE_SELECT_RES); // 수신

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 일정 조회 (전체)
	public Schedule[] getSchedules() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SCHEDULES_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_SCHEDULES_INFO_RES);

		return (Schedule[]) protocol.getBody();
	}

	// ## 일정 확인 (단일)
	public boolean checkSchedule(String name) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SCHEDULE_CHECK_REQ);
		protocol.setBody(name);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_SCHEDULE_CHECK_RES); // 수신

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 모집 조회 (전체)
	public Recruit[] getRecruits() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_RECRUITS_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_RECRUITS_INFO_RES);

		return (Recruit[]) protocol.getBody();
	}

	// ## 세부모집 조회 (전체, 세부정보 제외)
	public SubRecruit[] getSubRecruits() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SUBRECRUITS_INFO_REQ);
		protocol.setBody(null);

		send(protocol);
		protocol = recv(Protocol.TYPE_SUBRECRUITS_INFO_RES);

		return (SubRecruit[]) protocol.getBody();
	}

	// ## 세부모집 조회 (단일, 세부정보 포함)
	public SubRecruit getSubRecruit(String name) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SUBRECRUIT_INFO_REQ);
		protocol.setBody(name);

		send(protocol);
		protocol = recv(Protocol.TYPE_SUBRECRUIT_INFO_RES);

		return (SubRecruit) protocol.getBody();
	}

	// ## 세부모집 등록 (단일)
	public boolean createSubRecruit(SubRecruit data) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SUBRECRUIT_CREATE_REQ);
		protocol.setBody(data);
		send(protocol);
		protocol = recv(Protocol.TYPE_SUBRECRUIT_CREATE_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 세부모집 갱신 (단일)
	public boolean updateSubRecruit(SubRecruit data) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SUBRECRUIT_UPDATE_REQ);
		protocol.setBody(data);

		send(protocol);
		protocol = recv(Protocol.TYPE_SUBRECRUIT_UPDATE_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 신청정보 조회 (전체, 관리자)
	public Apply[] getApplies() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_APPLIES_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_APPLIES_INFO_RES);

		return (Apply[]) protocol.getBody();
	}

	// ## 신청정보 조회 (단일, 학생)
	public Apply getApply() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_APPLY_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_APPLY_INFO_RES);

		return (Apply) protocol.getBody();
	}

	// ## 신청정보 생성 요청
	// GPA(평점평균)와 Point(지역가산점), 신청일시는 비우고 전송 (서버에서 계산함)
	public boolean createApply(Apply apply) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_APPLY_CREATE_REQ);
		protocol.setBody(apply);

		send(protocol);
		protocol = recv(Protocol.TYPE_APPLY_CREATE_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 신청정보 갱신 요청
	// 학번, 모집정보, 식사정보 외에는 비우고 전송해도 무방함
	public boolean updateApply(Apply apply) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_APPLY_UPDATE_REQ);
		protocol.setBody(apply);

		send(protocol);
		protocol = recv(Protocol.TYPE_APPLY_UPDATE_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 선발정보 조회 (전체, 관리자)
	public Selection[] getSelections() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SELECTIONS_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_SELECTIONS_INFO_RES);

		return (Selection[]) protocol.getBody();
	}

	// ## 선발정보 조회 (단일, 학생)
	public Selection getSelection() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SELECTION_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_SELECTION_INFO_RES);

		return (Selection) protocol.getBody();
	}

	// ## 선발유효 변경
	// str[0]학번 str[1]유효(Y/N)
	public boolean validSelection(String[] str) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SELECTION_VALID_REQ);
		protocol.setBody(str);

		send(protocol);
		protocol = recv(Protocol.TYPE_SELECTION_VALID_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 승인 (고지서)
	// str[0]학번 str[1]납부일시
	public boolean accpetInvoice(String[] str) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_ACCEPT_INVOICE_REQ);
		protocol.setBody(str);

		send(protocol);
		protocol = recv(Protocol.TYPE_ACCEPT_INVOICE_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 승인 (결핵진단서)
	public boolean acceptFile(String[] ids) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_ACCEPT_FILE_REQ);
		protocol.setBody(ids);

		send(protocol);
		protocol = recv(Protocol.TYPE_ACCEPT_FILE_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 파일 업로드 (결핵진단서)
	public boolean uploadFile(File file) throws Exception {
		byte[] bytesArray = new byte[(int) file.length()];

		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();

		Protocol protocol = new Protocol(Protocol.TYPE_FILE_UPLOAD_REQ);
		protocol.setBody(bytesArray);

		send(protocol);
		protocol = recv(Protocol.TYPE_FILE_UPLOAD_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 파일 다운로드 (결핵진단서)
	public byte[] downloadFile(String id) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_FILE_DOWNLOAD_REQ);
		protocol.setBody(id);

		send(protocol);
		protocol = recv(Protocol.TYPE_FILE_DOWNLOAD_RES);

		if (protocol.getCode() == 1)
			return (byte[]) protocol.getBody();
		else
			return null;
	}

	// ## 선발 요청
	public boolean enrollSelection(String div) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SELECTION_ENROLL_REQ);
		protocol.setBody(div);

		send(protocol);
		protocol = recv(Protocol.TYPE_SELECTION_ENROLL_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## 호실 배정 요청
	public boolean enrollBed(String name) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_BED_ENROLL_REQ);
		protocol.setBody(name);

		send(protocol);
		protocol = recv(Protocol.TYPE_BED_ENROLL_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}
}
