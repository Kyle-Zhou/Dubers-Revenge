import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Bullet extends Projectile {
  private BufferedImage sprite;
  
  Bullet(int xCord, int yCord, int speed){
    super(xCord, yCord, speed);
    loadSprite();
    super.seteWidth(sprite.getWidth());
    super.seteHeight(sprite.getHeight());
    super.setHitbox(new Rectangle(xCord, yCord, super.geteWidth(), super.geteHeight()));
  }

  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("bullet.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... bullet.png");};
  }

  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight());
  }
  
  public void travel(Human duber, int xCursor, int yCursor) {
    super.setAngle((float)Math.atan2(yCursor - duber.getyCentre(), xCursor - duber.getxCentre()));
    if (super.isLimit() == false){
      super.setxDirection((float)((super.getSpeed()) * Math.cos(super.getAngle())));
      super.setyDirection((float)((super.getSpeed()) * Math.sin(super.getAngle())));
      super.setLimit(true);
    } 
    super.getHitbox().setLocation((int)super.getHitbox().getX() + (int)super.getxDirection(),
          (int)super.getHitbox().getY() + (int)super.getyDirection());
    super.setxCord(super.getxCord() + (int)super.getxDirection());
    super.setyCord(super.getyCord() + (int)super.getyDirection());
  }
}
