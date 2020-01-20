import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.util.Random;

/**
* [Canister.java]
* a canister in the laboratory that obstructs movement
* subclass to Obstacle
* contains loadSprite method
* contains overridden draw method
* @author Ethan Zhang
**/

class Canister extends Obstacle {
  private BufferedImage sheet, sprite;
  private Random random;
  private int identity;
  private Rectangle spawnHitbox;
  
  /**
  * Bandage constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * sets hitbox
  * picks from one of the three sprites from the sprite sheet at random
  */
  Canister(int xCord, int yCord) {
    super(xCord, yCord);
    random = new Random();
    identity = random.nextInt(3);
    loadSprites();
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
  
  /**
  * loadSprites
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
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
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
  
  /**
  * getSpawnHitbox
  * @return the spawning hitbox of the canister, preventing overlapping
  */
  public Rectangle getSpawnHitbox() {
    return spawnHitbox;
  }
}