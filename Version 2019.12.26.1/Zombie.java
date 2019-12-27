import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Zombie extends Mammal {
  private int cooldown;
  BufferedImage sprite;

  Zombie(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprite();
    super.seteWidth(sprite.getWidth());
    super.seteHeight(sprite.getHeight());
    super.setxCentre(xCord + (super.geteWidth() / 2));
    super.setyCentre(yCord + (super.geteHeight() / 2));
    super.setHitbox(new Rectangle(xCord, yCord, super.geteWidth(), super.geteHeight()));
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("undead.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... undead.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight());
  }
  
  public void move(Human duber) {
    if ((super.getxCentre() >= duber.getxCentre()) && (super.getyCentre() >= duber.getyCentre())) {
      super.setxDirection(-(getSpeed()));
      super.setyDirection(-(getSpeed()));
    } else if ((super.getxCentre() <= duber.getxCentre()) && (super.getyCentre() >= duber.getyCentre())) {
      super.setxDirection(getSpeed());
      super.setyDirection(-(getSpeed()));
    } else if ((super.getxCentre() <= duber.getxCentre()) && (super.getyCentre() <= duber.getyCentre())) {
      super.setxDirection(getSpeed());
      super.setyDirection(getSpeed());
    } else if ((super.getxCentre() >= duber.getxCentre()) && (super.getyCentre() <= duber.getyCentre())) {
      super.setxDirection(-(getSpeed()));
      super.setyDirection(getSpeed());
    }
    super.getHitbox().setLocation((int)super.getHitbox().getX() + super.getxDirection(),
          (int)super.getHitbox().getY() + super.getyDirection());
    super.setxCord(super.getxCord() + super.getxDirection());
    super.setyCord(super.getyCord() + super.getyDirection());
    super.setxCentre(super.getxCentre() + super.getxDirection());
    super.setyCentre(super.getyCentre() + super.getyDirection());
  }
  
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }
  
  public int getCooldown() {
    return cooldown;
  }
  
  public void attack(Human duber) {
    cooldown = 100;
    duber.setHealth(duber.getHealth() - super.getDamage());
  }
}