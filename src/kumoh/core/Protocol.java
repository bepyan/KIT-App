package kumoh.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Protocol {

   public static final int LEN_TYPE = 1;
   public static final int LEN_CODE = 1;
   public static final int LEN_BODYLENGTH = 4;
   public static final int LEN_HEADER = 6; // 6 Byte

   public static final int TYPE_ERROR = -1; // 오류 응답
   public static final int TYPE_UNDEFINED = 0; // 프로토콜이 지정되어 있지 않은 경우
   public static final int TYPE_EXIT = 1; // 프로그램 종료
   public static final int TYPE_LOGIN_REQ = 2; // 로그인 요청
   public static final int TYPE_LOGIN_RES = 3; // 로그인 응답
   public static final int TYPE_LOGOUT_REQ = 4; // 로그아웃 요청
   public static final int TYPE_LOGOUT_RES = 5; // 로그인 응답
   public static final int TYPE_STUDENT_INFO_REQ = 6; // 학생정보 요청 (단일, 자기자신)
   public static final int TYPE_STUDENT_INFO_RES = 7; // 학생정보 응답 (단일, 자기자신)
   public static final int TYPE_QUERY_SERVERTIME_REQ = 8; // 서버시간 요청
   public static final int TYPE_QUERY_SERVERTIME_RES = 9; // 서버시간 응답
   
   public static final int TYPE_DATAPACKAGE_INFO_REQ = 11; // 관리 데이터 집합 조회 요청 (단일 년도/학기)
   public static final int TYPE_DATAPACKAGE_INFO_RES = 12; // 관리 데이터 집합 조회 응답 (단일 년도/학기)   
   public static final int TYPE_DATAPACKAGE_CREATE_REQ = 13; // 관리 데이터 집합 등록 요청 (단일 년도/학기)
   public static final int TYPE_DATAPACKAGE_CREATE_RES = 14; // 관리 데이터 집합 등록 응답 (단일 년도/학기)   
   public static final int TYPE_DATAPACKAGE_UPDATE_REQ = 15; // 관리 데이터 집합 갱신 요청 (단일 년도/학기)
   public static final int TYPE_DATAPACKAGE_UPDATE_RES = 16; // 관리 데이터 집합 갱신 응답 (단일 년도/학기)   
   
   public static final int TYPE_RECRUITDATES_INFO_REQ = 21; // 년도학기 조회 요청 (전체)
   public static final int TYPE_RECRUITDATES_INFO_RES = 22; // 년도학기 조회 응답 (전체)
   public static final int TYPE_RECRUITDATE_SELECT_REQ = 23; // 년도학기 선택 요청 (단일)
   public static final int TYPE_RECRUITDATE_SELECT_RES = 24; // 년도학기 선택 응답 (단일)

   public static final int TYPE_SCHEDULES_INFO_REQ = 31; // 일정 조회 요청 (전체)
   public static final int TYPE_SCHEDULES_INFO_RES = 32; // 일정 조회 응답 (전체)
   public static final int TYPE_SCHEDULE_CHECK_REQ = 33; // 일정 확인 요청 (단일)
   public static final int TYPE_SCHEDULE_CHECK_RES = 34; // 일정 확인 응답 (단일)
   public static final int TYPE_SCHEDULE_CHECK2_REQ = 35; // 일정 확인2 요청 (단일)
   public static final int TYPE_SCHEDULE_CHECK2_RES = 36; // 일정 확인2 응답 (단일

   public static final int TYPE_RECRUITS_INFO_REQ = 41; // 모집 조회 요청 (전체)
   public static final int TYPE_RECRUITS_INFO_RES = 42; // 모집 조회 응답 (전체)

   public static final int TYPE_SUBRECRUITS_INFO_REQ = 51; // 세부모집 조회 요청 (전체, 세부정보 제외)
   public static final int TYPE_SUBRECRUITS_INFO_RES = 52; // 세부모집 조회 응답 (전체, 세부정보 제외)
   public static final int TYPE_SUBRECRUIT_INFO_REQ = 53; // 세부모집 조회 요청 (단일, 세부정보 포함)
   public static final int TYPE_SUBRECRUIT_INFO_RES = 54; // 세부모집 조회 응답 (단일, 세부정보 포함)
   public static final int TYPE_SUBRECRUIT_CREATE_REQ = 55; // 세부모집 등록 요청 (단일)
   public static final int TYPE_SUBRECRUIT_CREATE_RES = 56; // 세부모집 등록 응답 (단일)
   public static final int TYPE_SUBRECRUIT_UPDATE_REQ = 57; // 세부모집 갱신 요청 (단일)
   public static final int TYPE_SUBRECRUIT_UPDATE_RES = 58; // 세부모집 갱신 응답 (단일)
   
   public static final int TYPE_APPLIES_INFO_REQ = 61; // 신청정보 조회 요청 (전체, 관리자)
   public static final int TYPE_APPLIES_INFO_RES = 62; // 신청정보 조회 응답 (전체, 관리자)
   public static final int TYPE_APPLY_INFO_REQ = 63; // 신청정보 조회 요청 (단일, 학생)
   public static final int TYPE_APPLY_INFO_RES = 64; // 신청정보 조회 응답 (단일, 학생)
   public static final int TYPE_APPLY_CREATE_REQ = 65; // 신청정보 생성 요청 (단일)
   public static final int TYPE_APPLY_CREATE_RES = 66; // 신청정보 생성 응답 (단일)   
   public static final int TYPE_APPLY_UPDATE_REQ = 67; // 신청정보 갱신 요청 (단일)
   public static final int TYPE_APPLY_UPDATE_RES = 68; // 신청정보 갱신 응답 (단일)
   
   public static final int TYPE_SELECTIONS_INFO_REQ = 71; // 선발정보 조회 요청 (전체, 관리자)
   public static final int TYPE_SELECTIONS_INFO_RES = 72; // 선발정보 조회 응답 (전체, 관리자)
   public static final int TYPE_SELECTION_INFO_REQ = 73; // 선발정보 조회 요청 (단일, 학생)
   public static final int TYPE_SELECTION_INFO_RES = 74; // 선발정보 조회 응답 (단일, 학생)
   public static final int TYPE_SELECTION_VALID_REQ = 75; // 선발정보 유효변경 요청 (단일)
   public static final int TYPE_SELECTION_VALID_RES = 76; // 선발정보 유효변경 응답 (단일)
   public static final int TYPE_SELECTION_WAITNUM_REQ = 77; // 선발 대기번호 조회 요청 (단일, 자기자신)
   public static final int TYPE_SELECTION_WAITNUM_RES = 78; // 선발 대기번호 조회 응답 (단일, 자기자신)
   public static final int TYPE_SELECTION_VALID2_REQ = 79; // 선발정보 유효변경2 요청 (단일)
   public static final int TYPE_SELECTION_VALID2_RES = 80; // 선발정보 유효변경2 응답 (단일)

   public static final int TYPE_ACCEPT_INVOICE_REQ = 81; // 선발정보 납부 승인 요청 (다중)
   public static final int TYPE_ACCEPT_INVOICE_RES = 82; // 선발정보 납부 승인 응답 (다중)
   public static final int TYPE_ACCEPT_FILE_REQ = 83; // 결핵진단서 이미지 승인 요청 (다중)
   public static final int TYPE_ACCEPT_FILE_RES = 84; //  결핵진단서 이미지 승인 응답 (다중)

   public static final int TYPE_SELECTION_ENROLL_REQ = 101; // 선발 요청
   public static final int TYPE_SELECTION_ENROLL_RES = 102; // 선발 응답
   public static final int TYPE_BED_ENROLL_REQ = 103; // 호실 배정 요청
   public static final int TYPE_BED_ENROLL_RES = 104; // 호실 배정 응답
   
   public static final int TYPE_BED_FREE_REQ = 105; // 할당되어있던 호실을 되돌림 (세부모집단위)
   public static final int TYPE_BED_FREE_RES = 106; // 할당되어있던 호실을 되돌림 (세부모집단위)
   
   public static final int TYPE_FILE_UPLOAD_REQ = 121; // 선발: 결핵진단서 이미지 업로드 요청 (단일)
   public static final int TYPE_FILE_UPLOAD_RES = 122; // 선발: 결핵진단서 이미지 업로드 응답 (단일)
   public static final int TYPE_FILE_DOWNLOAD_REQ = 123; // 선발: 결핵진단서 이미지 다운로드 요청 (단일)
   public static final int TYPE_FILE_DOWNLOAD_RES = 124; // 선발: 결핵진단서 이미지 다운로드 응답 (단일)
   
   private byte type;
   private byte code;
   private int bodyLength;

   private byte[] body; // 직렬화 하여 저장

   public Protocol() {
      this(TYPE_UNDEFINED, 0);
   }

   public Protocol(int type) {
      this(type, 0);
   }

   public Protocol(int type, int code) {
      setType(type);
      setCode(code);
      setBodyLength(0);
   }

   public byte getType() {
      return type;
   }

   public void setType(int type) {
      this.type = (byte) type;
   }

   public byte getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = (byte) code;
   }

   public int getBodyLength() {
      return bodyLength;
   }

   private void setBodyLength(int bodyLength) { // Body Length는 직접 설정할 수 없음
      this.bodyLength = bodyLength;
   }

   public Object getBody() {
      if (getType() == 121 || getType() == 124)
         return body;
      else
         return deserialize(body);
   }
   
   public void setBody(Object body) {
      if (getType() == 121 || getType() == 124) {
         this.body = (byte[]) body;
         setBodyLength(this.body.length);
      } else {
         byte[] serializedObject = serialize(body);
         this.body = serializedObject;
         setBodyLength(serializedObject.length);
      }
   }

   public byte[] getPacket() { // 현재 header와 body로 패킷을 생성하여 리턴
      byte[] packet = new byte[LEN_HEADER + getBodyLength()];
      packet[0] = getType();
      packet[LEN_TYPE] = getCode();
      System.arraycopy(intToByte(getBodyLength()), 0, packet, LEN_TYPE + LEN_CODE, LEN_BODYLENGTH);
      if (getBodyLength() > 0) {
         System.arraycopy(body, 0, packet, LEN_HEADER, getBodyLength());
      }

      return packet;
   }

   public void setPacketHeader(byte[] packet) { // 매개 변수 packet을 통해 header만 생성
      byte[] data;

      setType(packet[0]);
      setCode(packet[LEN_TYPE]);

      data = new byte[LEN_BODYLENGTH];
      System.arraycopy(packet, LEN_TYPE + LEN_CODE, data, 0, LEN_BODYLENGTH);
      setBodyLength(byteToInt(data));
   }
   
   public void setPacketBody(byte[] packet) { // 매개 변수 packet을 통해 body를 생성
      byte[] data;

      if (getBodyLength() > 0) {
         data = new byte[getBodyLength()];
         System.arraycopy(packet, 0, data, 0, getBodyLength());
         if (getType() == 121 || getType() == 124)
            setBody(data);
         else 
            setBody(deserialize(data));
      }
   }

   private byte[] serialize(Object b) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(baos);
         oos.writeObject(b);
         return baos.toByteArray();
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   private Object deserialize(byte[] b) {
      try {
         ByteArrayInputStream bais = new ByteArrayInputStream(b);
         ObjectInputStream ois = new ObjectInputStream(bais);
         Object ob = ois.readObject();
         return ob;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

    private byte[] intToByte(int i) {
    return ByteBuffer.allocate(Integer.SIZE / 8).putInt(i).array();
    }

    private int byteToInt(byte[] b) {
    return ByteBuffer.wrap(b).getInt();
   }

   /*
   private byte[] shortToByte(short s) {
      return ByteBuffer.allocate(Short.SIZE / 8).putShort(s).array();
   }

   private int byteToShort(byte[] b) {
      return ByteBuffer.wrap(b).getShort();
   }
   */

}