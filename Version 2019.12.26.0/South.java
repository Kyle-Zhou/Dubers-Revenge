import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class South extends Wall {
  BufferedImage sprite;
  
  South(int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    super(xCord, yCord, eWidth, eHeight, adjust);
    loadSprite();
    super.setHitbox(new Rectangle(xCord, super.getAdjust(), super.geteWidth(), super.geteHeight()));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("south.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... south.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getAdjust(), super.geteWidth(), super.geteHeight());
  }
}