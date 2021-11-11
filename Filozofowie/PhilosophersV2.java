import java.util.concurrent.Semaphore;

public class PhilosophersV2 extends Thread
{
	static int MAX = 5;
	static Semaphore[] widelec = new Semaphore[MAX];
	int mojNum;
	
	public PhilosophersV2(int nr) 
	{
		mojNum=nr;
	}
	
	@Override
	public void run() 
	{
		while(true) 
		{
			//myslenie
			System.out.println("Mysle ¦ "+mojNum);
			try 
			{
				Thread.sleep((long)(5000*Math.random()));
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			if(mojNum == 0) 
			{
				widelec[(mojNum+1)%MAX].acquireUninterruptibly();
				widelec[mojNum].acquireUninterruptibly();
			} 
			else 
			{
				widelec[mojNum].acquireUninterruptibly();
				widelec[(mojNum+1)%MAX].acquireUninterruptibly();
			}
		
			//jedzenie
			System.out.println("Zaczyna jesc "+mojNum);
			
			try 
			{
				Thread.sleep((long)(3000*Math.random()));
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			System.out.println("Konczy jesc "+mojNum);
			
			widelec[mojNum].release();
			widelec[(mojNum+1)%MAX].release();
			
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
