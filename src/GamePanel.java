import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

class GamePanel extends JPanel {
         
  static int maxX, maxY, GridToScreenRatio;
  private Human duber;
  private Mammal[] entities;
  
  Map map = new Map();
  
  GamePanel(Human duber, Mammal[] entities) {
    this.duber = duber;
    this.entities = entities;
    addMouseListener(new MyMouseListener(duber));
    addKeyListener(new MyKeyListener(duber));
  }

  public void paintComponent(Graphics g) {
      
    super.paintComponent(g); //required
    setDoubleBuffered(true);

    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    
    this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = (maxY) / (map.getMap().length + 1);

    for(int i = 0; i < 25; i++) {
      for(int j = 0; j < 25; j++) {
        if(map.getMap()[i][j] == 1) {
          g.setColor(Color.green);
          g.fillRect(j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
        } else if (map.getMap()[i][j] == 2){//if(map[i][j] == 2) {
          g.setColor(Color.cyan);
          g.fillRect(j * GridToScreenRatio, i * GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
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
        }
        if (duber.getHealth() <= 0) {
          duber = null;
        }
      }
      


     


    }
    
  }