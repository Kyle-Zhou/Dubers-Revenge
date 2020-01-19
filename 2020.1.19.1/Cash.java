import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

class Cash extends Entity {
  private BufferedImage sprite;
  
  Cash(int xCord, int yCord){
    super(xCord, yCord);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("cash.png"));
    } catch(Exception e) { System.out.println("Error Loading 'cash.png'...");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
  
}