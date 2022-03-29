import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client_Linux {
	 static String msg_len="00000279";
	 static String guid="20220316090000000 I_MEK_01/001";
	 static String service_id="SC10394006     ";
	 static String user_ip= "192.168.20.72         ";
	 static String system_id= "          ";
	 static String adapter_id= "                         ";
	 static String request_tm= "20220318103011456                  ";
	 static String response_tm= "                                   ";
	 static String error_cd= "   ";
	 static String error_msg= "complete                                                                                            ";
	 static String branch_code= "777";
	 static String account_state= "1";

	 static String req_data = msg_len + guid + service_id + user_ip + system_id + adapter_id + request_tm + response_tm
    		 + error_cd + error_msg + branch_code + account_state;


	public static void main(String[] args) {
		String hostname = "192.168.20.71";
		int port = 8500;

		System.out.println("Connected! " + hostname + "/" + port);

		try (Socket socket = new Socket(hostname, port)) {
			socket.setSoTimeout(30000);
			
			//To Server
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);
			writer.println(req_data);
			
			System.out.println("Request[" + req_data + "]");
			writer.flush();
			
			//From Server
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String inputLine = reader.readLine();

			while ((inputLine != null)) {
				System.out.println("Response[" + inputLine + "]");
				break;
			}

			
			input.close();
			out.close();
			reader.close();
			writer.close();
			socket.close();
			
			System.out.println("Completed!");

		} catch (UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("I/O error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
