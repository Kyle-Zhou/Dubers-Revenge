import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;

class Cash extends Entity{
  
  private BufferedImage sprite;
  
  Cash(int xCord, int yCord){
    super(xCord, yCord);
    loadSprite();
    super.seteWidth(sprite.getWidth());
    super.seteHeight(sprite.getHeight());
    super.setHitbox(new Rectangle(xCord, yCord, super.geteWidth(), super.geteHeight()));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("cash.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite...");};
  }
  
  public void draw(Graphics g) {
    
    g.drawImage(sprite, super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight());
    
//    g.drawImage(sprite, xCord, yCord+3, null);
//    g.setColor(Color.RED);
//    g.drawRect(xCord, yCord+3, eWidth, eHeight);
    
  }
  
}