import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{

  private Human duber;
  Keys(Human duber){
    this.duber=duber;
  }
  
  public void keyTyped(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {

    if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //If 'A' is pressed
      duber.xDirection = -duber.getSpeed();
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {  //If 'S' is pressed
      duber.yDirection = duber.getSpeed();
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //If 'D' is pressed
      duber.xDirection = duber.getSpeed();
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  //If 'W' is pressed
      duber.yDirection = -duber.getSpeed();
    }
  }
    
  public void keyReleased(KeyEvent e) {
      
    if(e.getKeyChar() == 'a' ){    //Good time to use a Switch statement
      duber.xDirection=0;
    } else if(e.getKeyChar() == 's' ){
      duber.yDirection=0;
    } else if(e.getKeyChar() == 'd' ){
      duber.xDirection=0;
    } else if(e.getKeyChar() == 'w' ){
      duber.yDirection=0;
    }  //note - would be better to make player class and pass in map, test movement in there
      
  }
}