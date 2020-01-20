import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

/**
* [Keys.java]
* contains keyTyped, keyPressed, keyReleased methods
* contains movement method
* Controls all key interactions
* implements MouseListener
* @author Kyle Zhou, Ethan Zhang
* Kyle Zhou - movement, weapon switching and shop interactions
* Ethan Zhang - mouse holding and movement booleans
**/

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

  /**
  * Keys constructor 
  * inherits duber, weapons[], spawner, hud, entites[], shop, counter
  */
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
  
  /**
  * keyPressed 
  * checks all interactions when a key is pressed
  * @param e, KeyEvent
  */
  public void keyPressed(KeyEvent e) {
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //If 'A' or 'a' is pressed
      left = true;
      duber.setxDirection(-duber.getSpeed()); //duber moves along x at negative speed - left
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")){  //If 'S' or 's' is pressed
      down = true; 
      duber.setyDirection(duber.getSpeed()); //duber moves along y at positive speed - down
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //If 'D' or 'd' is pressed
      right = true;
      duber.setxDirection(duber.getSpeed()); //duber moves along x at positive speed - right
    } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) { //If 'W' or 'w' is pressed
      up = true;
      duber.setyDirection(-duber.getSpeed()); //duber moves along y at negative speed - up
    }
    
    if ((e.getKeyChar() == '1')) { //If '1' is pressed
        spawner.setCurrentWeapon(0); //set weapon to pistol
    } else if ((e.getKeyChar() == '2')) { //If '2' is pressed
      if (shop.getShotgunBought()){
        spawner.setCurrentWeapon(1); //set weapon to shotgun
      }
    } else if (e.getKeyChar() == '3') { //If '3' is pressed
      if (shop.getAutomaticBought()) {
        spawner.setCurrentWeapon(2); //set weapon to machine gun
      }
    } else if (e.getKeyChar() == '4') { //If '4' is pressed
      if (shop.getRocketLauncherBought()) { 
        spawner.setCurrentWeapon(3); //set weapon to rocketLauncher
      }
    } else if (e.getKeyChar() == '5') { //If '5' is pressed
      if (shop.getFlameThrowerBought()) {
        spawner.setCurrentWeapon(4); //set weapon to flamethrower
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("E")) {   //If 'E' or 'e' is pressed
      if (entities[spawner.getCurrentBandage()] instanceof Bandage) {
        //if duber is currently touching a bandage
        if (entities[spawner.getCurrentBandage()].getHitbox().intersects(duber.getHitbox())) { 
          hud.setBandageCount(hud.getBandageCount()+1); 
          entities[spawner.getCurrentBandage()] = null;
        }
      } 
      if (entities[spawner.getCurrentMedKit()] instanceof MedKit){
        //if duber is currently touching a medKit
        if (entities[spawner.getCurrentMedKit()].getHitbox().intersects(duber.getHitbox())){
          hud.setMedKitCount(hud.getMedKitCount()+1);   
          entities[spawner.getCurrentMedKit()] = null;
        }
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("R")) { //If 'R' or 'r' is pressed
      //if clip size is not equal to original clip size (if bullets have been fired)
      if (weapons[spawner.getCurrentWeapon()].getClip() != weapons[spawner.getCurrentWeapon()].getOriginalClip()){
        counter.setReloadTime(System.currentTimeMillis()); //set time of when 'R' was pressed
        weapons[spawner.getCurrentWeapon()].setReloading(true); 
        weapons[spawner.getCurrentWeapon()].setShootingLock(true); //lock shooting while reloading
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("H")) { //If 'H' or 'h' is pressed
      // if duber is currently equipped with any bandages
      if (hud.getBandageCount() > 0) {
        hud.setBandageCount(hud.getBandageCount()-1);  
        if (duber.getHealth() + 20 > 100){ //prevents duber's health from exceeding 100
          duber.setHealth(100);
        } else {
          duber.setHealth(duber.getHealth() + 20);
        }
      }
    }
    
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("J")) { //If 'J' or 'j' is pressed
      if (hud.getMedKitCount() > 0){ 
        hud.setMedKitCount(hud.getMedKitCount()-1);  
        duber.setHealth(100); //always heal to 100 with medkits
      }
    }
    
    if (shop.getAddedShop() == true) { //if shop is open
      if ((e.getKeyChar() == e.VK_ESCAPE)){ //if escape key is pressed
        shop.setAddedShop(false); //close shop
        duber.setMoveLock(false); //allow for movement again
        weapons[spawner.getCurrentWeapon()].setShootingLock(false); //allow for shooting again
      }
    }
  }
  
  /**
  * keyReleased 
  * checks all interactions when a key is released
  * @param e, KeyEvent
  */
  public void keyReleased(KeyEvent e) {
    if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {    
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
    }  
  }
  
  public void keyTyped(KeyEvent e) {}
  
  /**
  * movement 
  * checks if duber is currently moving
  * @return boolean
  */
  public boolean movement() {
    if (left || down || right || up) {
      return true;
    } else {
      return false;
    }
  }
   
}