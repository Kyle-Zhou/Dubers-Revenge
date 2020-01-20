import java.awt.Graphics;

abstract class Weapon {
  private int xCord, yCord, damage, totalAmmo, clip, originalClip;
  private long reloadSpeed;
  private boolean shootingLock, direction, reloading;
  
  Weapon(int xCord, int yCord, int damage, int totalAmmo, int clip, long reloadSpeed) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.damage = damage;
    this.totalAmmo = totalAmmo;
    this.clip = clip;
    this.reloadSpeed = reloadSpeed;
    originalClip = clip;
  }
  
  abstract void draw(Graphics g);
  
  abstract void move(Human duber);
  
  abstract void reload();
  
  public int getxCord() {
    return xCord;
  }

  public int getyCord() {
    return yCord;
  }

  public int getDamage() {
    return damage;
  }
  
  public int getTotalAmmo(){
    return totalAmmo;
  }
  
  public int getClip(){
    return clip;
  }
  
  public int getOriginalClip(){
    return originalClip;
  }

  public void setxCord(int xCord) {
    this.xCord = xCord;
  }

  public void setyCord(int yCord) {
    this.yCord = yCord;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }
  
  public void setTotalAmmo(int totalAmmo){
    this.totalAmmo = totalAmmo;
  }
  
  public void setClip(int clip){
    this.clip = clip;
  }
  
  public void setShootingLock(boolean shootingLock){
    this.shootingLock = shootingLock;
  }
  
  public boolean getShootingLock(){
    return shootingLock;
  }
  
  public void setDirection(boolean direction) {
    this.direction = direction;
  }
  
  public boolean getDirection() {
    return direction;
  }
  
  public void setReloading(boolean reloading){
    this.reloading = reloading;
  }
  
  public boolean getReloading(){
    return reloading;
  }
  
  public long getReloadSpeed(){
    return reloadSpeed;
  }
}