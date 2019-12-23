import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Mouse implements MouseListener {

  private Human duber;
  private Spawner spawner;
  private Projectile[] bullets;
  private static int mouseX, mouseY;
  
  Mouse(Human duber, Spawner spawner, Projectile[] bullets){
    this.duber = duber;
    this.spawner = spawner;
    this.bullets = bullets;
  }
 
  public void mouseClicked(MouseEvent e) {
  }

  public int getMouseX() {
    return mouseX;
  }
  
  public int getMouseY() {
    return mouseY;
  }
  
  public void mousePressed(MouseEvent e){
    mouseX = e.getX();
    mouseY = e.getY();
    if (duber != null) {
      spawner.spawnBullets(duber);
    }
  }
  
  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }
} 