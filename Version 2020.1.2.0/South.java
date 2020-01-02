import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class South extends Wall {
  BufferedImage sprite;
  
  South(int xCord, int yCord) {
    super(xCord, yCord);
    loadSprite();
    super.seteWidth(sprite.getWidth());
    super.seteHeight(sprite.getHeight());
    super.setHitbox(new Rectangle(xCord, yCord + 65, super.geteWidth(), super.geteHeight() - 65));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("south.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... south.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord() + 65, super.geteWidth(), super.geteHeight() - 65);
  }
}