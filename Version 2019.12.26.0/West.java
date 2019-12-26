import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class West extends Wall {
  BufferedImage sprite;
  
  West(int xCord, int yCord, int eWidth, int eHeight) {
    super(xCord, yCord, eWidth, eHeight);
    loadSprite();
    super.setHitbox(new Rectangle(xCord, yCord, super.geteWidth(), super.geteHeight()));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("west.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... west.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight());
  }
}