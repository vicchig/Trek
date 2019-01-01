
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Player extends Character implements CollisionListener {

    private boolean moveUp, moveLeft, moveRight, moveDown, shootRight, shootLeft, shootUp, shootDown, moving, shooting,damageTaken, lifeHunt;
    private GraphicsObject playerHead, key, bomb, heart, halfHeart, emptyHeart;
    private double speedCap, coolDown, originalxPos, originalyPos,attack,fireRate;
    private int keys, bombs,frameCount;
    private int tempCoolDown;
    
    public Player(String name, double xPos, double yPos, int width, int height, int radius) {
        super(name, xPos, yPos, width, height, radius);
        this.setxSpeed(0);
        this.setySpeed(0);
        this.setStartCell(0);
        this.setEndCell(4);
        this.setCurrentCell(0);
        this.setPicX(0);
        this.setPicY(0);
        this.setHitHeight(27);
        this.setHitWidth(17);
        this.setHitCenterX(this.getxPos() + this.getHitWidth()/2);
        this.setHitCenterY(this.getyPos() + this.getHitHeight()/2);
        this.setFlying(false);
        this.setMaxSpeed(2);
        this.setAttack(5);
        key = new GraphicsObject("key.png", 10, 80, 30, 30);
        bomb = new GraphicsObject("bomb_ui.png", 10, 125, 30, 30);
        heart = new GraphicsObject("heart.png", 10, 10, 50, 50);
        halfHeart = new GraphicsObject("halfheart.png", 10, 10, 50, 50);
        emptyHeart = new GraphicsObject("emptyheart.png", 10, 10, 50, 50);
        originalxPos = heart.getxPos();
        originalyPos = heart.getyPos();
        bController.setBulletRange(100);
        speedCap = 4;
        bController.setInitCoolDown(25);
        this.setHealth(6);
        this.setMaxHp(6);
        playerHead = new GraphicsObject("playerHead.png",this.getxPos()+3,this.getyPos()-25,50,47);
        playerHead.setPicX(0);
        frameCount = 0;
        damageTaken = false;
        speedCap = 4;
        coolDown = 10;
        
        keys = 0;
        bombs = 1;
        lifeHunt = true;
    }

    public void setLifeHunt(boolean lifeHunt) {
        this.lifeHunt = lifeHunt;
    }

    public boolean isLifeHunt() {
        return lifeHunt;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of the temp cool donw.
     * Postcondition:
     *  The value of the temp cool down has been returned.
     */
    public int getTempCoolDown() {
        return tempCoolDown;
    }

    /**
     * Precondition:
     *  tempCoolDown > 0
     * Description:
     *  Sets the value of tempCoolDown to the passed value.
     * Postcondition:
     *  The value of tempCoolDown has been changed.
     */
    public void setTempCoolDown(int tempCoolDown) {
        this.tempCoolDown = tempCoolDown;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Activates the invincibility frames of the player.
     * Postcondition:
     *  Invincibility frames have been activated.
     */
    public boolean activeInvincibilityFrames(Graphics g) {
        if (damageTaken) { // will be converted to if (player is damaged)
            frameCount++;
            if (frameCount % 2 == 0) {
                playerHead.draw(g);
                super.draw(g);
            }
            if (frameCount > 50) {
                damageTaken = false;
                frameCount = 0;
            }
            return true;
        } else {
            playerHead.draw(g);
            super.draw(g);
            return false;
        }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of damageTaken to the passed value.
     * Postcondition:
     *  The value of damageTaken has been chhanged.
     */
    public void setDamageTaken(boolean damageTaken) {
        this.damageTaken = damageTaken;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of damageTaken.
     * Postcondition:
     *  The value of damageTaken has been returned.
     */
    public boolean isDamageTaken() {
        return damageTaken;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Plants bomb at the given location of the player.
     * Postcondition:
     *
     */
    public void plantBomb()
    {
        if(this.getBombs() > 0)
        {
            bController.addBomb(this.getxPos(), this.getyPos(), 25);
            this.setBombs(getBombs() - 1);
        }
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of keys.
     * Postcondition:
     *  The value of keys is returned.
     */
    public int getKeys() {
        return keys;
    }

    /**
     * Precondition:
     *  @param keys > 0
     * Description:
     *  Sets the value of keys.
     * Postcondition:
     *  The value of keys has been set.
     */
    public void setKeys(int keys) {
        this.keys = keys;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of bombs.
     * Postcondition:
     *  The value of bombs is returned.
     */
    public int getBombs() {
        return bombs;
    }

     /**
     * Precondition:
     *  @param bombs > 0
     * Description:
     *  Sets the value of bombs.
     * Postcondition:
     *  The value of bombs has been set.
     */
    public void setBombs(int bombs) {
        this.bombs = bombs;
    }
    
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Changes the direction of the head based on whether the player is moving, shooting or standing.
     * Postcondition:
     *  The direction of the head has been changed.
     */
    //MOVEMENT METHODS
    public void changeHeadState() {
        //tracking the head with the player
        if(moveUp && moveRight){
            playerHead.setxPos(this.getxPos()+14);
            playerHead.setyPos(this.getyPos()-34);
        }
        else if(moveUp && moveLeft){
            playerHead.setxPos(this.getxPos()+2);
            playerHead.setyPos(this.getyPos()-34);
        }
        else if(moveDown && moveRight){
            playerHead.setxPos(this.getxPos()+14);
            playerHead.setyPos(this.getyPos()-28);
        }
        else if(moveDown && moveLeft){
            playerHead.setxPos(this.getxPos()+2);
            playerHead.setyPos(this.getyPos()-28);
        }
        else if(moveDown || shootDown || (!moving && !shooting && this.getPicY() == 0)){
          playerHead.setxPos(this.getxPos()+2);
          playerHead.setyPos(this.getyPos()-28);      
        }
        else if((moveUp || (shootUp && moveUp))|| (!moving && this.getPicY() == 211)){
          playerHead.setxPos(this.getxPos()+9);
          playerHead.setyPos(this.getyPos()-28);  
        }
        else if((!moving && shootUp && this.getPicY() == 0) ){
          playerHead.setxPos(this.getxPos()+3);
          playerHead.setyPos(this.getyPos()-28);  
        }
        else if(shootLeft && moveRight || shootLeft){
            playerHead.setxPos(this.getxPos()+10);
            playerHead.setyPos(this.getyPos()-28);
        }
        else if(moveLeft ||(!moving && !shooting && this.getPicY() == 141)){
            playerHead.setxPos(this.getxPos()+3);
            playerHead.setyPos(this.getyPos()-28);
        }
        else if(moveRight||(shootRight && !moving)||(shootRight && moveRight)||(!moving && !shooting && this.getPicY() == 74)){
            playerHead.setxPos(this.getxPos()+10);
            playerHead.setyPos(this.getyPos()-29);
        }
        else if(shootUp && !moving){
            playerHead.setxPos(this.getxPos()+10);
            playerHead.setyPos(this.getyPos()-29);
        }
        //left right and shooting up
        
        //head faces the direction of the player while moving
        if (moveDown && moveRight) {
            playerHead.setPicX(52);//right
        } else if (moveDown && moveLeft) {
            playerHead.setPicX(0);//left
        } else if (moveUp && moveRight) {
            playerHead.setPicX(52);//right
        } else if (moveUp && moveLeft) {
            playerHead.setPicX(0);//left
        } else if (moveDown) {
            playerHead.setPicX(102);//down
        } else if (moveUp) {
            playerHead.setPicX(158);//up
        } else if (moveRight) {
            playerHead.setPicX(52);//right
        } else if (moveLeft) {
            playerHead.setPicX(0);//left
        }

        //head faces the direction of shooting while moving or standing in place
        if (shootDown) {
            playerHead.setPicX(102);
        } else if (shootUp) {
            playerHead.setPicX(158);
        } else if (shootRight) {
            playerHead.setPicX(52);
        } else if (shootLeft) {
            playerHead.setPicX(0);
        }

        if (shootLeft && shootRight) {
            playerHead.setPicX(52);//right
        } else if (shootUp && shootDown) {
            playerHead.setPicX(102);//down
        } else if (shootDown && shootRight) {
            playerHead.setPicX(102);//down
        } else if (shootDown && shootLeft) {
            playerHead.setPicX(102);//down
        } else if (shootUp && shootRight) {
            playerHead.setPicX(158);//up
        } else if (shootUp && shootLeft) {
            playerHead.setPicX(158);//up
        }

        if (!moving && !shooting) {
            if (this.getPicY() == 0) {
                playerHead.setPicX(102);//down
            } else if (this.getPicY() == 211) {
                playerHead.setPicX(158);//up
            } else if (this.getPicY() == 74) {
                playerHead.setPicX(52);//right
            } else if (this.getPicY() == 141) {
                playerHead.setPicX(0);//left
            }
        }
    }

    /**
     * Precondition:
     *  @param mult > 0
     * Description:
     *  Updates the position of the player.
     * Postcondition:
     *  The position of the player has been updated.
     */
    public void move(double multi) {
        this.setHitCenterX(this.getxPos() + this.getHitWidth());
        this.setHitCenterY(this.getyPos() + this.getHitHeight());
        if (this.getMaxSpeed() > speedCap) {
            this.setMaxSpeed(speedCap);
        }

        //diagonal acceleration
        if (moveUp && (moveRight || moveLeft)) {
            moveUp();
            setxSpeed(getxSpeed() / 1);
            setySpeed(getySpeed() / 1.25);//1.65 for a 45 degree angle
        }
        if (moveDown && (moveRight || moveLeft)) {
            moveDown();
            setxSpeed(getxSpeed() / 1);
            setySpeed(getySpeed() / 1.25);
        }
        //regular acceleration and slowing down
        if (moveUp) {
            slowDownX();
            moveUp();
        }
        if (moveDown) {
            slowDownX();
            moveDown();
        }
        if (moveLeft) {
            slowDownY();
            moveLeft();
        }
        if (moveRight) {
            slowDownY();
            moveRight();
        }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Changes the sprite of the player when moving down.
     * Postcondition:
     *  The sprite of the player has been changed.
     */
    public void moveDown() {
        this.accelerateYD();
        this.moveY(this.getySpeed());
        this.setPicX(0);
        this.setPicY(0);
        this.setLoopCells(true);
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Changes the sprite of the player when moving up.
     * Postcondition:
     *  The sprite of the player has been changed.
     */
    public void moveUp() {
        this.accelerateYU();
        this.moveY(this.getySpeed());
        this.setPicX(0);
        this.setPicY(211);
        this.setLoopCells(true);
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Changes the sprite of the player when moving right.
     * Postcondition:
     *  The sprite of the player has been changed.
     */
    public void moveRight() {
        this.accelerateXR();
        this.moveX(this.getxSpeed());
        this.setPicX(0);
        this.setPicY(74);
        this.setLoopCells(true);
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Changes the sprite of the player when moving left.
     * Postcondition:
     *  The sprite of the player has been changed.
     */
    public void moveLeft() {
        this.accelerateXL();
        this.moveX(this.getxSpeed());
        this.setPicX(0);
        this.setPicY(141);
        this.setLoopCells(true);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Stops player movement.
     * Postcondition:
     *  The player has been stopped.
     */
    public void stop() {
        this.setCurrentCell(0);
        this.setPicX(0);
        slowDownX();
        slowDownY();
        setMoving(false);
        if (moveDown) {
            this.setPicY(0);
        } else if (moveUp) {
            this.setPicY(211);
        } else if (moveLeft) {
            this.setPicY(141);
        } else if (moveRight) {
            this.setPicY(74);
        }
    }

    
    //ACCELERATION METHODS
    //change the increment for the speed to change acceleration speed, change the if statement to limit the speed
    /**
     * Precondition:
     *  None.
     * Description:
     *  Accelerates the enemy in the right direction.
     * Postcondition:
     *  The enmy has been accelerated.
     */
    public void accelerateXR() {
        double temp;
        temp = this.getxSpeed();
        if (temp <= this.getMaxSpeed()) {
            temp += this.getMaxSpeed() / 10;
        }
        this.setxSpeed(temp);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Accelerates the enemy in the left direction.
     * Postcondition:
     *  The enemy has been accelerated.
     */
    public void accelerateXL() {
        double temp;
        temp = this.getxSpeed();
        if (temp >= -this.getMaxSpeed()) {
            temp += -this.getMaxSpeed() / 10;
        }
        this.setxSpeed(temp);

    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Accelerates the player up
     * Postcondition:
     *  The player has been accelerated.
     */
    public void accelerateYU() {
        double temp;
        temp = this.getySpeed();
        if (temp >= -this.getMaxSpeed()) {
            temp += -this.getMaxSpeed() / 10;
        }
        this.setySpeed(temp);

    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Accelerates the player down.
     * Postcondition:
     *  The enemy has been accelerated.
     */
    public void accelerateYD() {
        double temp;
        temp = this.getySpeed();
        if (temp <= this.getMaxSpeed()) {
            temp += this.getMaxSpeed() / 10;
        }
        this.setySpeed(temp);

    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Slows down the player in the x direction.
     * Postcondition:
     *  The player has been slowed down.
     */
    //SLOWING DOWN 
    public void slowDownX() {
        double temp;
        temp = this.getxSpeed();

        if (temp > 0.1 || temp < -0.1) {
            temp /= 1.1;
        } else {
            temp = 0;
        }
        this.setxSpeed(temp);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Slows down the player in the y direction.
     * Postcondition:
     *  The player has been slowed down.
     */
    public void slowDownY() {
        double temp;
        temp = this.getySpeed();
        if (temp > 0.1 || temp < -0.1) {
            temp /= 1.1;
        } else {
            temp = 0;
        }
        this.setySpeed(temp);
    }


    /**
     * Precondition:
     *  None.
     * Description:
     *  Shoots bullets based on the direction of the shot.
     * Postcondition:
     *  A bullet has been shot in the appropriate direction.
     */
    //SHOOTING METHODS
    public void shoot() {

        //DOWN
        if (moveDown && shootDown) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 0 * this.getBulletSpeedMulti(), (5 + this.getySpeed()) * this.getBulletSpeedMulti());

        } else if (moveDown && shootUp) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 0 * this.getBulletSpeedMulti(), (-5 + this.getySpeed() / 2) * this.getBulletSpeedMulti());
        } else if (moveDown && shootLeft) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), -5 * this.getBulletSpeedMulti(), (this.getySpeed()) * this.getBulletSpeedMulti());
        } else if (moveDown && shootRight) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 5 * this.getBulletSpeedMulti(), (this.getySpeed()) * this.getBulletSpeedMulti());
        }

        //UP
        else if (moveUp && shootDown) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 0 * this.getBulletSpeedMulti(), (5 + this.getySpeed() / 2) * this.getBulletSpeedMulti());

        } else if (moveUp && shootUp) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 0 * this.getBulletSpeedMulti(), (-5 + this.getySpeed()) * this.getBulletSpeedMulti());

        } else if (moveUp && shootLeft) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), -5 * this.getBulletSpeedMulti(), (this.getySpeed()) * this.getBulletSpeedMulti());
        } else if (moveUp && shootRight) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 5 * this.getBulletSpeedMulti(), (this.getySpeed()) * this.getBulletSpeedMulti());
        }

        //LEFT     
        else if (moveLeft && shootDown) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (this.getxSpeed()) * this.getBulletSpeedMulti(), (5) * this.getBulletSpeedMulti());
        } else if (moveLeft && shootUp) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (this.getxSpeed()) * this.getBulletSpeedMulti(), (-5) * this.getBulletSpeedMulti());
        } else if (moveLeft && shootLeft) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (-5 + this.getxSpeed()) * this.getBulletSpeedMulti(), (0) * this.getBulletSpeedMulti());
        } else if (moveLeft && shootRight) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (5 + this.getxSpeed() / 2) * this.getBulletSpeedMulti(), (0) * this.getBulletSpeedMulti());
        }

        //RIGHT
        else if (moveRight && shootDown) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (this.getxSpeed()) * this.getBulletSpeedMulti(), (5) * this.getBulletSpeedMulti());
        } else if (moveRight && shootUp) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (this.getxSpeed()) * this.getBulletSpeedMulti(), (-5) * this.getBulletSpeedMulti());
        } else if (moveRight && shootLeft) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (-5 + this.getxSpeed() / 2) * this.getBulletSpeedMulti(), (0) * this.getBulletSpeedMulti());
        } else if (moveRight && shootRight) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), (5 + this.getxSpeed()) * this.getBulletSpeedMulti(), (0) * this.getBulletSpeedMulti());
        } //STANDING STILL
        
        
        if (shootDown && !moving) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 0 * this.getBulletSpeedMulti(), 5 * this.getBulletSpeedMulti());
        } else if (shootUp && !moving) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 0 * this.getBulletSpeedMulti(), -5 * this.getBulletSpeedMulti());

        } else if (shootLeft && !moving) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), -5 * this.getBulletSpeedMulti(), 0 * this.getBulletSpeedMulti());

        } else if (shootRight && !moving) {
            bController.addBullet("playerbullet.png",this.getxPos(), this.getyPos(), this.getRadius(), 5 * this.getBulletSpeedMulti(), 0 * this.getBulletSpeedMulti());

        }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Moves the player's bullets.
     * Postcondition:
     *  The players' bullets have been moved.
     */
    public void moveBullets() {
        bController.increment();
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Changes shooting to the value of the passed variable.
     * Postcondition:
     *  Shooting value has been changed.
     */
    //GETS AND SETS FOR SHOOTING STATES
    public void setShooting(boolean TorF) {
        this.shooting = TorF;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of shooting.
     * Postcondition:
     *  The value of shooting has been returned.
     */
    public boolean isShooting() {
        return shooting;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of shootDown to the value of the passed argument.
     * Postcondition:
     *  The value of shootDown has been set.
     */
    public void setShootDown(boolean TorF) {
        shootDown = TorF;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of shootUp to the value of the passed argument.
     * Postcondition:
     *  The value of shootUp is cahgned.
     */
    public void setShootUp(boolean TorF) {
        shootUp = TorF;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of shootRight to the value of the passed argument.
     * Postcondition:
     *  The value of shootRight has been changed.
     */
    public void setShootRight(boolean TorF) {
        shootRight = TorF;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of shootRight to the vvalue of the passed argument.
     * Postcondition:
     *  The value of shootLeft is changed.
     */
    public void setShootLeft(boolean TorF) {
        shootLeft = TorF;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of shootDown.
     * Postcondition:
     *  The value of shootDown has been returned.
     */
    public boolean isShootDown() {
        return shootDown;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of shoot left.
     * Postcondition:
     *  The value of shootLeft is returned.
     */
    public boolean isShootLeft() {
        return shootLeft;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of shoot right.
     * Postcondition:
     *  The value of shootRight is returned.
     */
    public boolean isShootRight() {
        return shootRight;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of shootUp.
     * Postcondition:
     *  The value of shootUp is returned.
     */
    public boolean isShootUp() {
        return shootUp;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of moving.
     * Postcondition:
     *  The value of moving has been returned.
     */
    //GETS AND SETS FOR MOVING STATES
    public boolean isMoving() {
        return moving;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of moving to the value of the passed argument.
     * Postcondition:
     *  The value of moving has been changed.
     */
    public void setMoving(boolean TorF) {
        this.moving = TorF;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of moveDown.
     * Postcondition:
     *  The value of moveDown is returned.
     */
    public boolean isMoveDown() {
        return moveDown;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of moveLeft.
     * Postcondition:
     *  The value of moveLeft is returned.
     */
    public boolean isMoveLeft() {
        return moveLeft;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of moveRight.
     * Postcondition:
     *  The value of moveRightis returned.
     */
    public boolean isMoveRight() {
        return moveRight;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of moveUp.
     * Postcondition:
     *  The value of moveUp is returned.
     */
    public boolean isMoveUp() {
        return moveUp;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of moveUp to the value of the passed argument.
     * Postcondition:
     *  The value of moveUp is changed.
     */
    public void setMoveUp(boolean TorF) {
        moveUp = TorF;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of moveDown to the value of the passed argument.
     * Postcondition:
     *  The value of moveDown is changed.
     */
    public void setMoveDown(boolean TorF) {
        moveDown = TorF;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of moveRight to the value of the passed argument.
     * Postcondition:
     *  The value of moveRight is changed.
     */
    public void setMoveRight(boolean TorF) {
        moveRight = TorF;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of moveLeft to the value of the passed argument.
     * Postcondition:
     *  The value of moveLeft is changed.
     */
    public void setMoveLeft(boolean TorF) {
        moveLeft = TorF;
    }
    
    /**
     * Precondition:
     *  None
     * Description:
     *  Draws the ehalth, bombs and keys displays.
     * Postcondition:
     *  The display has been drawn.
     */
    public void UIDrawing(Graphics g)
    {
       key.draw(g);
       bomb.draw(g);
       heart.setxPos(originalxPos);
       heart.setyPos(originalyPos);
       halfHeart.setxPos(originalxPos);
       halfHeart.setyPos(originalyPos);
       emptyHeart.setxPos(originalxPos);
       emptyHeart.setyPos(originalyPos);
       g.setFont(new Font("Chiller", Font.BOLD, 30)); 
       if(this.getKeys() < 10)
       {
           g.setColor(Color.BLACK);
           g.drawString(": 0" + this.getKeys(), 40 + 2, 100);
           g.drawString(": 0" + this.getKeys(), 40 - 2, 100);
           g.drawString(": 0" + this.getKeys(), 40, 100 + 2);
           g.drawString(": 0" + this.getKeys(), 40, 100 - 2);
           g.setColor(Color.WHITE);
           g.setFont(new Font("Chiller", Font.BOLD, 30));
           g.drawString(": 0"+ this.getKeys(), 40, 100);
       }
       else
       {
           g.setColor(Color.BLACK);
           g.drawString(": " + this.getKeys(), 40 + 2, 100);
           g.drawString(": " + this.getKeys(), 40 - 2, 100);
           g.drawString(": " + this.getKeys(), 40, 100 + 2);
           g.drawString(": " + this.getKeys(), 40, 100 - 2);
           g.setColor(Color.WHITE);
           g.setFont(new Font("Chiller", Font.BOLD, 30));
           g.drawString(": "+ this.getKeys(), 40, 100);
       }
       if(this.getBombs() < 10)
       {
           g.setColor(Color.BLACK);
           g.drawString(": 0" + this.getBombs(), 40 + 2, 150);
           g.drawString(": 0" + this.getBombs(), 40 - 2, 150);
           g.drawString(": 0" + this.getBombs(), 40, 150 + 2);
           g.drawString(": 0" + this.getBombs(), 40, 150 - 2);
           g.setColor(Color.WHITE);
           g.setFont(new Font("Chiller", Font.BOLD, 30));
           g.drawString(": 0"+ this.getBombs(), 40, 150);
       }
       else
       {
           g.setColor(Color.BLACK);
           g.drawString(": " + this.getBombs(), 40 + 2, 150);
           g.drawString(": " + this.getBombs(), 40 - 2, 150);
           g.drawString(": " + this.getBombs(), 40, 150 + 2);
           g.drawString(": " + this.getBombs(), 40, 150 - 2);
           g.setColor(Color.WHITE);
           g.setFont(new Font("Chiller", Font.BOLD, 30));
           g.drawString(": "+ this.getBombs(), 40, 150);
       }
       for(int i = 0; i < this.getMaxHp() / 2; i++)
       {
           emptyHeart.setxPos(originalxPos + (35 * i));
           if(i >= 6)
           {
               emptyHeart.setyPos(originalyPos + 30);
               emptyHeart.setxPos(originalxPos + (35 * (i - 6)));
           }
           emptyHeart.draw(g);
       }
       for(int i = 0; i < this.getHealth() / 2; i++)
       {
           heart.setxPos(originalxPos + (35 * i));
           if(i >= 6)
           {
               heart.setyPos(originalyPos + 30);
               heart.setxPos(originalxPos + (35 * (i - 6)));
           }
           heart.draw(g);
           if(i == this.getHealth()/2 - 0.5 && ((int)this.getHealth() % 2) != 0)
           {
               halfHeart.setxPos(originalxPos + (35 * i));
               if(i >= 6)
               {
                   halfHeart.setxPos(originalxPos + (35 * (i - 6)));
                   halfHeart.setyPos(originalyPos + 30);
               }
               halfHeart.draw(g);
           }
       }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the UI and the invincibility frames effect.
     * Postcondition:
     *  The UI and the effect have been drawn.
     */
    public void draw(Graphics g) {
        
        activeInvincibilityFrames(g);                

        UIDrawing(g);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks the collision between the player and another object.
     * Postcondition:
     *  Returns true if the collision is true, else returns false.
     */
    @Override
    public <T extends VectorSprite> boolean collide(T object1, GraphicsObject object2) {
        double xDistance, yDistance;

        xDistance = this.getHitCenterX() + object1.getRadius() / 2 - Math.max(object2.getxPos(), Math.min(this.getHitCenterX() + object1.getRadius() / 2, object2.getxPos() + object2.getWidth()));
        yDistance = this.getHitCenterY() + object1.getRadius() / 2 - Math.max(object2.getyPos(), Math.min(this.getHitCenterY() + object1.getRadius() / 2, object2.getyPos() + object2.getHeight()));

        return (xDistance * xDistance + yDistance * yDistance) < (object1.getRadius() / 2 * object1.getRadius() / 2);
    }

    @Override
    public <T extends VectorSprite> void collideAction(T object) {

    }
}
