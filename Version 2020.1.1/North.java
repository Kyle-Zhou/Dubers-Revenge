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
    super.seteWidth(sprite.getWidth());
    super.seteHeight(sprite.getHeight());
    super.setHitbox(new Rectangle(xCord, yCord, super.geteWidth(), 100));
    eastHitbox = new Rectangle(super.geteWidth() - 64, yCord, 64, super.geteHeight());
    westHitbox = new Rectangle(xCord, yCord, 64, super.geteHeight());
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("north.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... north.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord(), super.geteWidth(), 100);
    g.drawRect(super.geteWidth() - 64, super.getyCord(), 64, super.geteHeight());
    g.drawRect(super.getxCord(), super.getyCord(), 64, super.geteHeight());
  }
  
  public Rectangle getEastHitbox() {
    return eastHitbox;
  }
  
  public Rectangle getWestHitbox() {
    return westHitbox;
  }
}
