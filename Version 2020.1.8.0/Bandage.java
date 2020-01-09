import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Bandage extends Entity{
  
  private BufferedImage sprite;
  
  Bandage (int xCord, int yCord){
    super(xCord, yCord);
    loadSprite();
    super.seteWidth(sprite.getWidth());
    super.seteHeight(sprite.getHeight());
    super.setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("pill.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite...");};
  }
  
  public void draw(Graphics g) {
    
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord(), geteWidth(), geteHeight());
  }
  
//  public void heal(Human duber){
//    if (duber.getHealth() + 20 > 100){
//      duber.setHealth(100);
//    } else {
//      duber.setHealth(duber.getHealth() + 20);
//    }
//  }
  
}