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

   public static final int TYPE_ERROR = -1; // ���� ����
   public static final int TYPE_UNDEFINED = 0; // ���������� �����Ǿ� ���� ���� ���
   public static final int TYPE_EXIT = 1; // ���α׷� ����
   public static final int TYPE_LOGIN_REQ = 2; // �α��� ��û
   public static final int TYPE_LOGIN_RES = 3; // �α��� ����
   public static final int TYPE_LOGOUT_REQ = 4; // �α׾ƿ� ��û
   public static final int TYPE_LOGOUT_RES = 5; // �α��� ����
   public static final int TYPE_STUDENT_INFO_REQ = 6; // �л����� ��û (����, �ڱ��ڽ�)
   public static final int TYPE_STUDENT_INFO_RES = 7; // �л����� ���� (����, �ڱ��ڽ�)
   public static final int TYPE_QUERY_SERVERTIME_REQ = 8; // �����ð� ��û
   public static final int TYPE_QUERY_SERVERTIME_RES = 9; // �����ð� ����
   
   public static final int TYPE_DATAPACKAGE_INFO_REQ = 11; // ���� ������ ���� ��ȸ ��û (���� �⵵/�б�)
   public static final int TYPE_DATAPACKAGE_INFO_RES = 12; // ���� ������ ���� ��ȸ ���� (���� �⵵/�б�)   
   public static final int TYPE_DATAPACKAGE_CREATE_REQ = 13; // ���� ������ ���� ��� ��û (���� �⵵/�б�)
   public static final int TYPE_DATAPACKAGE_CREATE_RES = 14; // ���� ������ ���� ��� ���� (���� �⵵/�б�)   
   public static final int TYPE_DATAPACKAGE_UPDATE_REQ = 15; // ���� ������ ���� ���� ��û (���� �⵵/�б�)
   public static final int TYPE_DATAPACKAGE_UPDATE_RES = 16; // ���� ������ ���� ���� ���� (���� �⵵/�б�)   
   
   public static final int TYPE_RECRUITDATES_INFO_REQ = 21; // �⵵�б� ��ȸ ��û (��ü)
   public static final int TYPE_RECRUITDATES_INFO_RES = 22; // �⵵�б� ��ȸ ���� (��ü)
   public static final int TYPE_RECRUITDATE_SELECT_REQ = 23; // �⵵�б� ���� ��û (����)
   public static final int TYPE_RECRUITDATE_SELECT_RES = 24; // �⵵�б� ���� ���� (����)

   public static final int TYPE_SCHEDULES_INFO_REQ = 31; // ���� ��ȸ ��û (��ü)
   public static final int TYPE_SCHEDULES_INFO_RES = 32; // ���� ��ȸ ���� (��ü)
   public static final int TYPE_SCHEDULE_CHECK_REQ = 33; // ���� Ȯ�� ��û (����)
   public static final int TYPE_SCHEDULE_CHECK_RES = 34; // ���� Ȯ�� ���� (����)
   public static final int TYPE_SCHEDULE_CHECK2_REQ = 35; // ���� Ȯ��2 ��û (����)
   public static final int TYPE_SCHEDULE_CHECK2_RES = 36; // ���� Ȯ��2 ���� (����

   public static final int TYPE_RECRUITS_INFO_REQ = 41; // ���� ��ȸ ��û (��ü)
   public static final int TYPE_RECRUITS_INFO_RES = 42; // ���� ��ȸ ���� (��ü)

   public static final int TYPE_SUBRECRUITS_INFO_REQ = 51; // ���θ��� ��ȸ ��û (��ü, �������� ����)
   public static final int TYPE_SUBRECRUITS_INFO_RES = 52; // ���θ��� ��ȸ ���� (��ü, �������� ����)
   public static final int TYPE_SUBRECRUIT_INFO_REQ = 53; // ���θ��� ��ȸ ��û (����, �������� ����)
   public static final int TYPE_SUBRECRUIT_INFO_RES = 54; // ���θ��� ��ȸ ���� (����, �������� ����)
   public static final int TYPE_SUBRECRUIT_CREATE_REQ = 55; // ���θ��� ��� ��û (����)
   public static final int TYPE_SUBRECRUIT_CREATE_RES = 56; // ���θ��� ��� ���� (����)
   public static final int TYPE_SUBRECRUIT_UPDATE_REQ = 57; // ���θ��� ���� ��û (����)
   public static final int TYPE_SUBRECRUIT_UPDATE_RES = 58; // ���θ��� ���� ���� (����)
   
   public static final int TYPE_APPLIES_INFO_REQ = 61; // ��û���� ��ȸ ��û (��ü, ������)
   public static final int TYPE_APPLIES_INFO_RES = 62; // ��û���� ��ȸ ���� (��ü, ������)
   public static final int TYPE_APPLY_INFO_REQ = 63; // ��û���� ��ȸ ��û (����, �л�)
   public static final int TYPE_APPLY_INFO_RES = 64; // ��û���� ��ȸ ���� (����, �л�)
   public static final int TYPE_APPLY_CREATE_REQ = 65; // ��û���� ���� ��û (����)
   public static final int TYPE_APPLY_CREATE_RES = 66; // ��û���� ���� ���� (����)   
   public static final int TYPE_APPLY_UPDATE_REQ = 67; // ��û���� ���� ��û (����)
   public static final int TYPE_APPLY_UPDATE_RES = 68; // ��û���� ���� ���� (����)
   
   public static final int TYPE_SELECTIONS_INFO_REQ = 71; // �������� ��ȸ ��û (��ü, ������)
   public static final int TYPE_SELECTIONS_INFO_RES = 72; // �������� ��ȸ ���� (��ü, ������)
   public static final int TYPE_SELECTION_INFO_REQ = 73; // �������� ��ȸ ��û (����, �л�)
   public static final int TYPE_SELECTION_INFO_RES = 74; // �������� ��ȸ ���� (����, �л�)
   public static final int TYPE_SELECTION_VALID_REQ = 75; // �������� ��ȿ���� ��û (����)
   public static final int TYPE_SELECTION_VALID_RES = 76; // �������� ��ȿ���� ���� (����)
   public static final int TYPE_SELECTION_WAITNUM_REQ = 77; // ���� ����ȣ ��ȸ ��û (����, �ڱ��ڽ�)
   public static final int TYPE_SELECTION_WAITNUM_RES = 78; // ���� ����ȣ ��ȸ ���� (����, �ڱ��ڽ�)
   public static final int TYPE_SELECTION_VALID2_REQ = 79; // �������� ��ȿ����2 ��û (����)
   public static final int TYPE_SELECTION_VALID2_RES = 80; // �������� ��ȿ����2 ���� (����)

   public static final int TYPE_ACCEPT_INVOICE_REQ = 81; // �������� ���� ���� ��û (����)
   public static final int TYPE_ACCEPT_INVOICE_RES = 82; // �������� ���� ���� ���� (����)
   public static final int TYPE_ACCEPT_FILE_REQ = 83; // �������ܼ� �̹��� ���� ��û (����)
   public static final int TYPE_ACCEPT_FILE_RES = 84; //  �������ܼ� �̹��� ���� ���� (����)

   public static final int TYPE_SELECTION_ENROLL_REQ = 101; // ���� ��û
   public static final int TYPE_SELECTION_ENROLL_RES = 102; // ���� ����
   public static final int TYPE_BED_ENROLL_REQ = 103; // ȣ�� ���� ��û
   public static final int TYPE_BED_ENROLL_RES = 104; // ȣ�� ���� ����
   
   public static final int TYPE_BED_FREE_REQ = 105; // �Ҵ�Ǿ��ִ� ȣ���� �ǵ��� (���θ�������)
   public static final int TYPE_BED_FREE_RES = 106; // �Ҵ�Ǿ��ִ� ȣ���� �ǵ��� (���θ�������)
   
   public static final int TYPE_FILE_UPLOAD_REQ = 121; // ����: �������ܼ� �̹��� ���ε� ��û (����)
   public static final int TYPE_FILE_UPLOAD_RES = 122; // ����: �������ܼ� �̹��� ���ε� ���� (����)
   public static final int TYPE_FILE_DOWNLOAD_REQ = 123; // ����: �������ܼ� �̹��� �ٿ�ε� ��û (����)
   public static final int TYPE_FILE_DOWNLOAD_RES = 124; // ����: �������ܼ� �̹��� �ٿ�ε� ���� (����)
   
   private byte type;
   private byte code;
   private int bodyLength;

   private byte[] body; // ����ȭ �Ͽ� ����

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

   private void setBodyLength(int bodyLength) { // Body Length�� ���� ������ �� ����
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

   public byte[] getPacket() { // ���� header�� body�� ��Ŷ�� �����Ͽ� ����
      byte[] packet = new byte[LEN_HEADER + getBodyLength()];
      packet[0] = getType();
      packet[LEN_TYPE] = getCode();
      System.arraycopy(intToByte(getBodyLength()), 0, packet, LEN_TYPE + LEN_CODE, LEN_BODYLENGTH);
      if (getBodyLength() > 0) {
         System.arraycopy(body, 0, packet, LEN_HEADER, getBodyLength());
      }

      return packet;
   }

   public void setPacketHeader(byte[] packet) { // �Ű� ���� packet�� ���� header�� ����
      byte[] data;

      setType(packet[0]);
      setCode(packet[LEN_TYPE]);

      data = new byte[LEN_BODYLENGTH];
      System.arraycopy(packet, LEN_TYPE + LEN_CODE, data, 0, LEN_BODYLENGTH);
      setBodyLength(byteToInt(data));
   }
   
   public void setPacketBody(byte[] packet) { // �Ű� ���� packet�� ���� body�� ����
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