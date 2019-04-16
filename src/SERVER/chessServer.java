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
	private board master;
	private boolean game_on;
	private Thread thread;
    private boolean stop;
    private int player;
    private PrintWriter pr;
    private boolean time_up;
	
	
	public chessServer() throws IOException, InterruptedException
	{
		master = new board(false, false, false, "", "", "", false);
		
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

		game_on = true;
		while (game_on) {
			
			// start timer for p1 move
			int seconds = 0;
		    long startTime = System.currentTimeMillis();
            
			// wait for player 1 move
			p1_str = p1_bf.readLine();
			System.out.println("Player 1 moved: " + p1_str);
			
		    long endTime = System.currentTimeMillis();
		    
		    if ( (endTime - startTime) > (seconds * 1000) && seconds != 0)
		    {
		    	// time violation
				p1_pr.println("TIME");
				p1_pr.flush();
				p1_pr.println("LOSER");
				p1_pr.flush();
				
				// send WIN message to player 2
				p2_pr.println("WINNER");
				p2_pr.flush();				
		    }
		    else
		    {
				// send OK message'
				p1_pr.println("OK");
				p1_pr.flush();
		    }
			
			// stop timer
			stop = true;
			
			// validate 
			if (!master.serverCheck(p1_str))
			{
				p1_pr.println("ILLEGAL");
				p1_pr.println("LOSER");
				game_on = false;
				break;
			}

			// send move to player 2
			p2_pr.println(p1_str);
			p2_pr.flush();

			// start timer for p2 move
			seconds = 0;
			startTime = System.currentTimeMillis();
			
			// wait for player 2 to move
			p2_str = p2_bf.readLine();
			System.out.println("Player 2 moved: " + p2_str);
			
		    endTime = System.currentTimeMillis();
		    
		    if ( (endTime - startTime) > (seconds * 1000) && seconds != 0)
		    {
		    	// time violation
				p2_pr.println("TIME");
				p2_pr.flush();
				p2_pr.println("LOSER");
				p2_pr.flush();
				
				// send WIN message to player 2
				p1_pr.println("WINNER");
				p1_pr.flush();				
		    }
		    else
		    {
				// send OK message'
				p2_pr.println("OK");
				p2_pr.flush();
		    }
			
			// stop timer
			stop = true; 
			
			// validate 
			if (!master.serverCheck(p2_str))
			{
				p2_pr.println("ILLEGAL");
				p2_pr.println("LOSER");
				game_on = false;
				break;
			}

			// send move to player 1
			p1_pr.println(p2_str);
			p1_pr.flush();
		}
		// ss.close()
	}
}


