import java.util.Scanner;

public class Philosophers 
{
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Podaj liczbê filozofów.");
		int philosophers = s.nextInt();
		System.out.println(System.lineSeparator()+"Podaj nr metody 1,2,3.");
		int method = s.nextInt();
		
		switch(method)
		{
			case 1:
				PhilosophersV1.process(philosophers);
				break;
			case 2:
				PhilosophersV2.process(philosophers);
				break;
			case 3:
				PhilosophersV3.process(philosophers);
				break;
			default:
				System.out.println(System.lineSeparator()+"B³¹d. Wybierz 1,2 lub 3.");
				System.exit(1);
		}
	}
}
