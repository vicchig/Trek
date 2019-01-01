public class LockedDoor extends Door {
    
    
    public LockedDoor(String name, double xPos, double yPos, int w, int h)
    {
        super(name, xPos, yPos, w, h);
        this.setOpen(false);
        this.setLocked(true);
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
    
}
