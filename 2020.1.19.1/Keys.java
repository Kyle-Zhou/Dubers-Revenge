import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

public class Keys implements KeyListener{

  private Human duber;
  private Weapon[] weapons;
  private Entity[] entities;
  private Spawner spawner;
  private HeadUpDisplay hud;
  private Shop shop;
  private Graphics g;
  private static boolean left, down, right, up;
  private Counter counter;

  Keys(Human duber, Weapon[] weapons, Spawner spawner, HeadUpDisplay hud, Entity entities[], Shop shop, 
       Counter counter){
    this.duber = duber;
    this.weapons = weapons;
    this.spawner = spawner;
    this.hud = hud;
    this.entities = entities;
    this.shop = shop;
    this.counter = counter;
  }
  
  public void keyTyped(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //If 'A' is pressed
      left = true;
      duber.setxDirection(-duber.getSpeed());
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")){  //If 'S' is pressed
      down = true;
      duber.setyDirection(duber.getSpeed());
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //If 'D' is pressed
      right = true;
      duber.setxDirection(duber.getSpeed());
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) { //If 'W' is pressed
      up = true;
      duber.setyDirection(-duber.getSpeed());
    }
    
    if ((e.getKeyChar() == '1')) {
        spawner.setCurrentWeapon(0);
    } else if ((e.getKeyChar() == '2')) {
      if (shop.getShotgunBought()){
        spawner.setCurrentWeapon(1);
      }
    } else if (e.getKeyChar() == '3') {
      if (shop.getAutomaticBought()) {
        spawner.setCurrentWeapon(2);
      }
    } else if (e.getKeyChar() == '4') {
      if (shop.getRocketLauncherBought()) {
        spawner.setCurrentWeapon(3);
      }
    } else if (e.getKeyChar() == '5') {
      if (shop.getFlameThrowerBought()) {
        spawner.setCurrentWeapon(4);
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("E")) { 
      if (entities[spawner.getCurrentBandage()] instanceof Bandage){
        if (entities[spawner.getCurrentBandage()].getHitbox().intersects(duber.getHitbox())){
          hud.setBandageCount(hud.getBandageCount()+1);   
          entities[spawner.getCurrentBandage()] = null;
        }
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("R")) { 
      if (weapons[spawner.getCurrentWeapon()].getClip() != weapons[spawner.getCurrentWeapon()].getOriginalClip()){
        counter.setReloadTime(System.currentTimeMillis());
        weapons[spawner.getCurrentWeapon()].setReloading(true);
        weapons[spawner.getCurrentWeapon()].setShootingLock(true);
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("H")) { 
      if (hud.getBandageCount() > 0){
        hud.setBandageCount(hud.getBandageCount()-1);  
        if (duber.getHealth() + 20 > 100){
          duber.setHealth(100);
        } else {
          duber.setHealth(duber.getHealth() + 20);
        }
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("J")) { 
      if (hud.getMedKitCount() > 0){
        hud.setMedKitCount(hud.getMedKitCount()-1);  
        duber.setHealth(100);
      }
    }
    
    if (shop.getAddedShop() == true){
      if ((e.getKeyChar() == e.VK_ESCAPE)){
        shop.setAddedShop(false);
        duber.setMoveLock(false);
        weapons[0].setShootingLock(false);
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {    //Good time to use a Switch statement
      duber.setxDirection(0);
      left = false;
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {
      duber.setyDirection(0);
      down = false;
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {
      duber.setxDirection(0);
      right = false;
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")){
      duber.setyDirection(0);
      up = false;
    }  //note - would be better to make player class and pass in map, test movement in there
  }
  
  public boolean movement() {
    if (left || down || right || up) {
      return true;
    } else {
      return false;
    }
  }
   
}