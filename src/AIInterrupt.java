

public class AIInterrupt implements Runnable{

	int totalTime;
	int interruptTime;
	AI ai;
	Thread t;
	AIInterrupt(int miliseconds,Thread tin,AI ai_in)
	{
		totalTime = miliseconds;
		t = tin;
		ai = ai_in;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		interruptTime = totalTime - totalTime/3;
		try {
			wait(interruptTime);
			t.interrupt();
			//t = new thread
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
