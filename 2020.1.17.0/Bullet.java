import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Bullet extends Projectile {
  private BufferedImage sprite;
  
  Bullet(int xCord, int yCord, int speed) {
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
  
  public void travel(Human duber, int xCursor, int yCursor) {
    setAngle((double)Math.atan2(yCursor - duber.getyCentre(), xCursor - duber.getxCentre()));
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
