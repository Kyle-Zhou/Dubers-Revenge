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

class Game extends JFrame {

  //class variable (non-static)
  static int maxX, maxY, GridToScreenRatio;
  static double x, y;
  static GamePanel gamePanel;
  
//  
//  Zombie zombie = new Zombie(100, 100, 100, 50, 1);
  
  Game(String title) {

    
    super(title);
    // Set the frame to full screen 
    
    
      Human duber = new Human(Toolkit.getDefaultToolkit().getScreenSize().width / 2, 
                          Toolkit.getDefaultToolkit().getScreenSize().height / 2, 
                          100, 5, 2);
    
    
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    gamePanel = new GamePanel(duber);
    this.add(new GamePanel(duber));

    MyKeyListener keyListener = new MyKeyListener(duber);
    this.addKeyListener(keyListener);

    MyMouseListener mouseListener = new MyMouseListener();
    this.addMouseListener(mouseListener);

    this.requestFocusInWindow(); //make sure the frame has focus   

    this.setVisible(true);

    this.add(new GamePanel(duber));
    
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