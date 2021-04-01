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
			System.out.println("��ſ���: �������� ���ῡ ������");
		}
	}

	private void send(Protocol protocol) throws Exception {
		try {
			os.write(protocol.getPacket());
		} catch (Exception e) {
			throw new Exception("��ſ���: ������ �۽� ������");
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
						throw new Exception("��ſ���: ���� ������");
					}
				}
				protocol.setPacketBody(buf);
				if (protocol.getType() == Protocol.TYPE_ERROR) {
					throw new Exception("��ſ���: �������� ���� �߻���");
				} else if (protocol.getType() == type) {
					return protocol;
				}
			} while (true); // ���� �ʿ��� ������ �ƴҰ�� �����ϰ� ���� ������ ���
		} catch (IOException e) {
			throw new Exception("��ſ���: ������ ���� ������");
		}
	}

	// ---- ��� �Լ�

	// ## ���α׷� ����
	public void exit() throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_EXIT);

		send(protocol); // ����
		System.exit(0);
	}

	// ## �α���
	public boolean[] login(String id, String pw) throws Exception {
		String[] body = new String[2];
		body[0] = id;
		body[1] = pw;

		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_LOGIN_REQ);
		protocol.setBody(body);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_LOGIN_RES); // ����

		boolean[] result = new boolean[2];
		if (protocol.getCode() == 1) { // �α��� ����
			result[0] = true;
			result[1] = ((String) protocol.getBody()).equals("������");
		} else { // �α��� ����
			result[0] = false;
			result[1] = false;
		}
		return result;
	}

	// ## �α׾ƿ�
	public boolean logout() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_LOGOUT_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_LOGOUT_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## �л����� ��ȸ (�ڱ��ڽ�)
	public Student getStudent() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_STUDENT_INFO_REQ);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_STUDENT_INFO_RES); // ����

		return (Student) protocol.getBody();
	}

	// ## ������ ��Ű�� ��ȸ (������ �⵵�б�)
	public DataPackage getDataPackage() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_DATAPACKAGE_INFO_REQ);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_DATAPACKAGE_INFO_RES); // ����

		return (DataPackage) protocol.getBody();
	}

	// ## ������ ��Ű�� ���� (������ �⵵�б�)
	public boolean createDataPackage(DataPackage pkg) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_DATAPACKAGE_CREATE_REQ);
		protocol.setBody(pkg);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_DATAPACKAGE_CREATE_RES); // ����

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## ������ ��Ű�� ����
	public boolean updateDataPackage(DataPackage pkg) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_DATAPACKAGE_UPDATE_REQ);
		protocol.setBody(pkg);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_DATAPACKAGE_UPDATE_RES); // ����

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## �⵵�б� ��ȸ (��ü)
	public RecruitDate[] getRecruitDates() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_RECRUITDATES_INFO_REQ);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_RECRUITDATES_INFO_RES); // ����

		return (RecruitDate[]) protocol.getBody();
	}

	// ## �⵵�б� ���� (����)
	public boolean selectRecruitDate(RecruitDate rcd) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_RECRUITDATE_SELECT_REQ);
		protocol.setBody(rcd);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_RECRUITDATE_SELECT_RES); // ����

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## ���� ��ȸ (��ü)
	public Schedule[] getSchedules() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SCHEDULES_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_SCHEDULES_INFO_RES);

		return (Schedule[]) protocol.getBody();
	}

	// ## ���� Ȯ�� (����)
	public boolean checkSchedule(String name) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SCHEDULE_CHECK_REQ);
		protocol.setBody(name);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_SCHEDULE_CHECK_RES); // ����

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	// ## ���� ��ȸ (��ü)
	public Recruit[] getRecruits() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_RECRUITS_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_RECRUITS_INFO_RES);

		return (Recruit[]) protocol.getBody();
	}

	// ## ���θ��� ��ȸ (��ü, �������� ����)
	public SubRecruit[] getSubRecruits() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SUBRECRUITS_INFO_REQ);
		protocol.setBody(null);

		send(protocol);
		protocol = recv(Protocol.TYPE_SUBRECRUITS_INFO_RES);

		return (SubRecruit[]) protocol.getBody();
	}

	// ## ���θ��� ��ȸ (����, �������� ����)
	public SubRecruit getSubRecruit(String name) throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SUBRECRUIT_INFO_REQ);
		protocol.setBody(name);

		send(protocol);
		protocol = recv(Protocol.TYPE_SUBRECRUIT_INFO_RES);

		return (SubRecruit) protocol.getBody();
	}

	// ## ���θ��� ��� (����)
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

	// ## ���θ��� ���� (����)
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

	// ## ��û���� ��ȸ (��ü, ������)
	public Apply[] getApplies() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_APPLIES_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_APPLIES_INFO_RES);

		return (Apply[]) protocol.getBody();
	}

	// ## ��û���� ��ȸ (����, �л�)
	public Apply getApply() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_APPLY_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_APPLY_INFO_RES);

		return (Apply) protocol.getBody();
	}

	// ## ��û���� ���� ��û
	// GPA(�������)�� Point(����������), ��û�Ͻô� ���� ���� (�������� �����)
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

	// ## ��û���� ���� ��û
	// �й�, ��������, �Ļ����� �ܿ��� ���� �����ص� ������
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

	// ## �������� ��ȸ (��ü, ������)
	public Selection[] getSelections() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SELECTIONS_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_SELECTIONS_INFO_RES);

		return (Selection[]) protocol.getBody();
	}

	// ## �������� ��ȸ (����, �л�)
	public Selection getSelection() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_SELECTION_INFO_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_SELECTION_INFO_RES);

		return (Selection) protocol.getBody();
	}

	// ## ������ȿ ����
	// str[0]�й� str[1]��ȿ(Y/N)
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

	// ## ���� (������)
	// str[0]�й� str[1]�����Ͻ�
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

	// ## ���� (�������ܼ�)
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

	// ## ���� ���ε� (�������ܼ�)
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

	// ## ���� �ٿ�ε� (�������ܼ�)
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

	// ## ���� ��û
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

	// ## ȣ�� ���� ��û
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
