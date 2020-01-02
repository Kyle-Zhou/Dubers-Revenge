import javax.imageio.ImageIO;
import java.io.File;

class Shotgun extends Weapon { 
  
  Shotgun(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
    this.setDamage(damage);
    this.setTotalAmmo(totalAmmo);
    this.setClip(clip);
  }
  
  public void reload(){
    
  }
  
}