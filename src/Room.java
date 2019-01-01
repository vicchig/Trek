
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

 public class Room extends GraphicsObject implements RandCalculator {

    protected ArrayList<Enemy> enemies = new ArrayList();
    protected ArrayList<Obstacle> obstacles = new ArrayList();
    protected ArrayList<Door> doors = new ArrayList();
    protected ArrayList<Item> items = new ArrayList();
    private int[][] map;
    private int randNum;
    private boolean revealed;
    private boolean endRoom, seenRoom, entered, pickupItem, viableDrops;

    public Room(String name, double xPos, double yPos, int width, int height, int[][] map) {
        super(name, xPos, yPos, width, height);
        this.map = map;
        for (int i = 0; i < 4; i++) {
            doors.add(null);
        }
        endRoom = false;
        seenRoom = false;
        entered = false;
        viableDrops = false;
        obstacles.add(new Wall("empty.png", 0, 0, 89, 801, 89));
        obstacles.add(new Wall("empty.png", 0, 0, 1157, 89, 89));
        obstacles.add(new Wall("empty.png", 0, 712, 1157, 89, 89));
        obstacles.add(new Wall("empty.png", 1068, 0, 89, 801, 89));
        revealed = true;
    }
    

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
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

    public void changeDoor(int i) {
        if (doors.get(0) != null && i == 0) {
            if (doors.get(0).isBossDoor()) {
                doors.get(0).setOpen(true);
            } else if (doors.get(0).isOpen()) {
                doors.set(0, (new Door("closedDoorUp.png", 534, 0, 89, 89)));
            } else if (!doors.get(i).isLocked() || !doors.get(i).isClosed()) {
                doors.set(0, (new Door("dooropenup.png", 534, 0, 89, 89)));
            }
        } else if (doors.get(1) != null && i == 1) {
            if (doors.get(1).isBossDoor()) {
                doors.get(1).setOpen(true);
            } else if (doors.get(1).isOpen()) {
                doors.set(1, (new Door("closedDoorRight.png", 1068, 356, 89, 89)));
            } else if (!doors.get(i).isLocked() || !doors.get(i).isClosed()) {
                doors.set(1, (new Door("dooropenright.png", 1068, 356, 89, 89)));
            }
        } else if (doors.get(2) != null && i == 2) {
            if (doors.get(2).isBossDoor()) {
                doors.get(2).setOpen(true);
            } else if (doors.get(2).isOpen()) {
                doors.set(2, (new Door("closedDoorDown.png", 534, 712, 89, 89)));
            } else if (!doors.get(i).isLocked() || !doors.get(i).isClosed()) {
                doors.set(2, (new Door("dooropendown.png", 534, 712, 89, 89)));
            }
        } else if (doors.get(3) != null && i == 3) {
            if (doors.get(3).isBossDoor()) {
                doors.get(3).setOpen(true);
            } else if (doors.get(3).isOpen()) {
                doors.set(3, (new Door("closedDoorLeft.png", 0, 356, 89, 89)));
            } else if (!doors.get(i).isLocked() || !doors.get(i).isClosed()) {
                doors.set(3, (new Door("dooropenleft.png", 0, 356, 89, 89)));
            }
        }
    }

    public void buildRoom(Player player) {
        randNum = randNum(1,50);
        if (randNum == 1) {
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 5, 89 * 4, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 2) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 5, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 3) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 6, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 1, 89 * 1, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 11, 89 * 1, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 11, 89 * 7, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 1, 89 * 7, 74, 62, 38, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 4) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 4, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 0, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 5) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 4, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 8, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 6) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 0, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 1, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 7) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 5, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 0, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 1, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));

        }
        else if (randNum == 8) {

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

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 5, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));

        }
        else if (randNum == 9) {

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 6, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 6, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 4, 89, 89, 89));

        }

        else if (randNum == 10) {

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 4, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 2, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 5, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 7, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 5, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));

        }
        else if (randNum == 11) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 4, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 4, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 8, 89 * 7, 20, 20, 30, player));

        }
        else if (randNum == 12) {

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 5, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 3, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 5, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 5, 89 * 5, 20, 20, 30, player));

        }
        else if (randNum == 13) {

            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 6, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 4, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 5, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 1, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 5, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 3, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 5, 20, 20, 30, player, 3, 10));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 14) {

            obstacles.add(new Bonfire("fire.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 3, 89 * 5, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 7, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 7, 89 * 5, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 8, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 9, 89 * 3, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 5, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 10, 89 * 6, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 3, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 5, 20, 20, 30, player, 3, 10));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }

        else if (randNum == 15) {

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 7, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 7, 20, 20, 30, player));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));

        }
        else if (randNum == 16) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 6, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 5, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 17) {

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 2, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 6, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 5, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 5, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));

        }
        else if (randNum == 18) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 7, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 7, 20, 20, 30, player));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 19) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 7, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 7, 20, 20, 30, player));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 20) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 21) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 5, 74, 62, 38, player));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 22) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 3, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 5, 20, 20, 30, player, 0, 10));

        }
        else if (randNum == 23) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 3, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 5, 20, 20, 30, player, 0, 10));

        }
        else if (randNum == 24) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 5, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));

        }
        else if (randNum == 25) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 1, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 5, 89 * 4, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 4, 20, 20, 30, player));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 26) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 1, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 1, 89 * 7, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 11, 89 * 7, 20, 20, 30, player));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 27) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 6, 89 * 4, 53, 43, 30, player, 400));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 1, 10));

        }
        else if (randNum == 28) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 6, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 6, 89 * 4, 53, 43, 30, player, 400));

        }
        else if (randNum == 29) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 5, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 1, 89 * 1, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 1, 89 * 7, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 11, 89 * 1, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 11, 89 * 7, 74, 62, 38, player));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 6, 89 * 4, 53, 43, 30, player, 400));

        }
        else if (randNum == 30) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 6, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 1, 10));

        }
        else if (randNum == 31) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 5, 89, 89, 89));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 6, 89 * 4, 53, 43, 30, player, 400));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 1, 10));
        }
        else if (randNum == 32) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 4, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 4, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 3, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 5, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 5, 20, 20, 30, player));

        }
        else if (randNum == 33) {
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 4, 89, 89, 89));

            obstacles.add(new Bonfire("fire.png", 89 * 3, 89 * 4, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 6, 89 * 5, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 9, 89 * 4, 89, 89, 89));

            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 1, 10));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 34) {

            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Bonfire("fire.png", 89 * 11, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 2, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 7, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 35) {

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 1, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 1, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 1, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 7, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 7, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 5, 89 * 4, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 4, 20, 20, 30, player));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 6, 89 * 4, 53, 43, 30, player, 400));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 8, 89 * 3, 74, 62, 38, player));

        }
        else if (randNum == 36) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 1, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 1, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 7, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 2, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 3, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 4, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 6, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 2, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 9, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 8, 89 * 6, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 5, 89 * 4, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 4, 20, 20, 30, player));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 6, 89 * 4, 53, 43, 30, player, 400));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 8, 89 * 3, 74, 62, 38, player));

        }
        else if (randNum == 37) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 8, 89 * 3, 74, 62, 38, player));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 38) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 8, 89 * 3, 74, 62, 38, player));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 39) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 8, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 40) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 5, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 6, 89, 89, 89));

        }
        else if (randNum == 41) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 4, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 6, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 5, 89 * 4, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 42) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 6, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 8, 89 * 3, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));

        }
        else if (randNum == 43) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 44) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));

            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 5, 89 * 4, 74, 62, 38, player));
            enemies.add(new FollowingSlime("/slimes/blueslime.png", 89 * 7, 89 * 4, 74, 62, 38, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 6, 89 * 4, 20, 20, 30, player));

        }
        else if (randNum == 45) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));

            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 5, 89 * 4, 20, 20, 30, player));
            enemies.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", 89 * 7, 89 * 4, 20, 20, 30, player));

        }

        else if (randNum == 46) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 4, 89 * 4, 53, 43, 30, player, 400));
            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 6, 89 * 4, 53, 43, 30, player, 400));
            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 5, 89 * 4, 53, 43, 30, player, 400));

        }
        else if (randNum == 47) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 4, 89 * 4, 53, 43, 30, player, 400));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));

        }
        else if (randNum == 48) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 4, 89 * 4, 53, 43, 30, player, 400));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 1, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));

        }
        else if (randNum == 49) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 3, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 5, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 1, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 2, 89 * 7, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 7, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 1, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 11, 89 * 3, 89, 89, 89));

            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 3, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 5, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new SpikeTrap("spiketrap.png", 89 * 6, 89 * 5, 89, 89, 89));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 4, 89 * 4, 53, 43, 30, player, 400));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 2, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));

        }
        else if (randNum == 50) {

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 3, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 7, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 3, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 4, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 5, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 10, 89 * 6, 89, 89, 89));

            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 2, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 8, 89 * 6, 89, 89, 89));
            obstacles.add(new Obstacle("obstacle_rock.png", 89 * 9, 89 * 6, 89, 89, 89));

            enemies.add(new BatEnemy("/flying + following enemy/bat.png", 89 * 4, 89 * 4, 53, 43, 30, player, 400));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 0, 10));
            enemies.add(new RoamingEnemy("eyeballenemyG.png", 89 * 6, 89 * 4, 20, 20, 30, player, 3, 10));

        }

    }

    public void defaultDoors() {
        if (doors.get(0) != null) {
            doors.get(0).setyPos(0);
            doors.get(0).setxPos(534);
        }
        if (doors.get(1) != null) {
            doors.get(1).setxPos(1068);
            doors.get(1).setyPos(356);
        }
        if (doors.get(2) != null) {
            doors.get(2).setyPos(712);
            doors.get(2).setxPos(534);
        }
        if (doors.get(3) != null) {
            doors.get(3).setxPos(0);
            doors.get(3).setyPos(356);
        }
    }

    public void moveSecondaryRoomUp() {
        super.setyPos(super.getyPos() + 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorVertical(60);
            }
        }
        if (super.getyPos() >= 0) {
            super.setyPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
        }
    }

    public boolean moveRoomUp() {
        super.setyPos(super.getyPos() + 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).bController.removeAllBullets();
            enemies.get(i).resetPosition();
        }
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorVertical(60);
            }
        }
        if (super.getyPos() >= 801) {
            super.setyPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
            return true;
        } else {
            return false;
        }
    }

    public void moveSecondaryRoomRight() {
        super.setxPos(super.getxPos() - 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorHorizontal(-60);
            }
        }
        if (super.getxPos() <= 0) {
            super.setxPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
        }
    }

    public boolean moveRoomRight() {
        super.setxPos(super.getxPos() - 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).bController.removeAllBullets();
            enemies.get(i).resetPosition();
        }
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorHorizontal(-60);
            }
        }
        if (super.getxPos() <= -1157) {
            super.setxPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
            return true;
        } else {
            return false;
        }
    }

    public void moveSecondaryRoomDown() {
        super.setyPos(super.getyPos() - 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorVertical(-60);
            }
        }
        if (super.getyPos() <= 0) {
            super.setyPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
        }
    }

    public boolean moveRoomDown() {
        super.setyPos(super.getyPos() - 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).bController.removeAllBullets();
            enemies.get(i).resetPosition();
        }
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorVertical(-60);
            }
        }
        if (super.getyPos() <= -801) {
            super.setyPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
            return true;
        } else {
            return false;
        }
    }

    public void displaceDoorVert(double displace) {
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).displaceDoorVertical(displace);
            }
        }
    }

    public void displaceDoorHorizontal(double displace) {
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).displaceDoorHorizontal(displace);
            }
        }
    }

    public void moveObstacles() {
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).setxPos(this.getxPos() + obstacles.get(i).getOriginalxPos());
            obstacles.get(i).setyPos(this.getyPos() + obstacles.get(i).getOriginalyPos());
        }
    }

    public void moveSecondaryRoomLeft() {
        super.setxPos(super.getxPos() + 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorHorizontal(60);
            }
        }
        if (super.getxPos() >= 0) {
            super.setxPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
        }
    }

    public boolean moveRoomLeft() {
        super.setxPos(super.getxPos() + 60);
        this.moveObstacles();
        this.moveItems();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).bController.removeAllBullets();
            enemies.get(i).resetPosition();
        }
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).moveDoorHorizontal(60);
            }
        }
        if (super.getxPos() >= 1157) {
            super.setxPos(0);
            this.defaultDoors();
            this.moveObstacles();
            this.moveItems();
            return true;
        } else {
            return false;
        }
    }

    public void buildDoors(int x, int y) {

        //up
        if (map[y - 1][x] == 1) {
            doors.set(0, (new Door("dooropenup.png", 534, 0, 89, 89)));
        }
        //right
        if (map[y][x + 1] == 1) {
            doors.set(1, (new Door("dooropenright.png", 1068, 356, 89, 89)));
        }
        //down
        if (map[y + 1][x] == 1) {
            doors.set(2, (new Door("dooropendown.png", 534, 712, 89, 89)));
        }
        //left
        if (map[y][x - 1] == 1) {
            doors.set(3, (new Door("dooropenleft.png", 0, 356, 89, 89)));
        }

        if (map[y - 1][x] == 2) {
            doors.set(0, (new LockedDoor("lockeddoorup.png", 534, 0, 89, 89)));
        }
        //right
        if (map[y][x + 1] == 2) {
            doors.set(1, (new LockedDoor("lockeddoorright.png", 1068, 356, 89, 89)));
        }
        //down
        if (map[y + 1][x] == 2) {
            doors.set(2, (new LockedDoor("lockeddoordown.png", 534, 712, 89, 89)));
        }
        //left
        if (map[y][x - 1] == 2) {
            doors.set(3, (new LockedDoor("lockeddoorleft.png", 0, 356, 89, 89)));
        }

        //up
        if (map[y - 1][x] == 8) {
            doors.set(0, (new SecretDoor("dooropenup.png", 534, 0, 89, 89)));
        }
        //right
        if (map[y][x + 1] == 8) {
            doors.set(1, (new SecretDoor("dooropenright.png", 1068, 356, 89, 89)));
        }
        //down
        if (map[y + 1][x] == 8) {
            doors.set(2, (new SecretDoor("dooropendown.png", 534, 712, 89, 89)));
        }
        //left
        if (map[y][x - 1] == 8) {
            doors.set(3, (new SecretDoor("dooropenleft.png", 0, 356, 89, 89)));
        }
        if (map[y - 1][x] == 9) {
            doors.set(0, (new BossDoor("bossdoorup.png", 534, 0, 89, 89)));
        }
        //right
        if (map[y][x + 1] == 9) {
            doors.set(1, (new BossDoor("bossdoorright.png", 1068, 356, 89, 89)));
        }
        //down
        if (map[y + 1][x] == 9) {
            doors.set(2, (new BossDoor("bossdoordown.png", 534, 712, 89, 89)));
        }
        //left
        if (map[y][x - 1] == 9) {
            doors.set(3, (new BossDoor("bossdoorleft.png", 0, 356, 89, 89)));
        }
    }

    public void collideObstaclesP(Player player) {
        for (int i = 0; i < obstacles.size(); i++) {
            if (player.collide(player, obstacles.get(i))) {
                obstacles.get(i).collideAction(player);
            }
        }
    }

    public <T extends VectorSprite> boolean collideBomb(T object1, GraphicsObject object2) {
        double xDistance, yDistance, totalDistance;

        xDistance = (float) Math.abs(object1.getxPos() + object1.getRadius() / 2 - object2.getxPos() - object2.getHeight() / 2);
        yDistance = (float) Math.abs(object1.getyPos() + object1.getRadius() / 2 - object2.getyPos() - object2.getHeight() / 2);

        totalDistance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        return (totalDistance <= object1.getRadius() / 2 + object2.getHeight() / 2);
    }

    public void itemDrops() {
        randNum = randNum(1, 3);
        if (randNum == 1) {
            items.add(new RedHeart("heart.png", 89 * 6 + 20, 89 * 4 + 20, 50, 50, 1));
        } else if (randNum == 2) {
            items.add(new KeyItem("key.png", 89 * 6 + 20, 89 * 4 + 20, 30, 30, 1));
        } else if (randNum == 3) {
            items.add(new BombItem("bomb_ui.png", 89 * 6 + 20, 89 * 4 + 20, 30, 30, 1));
        }
    }

    public void updateDoors(int i) {
        if (i == 0) {
            doors.get(2).setClosed(false);
        } else if (i == 1) {
            doors.get(3).setClosed(false);
        } else if (i == 2) {
            doors.get(0).setClosed(false);
        } else if (i == 3) {
            doors.get(1).setClosed(false);
        }
    }

    public void operate(Player player) {
        collideObstaclesP(player);

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).spawnEnemy(enemies);
            enemies.get(i).move(1);
            enemies.get(i).shoot();
            enemies.get(i).bController.increment();
            if (collideBomb(enemies.get(i), player) && !player.isDamageTaken()) {
                player.setDamageTaken(true);
                player.damaged(1);
            }
            //collision between player and enemy bullets
            for (int k = 0; k < enemies.get(i).bController.bullets.size(); k++) {
                if (enemies.get(i).bulletCollide(player, enemies.get(i).bController.bullets.get(k)) && !player.isDamageTaken()) {
                    player.setDamageTaken(true);
                    enemies.get(i).bController.removeBullet(enemies.get(i).bController.bullets.get(k));
                    player.damaged(enemies.get(i).getAttack());
                }
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

            if (enemies.get(i).getHealth() <= 0) {
                if(player.isLifeHunt() && (player.getHealth() < player.getMaxHp())){
                    //player.heal(1);
                }
                enemies.remove(i);
            }
        }
        checkDoors();
        pickUpItem(player);
        if (!enemies.isEmpty()) {
            for (int i = 0; i < doors.size(); i++) {
                if (doors.get(i) != null && doors.get(i).isOpen()) {
                    changeDoor(i);
                    doors.get(i).changeOpenState();
                }
            }
        } else if (enemies.isEmpty()) {
            for (int i = 0; i < doors.size(); i++) {
                if (doors.get(i) != null && (doors.get(i).isLocked() || doors.get(i).isClosed())) {
                } else if (doors.get(i) != null && !(doors.get(i).isOpen())) {
                    changeDoor(i);
                    endRoom = true;
                }
            }
        }
        for (int i = 0; i < obstacles.size(); i++) {
            for (int j = 0; j < player.bController.bullets.size(); j++) {
                if (obstacles.get(i).bulletCollision(player.bController.bullets.get(j))) {
                    player.bController.removeBullet(player.bController.bullets.get(j));
                }
            }
            for (int k = 0; k < enemies.size(); k++) {
                for (int j = 0; j < enemies.get(k).bController.bullets.size(); j++) {
                    if (obstacles.get(i).bulletCollision(enemies.get(k).bController.bullets.get(j))) {
                        enemies.get(k).bController.removeBullet(enemies.get(k).bController.bullets.get(j));
                    }
                }
                enemies.get(k).collideWObstacle(obstacles.get(i));
            }
        }
        if (endRoom) {
            if (viableDrops) {
                itemDrops();
                //System.out.println("ja");
                viableDrops = false;
            }

            //System.out.println("drops");
            endRoom = false;
        }
        if (!player.bController.bombs.isEmpty()) {
            for (int i = 0; i < obstacles.size(); i++) {
                if (collideBomb(player.bController.bombs.get(0), obstacles.get(i)) && obstacles.get(i).isRock() && player.bController.bombs.get(0).isExplode()) {
                    obstacles.remove(i);
                    //System.out.println("collide");
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                if (collideBomb(player.bController.bombs.get(0), enemies.get(i)) && player.bController.bombs.get(0).isExplode()) {
                    enemies.get(i).damaged(40);
                }
            }
            if (collideBomb(player.bController.bombs.get(0), player) && player.bController.bombs.get(0).isExplode() && !player.isDamageTaken()) {
                player.damaged(2);
                player.setDamageTaken(true);
            }
            for (int i = 0; i < doors.size(); i++) {
                if (doors.get(i) != null && collideBomb(player.bController.bombs.get(0), doors.get(i)) && player.bController.bombs.get(0).isExplode()) {
                    doors.get(i).setClosed(false);
                    revealed = true;
                }
            }
        }
    }

    public void pickUpItem(Player p) {
        for (int i = 0; i < items.size(); i++) {
            if (collideBomb(items.get(i), p)) {
                pickupItem = items.get(i).collideAction(p);
                if (pickupItem) {
                    items.remove(i);
                }
            }
        }
    }

    public void checkDoors() {
        if (!enemies.isEmpty()) {
            viableDrops = true;
            for (int i = 0; i < doors.size(); i++) {
                if (doors.get(i) != null && doors.get(i).isOpen()) {
                    changeDoor(i);
                    doors.get(i).changeOpenState();
                }
            }
        } else if (enemies.isEmpty()) {
            for (int i = 0; i < doors.size(); i++) {
                if (doors.get(i) != null && (doors.get(i).isLocked() || doors.get(i).isClosed())) {
                } else if (doors.get(i) != null && !(doors.get(i).isOpen())) {
                    changeDoor(i);
                    endRoom = true;
                }
            }
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).draw(g);
        }

        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i) != null) {
                doors.get(i).draw(g);
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
            enemies.get(i).bController.draw(g);
        }

        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(g);
        }

    }

    public void moveItems() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setxPos(this.getxPos() + items.get(i).getOriginalxPos());
            items.get(i).setyPos(this.getyPos() + items.get(i).getOriginalyPos());
        }
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEndRoom(boolean endRoom) {
        this.endRoom = endRoom;
    }

    public boolean isEndRoom() {
        return endRoom;
    }

    public void setSeenRoom(boolean seenRoom) {
        this.seenRoom = seenRoom;
    }

    public boolean isSeenRoom() {
        return seenRoom;
    }

}
