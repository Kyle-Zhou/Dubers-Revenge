import java.awt.Graphics;

/**
* [Weapon.java]
* Abstract weapon class 
* superclass to Pistol, Shotgun, Machinegun, Flamethrower, RocketLauncher
* contains abstract draw, move and reload methods
* @author Kyle Zhou, Ethan Zhang
**/

abstract class Weapon {
  
  private int xCord, yCord;
  private int damage;
  private int totalAmmo, clip, originalClip;
  private long reloadSpeed;
  private boolean shootingLock; 
  private boolean direction;
  private boolean reloading;
  
  /**
  * Weapon constructor 
  * Sets xCoordinate, yCoordinate, damage, totalAmmo, clip size and reloadSpeed as parameters
  */  
  Weapon(int xCord, int yCord, int damage, int totalAmmo, int clip, long reloadSpeed) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.damage = damage;
    this.totalAmmo = totalAmmo;
    this.clip = clip;
    this.reloadSpeed = reloadSpeed;
    originalClip = clip;
  }
  
  /**
  * draw 
  * Determines direction of cursor and draws accordingly
  * @param g, Graphics
  */
  abstract void draw(Graphics g);
  
  /**
  * move 
  * Moves weapon with duber
  * @param duber, Human
  */
  abstract void move(Human duber);
  
  /**
  * reload 
  * Determines if reloading is possible and then reloads ammo in the gun
  * Prevents shooting while reloading
  */
  abstract void reload();
  
  /**
  * getxCord 
  * @return int value of xCord
  */
  public int getxCord() {
    return xCord;
  }
  
  /**
  * getyCord 
  * @return int value of yCord
  */
  public int getyCord() {
    return yCord;
  }
  
  /**
  * getDamage
  * @return int value of xCord
  */
  public int getDamage() {
    return damage;
  }

  /**
  * getTotalAmmo 
  * @return int value of totalAmmo
  */
  public int getTotalAmmo(){
    return totalAmmo;
  }
  
  /**
  * getClip 
  * @return int value of clip
  */
  public int getClip(){
    return clip;
  }
  
  /**
  * getOriginalClip 
  * @return int value of originalClip
  */
  public int getOriginalClip(){
    return originalClip;
  }

  /**
  * setxCord 
  * @param xCord, int value of xCord
  */
  public void setxCord(int xCord) {
    this.xCord = xCord;
  }
  
  /**
  * setyCord 
  * @param yCord, int value of yCord
  */
  public void setyCord(int yCord) {
    this.yCord = yCord;
  }

  /**
  * setDamage 
  * @param damage, int value of damage
  */
  public void setDamage(int damage) {
    this.damage = damage;
  }
  
  /**
  * setTotalAmmo 
  * @param totalAmmo, int value of totalAmmo
  */
  public void setTotalAmmo(int totalAmmo){
    this.totalAmmo = totalAmmo;
  }
  
  /**
  * setClip 
  * @param clip, int value of clip
  */
  public void setClip(int clip){
    this.clip = clip;
  }
  
  /**
  * setShootingLock 
  * @param shootingLock, boolean value of shootingLock
  */
  public void setShootingLock(boolean shootingLock){
    this.shootingLock = shootingLock;
  }
  
  /**
  * getShootingLock 
  * @return boolean value of shootingLock
  */
  public boolean getShootingLock(){
    return shootingLock;
  }
  
  /**
  * setDirection 
  * @param direction, boolean value of direction
  */
  public void setDirection(boolean direction) {
    this.direction = direction;
  }
  
  /**
  * getDirection 
  * @return boolean value of direction
  */
  public boolean getDirection() {
    return direction;
  }
  
  /**
  * setReloading 
  * @param reloading, boolean value of reloading
  */
  public void setReloading(boolean reloading){
    this.reloading = reloading;
  }
  
  /**
  * getReloading 
  * @return boolean value of reloading
  */
  public boolean getReloading(){
    return reloading;
  }
  
  /**
  * getReloadSpeed 
  * @return long value of reloadSpeed
  */
  public long getReloadSpeed(){
    return reloadSpeed;
  }
}