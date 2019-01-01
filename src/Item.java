
import java.awt.Color;
import java.awt.Graphics;

public abstract class Item extends VectorSprite {
    
    private double originalxPos, originalyPos;
    
    public Item(String name, double xPos, double yPos, int width, int height, int radius) {
        super(name, xPos, yPos, width, height, radius);
        this.originalxPos = xPos;
        this.originalyPos = yPos;
    }

    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
    }

    public String getDescription() {
        return null;
    }

    public String getNameI() {
        return null;
    }
    
    public abstract  boolean collideAction(Player object);

    public double getOriginalxPos() {
        return originalxPos;
    }

    public double getOriginalyPos() {
        return originalyPos;
    }

    
    
}
