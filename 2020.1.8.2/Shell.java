import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

class Shell extends Projectile {
  private BufferedImage sprite;
  private int spread;
  private Random random;
  
  Shell(int xCord, int yCord, int speed){
    super(xCord, yCord, speed);
    random = new Random();
    spread = random.nextInt(10 + 10) - 10;
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
  }

  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("pellet.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... pellet.png");};
  }

  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord(), geteWidth(), geteHeight());
  }
  
  public void travel(Human duber, int xCursor, int yCursor) {
    setAngle((float)Math.atan2(yCursor - duber.getyCentre() + spread, xCursor - duber.getxCentre() + spread));
    if (isLimit() == false){
      setxDirection((float)((getSpeed()) * Math.cos(getAngle())));
      setyDirection((float)((getSpeed()) * Math.sin(getAngle())));
      setLimit(true);
    } 
    getHitbox().setLocation((int)getHitbox().getX() + (int)getxDirection(),
          (int)getHitbox().getY() + (int)getyDirection());
    setxCord(getxCord() + (int)getxDirection());
    setyCord(getyCord() + (int)getyDirection());
  }
}
