import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ParallelJulie extends Thread 
{
	final static int N = 4096;
	final static int CUTOFF = 100;
	static int[][] set = new int[N][N];
	
	int index;	
	//konstruktor, który ustawie numeracje wątków	
	public ParallelJulie(int index) 
	{
		this.index = index;
	}
	
	//procedura wykonywana przez każdy z 4 wątków sprawdzająca czy dany punkt należy do zbioru Julii
	@Override
	public void run() 
	{
		int begin = 0, end = 0;
		
		switch(index)
		{				
			case 0:
				begin = 0;
				end = (N / 4) * 1;
				break;
			case 1:
				begin = (N / 4) * 1;
				end = (N / 4) * 2;
				break;
			case 2:
				begin = (N / 4) * 2;
				end = (N / 4) * 3;
				break;
			case 3:
				begin = (N / 4) * 3;
				end = N;
				break;		
		}
		
		for (int i = begin; i < end; i++) 
		{
			for (int j = 0; j < N; j++) 
			{
				//przeskalowanie punktów do układu wspolrzednych kartezjanskich
				double cr = -0.25;//-0.2;
                double ci = 0.76;//0.65;
                double zr = i * (1.25 - -1.25) / N + -1.25;
                double zi = j * (1.25 - -1.25) / N + -1.25;
                int k = 0;

                // sprawdzenie
                while (k < CUTOFF && zr * zr + zi * zi < 4.0)
                {
                    double newr = cr + zr * zr - zi * zi;
                    double newi = ci + 2 * zr * zi;
                    zr = newr;
                    zi = newi;
                    k++;
                }
                
                set[i][j] = k;
			}
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		ParallelJulie thread;
		
		//ustawienie stopera liczącego czas obliczeń
		long startTime = System.currentTimeMillis();
		
		//ustawienie 4 wątków generujących fraktal w 4 ćwiartkach
		for(int i=0; i<4; i++)
		{
			thread = new ParallelJulie(i);
			thread.start();
			thread.join();
		}
		
		//zakończenie działania stopera i wyświetlenie czasu generowania fraktala
		long endTime = System.currentTimeMillis();
		System.out.println("Obliczenia zakończone w czasie " + (endTime - startTime) + " millisekund");
		
		//ustawienie pliku graficznego, w którym zostanie wygenerowany fraktal
		BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);
		
		//wstawianie pixeli do pliku graficznego
		for (int i = 0; i < N; i++) 
		{
			for (int j = 0; j < N; j++) 
			{
				int k = set[i][j];
				float level;
				if (k < CUTOFF) 
				{
					//pixel o wspolrzednych i,j należy do zbioru Julii
					level = (float) k / CUTOFF;
				} 
				else 
				{
					//pixel o wspolrzednych i,j nie należy do zbioru Julii
					level = 0;
				}
				
				//zapisywanie pixela (na niebiesko lub czarno)
				Color c = new Color(0, 0, level); // niebieski
				img.setRGB(i, j, c.getRGB());
			}
		}
		
		//zapis rysunku do pliku Mandelbrot.png
		ImageIO.write(img, "PNG", new File("Julie.png"));	
	}
}
