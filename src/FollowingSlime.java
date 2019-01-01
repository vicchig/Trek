
public class FollowingSlime extends FollowingEnemy{

    public FollowingSlime(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setHealth(25);
        this.setMaxHp(25);
        bController.setInitCoolDown(35);
        bController.setBulletRange(250);
        this.setAttack(1);
        this.setStartCell(0);
        this.setEndCell(4);
        this.setCurrentCell(0);
        this.setLoopCells(true);
    }
    /**
     * Precondition:
     *  @param mult > 0
     * Description:
     *  Animates and updates the position of the object.
     * Postcondition:
     *  The object has been animated and its position updated.
     */
    public void move(double mult) {
        super.move(mult); 
        this.animate();
    }
}
