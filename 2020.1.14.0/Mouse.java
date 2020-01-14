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
  private static boolean holding;
  
  Mouse(Human duber, Spawner spawner, Camera scroll, HeadUpDisplay hud, Shop shop) {
    this.duber = duber;
    this.spawner = spawner;
    this.scroll = scroll;
    this.hud = hud;
    this.shop = shop;
    holding = false;
  }

  
  public void mouseClicked(MouseEvent e) {
  }
  
  public void mousePressed(MouseEvent e) {
    entities = spawner.getEntities();
    duber = (Human)entities[0];
    xCursor = e.getX() - (int)scroll.getXCamera();
    yCursor = e.getY() - (int)scroll.getYCamera();
    if (duber != null) {
      if (hud.getShopHitbox().contains(xCursor, yCursor)){
        shop.setAddedShop(true);
      }
      if (shop.getAddedShop() == true){
      
        if (shop.getSelfButtonHitbox().contains(xCursor, yCursor)){
          shop.setCurrentShop(2);
        } 
        if (shop.getWeaponButtonHitbox().contains(xCursor, yCursor)){
          shop.setCurrentShop(1);
        }
        if (shop.getFiftyButtonHitbox().contains(xCursor, yCursor)){
          if (hud.getCurrency() >= 50){
            hud.setCurrency(hud.getCurrency()-50);
            spawner.addShotgun(duber);
            shop.setShotgunBought(true);
          }
        }
        
        
      }
      
      
      
      if (spawner.getCurrentWeapon() == 0) {
        holding = true;
        spawner.fireBullet(duber);
      } else if (spawner.getCurrentWeapon() == 1) {
        spawner.fireShell(duber);
      } else if (spawner.getCurrentWeapon() == 2) {
        holding = true;
      } else if (spawner.getCurrentWeapon() == 3) {
        //rocket launcher
      } else if (spawner.getCurrentWeapon() == 4) {
        //flamethrower;
      }
    }
  }
  
  public void mouseReleased(MouseEvent e) {
    holding = false;
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
  
  public boolean getHolding() {
    return holding;
  }
  
  public boolean checkAutomatic() {
    if ((spawner.getCurrentWeapon() == 0) || (spawner.getCurrentWeapon() == 1) || (spawner.getCurrentWeapon() == 3)) {
      return false;
    } else {
      return true;
    }
  }
}