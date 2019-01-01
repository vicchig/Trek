
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BufferedImageLoader {
    /**
     * Precondition:
     *  None.
     * Description:
     *  Loads an image from file.
     * Postcondition:
     *  Image has been loaded from file.
     */
    public BufferedImage loadImage(String pathOfImageRelToThis)throws IOException{
        URL url = this.getClass().getResource("/Graphics/"+pathOfImageRelToThis);
        BufferedImage img = ImageIO.read(url);
        return img;
    }
}

