import javax.imageio.ImageIO;
import java.io.File;

class Weapon1 extends Weapon{
  
  Weapon1(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
    super.setDamage(20);
    super.setTotalAmmo(30);
    super.setClip(10);
  }
  
}