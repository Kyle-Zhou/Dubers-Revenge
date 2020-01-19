import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Spitter extends Zombie {

  BufferedImage sprite;
  private int cooldown;
  private Rectangle range;
  
  Spitter(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setxCentre(xCord + (geteWidth() / 2));
    setyCentre(yCord + (geteHeight() / 2));
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
    range = new Rectangle(getxCord() - 200, getyCord() - 200, 400 + geteWidth(), 400 + geteWidth());
    setStartingHealth(health);
    cooldown = getCooldown();
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("spitter.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... spitter.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getHitbox().x, getHitbox().y, getHitbox().width, getHitbox().height);
    g.drawRect(range.x, range.y, range.width, range.height);
  }
  
  public void adjustRange() {
    range.setLocation(getxCord() - 200, getyCord() - 200);
  }
  
  public void attack(Human duber) {
    setCooldown(50);
    duber.setHealth(duber.getHealth() - getDamage());
  } 
  
  public Rectangle getRange() {
    return range;
  }
}