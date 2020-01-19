import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.util.Random;

class Canister extends Obstacle {
  private BufferedImage sheet, sprite;
  private Random random;
  private int identity;
  private Rectangle spawnHitbox;
  
  Canister(int xCord, int yCord) {
    super(xCord, yCord);
    random = new Random();
    identity = random.nextInt(3);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    if (identity == 0) {
    setHitbox(new Rectangle(getxCord() + 7, getyCord(), geteWidth() - 15, geteHeight() - 30));
    } else if (identity == 1) {
    setHitbox(new Rectangle(getxCord() + 10, getyCord() + 16, geteWidth() - 21, geteHeight() - 40));
    } else if (identity == 2) {
    setHitbox(new Rectangle(getxCord() + 7, getyCord(), geteWidth() - 15, geteHeight() - 30));
    }
    spawnHitbox = new Rectangle(getxCord(), getyCord(), geteWidth(), geteHeight());
  }
  
  public void loadSprite() {
    try {
      sheet = ImageIO.read(new File("canisterSheet.png"));
      if (identity == 0) {
        sprite = sheet.getSubimage(0, 0, 80, 110);
        setHitbox(new Rectangle(getxCord() + 7, getyCord(), geteWidth() - 15, geteHeight() - 30));
      } else if (identity == 1) {
        sprite = sheet.getSubimage(80, 0, 80, 110);
        setHitbox(new Rectangle(getxCord() + 10, getyCord() + 16, geteWidth() - 21, geteHeight() - 40));
      } else if (identity == 2) {
        sprite = sheet.getSubimage(160, 0, 80, 110);
      }
    } catch(Exception e) { System.out.println("Error Loading 'canisterSheet.png'...");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
  
  public Rectangle getSpawnHitbox() {
    return spawnHitbox;
  }
}