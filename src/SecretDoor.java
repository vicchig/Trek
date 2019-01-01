
import java.awt.Graphics;


public class SecretDoor extends Door{
    
    
    public SecretDoor(String name, double xPos, double yPos, int w, int h)
    {
        super(name, xPos, yPos, w, h);
        this.setOpen(false);
        this.setClosed(true);
    }
    
    @Override
    public void changeOpenState() {
        if(this.isOpen())
        {
            this.setOpen(false);
        }
        else
        {
            this.setOpen(true);
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if(!this.isClosed())
        {
            super.draw(g);
            //System.out.println("drawing");
        }
    }
}
