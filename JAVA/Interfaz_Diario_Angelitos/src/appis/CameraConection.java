package appis;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;


public class CameraConection {
    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    
    CameraConection(){
        
    }
    
    public void takePhoto( String ruta ){
        Highgui.imwrite( ruta , this.frame);
    }
    
    public void stopCam(){
        
    }
    
    public void startCam(){
        
    }
    
    class DaemonThread implements Runnable
    {
        protected volatile boolean runnable = false;

        @Override
        public  void run()
        {
            synchronized(this)
            {
                while(runnable)
                {
                    if(webSource.grab())
                    {
                        try
                        {
                            webSource.retrieve(frame);
                            Highgui.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            //Graphics g=jPanel1.getGraphics();

                            //if (g.drawImage(buff, 0, 0, getWidth(), getHeight() -150 , 0, 0, buff.getWidth(), buff.getHeight(), null))

                            if(runnable == false)
                            {
                                System.out.println("Going to wait()");
                                this.wait();
                            }
                         }
                         catch(Exception ex)
                         {
                            System.out.println("Error");
                         }
                    }
                }
            }
        }
   }
}
