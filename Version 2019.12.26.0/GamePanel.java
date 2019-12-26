import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;

class GamePanel extends JPanel {
  
  static int maxX, maxY, GridToScreenRatio;
  private Human duber;
  private Wall north, east, south, west;
  private Spawner spawner;
  private Camera scroll;
  private Entity[] entities;
  private Mouse listener;
  private int vitality;
  
  Map map = new Map();
  
  GamePanel(Human duber, Spawner spawner, Camera scroll, Entity[] entities) {
    this.duber = duber;
    this.spawner = spawner;
    this.scroll = scroll;
    this.entities = entities;
    north = (Wall)entities[1];
    east = (Wall)entities[2];
    south = (Wall)entities[3];
    west = (Wall)entities[4];
    listener = new Mouse(duber, spawner, scroll);
    addKeyListener(new Keys(duber));
    vitality = duber.getHealth();
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
    for(int i = 0; i < 75; i++) {
      for(int j = 0; j < 75; j++) {
        if(map.getMap()[i][j] == 1) {
          g.drawImage(tile, j * 60, i * 60, 60, 60, this);
        } else if (map.getMap()[i][j] == 2){
          g.drawImage(crackedTile, j * 60, i * 60, 60, 60, this);
        } else if (map.getMap()[i][j] == 3) {
          g.drawImage(brokenTile, j * 60, i * 60, 60, 60, this);
        }
      }
    }
    
    north.draw(g);
    east.draw(g);
    west.draw(g);
    
    for (int i = 0; i < entities.length; i++) {
      if (entities[i] instanceof Zombie) {
        ((Zombie)entities[i]).draw(g);
        if (((Zombie)entities[i]).getCooldown() > 0) {
          ((Zombie)entities[i]).setCooldown(((Zombie)entities[i]).getCooldown() - 1);
        }
      } else if (entities[i] instanceof Projectile) {
        entities[i].draw(g);
      }
    }
    
    if (duber != null) {
      duber.draw(g);
      duber.move();
      for (int i = 0; i < entities.length; i++) {
        if (entities[i] instanceof Zombie) {
          if (entities[i].getHitbox().intersects(duber.getHitbox())) {
            ((Zombie)entities[i]).setxDirection(0);
            ((Zombie)entities[i]).setyDirection(0);
            if (((Zombie)entities[i]).getCooldown() == 0) {
              ((Zombie)entities[i]).attack(duber);
            }
          } else {
            ((Zombie)entities[i]).move(duber);
          }
        }
        if (entities[i] instanceof Projectile) {
          ((Projectile)entities[i]).travel(duber, listener.getXCursor(), listener.getYCursor());
          for (int j = 0; j < entities.length; j++) {
            if ((entities[j] instanceof Zombie) && (entities[i].getHitbox().intersects(entities[j].getHitbox()))) {
              ((Zombie)entities[j]).setHealth(((Zombie)entities[j]).getHealth() - duber.getDamage());
              entities[i] = null;
              if (((Zombie)entities[j]).getHealth() <= 0) {
                entities[j] = null;
              }
              j += entities.length;
            }
          }
        }
      }
      
      south.draw(g);
      
      if (vitality > duber.getHealth()) {
        vitality--;
      }
      int ratio = 3; //create a HUD class
      g.setColor(Color.RED);
      g.fillRect((int)-scroll.getXCamera() + 50, (int)-scroll.getYCamera() + 30, vitality * ratio, 25);
      g.setColor(Color.BLACK);
      g.drawRect((int)-scroll.getXCamera() + 50, (int)-scroll.getYCamera() + 30, 100 * ratio, 25);
      
      if (duber.getHealth() <= 0) {
        duber = null;
        entities[0] = null;
      }
    }
    g2d.translate(-scroll.getXCamera(), -scroll.getYCamera());
  }
}