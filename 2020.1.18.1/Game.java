import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Game extends JFrame {

  //class variable (non-static)
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

  Game(String title) {
    super(title);
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    cursor();
    entities = new Entity[2048];
    weapons = new Weapon[5];
    spawner = new Spawner(entities, weapons, shop);
    scroll = new Camera(maxX, maxY);    
    hud = new HeadUpDisplay(scroll, maxX, maxY, spawner, shop, weapons, counter);
    shop = new Shop(maxX, maxY, scroll, hud);
    counter = new Counter();
    spawner.spawnHuman(900, 600, 100, 25, 3); //change coords
    spawner.spawnNorth(0, 0);
    spawner.spawnSouth(64, 4500 - 163);
    random = new Random();
    for (int i = 3; i < 54; i++) {
      spawner.spawnCanister((random.nextInt(4344) + 64), (random.nextInt(4327) + 150));
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
    duber = ((Human)entities[0]);

    spawner.addPistol(duber);

    Keys keys = new Keys(duber, weapons, spawner, hud, entities, shop, counter);
    this.addKeyListener(keys);
    Mouse mouse = new Mouse(duber, spawner, scroll, hud, shop);
    this.addMouseListener(mouse);
    
    // Set the frame to full screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    gamePanel = new GamePanel(duber, spawner, scroll, hud, entities, weapons, shop, counter);

    this.requestFocusInWindow(); //make sure the frame has focus

    this.add(gamePanel);

    this.setVisible(true);

  }

  public void refresh() {
    this.repaint();
    scroll.xFollow(duber, Toolkit.getDefaultToolkit().getScreenSize().width / 2);
    scroll.yFollow(duber, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
    hud.update(scroll, duber);

  }


  public void cursor() {
    setCursor(Cursor.getDefaultCursor());
    Image crosshair = Toolkit.getDefaultToolkit().getImage("crosshair.png");
    Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(crosshair, new Point(this.getX(), this.getY()), "img");
    this.setCursor(c);
  }

  public void ending() {
    gamePanel.repaint();

  }
}