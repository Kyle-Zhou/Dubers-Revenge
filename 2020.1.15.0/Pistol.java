import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

class Pistol extends Weapon {
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  Pistol(int xCord, int yCord, int damage, int totalAmmo, int clip){
    super(xCord, yCord, damage, totalAmmo, clip);
    loadSprites();
    setDirection(true);
  }
  
  public void loadSprites() {
    try {
      sheet = ImageIO.read(new File("pistolSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 36, 20);
      spriteLeft = sheet.getSubimage(36, 0, 36, 20);
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