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
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord + 30, geteWidth(), geteHeight() - 30));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("south.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... south.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord() + 30, geteWidth(), geteHeight() - 30);
  }
}