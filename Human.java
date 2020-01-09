import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Human extends Mammal {
  private BufferedImage sheet;
  private BufferedImage[] sprites;
  private final int size = 120, rows = 6, columns = 8;
  private int sprite, frame;
  
  Human(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprites();
    super.seteWidth(sprites[0].getWidth());
    super.seteHeight(sprites[0].getHeight());
    super.setxCentre(xCord + (super.geteWidth() / 2));
    super.setyCentre(yCord + (super.geteHeight() / 2));
    super.setHitbox(new Rectangle(xCord + 48, yCord + 32, 24, 54));
  }
  
  public void loadSprites() {
    try {
      sheet = ImageIO.read(new File("characterSheet.png"));
      sprites = new BufferedImage[rows * columns];
      
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          sprites[(i * columns) + j] = sheet.getSubimage(j * size, i * size, size, size);
        }
      }
      
    } catch(Exception e) { System.out.println("Error Loading Sprites... character.png");}
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprites[sprite], super.getxCord(), super.getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(super.getxCord() + 48, super.getyCord() + 32, 24, 54);
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
      super.setyCord(150 - 32);
      super.setyCentre(super.getyCord() + (super.geteHeight() / 2));
      super.getHitbox().setLocation((int)super.getxCord() + 48, (int)super.getyCord() + 32);
    } else if (super.getHitbox().intersects(north.getEastHitbox())) {
      super.setxDirection(0);
      super.setxCord(4500 - 64 - super.geteWidth() - 72);
      super.setxCentre(super.getxCord() + (super.geteWidth() / 2));
      super.getHitbox().setLocation((int)super.getxCord() + 48, (int)super.getyCord() + 32);
    } else if (super.getHitbox().intersects(south.getHitbox())) { // 81
      super.setyDirection(0);
      super.setyCord(4402 - super.geteHeight());
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
  
  public void update() {
    if (frame % 6 == 0) {
      sprite++;
    }
    frame++;
    
    if (sprite == 7) {
      sprite = 0;
    } else if (sprite == 15) {
      sprite = 8;
    } else if (sprite == 23) {
      sprite = 16;
    } else if (sprite == 31) {
      sprite = 24;
    }
  }
  
  public void setSprite(int sprite) {
    this.sprite = sprite;
  }
  
  public int getSprite() {
    return sprite;
  }
}
