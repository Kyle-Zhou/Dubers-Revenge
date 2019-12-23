import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;

class GamePanel extends JPanel {
  
  static int maxX, maxY, GridToScreenRatio;
  private Human duber;
  private Spawner spawner;
  private Camera scroll;
  private Mammal[] entities;
  private Projectile[] projectiles;
  private Mouse listener;
  
  Map map = new Map();
  
  GamePanel(Human duber, Spawner spawner, Camera scroll, Mammal[] entities, Projectile[] projectiles) {
    this.duber = duber;
    this.spawner = spawner;
    this.scroll = scroll;
    this.entities = entities;
    this.projectiles = projectiles;
    listener = new Mouse(duber, spawner);
    addKeyListener(new Keys(duber));
  }
  
  public void paintComponent(Graphics g) {
    
    super.paintComponent(g); //required
    setDoubleBuffered(true);
    
    Graphics2D g2d = (Graphics2D)g;
    
    Image crackedTile = Toolkit.getDefaultToolkit().getImage("crackedTile.png");
    Image brokenTile = Toolkit.getDefaultToolkit().getImage("brokenTile.png");
    Image tile = Toolkit.getDefaultToolkit().getImage("tile.png");
    
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    
    this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = (maxY) / (map.getMap().length + 1);
    
    g2d.translate(scroll.getXCamera(), scroll.getYCamera());
    for(int i = 0; i < 25; i++) {
      for(int j = 0; j < 25; j++) {
        if(map.getMap()[i][j] == 1) {
          g.drawImage(tile, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio, GridToScreenRatio, this);
        } else if (map.getMap()[i][j] == 2){
          g.drawImage(tile, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio, GridToScreenRatio, this);
          g.drawImage(crackedTile, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio, GridToScreenRatio, this);
        } else if (map.getMap()[i][j] == 3) {
          g.drawImage(tile, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio, GridToScreenRatio, this);
          g.drawImage(brokenTile, j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio, GridToScreenRatio, this);
        }
      }
    }
    
    for (int i = 0; i < entities.length; i++) {
      if (entities[i] instanceof Zombie) {
        ((Zombie)entities[i]).draw(g);
        if (((Zombie)entities[i]).getCooldown() > 0) {
          ((Zombie)entities[i]).setCooldown(((Zombie)entities[i]).getCooldown() - 1);
        }
      }
      projectiles = spawner.getProjectiles();
      if (projectiles[i] instanceof Projectile) {
        projectiles[i].draw(g);
      }
    }
    
    if (duber != null) {
      duber.draw(g);
      duber.move();
      for (int i = 0; i < entities.length; i++) {
        if (entities[i] instanceof Zombie) {
          if (entities[i].hitbox.intersects(duber.hitbox)) {
            entities[i].xDirection = 0;
            entities[i].yDirection = 0;
            if (((Zombie)entities[i]).getCooldown() == 0) {
              ((Zombie)entities[i]).attack(duber);
            }
          } else {
            ((Zombie)entities[i]).move(duber);
          }
        }
        if (projectiles[i] instanceof Projectile) {
          projectiles[i].travel(duber, listener.getXCursor(), listener.getYCursor());
        }
      }
      if (duber.getHealth() <= 0) {
        duber = null;
      }
    }
    g2d.translate(-scroll.getXCamera(), -scroll.getYCamera());
  }
  
}