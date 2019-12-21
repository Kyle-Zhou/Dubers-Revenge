//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class GamePanel extends JPanel {
         
  static int maxX, maxY, GridToScreenRatio;
  private Human duber;
  
  Zombie zombie = new Zombie(100,100, 100, 5,2);
  Map map = new Map();
  
  GamePanel(Human duber) {
    this.duber = duber;
    addMouseListener(new MyMouseListener());
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
    zombie.draw(g);
    duber.draw(g);
    duber.move();

     
    if ((zombie.xCord >= duber.xCord) && (zombie.yCord >= duber.yCord)) { //Replace coordinates with Human coordinates.
      zombie.xDirection = -zombie.speed;
      zombie.yDirection = -zombie.speed;
      zombie.move();
    } else if ((zombie.xCord <= duber.xCord) && (zombie.yCord >= duber.yCord)) {
        zombie.xDirection = zombie.speed;
        zombie.yDirection = -zombie.speed;
        zombie.move();
    } else if ((zombie.xCord <= duber.xCord) && (zombie.yCord <= duber.yCord)) {
        zombie.xDirection = zombie.speed;
        zombie.yDirection = zombie.speed;
        zombie.move();
    } else if ((zombie.xCord >= duber.xCord) && (zombie.yCord <= duber.yCord)) {
        zombie.xDirection = -zombie.speed;
        zombie.yDirection = zombie.speed;
        zombie.move();
      } else if ((zombie.xCord == duber.xCord) && (zombie.yCord == duber.yCord)) { //Replace with Human hitbox collision.
        zombie.attack();
      }

    }
    
  }