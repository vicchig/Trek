
public class RedHeart extends Item{
    
    private double originalxPos, originalyPos;
    public RedHeart(String name, double xPos, double yPos, int width, int height, int radius)
    {
        super(name, xPos, yPos, width, height, radius);
        this.originalxPos = xPos;
        this.originalyPos = yPos;
    }
    
    @Override
    public boolean collideAction(Player object) {
        if(object.getHealth() == object.getMaxHp() - 1)
        {
            object.damaged(-1);
            return true;
        }
        else if(object.getHealth() < object.getMaxHp())
        {
            object.damaged(-2);
            return true;
        }
        return false;
    }
}
