
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class SecretRoom extends Room implements RandCalculator{

    private int[][] map;
    private int randNum, displayCount;
    private boolean pickUpItem;
    protected ArrayList<Item> upgrade = new ArrayList();

   
    

    public SecretRoom(String name, double xPos, double yPos, int width, int height, int[][] map) {
        super(name, xPos, yPos, width, height, map);
        this.map = map;
        for (int i = 0; i < 4; i++) {
            doors.add(null);
        }
        this.setRevealed(false);
        this.displayCount = 0;
    }
    @Override
    public void moveObstacles()
    {
        for(int i = 0; i < obstacles.size(); i++)
        {
            obstacles.get(i).setxPos(this.getxPos() + obstacles.get(i).getOriginalxPos());  
            obstacles.get(i).setyPos(this.getyPos() + obstacles.get(i).getOriginalyPos());
        }
    }

    
    
    public void generateItem()
    {
        randNum = randNum(0,16); //health, range, speed, firerate, damage
        if(randNum == 0)
        {       
            upgrade.add(new UpgradeItem("sword.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 0, 0, 0, 5, "Sword", "For self defence     +Damage"));
        }
        else if(randNum == 1)
        {
            upgrade.add(new UpgradeItem("boot.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 0, 1, 0, 0, "Boots", "Lighter on your feet     ++Speed"));
        }
        else if(randNum == 2)
        {
            upgrade.add(new UpgradeItem("bluepot.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 0, 0, 3, 0, "Blue Potion", "Take a sip     +Firerate"));
        }
        else if(randNum == 3)
        {
            upgrade.add(new UpgradeItem("meat.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 2, 0, 0, 0, 0, "Meat", "Nutritious meal     +Health"));
        }
        else if(randNum == 4)
        {
            upgrade.add(new UpgradeItem("arrow.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 50, 0, 0, 0, "Arrow", "Let it fly     +Range"));
        }
        else if(randNum == 5)
        {
            upgrade.add(new UpgradeItem("bowarrow.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 30, 0, 0, 5, "Bow", "Useful for ranged battles     +Range +Damage"));
        }
        else if(randNum == 6)
        {
            upgrade.add(new UpgradeItem("greenpot.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 2, 0, 0.5, 0, 0, "Green Potion", "Healthier already     +Health + Speed"));
        }
        else if(randNum == 7)
        {
            upgrade.add(new UpgradeItem("shield1.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 4, 0, 0, 0, 0, "Holy Shield", "Protect yourself!     ++Health"));
        }
        else if(randNum == 8)
        {
            upgrade.add(new UpgradeItem("toy.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 30, 0.5, 3, 0, "Toy", "Makes you feel like a child again     +Range +Speed +Firerate"));
        }
        else if(randNum == 9)
        {
            upgrade.add(new UpgradeItem("skull.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, -20, 0, 5, 4, "Skull", "Cursed?     +Damage +Firerate -Range"));
        }
        else if(randNum == 10)
        {
            upgrade.add(new UpgradeItem("chain.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 2, 0, -0.5, 0, 0, "Chain", "Drags you down     +Health -Speed"));
        }
        else if(randNum == 11)
        {
            upgrade.add(new UpgradeItem("shield2.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 2, 0, 0.5, 0, 0, "Wooden Shield", "Protect yourself!    +Health +Speed"));
        }
        else if(randNum == 12)
        {
            upgrade.add(new UpgradeItem("keypowerup.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 2, 30, 0.5, 2, 3, "Lucky Key", "Luck is on your side    +All Stats"));
        }
        else if(randNum == 13)
        {
            upgrade.add(new UpgradeItem("torch.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 0, 0, 0, 6, "Torch", "Fight fire with fire    ++Damage"));
        }
        else if(randNum == 14)
        {
            upgrade.add(new UpgradeItem("map.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 40, 0.5, 0, 0, "Empty Map", "It's blank?    +Range +Speed"));
        }
        else if(randNum == 15)
        {
            upgrade.add(new UpgradeItem("glitch.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 0, 0, 7, -2, "Glitched", "_>`|q~6p7|,]wX\\l\"b+DQkj<    ++Firerate --Damage"));
        }
        else if(randNum == 16)
        {
            upgrade.add(new UpgradeItem("chestitem.png", 89* 6, 89 * 4 - 20, 89, 120, 70, 0, 0, 0, -30, 20, "Small Chest", "Infinite possibilities     ++Damage --Firerate"));
        }
    }
    
    public double randNum(double min, double max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextDouble();
    }

    public int randNum(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;

        while (randomNum == 0) {
            randomNum = rand.nextInt(max - min + 1) + min;
        }

        return randomNum;
    }
    
    @Override
    public void moveItems()
    {
        for(int i = 0; i < upgrade.size(); i++)
        {
            upgrade.get(i).setxPos(this.getxPos() + upgrade.get(i).getOriginalxPos());
            upgrade.get(i).setyPos(this.getyPos() + upgrade.get(i).getOriginalyPos());
        }
    }
    
    @Override
    public void buildRoom(Player player) {
        randNum = randNum(1, 5);
        if(randNum == 1)
        {
            obstacles.add(new Bonfire("fire.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 7, 89 * 4, 89, 89, 89));
            generateItem();
        }
        else if(randNum == 2)
        {
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 1, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 3, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 2, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 3, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 3, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 7, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 8, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 9, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 8, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 7, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 8, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 9, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 2, 89, 89, 89));
            generateItem();
        }
        else if(randNum == 3)
        {
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 8, 89 * 6, 89, 89, 89));
            generateItem();
        }
        else if(randNum == 4)
        {
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 2, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 7, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 7, 89, 89, 89));
            generateItem();
        }
        else if(randNum == 5)
        {
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 7, 89, 89, 89));
            generateItem();
        }

    }

    @Override
    public void buildDoors(int x, int y)
    {
        if (map[y - 1][x] == 1 || map[y - 1][x] == 2) {
            doors.set(0, (new SecretDoor("dooropenup.png", 534, 0, 89, 89)));
        }
        //right
        if (map[y][x + 1] == 1 || map[y][x + 1] == 2) {
            doors.set(1, (new SecretDoor("dooropenright.png", 1068, 356, 89, 89)));
        }
        //down
        if (map[y + 1][x] == 1 || map[y + 1][x] == 2) {
            doors.set(2, (new SecretDoor("dooropendown.png", 534, 712, 89, 89)));
        }
        //left
        if (map[y][x - 1] == 1 || map[y][x - 1] == 2) {
            doors.set(3, (new SecretDoor("dooropenleft.png", 0, 356, 89, 89)));
        }
        
    }
    
    
    @Override
    public void pickUpItem(Player p)
    {
        for(int i = 0; i < upgrade.size(); i++)
        {
            if(collideBomb(upgrade.get(i), p) && displayCount == 0)
            {
                pickUpItem = upgrade.get(i).collideAction(p);
                if(pickUpItem)
                {
                    displayCount++;
                }
            }
        }

    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if(displayCount > 0)
        {
            for(int i = 0; i < upgrade.size(); i++)
            {
                g.setFont(new Font("Chiller", Font.BOLD, 70));
                g.setColor(Color.BLACK);
                g.drawString(upgrade.get(i).getNameI(), 450 + 2, 100);
                g.drawString(upgrade.get(i).getNameI(), 450 - 2, 100);
                g.drawString(upgrade.get(i).getNameI(), 450, 100 + 2);
                g.drawString(upgrade.get(i).getNameI(), 450, 100 - 2);
                g.setColor(Color.WHITE);
                g.drawString(upgrade.get(i).getNameI(), 450, 100);
                g.setFont(new Font("Chiller", Font.BOLD, 30));
                g.setColor(Color.BLACK);
                g.drawString(upgrade.get(i).getDescription(), 400 + 2, 140);
                g.drawString(upgrade.get(i).getDescription(), 400 - 2, 140);
                g.drawString(upgrade.get(i).getDescription(), 400, 140 + 2);
                g.drawString(upgrade.get(i).getDescription(), 400, 140 - 2);
                g.setColor(Color.WHITE);
                g.drawString(upgrade.get(i).getDescription(), 400, 140);
                displayCount++;
                if(displayCount > 400)
                {
                    upgrade.remove(i);
                }
            }
        }
        else
        {
            for(int i = 0; i < upgrade.size(); i++)
            {
                upgrade.get(i).draw(g);
            }
        }
//        for (int i = 0; i < obstacles.size(); i++) {
//            obstacles.get(i).draw(g);
//        }
//        for (int i = 0; i < doors.size(); i++) {
//            if (doors.get(i) != null) {
//                doors.get(i).draw(g);
//            }
//        }

    }

}
