import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GrayScale extends Thread 
{
	BufferedImage image;
	int width;
	int height;

	public GrayScale(int index) 
	{
		int begin = 0, end = 0;
		
		switch(index)
		{				
			case 0:
				begin = 0;
				end = (width / 4) * 1;
				break;
			case 1:
				begin = (height / 4) * 1;
				end = (width / 4) * 2;
				break;
			case 2:
				begin = (height / 4) * 2;
				end = (width / 4) * 3;
				break;
			case 3:
				begin = (height / 4) * 3;
				end = width;
				break;		
		}
		
		try 
		{
			//odczyt obrazu z pliku
			File input = new File("img.jpg");
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();
			
			 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach
			for(int i=1; i<height-1; i++)
			{
				for(int j=1; j<width-1; j++)
				{
					//odczyt sk³adowych koloru RGB
					Color c = new Color(image.getRGB(j, i));
					int red = (c.getRed());
					int green = (c.getGreen());
					int blue = (c.getBlue());
					
					int final_red, final_green, final_blue;
					
					//negatyw
					final_red = 255-red;
					final_green = 255-green;
					final_blue = 255-blue;
					Color newColor = new Color(final_red, final_green, final_blue);
					image.setRGB(j,i,newColor.getRGB());
				}
			}
			
			//zapis do pliku zmodyfikowanego obrazu
			File output = new File("img_grayscale.jpg");
			ImageIO.write(image, "jpg", output);
			System.out.println("W¹tek id "+index+" ukonczyl prace.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
