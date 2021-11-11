import java.util.Random;

public class Samochod extends Thread 
{
	private String nrRej;
	private int pojZbiornika;
	private int paliwo;
	
	public Samochod (String nr, int _pojZbiornika)
	{
		nrRej = nr;
		pojZbiornika = _pojZbiornika;
		paliwo = _pojZbiornika;
	}
	
	public void tankowanie (int _paliwo)
	{
		System.out.println("Samochod "+nrRej+" zatankował "+_paliwo+" paliwa");
		paliwo+=_paliwo;
		run();
	}
	
	@Override
	public void run()
	{
		Random r = new Random();
		
		while(true)
		{
			System.out.println("Ilosc paliwa "+nrRej+": "+paliwo);
			
			if(paliwo < 3 && paliwo > 0)
			{
				System.out.println("Samochod "+nrRej+" musi zatankowac");
				tankowanie(r.nextInt(10)+2);
				break;
			}
			else if(paliwo <=0)
			{
				System.out.println("Samochod "+nrRej+" zatrzymał sie z powodu braku paliwa");
				stop();			
			}

			paliwo--;
			
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
		}
	}
}
