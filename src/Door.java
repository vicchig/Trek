
import java.awt.Graphics;
import java.awt.geom.AffineTransform;



public class Door extends GraphicsObject {

    private boolean open;
    private boolean locked, closed, bossDoor;
    
    public Door(String name, double xPos, double yPos, int w, int h) {
        super(name, xPos, yPos, w, h);
        open = true;
        bossDoor = false;
    }

    public boolean isBossDoor()
    {
        return bossDoor;
    }
   
    public void setBossDoor(boolean bossDoor)
    {
        this.bossDoor = bossDoor;
    }
    
    public boolean isOpen() {
        return open;
    }

    public boolean isLocked() {
        return locked;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }
    
    public void moveDoorVertical(double move) {
        this.setyPos(this.getyPos() + move);
    }

    public void moveDoorHorizontal(double move) {
        this.setxPos(this.getxPos() + move);
    }

    public void displaceDoorHorizontal(double displace) {
        this.setxPos(this.getxPos() + displace);
    }

    public void displaceDoorVertical(double displace) {
        this.setyPos(this.getyPos() + displace);
    }

    //on off method so don't need to pass anything 
    public void changeOpenState() {
        open = !open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public void draw(Graphics g) {
        super.draw(g);
    }

}
