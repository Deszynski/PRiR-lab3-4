import java.util.Random;
import java.util.concurrent.Semaphore;

public class PhilosophersV3 extends Thread 
{
	static int MAX = 5;
	static Semaphore[] widelec = new Semaphore [MAX];
	int mojNum;
	Random losuj;
	
	public PhilosophersV3(int nr) 
	{
		mojNum=nr ;
		losuj = new Random(mojNum);
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			// myslenie
			System.out.println("Mysle ¦ " + mojNum);
			
			try 
			{
				Thread.sleep((long)(5000*Math.random()));
			} 
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			int strona = losuj.nextInt(2);
			boolean podnioslDwaWidelce = false;
			
			do 
			{
				if(strona == 0) 
				{
					widelec[mojNum].acquireUninterruptibly();
					if(!(widelec[(mojNum+1)%MAX].tryAcquire()))
					{
						widelec[mojNum].release();
					} 
					else 
					{
						podnioslDwaWidelce = true;
					}	
				} 
				else 
				{
					widelec[(mojNum+1)%MAX].acquireUninterruptibly();
					if(!(widelec[mojNum].tryAcquire()))
					{
						widelec[(mojNum+1)%MAX].release();
					} 
					else 
					{
						podnioslDwaWidelce = true;
					}
				}
			} while (podnioslDwaWidelce == false);
			
			System.out.println("Zaczyna jesc "+mojNum);
			try 
			{
				Thread.sleep((long)(3000*Math.random()));
			} 
			catch(InterruptedException e) 
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
