import java.awt.Color;
import java.awt.Graphics;

public class UpgradeItem extends Item {
    private double healthItemChange, speedChange, rangeChange, damageChange, fireRateChange;
    private String nameI, description;
    
    public UpgradeItem(String name, double xPos, double yPos, int width, int height, int radius, double healthItemChange, double rangeChange, double speedChange,double fireRateChange, double damageChange, String nameI, String description) {
        super(name, xPos, yPos, width, height, radius);
        this.healthItemChange = healthItemChange;
        this.speedChange = speedChange;
        this.damageChange = damageChange;
        this.fireRateChange = fireRateChange;
        this.rangeChange = rangeChange;
        this.nameI = nameI;
        this.description = description;
    }

    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getNameI() {
        return nameI;
    }
    
    

    
    
    public boolean collideAction(Player object) {
        object.setMaxHp(object.getMaxHp() + healthItemChange);
        object.damaged(-healthItemChange);
        object.bController.setBulletRange(object.bController.getBulletRange() + (int)rangeChange);
        object.setMaxSpeed(object.getMaxSpeed() + speedChange);
        object.bController.setInitCoolDown(object.bController.getInitCoolDown() - (int)fireRateChange);
        object.setAttack((int)(object.getAttack()+ damageChange));
        if(object.bController.getInitCoolDown() < 15){
            object.bController.setInitCoolDown(15);
        }
        //pseudo for life hunt
        /*
        if(!object.isLifeHunt() && this.nameI.equals("Life Hunt")){
            object.setLifeHunt(true);
        }
        */
        return true;
    }

}