import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Human extends Mammal {
  BufferedImage sprite;
  
  Human(int xCord, int yCord, int health, int damage, int speed) {
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
      sprite = ImageIO.read(new File("character.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... character.png");}
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord(), super.getyCord(), super.geteWidth(), super.geteHeight());
  }
  
  public void move() {
    super.getHitbox().setLocation((int)super.getHitbox().getX() + super.getxDirection(),
                                  (int)super.getHitbox().getY() + super.getyDirection());
    super.setxCord(super.getxCord() + super.getxDirection());
    super.setyCord(super.getyCord() + super.getyDirection());
    super.setxCentre(super.getxCentre() + super.getxDirection());
    super.setyCentre(super.getyCentre() + super.getyDirection());
  }
  
  public void collision(North north, South south) { //if player hitbox is changed later on, make edits
    if (super.getHitbox().intersects(north.getHitbox())) {
      super.setyDirection(0);
      super.setyCord(100);
      super.setyCentre(super.getyCord() + (super.geteHeight() / 2));
      super.getHitbox().setLocation((int)super.getxCord() + super.getxDirection(),
                                    (int)super.getyCord() + super.getyDirection());
    } else if (super.getHitbox().intersects(north.getEastHitbox())) {
      super.setxDirection(0);
      super.setxCord(4500 - 64 - super.geteWidth());
      super.setxCentre(super.getxCord() + (super.geteWidth() / 2));
      super.getHitbox().setLocation((int)super.getxCord() + super.getxDirection(),
                                    (int)super.getyCord() + super.getyDirection());
    } else if (super.getHitbox().intersects(south.getHitbox())) { // 81
      super.setyDirection(0);
      super.setyCord(4500 - 81 - super.geteHeight());
      super.setyCentre(super.getyCord() + (super.geteHeight() / 2));
      super.getHitbox().setLocation((int)super.getxCord() + super.getxDirection(),
                                    (int)super.getyCord() + super.getyDirection());
    } else if (super.getHitbox().intersects(north.getWestHitbox())) {
      super.setxDirection(0);
      super.setxCord(64);
      super.setxCentre(super.getxCord() + (super.geteWidth() / 2));
      super.getHitbox().setLocation((int)super.getxCord() + super.getxDirection(),
                                    (int)super.getyCord() + super.getyDirection());
    }
  }
  
}
