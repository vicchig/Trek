
import java.awt.Color;
import java.awt.Graphics;

public class SpeedItem extends VectorSprite {
    private double speedChange;
    
    public SpeedItem(String name, double xPos, double yPos, int width, int height, int radius, double speedChange) {
        super(name, xPos, yPos, width, height, radius);
        this.speedChange = speedChange;
    }

    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
    }

    public <T extends Player> void collideAction(T object) {
        object.setMaxSpeed(object.getMaxSpeed() + speedChange);
    }

}
