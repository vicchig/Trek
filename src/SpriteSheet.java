
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage ss) {
        this.spriteSheet = ss;
    }
    
    /**
     * Precondition:
     *  picX and picY are inside the sprite sheet. w and h are not larger than the respective parameters of the sprite sheet.
     * Description:
     *  Cuts out a sprite with the given dimensions from the sprite sheet.
     * Postcondition:
     *  A sprite has been grabbed from the sprite sheet.
     */
    public BufferedImage grabSprite(int picX, int picY, int w, int h) {
        BufferedImage sprite = spriteSheet.getSubimage(picX, picY, w, h);
        return sprite;
    }

}