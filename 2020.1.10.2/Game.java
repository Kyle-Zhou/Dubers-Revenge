import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;

class Game extends JFrame {

  //class variable (non-static)
  static int maxX, maxY, GridToScreenRatio;
  static double x, y;
  private static GamePanel gamePanel;
  private Entity[] entities;
  private Human duber;
  private Random random = new Random();
  private Spawner spawner;
  private Camera scroll;
  private HeadUpDisplay hud;
  private Shop shop;
  private Weapon[] weapons;
  private boolean zombiesAlive;

  Game(String title) {
    super(title);
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    cursor();
    entities = new Entity[1024];
    weapons = new Weapon[2];
    spawner = new Spawner(entities, weapons, shop);
    scroll = new Camera(maxX, maxY);    
    hud = new HeadUpDisplay(scroll, maxX, maxY, spawner, shop);
    shop = new Shop(maxX, maxY, scroll, hud);
    spawner.spawnHuman(900, 600, 100, 25, 3); //change coords
    spawner.spawnNorth(0, 0);
    spawner.spawnSouth(64, 4500 - 163);
    spawner.spawnCanister(950, 650);
    entities = spawner.getEntities();
    duber = ((Human)entities[0]);

    spawner.addPistol(duber);

    //spawner.addShotgun(duber);
    
    Keys keys = new Keys(duber, weapons, spawner, hud, entities, shop);
    this.addKeyListener(keys);
    Mouse mouse = new Mouse(duber, spawner, scroll, hud, shop);
    this.addMouseListener(mouse);
    
    // Set the frame to full screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    gamePanel = new GamePanel(duber, spawner, scroll, hud, entities, weapons, shop);
    this.add(new GamePanel(duber, spawner, scroll, hud, entities, weapons, shop));

    this.requestFocusInWindow(); //make sure the frame has focus

    this.setVisible(true);

    this.add(new GamePanel(duber, spawner, scroll, hud, entities, weapons, shop));

    //Start the game loop in a separate thread
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop
    t.start();
  }


  public void refresh() {
    this.repaint();
    scroll.xFollow(duber, Toolkit.getDefaultToolkit().getScreenSize().width / 2);
    scroll.yFollow(duber, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
    hud.update(scroll, duber);

  }

  //the main gameloop - this is where the game state is updated
  public void animate() {
    while(true){
      this.x = (Math.random()*1024);  //update coords
      this.y = (Math.random()*768);
      try{Thread.sleep(500);} catch (Exception exc){}  //delay
      this.repaint();
    }
  }

  public void cursor(){
    setCursor(Cursor.getDefaultCursor());
    Image crosshair = Toolkit.getDefaultToolkit().getImage("crosshair.png");
    Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(crosshair, new Point(this.getX(), this.getY()), "img");
    this.setCursor(c);
  }
}