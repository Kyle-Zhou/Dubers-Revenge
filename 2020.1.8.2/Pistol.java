class Pistol extends Weapon {
  
  Pistol(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
    this.setDamage(damage);
    this.setTotalAmmo(totalAmmo);
    this.setClip(clip);
  }
  
  public void reload(){
    
  }
  
}