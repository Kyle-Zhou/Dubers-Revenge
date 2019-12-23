import javax.swing.JFrame;
import java.awt.Toolkit;

class Game extends JFrame {

  //class variable (non-static)
  static int maxX, maxY, GridToScreenRatio;
  static double x, y;
  static GamePanel gamePanel;
  public Mammal[] entities;  
  private Projectile[] projectiles;
  private Human duber;
  private Spawner spawner;
  
  Game(String title) {

    super(title);    
    projectiles = new Projectile[255];
    entities = new Mammal[255];
    spawner = new Spawner(entities, projectiles);
    spawner.spawnHuman(Toolkit.getDefaultToolkit().getScreenSize().width / 2, 
                       Toolkit.getDefaultToolkit().getScreenSize().height / 2, 
                       5000, 5, 2);
    
    int dog = 100; //temporary variable for changing coordinates; REMOVE LATER
    for (int i = 0; i < 5; i++) { //zombie spawns will not occur here; MOVE LATER
      spawner.spawnZombie(dog, 200, 100, 10, 1);
      dog += 75;
    }
    entities = spawner.getEntities();
    duber = ((Human)entities[0]);
    //projectiles = spawner.getProjectiles();
    
    
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    gamePanel = new GamePanel(duber, entities, projectiles, spawner);
    this.add(new GamePanel(duber, entities, projectiles, spawner));

    Keys keyListener = new Keys(duber);
    this.addKeyListener(keyListener);

    Mouse mouseListener = new Mouse(duber, spawner, projectiles);
    this.addMouseListener(mouseListener);

    this.requestFocusInWindow(); //make sure the frame has focus   

    this.setVisible(true);

    this.add(new GamePanel(duber, entities, projectiles, spawner));
    
    //Start the game loop in a separate thread
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop
    t.start();

  }

  
  public void refresh() {
    this.repaint();
  }

  //the main gameloop - this is where the game state is updated
  public void animate() {

    while(true){
      this.x = (Math.random()*1024);  //update coords
      this.y = (Math.random()*768);
      try{ Thread.sleep(500);} catch (Exception exc){}  //delay
      this.repaint();
    }
  }
}