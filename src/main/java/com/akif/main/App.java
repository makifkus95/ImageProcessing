package com.akif.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        imageProcessing("E:\\indirilenler\\PDF\\TESTCropped.jpg");
        cropper("E:\\indirilenler\\PDF\\09.jpg");
    }
    /*
     * dosya yolu belitilen bir resim'in r,g,b değerlerini
     * değiştirirek rengini değiştiriyor.
     * */
    private static void imageProcessing(String path) {
        BufferedImage img = null;
        File f = null;

        //read image
        try{
            f = new File(path);
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }

        //get width and height
        int width = img.getWidth();
        int height = img.getHeight();

        //convert to green image
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = img.getRGB(x,y);

                //int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
/*
                System.out.println("r : "+r);
                System.out.println("g : "+g);
                System.out.println("b : "+b);
*/
                if( r > 200 || g > 200 || b > 200){

                    r = 255;
                    g = 255;
                    b = 255;
                    p = (0<<24) | (r<<16) | (g<<8) | b;
                }
                else{/*
                    r = 255;
                    g = 255;
                    b = 255;
                    p = (0<<24) | (r<<16) | (g<<8) | b;*/
                }

                img.setRGB(x, y, p);
            }
        }

        //write image
        try{
            f = new File("E:\\indirilenler\\PDF\\TEST6.jpg");
            ImageIO.write(img, "jpg", f);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    /*
     * Belirtilen bir dosya yolunda bulunun 09.jpg isimli resm var
     * bu resmi belirtilen koordinatlarda kırpma işlemi yapar.
     * */
    public static void cropper(String path){
        try {
            BufferedImage originalImgage = ImageIO.read(new File(path));

            System.out.println("Original Image Dimension: "+originalImgage.getWidth()+"x"+originalImgage.getHeight());

            //x,y değişkeni resmi kırpmaya başladığımız index(0, 0).
            BufferedImage SubImgage = originalImgage.getSubimage(0, 375, originalImgage.getWidth(), 55);//834*461
            System.out.println("Cropped Image Dimension: "+SubImgage.getWidth()+"x"+SubImgage.getHeight());

            File outputfile = new File("E:\\indirilenler\\PDF\\TESTCropped.jpg");
            ImageIO.write(SubImgage, "jpg", outputfile);

            System.out.println("Image cropped successfully: "+outputfile.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void blackAndWhite(){
        try {
            for (int j = 1; j < 8 ; j++){

                File input = new File("E:\\indirilenler\\PDF\\0"+j+".png");
                BufferedImage image = ImageIO.read(input);

                BufferedImage result = new BufferedImage(
                        image.getWidth(),
                        image.getHeight(),
                        BufferedImage.TYPE_BYTE_BINARY);

                Graphics2D graphic = result.createGraphics();
                graphic.drawImage(image, 0, 0, Color.WHITE, null);
                graphic.dispose();

                File output = new File("E:\\indirilenler\\PDF\\Test"+j+".png");
                ImageIO.write(result, "png", output);

            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
