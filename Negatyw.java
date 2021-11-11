public class Negatyw 
{
	public static void main(String[] args) throws Exception
	{
		GrayScale g;
		long startTime = System.currentTimeMillis();
		
		for(int i=0; i<4; i++)
		{
			g = new GrayScale(i);
			g.start();
			g.join();
		}	
		
		long endTime = System.currentTimeMillis();
		System.out.println("Obliczenia zakoñczone w czasie " + (endTime - startTime) + " millisekund");
	}
}
