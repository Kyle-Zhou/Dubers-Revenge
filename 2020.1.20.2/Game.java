import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;

/**
* [Game.java]
* intializes every aspect of the game
* extends JFrame
* @authors Kyle Zhou, Ethan Zhang, Eric Miao
* Kyle Zhou - General Edits, Cursor Assets
* Ethan Zhang - General Edits, Formatting
* Eric Miao - General Edits, Overall Logics
**/

class Game extends JFrame {
  
  static int maxX, maxY, GridToScreenRatio;
  static double x, y;
  private static GamePanel gamePanel;
  private Entity[] entities;
  private Human duber;
  private Random random;
  private Spawner spawner;
  private Camera scroll;
  private HeadUpDisplay hud;
  private Shop shop;
  private Weapon[] weapons;
  private Counter counter;
  private boolean zombiesAlive;

  /**
  * Game constructor 
  * inherits a string as the title
  * sets values for all aspects of the game
  */
  Game(String title) {
    super(title);
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    cursor();
    entities = new Entity[2048];
    weapons = new Weapon[5];
    spawner = new Spawner(entities, weapons, shop);
    scroll = new Camera(maxX, maxY);
    counter = new Counter();
    shop = new Shop(maxX, maxY, scroll, hud);
    hud = new HeadUpDisplay(scroll, maxX, maxY, spawner, shop, weapons, counter);
    spawner.spawnHuman(680, 150, 100, 25, 3);
    spawner.spawnNorth(0, 0);
    spawner.spawnSouth(64, 4500 - 163);
    
    random = new Random();
    for (int i = 3; i < 54; i++) {
      spawner.spawnCanister((random.nextInt(4308) + 64), (random.nextInt(4327) + 150));
      for (int j = 3; j < 54; j++) {
        if ((entities[j] != null) && 
            (((Canister)entities[i]).getSpawnHitbox().intersects(((Canister)entities[j]).getSpawnHitbox())) && 
            (i != j)) {
          entities[i] = null;
          i--;
          j += 54;
        }
      }
    }
    
    entities = spawner.getEntities();
    duber = ((Human)entities[0]); //always intialize duber at index 0 of the entities array
    spawner.addPistol(duber);
    
    Keys keys = new Keys(duber, weapons, spawner, hud, entities, shop, counter); 
    this.addKeyListener(keys);
    Mouse mouse = new Mouse(duber, spawner, scroll, hud, shop, weapons);
    this.addMouseListener(mouse);
    
    // Set the frame to full screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    gamePanel = new GamePanel(duber, spawner, scroll, hud, entities, weapons, shop, counter);
    this.add(gamePanel);

    this.requestFocusInWindow(); //make sure the frame has focus

    this.setVisible(true);

    this.add(new GamePanel(duber, spawner, scroll, hud, entities, weapons, shop, counter));

    //Start the game loop in a separate thread
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop
    t.start();
  }

  /**
  * refresh 
  * reloads the game and updates the visual aspects
  */
  public void refresh() {
    this.repaint();
    scroll.xFollow(duber, Toolkit.getDefaultToolkit().getScreenSize().width / 2); //camera update with every refresh
    scroll.yFollow(duber, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
    hud.update(scroll, duber); //head up display update with every refresh
  }

  /**
  * animate 
  * the main game loop
  */
  public void animate() {
    while(true){
      this.x = (Math.random()*1024);  //update coords
      this.y = (Math.random()*768);
      try{Thread.sleep(5);} catch (Exception exc){}  //delay
      this.repaint();
    }
  }
  
  /**
  * cursor 
  * sets a crosshair image to the cursor
  */
  public void cursor(){
    setCursor(Cursor.getDefaultCursor());
    Image crosshair = Toolkit.getDefaultToolkit().getImage("crosshair.png");
    Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(crosshair, new Point(this.getX(), this.getY()), "img");
    this.setCursor(c);
  }
  
}
