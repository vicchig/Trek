
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class BossRoom extends Room {
    
    private BossSlime boss;
    private BossPlayer boss2;
    private BossRoaming boss4;
    private BossDragon boss3;
    private Enemy currentBoss;
    private int[][] map;
    private Player player;
    private boolean roomCleared;
    private Door finalDoor;
    private int floorNum;
    

    public BossRoom(String name, double xPos, double yPos, int width, int height, int[][] map, int floorNum) {
        super(name, xPos, yPos, width, height, map);
        this.map = map;
        for (int i = 0; i < 4; i++) {
            doors.add(null);
        }
        this.floorNum = floorNum;
        this.player = player;
        roomCleared = false;
        finalDoor = new Door("pitfall.png",89*6,89*2,89,89);
        boss = new BossSlime("/slime boss/slimeboss.png", 600,400,131,115,120,player);
        boss2 = new BossPlayer("bossspritesheet.png", -1000,400,120,120,120,player,obstacles);
        boss4 = new BossRoaming("eyeballboss.png", -1000,400,50,50,40,player);
        boss3 = new BossDragon("crystalDragon.png", -1000,400,92,95,70,player);
        

        
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Changes @param roomCleared to the passed argument value.
     * Postcondition:
     *  @param roomCleared has been assigned a new value.
     */
    public void setRoomCleared(boolean roomCleared) {
        this.roomCleared = roomCleared;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns @param roomCleared.
     * Postcondition:
     *  @param roomCleared has been returned.
     */
    public boolean isRoomCleared() {
        return roomCleared;
    }
    /**
     * Precondition:
     *  @param y >= 0 && @param y < map.length.
     *  @param x >= 0 && @param x < map[y].length.
     * Description:
     *  Constructs doors in the bosss room based on what rooms it is connected to.
     * Postcondition:
     *  A door leading to another room has been constructed.
     */
    @Override
    public void buildDoors(int x, int y) {
        //up
        if (map[y - 1][x] == 1) {
            doors.set(0, (new BossDoor("bossdoorup.png", 534, 0, 89, 89)));
        }
        //right
        else if (map[y][x + 1] == 1) {
            doors.set(1, (new BossDoor("bossdoorright.png", 1068, 356, 89, 89)));
        }
        //down
        else if (map[y + 1][x] == 1) {
            doors.set(2, (new BossDoor("bossdoordown.png", 534, 712, 89, 89)));
        }
        //left
        else if (map[y][x - 1] == 1) {
            doors.set(3, (new BossDoor("bossdoorleft.png", 0, 356, 89, 89)));
        }
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Places obstacles around the room and initializes the floor appropriate boss.
     * Postcondition:
     *  A room with obstacles and a boss has been constructed.
     */
    public void buildRoom(Player player) {
        obstacles.add(new Bonfire("fire.png", 89, 89, 89, 89, 89));
        obstacles.add(new Bonfire("fire.png", 979, 89, 89, 89, 89));
        obstacles.add(new Bonfire("fire.png", 979, 623, 89, 89, 89));
        obstacles.add(new Bonfire("fire.png", 89, 623, 89, 89, 89));
        
        if(floorNum == 1){
            boss = new BossSlime("/slime boss/slimeboss.png", 600,400,131,115,120,player);
            currentBoss = boss;
            boss2.setDead(true);
            boss3.setDead(true);
            boss4.setDead(true);
        }
        else if(floorNum == 2){
            boss2 = new BossPlayer("bossspritesheet.png", 600,400,66,58,30,player,obstacles);
            boss.setDead(true);
            boss3.setDead(true);
            boss4.setDead(true);
            currentBoss = boss2;
        }
        else if(floorNum == 4){
            boss4 = new BossRoaming("eyeballboss.png", 600,400,180,190,80,player);
            boss2.setDead(true);
            boss.setDead(true);  
            boss3.setDead(true);
            currentBoss = boss4;
        }
        else if(floorNum == 3){
            boss2.setDead(true);
            boss.setDead(true); 
            boss4.setDead(true);
            boss3 = new BossDragon("crystalDragon.png", 600,400,96,93,90,player);
            currentBoss = boss3;
        }
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the position of obstacles to their original when moving in or out of the room.
     * Postcondition:
     *  Obstacles inside the room have been reset to their original position.
     */
    @Override
    public void moveObstacles()
    {
        for(int i = 0; i < obstacles.size(); i++)
        {
            obstacles.get(i).setxPos(this.getxPos() + obstacles.get(i).getOriginalxPos());  
            obstacles.get(i).setyPos(this.getyPos() + obstacles.get(i).getOriginalyPos());
        }
    }
    /**
     * Precondition:
     *  Player has entered the room.
     * Description:
     *  The rooms' doors are closed if a boss is alive and are opened once the boss is dead.
     * Postcondition:
     *  The doors are closed if the boss is alive and opened if it is dead.
     */
    @Override
    public void checkDoors(){
        //closes doors
        if(!boss.isDead() || !boss2.isDead() || !boss3.isDead() || !boss4.isDead())
        {
            for(int i = 0; i < doors.size(); i++)
            {
                if(doors.get(i) != null && doors.get(i).isOpen())
                {
                    changeDoor(i);
                    doors.get(i).changeOpenState();
                }
            }
        }
        //opens doors
        else if(boss.isDead() && boss2.isDead() && boss3.isDead() && boss4.isDead())
        {
            for(int i = 0; i < doors.size(); i++)
            {
                if(doors.get(i) != null && !(doors.get(i).isOpen()))
                {
                    changeDoor(i);
                }
            }
        }
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks collision between the boss and obstacles, the boss and player bullets, the player and boss bullets. Moves the boss' bullets and moves the boss. Checks if the boss's health is below0.
     * Postcondition:
     *  Collision between the boss and objects in the room has been checked. The boss' bullets have been moved and the boss has been moved. If boss's health is below or equal to 0, the boss is setDead.
     */
    public void operateBoss(Enemy boss,Player player, double movementMult){
        boss.shoot();
        boss.move(movementMult);
        boss.bController.increment();
        //Boss bullets and player collision
        for(int j = 0;j<boss.bController.bullets.size();j++){
            if(boss.bulletCollide(player, boss.bController.bullets.get(j)) && !player.isDamageTaken()){
                boss.bController.bullets.remove(j);
                player.setDamageTaken(true);
                player.damaged(boss.getAttack());
            }
        }
        for(int i = 0;i<boss.bController.cBulletsShield.size();i++){
            if(boss.bulletCollide(player,boss.bController.cBulletsShield.get(i)) && !player.isDamageTaken()){
                boss.bController.cBulletsShield.remove(i);
                player.setDamageTaken(true);
                player.damaged(boss.getAttack());
            }
            try{
                for (int n = 0; n < player.bController.bullets.size(); n++) {
                if(boss.bulletCollide(boss.bController.cBulletsShield.get(i), player.bController.bullets.get(n))){
                    player.bController.bullets.remove(n);
                    boss.bController.cBulletsShield.remove(i);
                }
            }
            }
            catch(IndexOutOfBoundsException e){
            }
            
        }
        for(int i = 0;i<boss.bController.bulletFStep.size();i++){
            if(boss.bulletCollide(player, boss.bController.bulletFStep.get(i)) && !player.isDamageTaken()){
                player.setDamageTaken(true);
                player.damaged(boss.getAttack());
            }
        }
        for(int i = 0;i<boss.bController.bulletsF.size();i++){
            if(boss.bulletCollide(player, boss.bController.bulletsF.get(i)) && !player.isDamageTaken()){
                player.setDamageTaken(true);
                player.damaged(boss.getAttack());
            }
        }
        //Obstacles and boss collision
        for(int z = 0;z<obstacles.size();z++){
            boss.collideWObstacle(obstacles.get(z));
            for(int f = 0;f<boss.bController.bullets.size();f++){
                if(obstacles.get(z).bulletCollision(boss.bController.bullets.get(f))){
                    boss.bController.removeBullet(boss.bController.bullets.get(f));
                }
            }
        }
        
        //Player bullets and boss collision
        for (int j = 0; j < player.bController.bullets.size(); j++) {
            if(boss.bulletCollide(boss, player.bController.bullets.get(j))){
                player.bController.removeBullet(player.bController.bullets.get(j));
                if(enemies.isEmpty()){
                    boss.damaged(player.getAttack());
                }
            }
        } 
        //checks if boss is dead
        if(boss.getHealth() <= 0){
            boss.setDead(true);
        }
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks the collision between the player and obstacles in the room. Checks appropriate collisions for spawned enemies in the room. Moves the spawned enemies and makes the attack the player.
     *  Runs boss specific methods. Checks if the boss is dead.
     * Postcondition:
     *  Collision between player and obstacles has been checked. Enemy collision has been checked. Boss specific methods have been run and boss states changed. If the boss is dead removes all 
     *  additional enemies from the room.
     */
    public void operate(Player player){

        collideObstaclesP(player);
        checkDoors();
        if(collideBomb(player, boss3) && !player.isDamageTaken())
        {
            player.setDamageTaken(true);
            player.damaged(1);
        }
        
        if(!boss.isDead()){
            operateBoss(boss,player,1);
            //boss.move(2);
            boss.spawnStage(enemies);
        }
        
        if(!boss2.isDead()){

            operateBoss(boss2,player,1);
            //boss2.move(1);
        }
        
        if(!boss3.isDead()){
            operateBoss(boss3,player,1);
            //boss3.spawnStage(enemies);

            //boss3.move(1);
            
//            if(boss3.isPhase1() && !boss3.isPhase1Done())
//            {
//                boss3.spawnEnemy(enemies);
//                boss3.setPhase1Done(true);
//            }
//            if(boss3.isPhase2() && !boss3.isPhase2Done())
//            {
//                boss3.spawnEnemy(enemies);
//                boss3.setPhase2Done(true);
//            }
//            if(!enemies.isEmpty())
//            {
//                if(boss3.isPhase2())
//                {
//                    boss3.setHealth(32);
//                }
//                else
//                {
//                    boss3.setHealth(65);
//                }
//            }
        }
        
        else if(boss3.isDead() && floorNum == 1){
            boss3.setxPos(-1000);
            boss3.setyPos(-1000);
            boss3.setRadius(1);
        }

        if(!boss4.isDead()){
            operateBoss(boss4,player,1);
            boss4.spawnStage(enemies);
        }

        for(int j = 0;j<obstacles.size();j++){
            for(int e = 0;e<player.bController.bullets.size();e++){
                if(obstacles.get(j).bulletCollision(player.bController.bullets.get(e))){
                    player.bController.removeBullet(player.bController.bullets.get(e));
                }
            }
        }

        if(!enemies.isEmpty() && (boss != null|| !boss.isDead())){
            for(int i = 0;i<enemies.size();i++){
                enemies.get(i).move(1);
                enemies.get(i).animate();
                enemies.get(i).shoot();
                enemies.get(i).bController.increment();
                if(collideBomb(enemies.get(i), player) && !player.isDamageTaken())
                {
                    player.setDamageTaken(true);
                    player.damaged(1);
                }
                if(boss.collideE(enemies.get(i))){
                    enemies.get(i).collideActionE(boss);
                }
                for (int j = i; j < enemies.size(); j++) {
                    if (i != j) {
                        if (enemies.get(i).collideE(enemies.get(j))) {
                            enemies.get(i).collideActionE(enemies.get(j));
                        }
                    }
                }
                for (int j = 0; j < player.bController.bullets.size(); j++) {
                    if (enemies.get(i).bulletCollide(enemies.get(i), player.bController.bullets.get(j))) {
                        player.bController.removeBullet(player.bController.bullets.get(j));
                        enemies.get(i).damaged(player.getAttack());
                    }
                }
                for(int k = 0;k<enemies.get(i).bController.bullets.size();k++){
                    if(enemies.get(i).bulletCollide(player,enemies.get(i).bController.bullets.get(k))&& !player.isDamageTaken()){
                        player.setDamageTaken(true);
                        player.damaged(enemies.get(i).getAttack());
                        enemies.get(i).bController.removeBullet(enemies.get(i).bController.bullets.get(k));
                    }
                }
                for(int j = 0;j<obstacles.size();j++){
                    for(int k = 0;k<enemies.size();k++){
                        for(int n = 0;n<enemies.get(k).bController.bullets.size();n++){
                            if(obstacles.get(j).bulletCollision(enemies.get(k).bController.bullets.get(n))){
                                enemies.get(k).bController.removeBullet(enemies.get(k).bController.bullets.get(n));
                            }
                        }
                    }
                }
            
                if (enemies.get(i).getHealth() <= 0) {
                    enemies.remove(i);
                }
            }
        }

        
        if(boss.isDead() && boss2.isDead() && boss3.isDead() && boss4.isDead()){
            roomCleared = true;
            for(int i = 0;i<enemies.size();i++){
                enemies.remove(i);
            }
        }
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns a reference to final door.
     * Postcondition:
     *  The reference to final door is returned.
     */
    public Door getFinalDoor() {
        return finalDoor;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the appropriate boss. Checks if the boss has been defeated.
     * Postcondition:
     *  The boss has been drawn. If the boss has been defeated, draws the door to the next level.
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if(!boss.isDead() || !boss2.isDead() || !boss3.isDead() || !boss4.isDead()){
            g.setColor(new Color(0,0,0,150));
            g.fillRect(1157/2-330, 801-89-40, 660, 30);
            g.setColor(new Color(144,0,32,100));
            g.fillRect(1157/2-330, 801-89-40, (int) (660 * (currentBoss.getHealth()/currentBoss.getMaxHp())), 30);
            g.setFont(new Font("Chiller", Font.BOLD, 40));
            g.setColor(Color.WHITE);
            g.drawString(currentBoss.toString(),1157/2-330,801-89-30-15);
        }
            if(!boss.isDead()){
                boss.draw(g);
            } 
            
            if(!boss2.isDead()){
                boss2.draw(g);
            }
            
            if(!boss3.isDead()){
                boss3.draw(g);
            }
            
            if(!boss4.isDead()){
                boss4.draw(g);
            }
            
        if(roomCleared){
            finalDoor.draw(g);
        }  
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns a string with the room type.
     * Postcondition:
     *  String with room type has been returned.
     */
    @Override
    public String toString() {
        String name = "Boss Room";
        return name;
    }
    
    
}
