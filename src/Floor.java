
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Floor implements CollisionListener {

    private int currentRoomX, currentRoomY;
    private int roomAmount;
    private int[][] roomsIntArray;
    private Room[][] floorMap;
    private Player player;
    private MapGenerator generator;
    private boolean isDone, moveRoomLeft,moveRoomRight,moveRoomDown,moveRoomUp;
    private boolean floorOver;
    int floorNum;
    
    public Floor(int roomAmount, Player player, int floorNum) {
        this.roomAmount = roomAmount;
        currentRoomX = (roomAmount * 2 + 3) / 2;
        currentRoomY = (roomAmount * 2 + 3) / 2;
        floorMap = new Room[roomAmount * 2 + 3][roomAmount * 2 + 3];
        this.player = player;
        this.floorNum = floorNum;
        generator = new MapGenerator();
        isDone = true;
    }
    public void killAll(boolean kill){
        for(int i = 0; i < floorMap[currentRoomY][currentRoomX].enemies.size();i++){
            floorMap[currentRoomY][currentRoomX].enemies.get(i).setHealth(0);
        }
    }
    public boolean isFloorOver() {
        return floorOver;
    }

    public void setFloorOver(boolean floorOver) {
        this.floorOver = floorOver;
    }


    public void generateFloor() {
        roomsIntArray = generator.generateAll(roomAmount);
        for (int y = 1; y < roomsIntArray.length - 1; y++) {
            for (int x = 1; x < roomsIntArray[y].length - 1; x++) {
                if (roomsIntArray[y][x] == 0) {
                    floorMap[y][x] = null;
                }
                else if(y == currentRoomY && x == currentRoomX)
                {
                    floorMap[y][x] = new Room("roomstarter.png", 0, 0, 1157, 801, roomsIntArray);
                }
                else if (roomsIntArray[y][x] == 1) {
                    floorMap[y][x] = new Room("room_default.png", 0, 0, 1157, 801, roomsIntArray);
                    //build(x,y);
                } //CHANGE THESE FOR ITEM SPECIAL AND BOSS ROOMS
                else if (roomsIntArray[y][x] == 2) {
                    floorMap[y][x] = new ItemRoom("room_default.png", 0, 0, 1157, 801, roomsIntArray);
                    //build(x,y);
                } 
                else if (roomsIntArray[y][x] == 8) {
                    floorMap[y][x] = new SecretRoom("room_default.png", 0, 0, 1157, 801, roomsIntArray);
                    //build(x,y);
                }
                else if (roomsIntArray[y][x] == 9) {
                    //floorMap[y][x] = new BossRoom("room_default.png", 0, 0, 1157, 801, roomsIntArray,player);
                    floorMap[y][x] = new BossRoom("room_default.png", 0, 0, 1157, 801, roomsIntArray,floorNum);
                    //build(x,y);
                }
                if (floorMap[y][x] != null) {
                    floorMap[y][x].buildDoors(x, y);
                    if(!(y == currentRoomY) || !(x == currentRoomX))
                    {
                        floorMap[y][x].buildRoom(player);
                    }
                    //floorMap[y][x].buildRoom(player);
                }

            }
        }
    }


    public void operateRooms(){
        Room tempRoom = floorMap[currentRoomY][currentRoomX];
        BossRoom tempBRoom = null;
//        BossRoom tempBossR = null;
//        if(tempRoom.toString().equals("Boss Room")){
//            tempBossR = (BossRoom)tempRoom;
        tempRoom.operate(player);
        if(tempRoom.toString().equals("Boss Room")){
            tempBRoom = (BossRoom)tempRoom;
            
            if(tempBRoom.isRoomCleared()){
                if(collide(player,tempBRoom.getFinalDoor())){
                    this.setFloorOver(true);
                }
            }
        }
//        }
//        else{
//            tempRoom.operate(player);
//            for(int i = 0;i<tempRoom.enemies.size();i++){
//                tempRoom.enemies.get(i).move(1);
//                tempRoom.enemies.get(i).bController.increment();
//                tempRoom.enemies.get(i).shoot();
//                for (int j = 0; j < player.bController.bullets.size(); j++) {
//                    if (tempRoom.enemies.get(i).bulletCollide(tempRoom.enemies.get(i), player.bController.bullets.get(j))) {
//                        player.bController.removeBullet(player.bController.bullets.get(j));
//                        tempRoom.enemies.get(i).damaged(10);
//                    }
//                }
//
//                if (tempRoom.enemies.get(i).getHealth() <= 0) {
//                    tempRoom.enemies.remove(i);
//                }
//
//            }
//
//
//            if(!tempRoom.enemies.isEmpty())
//            {
//                for(int i = 0; i < tempRoom.doors.size(); i++)
//                {
//                    if(tempRoom.doors.get(i) != null && tempRoom.doors.get(i).isOpen())
//                    {
//                        tempRoom.changeDoor(i);
//                        tempRoom.doors.get(i).changeOpenState();
//                    }
//                }
//            }
//            else if(tempRoom.enemies.isEmpty())
//            {
//                for(int i = 0; i < tempRoom.doors.size(); i++)
//                {
//                    if(tempRoom.doors.get(i) != null && !(tempRoom.doors.get(i).isOpen()))
//                    {
//                        tempRoom.changeDoor(i);
//                    }
//                }
//            }
//        }
        
    }
    


    public void changeCurrentRoom() {
        //UP
        if (floorMap[currentRoomY][currentRoomX].doors.get(0) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(0)) && floorMap[currentRoomY][currentRoomX].doors.get(0).isOpen()) {
            if(!moveRoomUp)
            {
                player.bController.removeAllBullets();
                floorMap[currentRoomY - 1][currentRoomX].setyPos(-801);
                floorMap[currentRoomY - 1][currentRoomX].displaceDoorVert(-801);
                moveRoomUp = true; 
            }
        } //right
        else if (floorMap[currentRoomY][currentRoomX].doors.get(1) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(1))&& floorMap[currentRoomY][currentRoomX].doors.get(1).isOpen()) {
            if(!moveRoomRight)
            {
                player.bController.removeAllBullets();
                floorMap[currentRoomY][currentRoomX + 1].setxPos(1157);
                floorMap[currentRoomY][currentRoomX + 1].displaceDoorHorizontal(1157);
                moveRoomRight = true;
            }
        } //down
        else if (floorMap[currentRoomY][currentRoomX].doors.get(2) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(2))&& floorMap[currentRoomY][currentRoomX].doors.get(2).isOpen()) {
            if(!moveRoomDown)
            {
                player.bController.removeAllBullets(); 
                floorMap[currentRoomY + 1][currentRoomX].setyPos(801);
                floorMap[currentRoomY + 1][currentRoomX].displaceDoorVert(801);
                moveRoomDown = true;
            }
        } //left
        else if (floorMap[currentRoomY][currentRoomX].doors.get(3) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(3))&& floorMap[currentRoomY][currentRoomX].doors.get(3).isOpen()) {
            if(!moveRoomLeft)
            {
                player.bController.removeAllBullets();
                floorMap[currentRoomY][currentRoomX - 1].setxPos(-1157);
                floorMap[currentRoomY][currentRoomX - 1].displaceDoorHorizontal(-1157);
                moveRoomLeft = true;
            }
        }
        
        if(floorMap[currentRoomY][currentRoomX].doors.get(0) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(0)) && floorMap[currentRoomY][currentRoomX].doors.get(0).isLocked() && player.getKeys() > 0)
        {
            if(!moveRoomUp)
            {
                player.bController.removeAllBullets();
                floorMap[currentRoomY - 1][currentRoomX].setyPos(-801);
                floorMap[currentRoomY - 1][currentRoomX].displaceDoorVert(-801);
                floorMap[currentRoomY][currentRoomX].doors.get(0).setLocked(false);
                player.setKeys(player.getKeys() - 1);
                moveRoomUp = true; 
            }
        }
        else if(floorMap[currentRoomY][currentRoomX].doors.get(1) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(1)) && floorMap[currentRoomY][currentRoomX].doors.get(1).isLocked() && player.getKeys() > 0)
        {
            if(!moveRoomRight)
            {
                player.bController.removeAllBullets();
                floorMap[currentRoomY][currentRoomX + 1].setxPos(1157);
                floorMap[currentRoomY][currentRoomX + 1].displaceDoorHorizontal(1157);
                floorMap[currentRoomY][currentRoomX].doors.get(1).setLocked(false);
                player.setKeys(player.getKeys() - 1);
                moveRoomRight = true;
            }
        }
        else if(floorMap[currentRoomY][currentRoomX].doors.get(2) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(2)) && floorMap[currentRoomY][currentRoomX].doors.get(2).isLocked() && player.getKeys() > 0)
        {
            if(!moveRoomDown)
            {
                floorMap[currentRoomY + 1][currentRoomX].setyPos(801);
                floorMap[currentRoomY + 1][currentRoomX].displaceDoorVert(801);
                floorMap[currentRoomY][currentRoomX].doors.get(2).setLocked(false);
                player.setKeys(player.getKeys() - 1);
                player.bController.removeAllBullets(); 
                moveRoomDown = true;
            }
        }
        else if(floorMap[currentRoomY][currentRoomX].doors.get(3) != null && collide(player, floorMap[currentRoomY][currentRoomX].doors.get(3)) && floorMap[currentRoomY][currentRoomX].doors.get(3).isLocked() && player.getKeys() > 0)
        {
            if(!moveRoomLeft)
            {
                floorMap[currentRoomY][currentRoomX - 1].setxPos(-1157);
                floorMap[currentRoomY][currentRoomX - 1].displaceDoorHorizontal(-1157);
                player.setKeys(player.getKeys() - 1);
                floorMap[currentRoomY][currentRoomX].doors.get(3).setLocked(false);
                moveRoomLeft = true;
                player.bController.removeAllBullets();
            }
        }
        
        if(moveRoomUp)
        {
            player.bController.removeAllBullets();
            for(int i = 0;i < floorMap[currentRoomY][currentRoomX].enemies.size();i++){
                floorMap[currentRoomY][currentRoomX].enemies.get(i).bController.removeAllBullets();
            }
            isDone = floorMap[currentRoomY][currentRoomX].moveRoomUp();
            floorMap[currentRoomY - 1][currentRoomX].moveSecondaryRoomUp();
            player.setyPos(649);
            player.setxPos(543);
            player.slowDownY();
            player.slowDownX();
            player.setySpeed(0);
            player.setxSpeed(0);
            if(isDone)
            {
                currentRoomY--;
                floorMap[currentRoomY][currentRoomX].updateDoors(0);
                moveRoomUp = false;
            }
        }
        
        else if(moveRoomRight)
        {
            player.bController.removeAllBullets();
            for(int i = 0;i < floorMap[currentRoomY][currentRoomX].enemies.size();i++){
                floorMap[currentRoomY][currentRoomX].enemies.get(i).bController.removeAllBullets();
            }
            isDone = floorMap[currentRoomY][currentRoomX].moveRoomRight();
            floorMap[currentRoomY][currentRoomX + 1].moveSecondaryRoomRight();
            player.setxPos(78);
            player.setyPos(358);
            player.slowDownY();
            player.slowDownX();
            player.setySpeed(0);
            player.setxSpeed(0);
            if(isDone)
            {
                currentRoomX++;
                floorMap[currentRoomY][currentRoomX].updateDoors(1);
                moveRoomRight = false;
            }
        }
        
        else if(moveRoomDown)
        {
            player.bController.removeAllBullets();
            for(int i = 0;i < floorMap[currentRoomY][currentRoomX].enemies.size();i++){
                floorMap[currentRoomY][currentRoomX].enemies.get(i).bController.removeAllBullets();
            }
            isDone = floorMap[currentRoomY][currentRoomX].moveRoomDown();
            floorMap[currentRoomY + 1][currentRoomX].moveSecondaryRoomDown();
            player.setyPos(66);
            player.setxPos(549);
            player.slowDownY();
            player.slowDownX();
            player.setySpeed(0);
            player.setxSpeed(0);
            if(isDone)
            {
                currentRoomY++;
                floorMap[currentRoomY][currentRoomX].updateDoors(2);
                moveRoomDown = false;
            }
        }
        
        else if(moveRoomLeft)
        {
            player.bController.removeAllBullets();
            for(int i = 0;i < floorMap[currentRoomY][currentRoomX].enemies.size();i++){
                floorMap[currentRoomY][currentRoomX].enemies.get(i).bController.removeAllBullets();
            }
            isDone = floorMap[currentRoomY][currentRoomX].moveRoomLeft();
            floorMap[currentRoomY][currentRoomX - 1].moveSecondaryRoomLeft();
            player.setxPos(1015);
            player.setyPos(365);
            player.slowDownY();
            player.slowDownX();
            player.setySpeed(0);
            player.setxSpeed(0);
            if(isDone)
            {
                currentRoomX--;
                floorMap[currentRoomY][currentRoomX].updateDoors(3);
                moveRoomLeft = false;
            }
        }
    }
    
    public boolean isIsDone()
    {
        if(isDone)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    public <T extends VectorSprite> boolean collide(T object1, GraphicsObject object2) {
        double xDistance, yDistance;

        xDistance = object1.getHitCenterX() + object1.getRadius() / 2 - Math.max(object2.getxPos(), Math.min(object1.getHitCenterX() + object1.getRadius() / 2, object2.getxPos() + object2.getWidth()));
        yDistance = object1.getHitCenterY() + object1.getRadius() / 2 - Math.max(object2.getyPos(), Math.min(object1.getHitCenterY() + object1.getRadius() / 2, object2.getyPos() + object2.getHeight()));

        return (xDistance * xDistance + yDistance * yDistance) < (object1.getRadius() / 2 * object1.getRadius() / 2);
    }

    @Override
    public <T extends VectorSprite> void collideAction(T object) {
    }

    
    
    
    public void draw(Graphics g) {
        for (int y = 0; y < floorMap.length; y++) {
            for (int x = 0; x < floorMap[y].length; x++) {
                if (y == currentRoomY && x == currentRoomX && floorMap != null) {
                    floorMap[y][x].draw(g);
                    if(moveRoomUp)
                    {
                        floorMap[y - 1][x].draw(g);
                    }
                    else if(moveRoomDown)
                    {
                        floorMap[y + 1][x].draw(g);
                    }
                    else if(moveRoomLeft)
                    {
                        floorMap[y][x - 1].draw(g);
                    }
                    else if(moveRoomRight)
                    {
                        floorMap[y][x + 1].draw(g);
                    }
                }
            }
        }

        for (int y = 0 ; y  < floorMap.length ; y++) {
            for (int x = 0 ; x < floorMap[y].length;x++) {
                if (y == currentRoomY && x == currentRoomX && floorMap[y][x] != null) {
                   // System.out.println((roomsIntArray[y+1][x]!=8 || roomsIntArray[y-1][x]!=8 || roomsIntArray[y][x+1]!=8 || roomsIntArray[y][x-1]!=8));
                    floorMap[y][x].setEntered(true);
                    
                    if(floorMap[y+1][x] != null && floorMap[y+1][x].isRevealed()) {
                        floorMap[y+1][x].setSeenRoom(true);
                    }
                    if(floorMap[y-1][x] != null && floorMap[y-1][x].isRevealed()) {
                        floorMap[y-1][x].setSeenRoom(true);
                    }
                    if(floorMap[y][x+1] != null && floorMap[y][x+1].isRevealed()) {
                        floorMap[y][x+1].setSeenRoom(true);
                    }
                    if(floorMap[y][x-1] != null && floorMap[y][x-1].isRevealed()) {
                        floorMap[y][x-1].setSeenRoom(true);
                    } 
                }

                if (floorMap[y][x]!= null) {
                    if (floorMap[y][x].isEntered()) {
                        g.setColor(Color.black);
                
                        if (y == currentRoomY && x == currentRoomX && floorMap != null) {
                            g.setColor(new Color(144,0,32));
                        }
                        g.fillRect(700+x*25+x, 100+y*15 + y, 25, 15);
                    } 
                    else if (floorMap[y][x].isSeenRoom()) {
                        g.setColor(new Color(0,0,0,70));
                        g.fillRect(700+x*25+x, 100+y*15 + y, 25, 15);
                    }
                }
            }
        }
    }
}
