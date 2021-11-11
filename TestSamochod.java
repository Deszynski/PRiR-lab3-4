import java.util.Random;

public class TestSamochod 
{
	static final int liczba_samochodow = 3;
	
	public static void main(String[] args) 
	{
		String nr = "";
		Random r = new Random();
		
		for(int i = 0; i < liczba_samochodow; i++)
		{
			nr = "BIA 0000"+(i+1);
			Samochod s = new Samochod(nr,r.nextInt(30)+10);
			s.start();
		}	
	}
}
