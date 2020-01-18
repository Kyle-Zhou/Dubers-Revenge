import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Spit extends Projectile {
  private BufferedImage sprite;
  Spit(int xCord, int yCord, int speed) {
    super(xCord, yCord, speed);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
  }

  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("bullet.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... bullet.png");};
  }

  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord(), geteWidth(), geteHeight());
  }

  public void travel(Human duber, int spitterX, int spitterY) {
//    spitterX = spitter.getxCentre();
//    spitterY = spitter.getyCentre()-10;
//    setAngle((double)Math.atan2(duber.getxCentre() - spitterX, duber.getyCentre() - spitterY));
//    if (isLimit() == false){
//      setxDirection((double)((getSpeed()) * Math.cos(getAngle())));
//      setyDirection((double)((getSpeed()) * Math.sin(getAngle())));
//      setLimit(true);
//    } 
//    getHitbox().setLocation((int)Math.round(getHitbox().getX()) + (int)Math.round(getxDirection()),
//          (int)Math.round(getHitbox().getY()) + (int)Math.round(getyDirection()));
//    setxCord(getxCord() + (int)Math.round(getxDirection()));
//    setyCord(getyCord() + (int)Math.round(getyDirection()));   
  }
//    if ((spitterX >= duber.getxCord()) && (spitterY >= duber.getyCord())) {
////      setxDirection(-(getSpeed()));
////      setyDirection(-(getSpeed()));
//      setxDirection(-1);
//      setyDirection(-1);
//    } else if ((spitterX <= duber.getxCord()) && (spitterY >= duber.getyCord())) {
////      setxDirection(getSpeed());
////      setyDirection(-(getSpeed()));;
//        setxDirection(1);
//      setyDirection(-1);
//    } else if ((spitterX <= duber.getxCord()) && (spitterY <= duber.getyCord())) {
////      setxDirection(getSpeed());
////      setyDirection(getSpeed());
//      setxDirection(1);
//      setyDirection(1);
//    } else if ((spitterX >= duber.getxCord()) && (spitterY <= duber.getyCord())) {
////      setxDirection(-(getSpeed()));
////      setyDirection(getSpeed());
//      setxDirection(-1);
//      setyDirection(1);
//    }
    
//    getHitbox().setLocation((int)getHitbox().getX() + (int)getxDirection(),
//          (int)getHitbox().getY() + (int)getyDirection());
//    setxCord(getxCord() + (int)getxDirection());
//    setyCord(getyCord() + (int)getyDirection());

  public void spit(Human duber, Spitter spitter){
    setAngle((double)Math.atan2(duber.getxCentre() - spitter.getxCord(), duber.getyCentre() - spitter.getyCord()));
    if (isLimit() == false){
      setxDirection((double)((getSpeed()) * Math.cos(getAngle())));
      setyDirection((double)((getSpeed()) * Math.sin(getAngle())));
      setLimit(true);
    } 
    getHitbox().setLocation((int)Math.round(getHitbox().getX()) + (int)Math.round(getxDirection()),
          (int)Math.round(getHitbox().getY()) + (int)Math.round(getyDirection()));
    setxCord(getxCord() + (int)Math.round(getxDirection()));
    setyCord(getyCord() + (int)Math.round(getyDirection())); 
  }
}
