import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;

/**
* [Mouse.java]
* contains mouseClicked, mousePressed, mouseReleased, mouseEntered, mouseExited methods
* contains trackX, trackY methods
* Controls all mouse interactions
* implements MouseListener
* @author Kyle Zhou
* Kyle Zhou - Foundation
* Ethan Zhang - Holding LMB
**/

public class Mouse implements MouseListener {
  
  private Human duber;
  private Spawner spawner;
  private Camera scroll;
  private HeadUpDisplay hud;
  private Shop shop;
  private Entity[] entities;
  private Weapon[] weapons;
  private static int xCursor, yCursor;
  private static boolean holding;
  
  /**
  * Mouse constructor 
  * inherits duber, spawner, scroll, hud, shop, and weapons[]
  */
  Mouse(Human duber, Spawner spawner, Camera scroll, HeadUpDisplay hud, Shop shop, Weapon[] weapons) {
    this.duber = duber;
    this.spawner = spawner;
    this.scroll = scroll;
    this.hud = hud;
    this.shop = shop;
    this.weapons = weapons;
    this.spawner = spawner;
  }
  
  /**
  * mousePressed 
  * checks all interactions when mouse is pressed
  * @param e, MouseEvent
  */
  public void mousePressed(MouseEvent e) {
    
    entities = spawner.getEntities();
    duber = (Human)entities[0];
    xCursor = e.getX() - (int)scroll.getXCamera();
    yCursor = e.getY() - (int)scroll.getYCamera();
    
    if (duber != null) {
      
      if (hud.getShopHitbox().contains(xCursor, yCursor)){ //if shop button is clicked on
        shop.setAddedShop(true); //sets boolean to true which opens shop
        weapons[spawner.getCurrentWeapon()].setShootingLock(true); //locks shooting
      }
      if (shop.getAddedShop() == true) { // when shop is open
      
        if (shop.getExitButtonHitbox().contains(xCursor, yCursor)) { //if shop's exit button is pressed
          shop.setAddedShop(false);  // close shop
          weapons[spawner.getCurrentWeapon()].setShootingLock(false); //turn off shooting lock
          duber.setMoveLock(false);  //turn off moving lock
        }
        
        if (shop.getitemButtonHitbox().contains(xCursor, yCursor)){ //if item button pressed
          shop.setCurrentShop(2); //switch to items shop
        } 
        if (shop.getWeaponButtonHitbox().contains(xCursor, yCursor)){ //if weapons button is pressed
          shop.setCurrentShop(1); //switch to weapons shop
        }
        
        if (shop.getCurrentShop() == 1) { //if weapons shop is open
          if (shop.getbuyingButtonHitbox().contains(xCursor, yCursor)) { //if shotgun buying button is pressed 
            if (hud.getCurrency() >= 50){ 
              hud.setCurrency(hud.getCurrency()-50); //subtract 50 from currency
              spawner.addShotgun(duber); //unlock shotgun
              shop.setShotgunBought(true); 
            }
          }
          if (shop.getbuyingButtonHitbox2().contains(xCursor, yCursor)){ //if machinegun buying button is pressed 
            if (hud.getCurrency() >= 50){
              hud.setCurrency(hud.getCurrency()-50);
              spawner.addMachinegun(duber); //unlock machinegun
              shop.setAutomaticBought(true);
            }
          }
          if (shop.getbuyingButtonHitbox3().contains(xCursor, yCursor)) { //if flamethrower buying button is pressed 
            if (hud.getCurrency() >= 50){
              hud.setCurrency(hud.getCurrency()-50);
              spawner.addFlamethrower(duber); //unlock flamethrower
              shop.setFlameThrowerBought(true);
            }
          }
          if (shop.getbuyingButtonHitbox4().contains(xCursor, yCursor)) { //if rocketlLauncher buying button is pressed 
            if (hud.getCurrency() >= 50){
              hud.setCurrency(hud.getCurrency()-50);
              spawner.addRocketLauncher(duber); //unlock rocketLauncher
              shop.setRocketLauncherBought(true);
            }
          }
          
        } else if (shop.getCurrentShop() == 2) { //if items shop is open
          
          if (shop.getbuyingButtonHitbox().contains(xCursor, yCursor)) { //if medkit buying button is pressed 
            if (hud.getCurrency() >= 50){
              hud.setCurrency(hud.getCurrency()-50);
              hud.setMedKitCount(hud.getMedKitCount()+1);
            }
          }
          if (shop.getbuyingButtonHitbox2().contains(xCursor, yCursor)) { //if ammo buying button is pressed 
            if (hud.getCurrency() >= 50){
              hud.setCurrency(hud.getCurrency()-50);
              weapons[spawner.getCurrentWeapon()].setTotalAmmo((weapons[spawner.getCurrentWeapon()].getTotalAmmo())+10);
            }
          }
          if (shop.getbuyingButtonHitbox4().contains(xCursor, yCursor)) { //if bandage buying button is pressed 
            if (hud.getCurrency() >= 50){
              hud.setCurrency(hud.getCurrency()-50);
              hud.setBandageCount(hud.getBandageCount()+1);
            }
          }
        }
      }
      //when shop is not open, mouse pressed means shooting
      if (spawner.getCurrentWeapon() == 0) { //pistol
        spawner.fireBullet(duber);
      } else if (spawner.getCurrentWeapon() == 1) { //shotgun
        spawner.fireShell(duber);
      } else if (spawner.getCurrentWeapon() == 2) { //machine gun
        holding = true;
      } else if (spawner.getCurrentWeapon() == 3) { //rocket launcher
        spawner.fireRocket(duber);
      } else if (spawner.getCurrentWeapon() == 4) { // flame thrower
        holding = true;
      }
    }
    
  }
  
  /**
  * mouseReleased 
  * sets holding to false when mouse is released
  * @param e, MouseEvent
  */
  public void mouseReleased(MouseEvent e) {
    holding = false;
  }
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  public void mouseClicked(MouseEvent e) {}
  
  /**
  * trackX 
  * ALWAYS tracks Y position
  * @return int value of current X position of mouse adjusted for camera
  */
  public int trackX() {
    return (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)scroll.getXCamera();
  }
  
  /**
  * trackY 
  * ALWAYS tracks X position
  * @return int value of current Y position of mouse adjusted for camera
  */
  public int trackY() {
    return (int)MouseInfo.getPointerInfo().getLocation().getY() - (int)scroll.getYCamera();
  }
  
  /**
  * getXCursor 
  * @return int value of current X position of mouse, ONLY when mouse is pressed
  */
  public int getXCursor() {
    return xCursor;
  }
  
  /**
  * getYCursor 
  * @return int value of current Y position of mouse, ONLY when mouse is pressed
  */
  public int getYCursor() {
    return yCursor;
  }
   
  /**
  * getHolding 
  * @return boolean value of holding
  */
  public boolean getHolding() {
    return holding;
  }
  
}