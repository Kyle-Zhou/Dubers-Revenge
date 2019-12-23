import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Mouse implements MouseListener{
  
  private Human duber;
  private Spawner spawner;
  private static int xCursor, yCursor;
  
  Mouse(Human duber, Spawner spawner){
    this.duber = duber;
    this.spawner = spawner;
  }
  
  public void mouseClicked(MouseEvent e) {
  }
  
  public void mousePressed(MouseEvent e) {
    xCursor = e.getX();
    yCursor = e.getY();
    if (duber != null) {
      spawner.fire(duber);
    }
  }
  
  public void mouseReleased(MouseEvent e) {
  }
  
  public void mouseEntered(MouseEvent e) {
  }
  
  public void mouseExited(MouseEvent e) {
  }
  
  public int getXCursor() {
    return xCursor;
  }
  
  public int getYCursor() {
    return yCursor;
  }
} 