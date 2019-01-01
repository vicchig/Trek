public class BombItem extends Item{
    
    private double originalxPos, originalyPos;
    public BombItem(String name, double xPos, double yPos, int width, int height, int radius)
    {
        super(name, xPos, yPos, width, height, radius);
        this.originalxPos = xPos;
        this.originalyPos = yPos;
    }
    
    @Override
    public boolean collideAction(Player object) {
        object.setBombs(object.getBombs() + 1);
        return true;
    }
    
}
