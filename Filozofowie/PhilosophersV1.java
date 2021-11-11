import java.util.concurrent.Semaphore;

public class PhilosophersV1 extends Thread 
{
	static int MAX = 5;
	static Semaphore[] widelec = new Semaphore[MAX];
	int mojNum;
	
	public PhilosophersV1(int nr)
	{
		mojNum = nr;
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			// myslenie
			System.out.println("Mysle Ś " + mojNum);
			
			try 
			{
				Thread.sleep((long)(7000*Math.random()));
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			widelec[mojNum].acquireUninterruptibly(); //przechwycenie L widelca
			widelec[(mojNum+1)%MAX].acquireUninterruptibly(); //przechwycenie P widelca
			
			// jedzenie
			System.out.println("Zaczyna jesc "+mojNum);
			try 
			{ 
				Thread.sleep((long)(5000*Math.random()));
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			System.out.println("Konczy jesc "+mojNum);
			widelec[mojNum].release(); //zwolnienie L widelca
			widelec[(mojNum+1)%MAX].release(); //zwolnienie P widelca
		}
	}
	
	public static void process(int max) 
	{
		MAX = max;
		
		for (int i=0; i<MAX; i++) 
		{
			widelec[i] = new Semaphore(1);
		}
		
		for (int i=0; i<MAX; i++) 
		{
			new PhilosophersV1(i).start();
		}
	}
}
