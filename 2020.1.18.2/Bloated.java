import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Bloated extends Zombie {

  BufferedImage sprite;
  private int cooldown;

  Bloated(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setxCentre(xCord + (geteWidth() / 2));
    setyCentre(yCord + (geteHeight() / 2));
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
    setStartingHealth(health);
    cooldown = getCooldown();
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("bloated.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... bloated.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord(), geteWidth(), geteHeight());
    
    g.setColor(Color.red);
    g.fillRect(getxCord()+7, getyCord()-10, (getStartingHealth()) / 2, 7);
    g.setColor(Color.green);
    g.fillRect(getxCord()+7, getyCord()-10, (getHealth()) / 2, 7);
  }
  
  
  public void attack(Human duber) {
    setCooldown(150);
    duber.setHealth(duber.getHealth() - getDamage());
  }
  
}