import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

class RocketLauncher extends Weapon {
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  RocketLauncher(int xCord, int yCord, int damage, int totalAmmo, int clip, long reloadSpeed){
    super(xCord, yCord, damage, totalAmmo, clip, reloadSpeed);
    loadSprites();
    setDirection(true);
  }
  
  public void loadSprites() {
    try {
      sheet = ImageIO.read(new File("rocketLauncherSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 87, 31);
      spriteLeft = sheet.getSubimage(87, 0, 87, 31);
    } catch(Exception e) {System.out.println("Error Loading Sprites...");};
  }
  
  public void draw(Graphics g) { //here
    if (getDirection() == true) {
      g.drawImage(spriteRight, getxCord(), getyCord(), null);
    } else {
      g.drawImage(spriteLeft, getxCord(), getyCord(), null);
    }
  }
  
  public void move(Human duber) {
    if (duber.getMoveLock() == false){
      setxCord(getxCord() + duber.getxDirection());
      setyCord(getyCord() + duber.getyDirection());
    }
  }
  
  public void reload(){
    if ((getClip() < getOriginalClip()) && (getTotalAmmo() > 0)) {
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
      setReloading(false);
      setShootingLock(false);
    }
  }
}