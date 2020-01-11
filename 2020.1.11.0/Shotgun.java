class Shotgun extends Weapon {
  
  Shotgun(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
  }
  
  public void reload(){
    if (getTotalAmmo() >= getOriginalClip()){
      setTotalAmmo(getTotalAmmo()-getOriginalClip());
      setClip(getClip()+getOriginalClip());
    }
  }
  
}