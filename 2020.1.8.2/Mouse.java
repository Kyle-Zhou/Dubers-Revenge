import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;

public class Mouse implements MouseListener {
  
  private Human duber;
  private Spawner spawner;
  private Camera scroll;
  private HeadUpDisplay hud;
  private Shop shop;
  private Entity[] entities;
  private static int xCursor, yCursor;
  
  Mouse(Human duber, Spawner spawner, Camera scroll, HeadUpDisplay hud, Shop shop) {
    this.duber = duber;
    this.spawner = spawner;
    this.scroll = scroll;
    this.hud = hud;
    this.shop = shop;
  }
  
  public void mouseClicked(MouseEvent e) {
  }
  
  public void mousePressed(MouseEvent e) {
    entities = spawner.getEntities();
    duber = (Human)entities[0];
    xCursor = e.getX() - (int)scroll.getXCamera();
    yCursor = e.getY() - (int)scroll.getYCamera();
    if (duber != null) {
      if (spawner.getCurrentWeapon() == 0) {
        spawner.fireBullet(duber);
      } else if (spawner.getCurrentWeapon() == 1) {
        spawner.fireShell(duber);
      }
     if (hud.getShopHitbox().contains(xCursor, yCursor)){
        System.out.print(1);
        shop.setAddedShop(true);

      }
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
  
  public int trackX() {
    return (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)scroll.getXCamera();
  }
  
  public int trackY() {
    return (int)MouseInfo.getPointerInfo().getLocation().getY() - (int)scroll.getYCamera();
  }
}