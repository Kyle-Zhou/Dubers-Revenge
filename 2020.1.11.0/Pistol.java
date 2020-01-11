class Pistol extends Weapon {
  
  Pistol(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
  }
  
  public void reload(){
    if ((getClip() < getOriginalClip()) && (getTotalAmmo() > 0)){

        
      setTotalAmmo(getTotalAmmo()-(getOriginalClip()-getClip()));
      setClip(getClip()+(getOriginalClip()-getClip()));
      if (getTotalAmmo() < 0){
        setTotalAmmo(0);
      }
      
    }
  }
    
  
}