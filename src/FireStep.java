
public class FireStep extends CrystalBullet{

    public FireStep(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed, BossDragon dragon) {
        super(name, xPos, yPos, radius, xSpeed, ySpeed, dragon,0);
        this.setHeight(128);
        this.setWidth(128);
        this.setPicX(0);
        this.setPicY(0);
        this.setStartCell(0);
        this.setCurrentCell(0);
        this.setEndCell(8);
    }
    
    @Override
    public void tick(){
        
        this.countDistance(1);
        this.animateY();
    }
    
}
