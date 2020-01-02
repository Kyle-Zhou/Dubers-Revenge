import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

class Walker extends Zombie {

  BufferedImage sprite;
  private int cooldown;

  Walker(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprite();
    super.seteWidth(sprite.getWidth());
    super.seteHeight(sprite.getHeight());
    super.setxCentre(xCord + (super.geteWidth() / 2));
    super.setyCentre(yCord + (super.geteHeight() / 2));
    super.setHitbox(new Rectangle(xCord, yCord, super.geteWidth(), super.geteHeight()));
    super.setStartingHealth(health);
    cooldown = getCooldown();
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("walker.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... walker.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight());
    
    g.setColor(Color.red);
    g.fillRect(super.getxCord()+7, super.getyCord()-10, (super.getStartingHealth()) / 2, 7);
    g.setColor(Color.green);
    g.fillRect(super.getxCord()+7, super.getyCord()-10, (getHealth()) / 2, 7);
  }
  
  
  public void attack(Human duber) {
    super.setCooldown(100);
    duber.setHealth(duber.getHealth() - super.getDamage());
  }
  
}