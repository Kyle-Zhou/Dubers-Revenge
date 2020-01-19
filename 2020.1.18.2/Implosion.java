import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Implosion extends Entity {
  private BufferedImage sheet;
  private BufferedImage[] sprites;
  private final int size = 300, rows = 6, columns = 10;
  private int sprite;
  
  Implosion(int xCord, int yCord) {
    super(xCord, yCord);
    sprites = new BufferedImage[rows * columns];
    loadSprites();
    seteWidth(sprites[0].getWidth());
    seteHeight(sprites[0].getHeight());
    sprite = 0;
    setHitbox(new Rectangle(xCord, yCord, size, size));
  }
  
  public void loadSprites() {
    try {
      sheet = ImageIO.read(new File("implosionSheet.png"));
      
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          sprites[(i * columns) + j] = sheet.getSubimage(j * size, i * size, size, size);
        }
      }
      
    } catch(Exception e) { System.out.println("Error Loading Sprites... implosionSheet.png");}
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprites[sprite], getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord(), size, size);
  }
  
  public void update() {
    sprite++;
  }
  
  public int getSprite() {
    return sprite;
  }
}
