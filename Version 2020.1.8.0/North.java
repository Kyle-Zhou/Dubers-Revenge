import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class North extends Wall {
  private BufferedImage sprite;
  private Rectangle eastHitbox, westHitbox;
  
  North(int xCord, int yCord) {
    super(xCord, yCord);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), 100));
    eastHitbox = new Rectangle(geteWidth() - 64, yCord, 64, geteHeight());
    westHitbox = new Rectangle(xCord, yCord, 64, geteHeight());
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("north.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... north.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord(), geteWidth(), 100);
    g.drawRect(geteWidth() - 64, getyCord(), 64, geteHeight());
    g.drawRect(getxCord(), getyCord(), 64, geteHeight());
  }
  
  public Rectangle getEastHitbox() {
    return eastHitbox;
  }
  
  public Rectangle getWestHitbox() {
    return westHitbox;
  }
}
