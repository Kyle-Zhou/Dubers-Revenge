import javax.imageio.ImageIO;
import java.io.File;

class Pistol extends Weapon { 
  
  Pistol(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
    super.setDamage(200);
    super.setTotalAmmo(30);
    super.setClip(10);
  }
  
  public void reload(){
    
  }
  
}