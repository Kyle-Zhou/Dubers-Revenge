import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Human extends Mammal {
  private BufferedImage sheet;
  private BufferedImage[] sprites;
  private final int size = 120, rows = 4, columns = 8;
  private int sprite, frame;
  private boolean moveLock, bumped;
  
  Human(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    sprites = new BufferedImage[rows * columns];
    loadSprites();
    seteWidth(sprites[0].getWidth());
    seteHeight(sprites[0].getHeight());
    setxCentre(xCord + (geteWidth() / 2));
    setyCentre(yCord + (geteHeight() / 2));
    setHitbox(new Rectangle(xCord + 48, yCord + 32, 24, 54));
  }
  
  public void loadSprites() {
    try {
      sheet = ImageIO.read(new File("characterSheet.png"));
      
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          sprites[(i * columns) + j] = sheet.getSubimage(j * size, i * size, size, size);
        }
      }
      
    } catch(Exception e) { System.out.println("Error Loading Sprites... character.png");}
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprites[sprite], getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord() + 48, getyCord() + 32, 24, 54);
  }
  
  public void move() {
    if (moveLock == false){
      getHitbox().setLocation((int)getHitbox().getX() + getxDirection(), (int)getHitbox().getY() + getyDirection());
      setxCord(getxCord() + getxDirection());
      setyCord(getyCord() + getyDirection());
      setxCentre(getxCentre() + getxDirection());
      setyCentre(getyCentre() + getyDirection());
    }
  }
  
  public void collision(North north, South south) { //if player hitbox is changed later on, make edits
    if (getHitbox().intersects(north.getHitbox())) {
      setyDirection(0);
      setyCord(150 - 32);
      setyCentre(getyCord() + (geteHeight() / 2));
      getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
    } else if (getHitbox().intersects(north.getEastHitbox())) {
      setxDirection(0);
      setxCord(4500 - 64 - geteWidth() + 48);
      setxCentre(getxCord() + (geteWidth() / 2));
      getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
    } else if (getHitbox().intersects(south.getHitbox())) { // 81
      setyDirection(0);
      setyCord(4367 - 87);
      setyCentre(getyCord() + (geteHeight() / 2));
      getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
    } else if (getHitbox().intersects(north.getWestHitbox())) {
      setxDirection(0);
      setxCord(64 - 48);
      setxCentre(getxCord() + (geteWidth() / 2));
      getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
    }
  }
  
  public void collision(Obstacle obstacle) {
    if (getHitbox().intersects(obstacle.getHitbox())) {
      if (getxCord() < obstacle.getxCord()) {
        setxDirection(0);
        setxCord(getxCord() - 1);
        setxCentre(getxCord() + (geteWidth() / 2));
        getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
      } else {
        setxDirection(0);
        setxCord(getxCord() + 1);
        setxCentre(getxCord() + (geteWidth() / 2));
        getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
      }
      
      if (getyCord() < obstacle.getyCord()) {
        setyDirection(0);
        setyCord(getyCord() - 1);
        setyCentre(getyCord() + (geteHeight() / 2));
        getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
      } else {
        setyDirection(0);
        setyCord(getyCord() + 1);
        setyCentre(getyCord() + (geteHeight() / 2));
        getHitbox().setLocation((int)getxCord() + 48, (int)getyCord() + 32);
      }
    }
  }
  
  public void update() {
    if (frame % 6 == 0) {
      sprite++;
    }
    
    if (frame > 48) {
      frame = 0;
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
  
  public void setMoveLock(boolean moveLock) {
    this.moveLock = moveLock;
  }
  
  public boolean getMoveLock() {
    return moveLock;
  }
  
  public int getFrame() {
    return frame;
  }
}
