import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;

import java.net.Socket;

public class Server_Linux {
	static String msg_len = "00000434";
	static String guid = "20220316090000000 I_MEK_01/001";
	static String service_id = "SC10394006     ";
	static String user_ip = "192.168.20.72         ";
	static String system_id = "          ";
	static String adapter_id = "                         ";
	static String request_tm = "20220318103011456                  ";
	static String response_tm = "                                   ";
	static String error_cd = "   ";
	static String error_msg = "complete                                                                                            ";

	static String account_number= "20220223 ";
	static String account_name= "PIZZA               ";
	static String account_type= "1";
	static String account_type_name= "DEPOSIT   ";
	static String customer_name= "PEPERONI            ";
	static String customer_grade= "EXCELLENT           ";
	static String branch_code= "777";
	static String branch_name= "KOREA               ";
	static String manager_member= "Olive               ";
	static String account_state= "1";
	static String account_state_name= "Normal              ";
	static String real_number= "Seoul         ";
	static String sex= "1";
	
    static String res_data = msg_len + guid + service_id + user_ip + system_id + adapter_id + request_tm 
            + response_tm + error_cd + error_msg
            + account_number + account_name + account_type + account_type_name+ customer_name+ customer_grade + branch_code + branch_name + manager_member +account_state+
            account_state_name + real_number + sex;

	public static void main(String[] args) {
		System.out.println("HI");

		try {
			ServerSocket serverSocket = new ServerSocket(8500); 
			System.out.println("Server is listening on port " + serverSocket);
			
			while (true) {
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(30000);
				
				System.out.println("Client is connecting");
				System.out.println("@@@ 111 @@@");


				while (true) {
					InputStream input = socket.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(input));
					String message = br.readLine();
					System.out.println("@@@ 222 @@@");
					System.out.println("@@@ 333 @@@");
					System.out.println("Request Data[" + message + "]");
					break;
				}

				OutputStream output = socket.getOutputStream();
				output.write(res_data.getBytes());
				 PrintWriter writer = new PrintWriter(output, true);
				 writer.println(res_data );
				System.out.println("Response Data[" + res_data + "]");
				 writer.flush();

				System.out.println("@@@ 444 @@@");

//				br.close();
				socket.close();
				serverSocket.close();

			}
		}

		catch (Exception e) {
			System.out.println("Server exception: " + e.getMessage());
			e.printStackTrace();

		}

	}

}
