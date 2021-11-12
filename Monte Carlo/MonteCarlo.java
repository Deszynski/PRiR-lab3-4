import java.util.Random;
import java.util.Scanner;

public class MonteCarlo extends Thread
{
    static int iterations;
    static double radius;
    double x_start, y_start, x_end, y_end;
    double result;
    int in;  
    Random r;

    public MonteCarlo(int iterations, double _x_start, double _y_start, double _x_end, double _y_end) 
    {
        this.iterations = iterations;
        this.x_start = _x_start;
        this.y_start = _y_start;
        this.x_end = _x_end;
        this.y_end = _y_end;   
        r = new Random();
        result = 0;
        in = 0;
    }

    @Override
    public void run() 
    { 
        for (int i = 0; i < this.iterations/4; i++) 
        {
            double x = r.nextDouble();
            double y = r.nextDouble();

            if(Math.sqrt((Math.pow(x,2) + Math.pow(y,2))) <= 1)
            {
            	in++;
            }    
        }
        
        System.out.println("In: "+in+"  All: "+(iterations/4)+System.lineSeparator());
        result = in;
    }

	public static void main(String[] args) 
	{
		MonteCarlo m1, m2, m3, m4;
        Scanner s = new Scanner(System.in);
        
        System.out.print("Podaj promien kola: ");
        radius = s.nextDouble();
        
        System.out.print(System.lineSeparator()+"Podaj liczbe prob: ");
        int i = s.nextInt();
        
        
        m1 = new MonteCarlo(i, radius, radius, 0, 0);
        m2 = new MonteCarlo(i, radius, radius, radius, 0);
        m3 = new MonteCarlo(i, radius, radius, 0, radius);
        m4 = new MonteCarlo(i, radius, radius, radius, radius);

        m1.start();
        m2.start();
        m3.start();
        m4.start();

        try 
        {
            m1.join();
            m2.join();
            m3.join();
            m4.join();
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }

        double surface = (m1.result + m2.result + m3.result + m4.result)/iterations*Math.pow(2*radius, 2);
        double pi = 4*(m1.result + m2.result + m3.result + m4.result)/iterations;
        System.out.println("Pole kola wynosi: "+surface+System.lineSeparator()+"PI: "+pi);
	}
}
