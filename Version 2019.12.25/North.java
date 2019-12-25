import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class North extends Wall {
  BufferedImage sprite;
  
  North(int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    super(xCord, yCord, eWidth, eHeight, adjust);
    loadSprite();
    hitbox = new Rectangle(xCord, yCord, eWidth, adjust);
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("north.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite...");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, xCord, yCord, eWidth, eHeight, null);
    g.setColor(Color.RED);
    g.drawRect(xCord, yCord, eWidth, adjust);
  }
}