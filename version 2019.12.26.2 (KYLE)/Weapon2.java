import javax.imageio.ImageIO;
import java.io.File;

class Weapon2 extends Weapon{
  
  Weapon2(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
    super.setDamage(5);
    super.setTotalAmmo(100);
    super.setClip(30);
  }
  
}