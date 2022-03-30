import java.io.*;
import java.net.*;

class HttpClient {
	private static final String msg_len = "00000001";
	private static final String guid = "20220316090000000 I_MEK_01/001";
	private static final String service_id = "SM394006_IN    ";
	private static final String user_ip = "192.168.20.72         ";
	private static final String system_id = "I_MEK_01  ";
	private static final String adapter_id = "I_MEK_394006             ";
	private static final String request_tm = "20220316101511456                  ";
	private static final String response_tm = "                                   ";
	private static final String error_cd = "   ";
	private static final String error_msg = "                                                                                                    ";
	private static final String user_id = "admin                         ";
	private static final String user_name = "test01                        ";
	private static final String department = "test01                        ";
	private static final String telephone = "test01                        ";
	private static final String mobilephone = "test01                        ";
	private static final String email = "test01                        ";
	private static final String password = "test01                        ";
	static String req_data = msg_len + guid + service_id + user_ip + system_id + adapter_id + request_tm + response_tm
			+ error_cd + error_msg + user_id + user_name + department + telephone + mobilephone + email + password;

	public static void main(String[] args) throws Exception {
		// URL url = new URL("http://�ش� IP:Port");
		URL url = new URL("http://192.168.20.72:8555");
		System.out.println("Connected to Server URL [" + url + "]");

		// HTTP ��� POST ���
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true);

		// ��û�� out ������
		DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
		outputStream.writeBytes(req_data);
		System.out.println("��û���� [" + req_data + "]");

		outputStream.flush();
		outputStream.close();

		// Server�� ���� ���䰪  in �ޱ�  
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "EUC-KR"));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;

		//���� ���� �� �̻� �����ÿ� �� ���
		while ((inputLine = in.readLine()) != null) {
			stringBuffer.append(inputLine);
			System.out.println("�������� [" + inputLine + "]");
		}

		in.close();
		System.out.println("Completed!");
	}
}