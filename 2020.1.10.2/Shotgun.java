class Shotgun extends Weapon {
  
  Shotgun(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
  }
  
  public void reload(){
    if ((getClip() < getOriginalClip()) && (getTotalAmmo() > 0)){

      if (getTotalAmmo()+getClip() >= getOriginalClip()){
        setTotalAmmo(getTotalAmmo()-(getOriginalClip()-getClip()));
        setClip(getClip()+(getOriginalClip()-getClip()));
        if (getTotalAmmo() < 0){
          setTotalAmmo(0);
        }
      } else {
        setClip(getClip()+getTotalAmmo());
        setTotalAmmo(0);
      }
    }
  }
  
}
