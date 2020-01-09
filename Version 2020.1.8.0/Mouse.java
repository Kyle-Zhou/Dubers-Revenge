import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.MouseInfo;

public class Mouse implements MouseListener, MouseMotionListener {

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
      if (spawner.getCurrentWeapon() == 0) {
        spawner.fireBullet(duber);
      } else if (spawner.getCurrentWeapon() == 1) {
        spawner.fireShell(duber);
      }
    }
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseDragged(MouseEvent e) {
  }

  public void mouseMoved(MouseEvent e) {
  }

  public int getXCursor() {
    return xCursor;
  }

  public int getYCursor() {
    return yCursor;
  }

  public int trackX() {
    return (int)MouseInfo.getPointerInfo().getLocation().getX();
  }

  public int trackY() {
    return (int)MouseInfo.getPointerInfo().getLocation().getY();
  }
}