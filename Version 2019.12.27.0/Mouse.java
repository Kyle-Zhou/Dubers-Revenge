import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Mouse implements MouseListener {
  
  private Human duber;
  private Spawner spawner;
  private Camera scroll;
  private Entity[] entities;
  private static int xCursor, yCursor;
  
  Mouse(Human duber, Spawner spawner, Camera scroll) {
    this.duber = duber;
    this.spawner = spawner;
    this.scroll = scroll;
  }
  
  public void mouseClicked(MouseEvent e) {
  }
  
  public void mousePressed(MouseEvent e) {
    entities = spawner.getEntities();
    duber = (Human)entities[0];
    if (duber != null) {
      xCursor = e.getX() - (int)scroll.getXCamera();
      yCursor = e.getY() - (int)scroll.getYCamera();
      spawner.fireBullet(duber);
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