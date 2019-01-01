
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private MapGenerator gen = new MapGenerator();
    private boolean start;
    private Player player;
    private Floor testFloor;
    private ArrayList<Floor> floors;
    private boolean story, begin;
    private GameMenu menu;
    private int screenShift;
    private double scaleX = 1;
    private double scaleY = 1;
    private static final int gameWidth = 1157, gameHeight = 801;
    private static Boolean up, right, left, down, mouseClicked, bulletUp, bulletDown, bulletRight, bulletLeft;
    private long desiredFPS = 60;
    private long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;
    private boolean generateNew, dying, pause, gameFinished;
    private boolean running = true;
    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private int floorNum;
    private int opac;
    private int mouseX, mouseY, opacity;
    private boolean mousePressed, gMode;

    // Constructor Main() acts as StartGame()
    public Main() {
        frame = new JFrame("Trek");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(gameWidth, gameHeight));
        panel.setLayout(null);
        canvas = new Canvas();
        canvas.setBounds(0, 0, gameWidth, gameHeight);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        floorNum = 1;
        gMode = false;
        opacity = 0;
        dying = false;
        gameFinished = false;
        pause = false;
        story = true;
        begin = false;
        //OBJECTS
        menu = new GameMenu(gameWidth, gameHeight);
        player = new Player("playerspritesheet.png", 150, 150, 66, 58, 30);
        testFloor = new Floor(7, player, floorNum);
        floors = new ArrayList();
        floors.add(testFloor);
        generateNew = false;
        mouseX = 0;
        mouseY = 0;
        mousePressed = false;
        opac = 0;
        start = true;

        //player movement
        up = false;
        down = false;
        right = false;
        left = false;

        //player bullet movement
        bulletUp = false;
        bulletDown = false;
        bulletRight = false;
        bulletLeft = false;
    }

    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    // run() tracks our game speed
    public void run() {

        long beginLoopTime;
        long endLoopTime;
        long currentUpdateTime = System.nanoTime();
        long lastUpdateTime;
        long deltaLoop;

        while (running) {
            beginLoopTime = System.nanoTime();

            graphicsBuffer();

            lastUpdateTime = currentUpdateTime;
            currentUpdateTime = System.nanoTime();
            actionPreformed();

            endLoopTime = System.nanoTime();
            deltaLoop = endLoopTime - beginLoopTime;

            if (deltaLoop > desiredDeltaLoop) {
                //Do nothing. We are already late.
            } else {
                try {
                    Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
                } catch (InterruptedException e) {
                }
            }
        }
    }

    // dont touch this idk
    private void graphicsBuffer() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, gameWidth, gameHeight);
        paint(g);
        g.dispose();
        bufferStrategy.show();
    }

    // acts as our actionpreformed
    protected void actionPreformed() {
        if (OSValidator.isMac()) {
            screenShift = 22;
            scaleX = (double) frame.getWidth() / gameWidth;
            scaleY = (double) (frame.getHeight() - (screenShift * (1 - frame.getHeight() / gameHeight))) / gameHeight;
        } else if (OSValidator.isWindows()) {
            screenShift = 22;

        }
        if (menu.isRunMenu()) {
            menu.runMenu(mouseX, mouseY, mousePressed);
        } else if (!menu.isRunMenu()) {

            //if (!menu.isStory()) {
            //    start = true;
            //}
            if (start) {

                floors.get(0).generateFloor();
                start = false;
            }
            player.changeHeadState();
            //CONTINUOUSLY RUNNING METHODS
            if (!floors.get(0).isIsDone() && !start) {
                floors.get(0).changeCurrentRoom();
            }

            else if (dying) {
                opacity++;
                if (opacity > 200) {
                    dying = false;
                    menu.setRunMenu(true);
                    player = new Player("playerspritesheet.png", 150, 150, 66, 58, 30);
                    floors.set(0, new Floor(8, player, 1));
                    floors.get(0).generateFloor();
                    floorNum = 1;
                }
            } else if (gameFinished) {
                opacity++;
                if (opacity > 200) {
                    dying = false;
                    gameFinished = false;
                    menu.setRunMenu(true);
                    player = new Player("playerspritesheet.png", 150, 150, 66, 58, 30);
                    floors.set(0, new Floor(8, player, 1));
                    floors.get(0).generateFloor();
                    floorNum = 1;
                }
            }
            
            else if (pause) {

            } else {
                if (gMode) {

                    player.setHealth(6);
                    player.setBombs(100);
                    player.setKeys(100);
                    player.bController.setInitCoolDown(10);
                }

                //if (!floors.get(0).isIsDone() && !start) {
                floors.get(0).changeCurrentRoom();
                //}
                //PLAYER MOVEMENT AND ANIMATION
                player.move(0);

                if (up || right || down || left) {
                    player.animate();
                    player.setMoving(true);
                } else if (!up && !right && !down && !left) {
                    player.stop();
                }

                //this is used for the player to be decelerating while keys are not pressed but speed is not 0
                player.moveX(player.getxSpeed());
                player.moveY(player.getySpeed());

                //PLAYER SHOOTING
                if (bulletRight || bulletDown || bulletUp || bulletLeft) {
                    player.shoot();
                    player.setShooting(true);
                } else if (!bulletRight && !bulletDown && !bulletUp && !bulletLeft) {
                    player.setShooting(false);
                }

                floors.get(0).operateRooms();
                if (player.getHealth() <= 0) {
                    dying = true;
                    opacity = 0;
                }
                if (floors.get(0).isFloorOver() && floorNum < 5) {
                    floorNum++;
                    //System.out.println(floorNum);
                    floors.add(new Floor(7+floorNum, player, floorNum));
                    floors.get(1).generateFloor();
                    floors.remove(floors.get(0));
                    
                }
                if (floorNum == 5) {
                    player.bController.removeAllBullets();
                    gameFinished = true;
                }

                player.bController.increment();

            }
        }
    }

    // acts as our paint
    protected void paint(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transformation = new AffineTransform();
        //transformation.translate(0, screenShift);
        transformation.scale(scaleX, scaleY);
        g2d.setTransform(transformation);

        if (menu.isRunMenu()) {
            menu.drawMenu(g);

        } else if (menu.isStory()) {
            menu.drawStoryScreen(g, new Font("Chiller", Font.BOLD, 20), mouseX, mouseY, mousePressed);
        } else if (pause) {
            menu.drawPauseScreen(g, new Font("Chiller", Font.BOLD, 80));
        } else if (!menu.isRunMenu()) {
            //DRAWING SPRITES
            //g.setColor(Color.BLACK);

            if (!start) {
                floors.get(0).draw(g);
            }
            player.draw(g);
            player.bController.draw(g);

            //g.drawString("" + player.getHealth(), 500, 100);
            //g.drawString("" + player.getxPos(), 500, 150);
            //g.drawString("" + player.getyPos(), 500, 165);
            if (dying) {
                g.setColor(new Color(0, 0, 0, opacity));
                g.fillRect(0, 0, gameWidth, gameHeight);
                g.setColor(new Color(255, 255, 255, opacity + 50));
                g.setFont(new Font("Chiller", Font.BOLD, 80));
                g.drawString("Your Trek is over", 200, 200);
                
            }
            if (gameFinished) {
                g.setColor(new Color(0, 0, 0, opacity));
                g.fillRect(0, 0, gameWidth, gameHeight);
                g.setColor(new Color(255, 255, 255, opacity + 50));
                g.setFont(new Font("Chiller", Font.BOLD, 60));
                g.drawString("You've completed The Trek", 150, 200);
            }
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            up = true;
            player.setMoveUp(true);
        }

        if (key == KeyEvent.VK_A) {
            left = true;
            player.setMoveLeft(true);
        }

        if (key == KeyEvent.VK_D) {
            right = true;
            player.setMoveRight(true);

        }

        if (key == KeyEvent.VK_S) {
            down = true;
            player.setMoveDown(true);
        }
        if (key == KeyEvent.VK_RIGHT) {
            bulletRight = true;
            player.setShootRight(true);
        }
        if (key == KeyEvent.VK_LEFT) {
            bulletLeft = true;
            player.setShootLeft(true);

        }
        if (key == KeyEvent.VK_UP) {
            bulletUp = true;
            player.setShootUp(true);
        }
        if (key == KeyEvent.VK_DOWN) {
            bulletDown = true;
            player.setShootDown(true);
        }
//        if (key == KeyEvent.VK_UP) {
//            up = true;
//            player.setMoveUp(true);
//        }
//
//        if (key == KeyEvent.VK_LEFT) {
//            left = true;
//            player.setMoveLeft(true);
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            right = true;
//            player.setMoveRight(true);
//
//        }
//
//        if (key == KeyEvent.VK_DOWN) {
//            down = true;
//            player.setMoveDown(true);
//        }
//        if (key == KeyEvent.VK_D) {
//            bulletRight = true;
//            player.setShootRight(true);
//        }
//        if (key == KeyEvent.VK_A) {
//            bulletLeft = true;
//            player.setShootLeft(true);
//
//        }
//        if (key == KeyEvent.VK_W) {
//            bulletUp = true;
//            player.setShootUp(true);
//        }
//        if (key == KeyEvent.VK_S) {
//            bulletDown = true;
//            player.setShootDown(true);
//        }
        if (key == KeyEvent.VK_E) {
            player.plantBomb();
        }
        if (key == KeyEvent.VK_P) {
            pause = !pause;
        }
        if (key == KeyEvent.VK_K) {
            floors.get(0).killAll(true);
        }
        if (key == KeyEvent.VK_G) {
            gMode = !gMode;
            if (gMode) {
                player.setTempCoolDown(player.bController.getInitCoolDown());
            } else {
                player.bController.setInitCoolDown(player.getTempCoolDown());
            }
        }
        //remove later
        if(key == KeyEvent.VK_O){
            player.setKeys(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            up = false;
            player.setMoveUp(false);
        }

        if (key == KeyEvent.VK_A) {
            left = false;
            player.setMoveLeft(false);

        }

        if (key == KeyEvent.VK_D) {
            right = false;
            player.setMoveRight(false);

        }

        if (key == KeyEvent.VK_S) {
            down = false;
            player.setMoveDown(false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            bulletRight = false;
            player.setShootRight(false);
        }
        if (key == KeyEvent.VK_LEFT) {
            bulletLeft = false;
            player.setShootLeft(false);
        }
        if (key == KeyEvent.VK_UP) {
            bulletUp = false;
            player.setShootUp(false);
        }
        if (key == KeyEvent.VK_DOWN) {
            bulletDown = false;
            player.setShootDown(false);
        }
//        if (key == KeyEvent.VK_UP) {
//            up = false;
//            player.setMoveUp(false);
//        }
//
//        if (key == KeyEvent.VK_LEFT) {
//            left = false;
//            player.setMoveLeft(false);
//
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            right = false;
//            player.setMoveRight(false);
//
//        }
//
//        if (key == KeyEvent.VK_DOWN) {
//            down = false;
//            player.setMoveDown(false);
//        }
//        if (key == KeyEvent.VK_D) {
//            bulletRight = false;
//            player.setShootRight(false);
//        }
//        if (key == KeyEvent.VK_A) {
//            bulletLeft = false;
//            player.setShootLeft(false);
//        }
//        if (key == KeyEvent.VK_W) {
//            bulletUp = false;
//            player.setShootUp(false);
//        }
//        if (key == KeyEvent.VK_S) {
//            bulletDown = false;
//            player.setShootDown(false);
//        }
    }
}
