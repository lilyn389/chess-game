import java.net.*;
import java.io.*;


public class chessServer {
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		chessServer serv = new chessServer();
	}
	
	private PrintWriter p1_pr;
	private PrintWriter p2_pr;
	private Socket p1_s;
	private Socket p2_s;
	private ServerSocket ss;
	private InputStreamReader p1_in;
	private InputStreamReader p2_in;
	private BufferedReader p1_bf;
	private BufferedReader p2_bf;
	private String p1_str;
	private String p2_str;
	private boolean p1_ready;
	private boolean p2_ready;
	
	
	public chessServer() throws IOException
	{
		p1_ready = false;
		p1_ready = false;
		
		// declare server socket
		ss = new ServerSocket(9998);

		// wait for the first player to join,
		System.out.println("Waiting for player 1 to join...");
		p1_s = new Socket();
		p1_s = ss.accept();

		System.out.println("Player 1 connected");
		// connect input stream from player one
		p1_in = new InputStreamReader(p1_s.getInputStream());
		p1_bf = new BufferedReader(p1_in);

		p1_str = p1_bf.readLine();
		System.out.println("client : " + p1_str);

		p1_pr = new PrintWriter(p1_s.getOutputStream());
		// send info message
		p1_pr.println("WELCOME");
		p1_pr.flush();
		// send INFO message
		p1_pr.println("INFO 0 white");
		p1_pr.flush();
		
		// wait for player 1 ready message
		p1_str = p1_bf.readLine();
		if (p1_str.equals("READY"))
		{
			System.out.println("Player 1 is ready");
			p1_ready = true;
		}
		else 
		{
			System.out.println("Player 1 not ready");
			p1_ready = false;
		}

		// wait for the second player to join,
		System.out.println("Waiting for player 2 to join...");
		p2_s = new Socket();
		p2_s = ss.accept();

		System.out.println("Player 2 connected");
		// connect input stream from player one
		p2_in = new InputStreamReader(p2_s.getInputStream());
		p2_bf = new BufferedReader(p2_in);

		p2_str = p2_bf.readLine();
		System.out.println("client : " + p2_str);

		p2_pr = new PrintWriter(p2_s.getOutputStream());
		p2_pr.println("WELCOME");
		p2_pr.flush();
		//send info message
		p2_pr.println("INFO 0 black");
		p2_pr.flush();
		
		// wait for player 2 ready message
		p2_str = p2_bf.readLine();
		if (p2_str.equals("READY"))
		{
			System.out.println("Player 2 is ready");
			p2_ready = true;
		}
		else 
		{
			System.out.println("Player 2 not ready");
			p2_ready = false;
		}		
		
		// send READY signal to player 2
		p2_pr.println("ready");
		p2_pr.flush();

		// send message to player 1, that player 2 has connected
		p1_pr.println("ready");
		p1_pr.flush();

		while (true) {
			
			// wait for player 1 move
			p1_str = p1_bf.readLine();
			System.out.println("Player 1 moved: " + p1_str);

			// send move to player 2
			p2_pr.println(p1_str);
			p2_pr.flush();

			// wait for player 2 to move
			p2_str = p2_bf.readLine();
			System.out.println("Player 2 moved: " + p2_str);

			// send move to player 1
			p1_pr.println(p2_str);
			p1_pr.flush();
		}
		// ss.close()
	}
}

