abstract class Weapon {
  
  private int xCord, yCord, damage, totalAmmo, clip;
  private boolean shootingLock;
  
  Weapon(int xCord, int yCord, int damage, int totalAmmo, int clip) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.damage = damage;
    this.totalAmmo = totalAmmo;
    this.clip = clip;
  }

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
    
}