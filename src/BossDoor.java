public class BossDoor extends Door {
    public BossDoor(String name, double xPos, double yPos,  int w, int h)
    {
        super(name, xPos, yPos, w, h);
        setBossDoor(true);
        setOpen(true);
    }
    
    
}
