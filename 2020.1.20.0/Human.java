import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**[Human.java]
 * This class extends Mammal
 * Class is defined as the Human in the game. It contains all sorts of variables a human would use
 * @author
 */
class Human extends Mammal {
  private BufferedImage sheet;
  private BufferedImage[] sprites;
  private final int size = 120, rows = 4, columns = 8;
  private int sprite, frame;
  private boolean moveLock;

  /**Human constructor
   * the constructor inherits the x coordinate, y coordinate, health, damage, and the speed of the human
   * @param xCord, an integer value that contains the x coordinate of the human
   * @param yCord, an integer value that contains the y coordinate of the human
   * @param health, an integer value that represents the max and spawn health
   * @param damage, an integer value that represents the damage human can deal to others
   * @param speed, an integer value that represents how fast the human can move
   */
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

  /**loadSprites
   * A method that tries to load the animated sprites
   * catches Exception if failed to load
   */
  public void loadSprites() {
    try {
      sheet = ImageIO.read(new File("characterSheet.png"));

      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          sprites[(i * columns) + j] = sheet.getSubimage(j * size, i * size, size, size);
        }
      }

    } catch(Exception e) { System.out.println("Error Loading 'characterSheet.png'...");}
  }

  /**draw
   * A method used to draw out the character
   * @param g, Graphics that allows drawings on JFrame/JPanel
   */
  public void draw(Graphics g) {
    g.drawImage(sprites[sprite], getxCord(), getyCord(), null);
  }

  /**move
   * A method used for the movements of the Human
   */
  public void move() {
    if (moveLock == false){
      getHitbox().setLocation((int)getHitbox().getX() + getxDirection(), (int)getHitbox().getY() + getyDirection());
      setxCord(getxCord() + getxDirection());
      setyCord(getyCord() + getyDirection());
      setxCentre(getxCentre() + getxDirection());
      setyCentre(getyCentre() + getyDirection());
    }
  }

  /**collision
   * A method used to detect the collision of the North, South wall
   * @param north, a wall that consists the North, West, and East wall
   * @param south, wall that consists the South wall
   */
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

  /**collision
   * A method used to detect the collision fo obstacles
   * @param obstacle, an Obstacle object that represents one obstacle
   */
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

  /**update
   * A method used to well-simulate the animations of the movement, so it is not repeating
   */
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

  /**setSprite
   * A method that sets the integer value of sprite number
   * @param sprite, an integer that holds the data of sprite number
   */
  public void setSprite(int sprite) {
    this.sprite = sprite;
  }

  /**getSprite
   * A method that gets the integer value of sprite number
   * @return the integer value of sprite number
   */
  public int getSprite() {
    return sprite;
  }

  /**setMoveLock
   * A method that sets the Human's move lock, meaning that they hit an obstacle that they cannot move
   * @param moveLock, a boolean variable that holds the data of moveLock
   */
  public void setMoveLock(boolean moveLock) {
    this.moveLock = moveLock;
  }

  /**getmoveLock
   * A method that gets the Human's move lock
   * Value is returned based on whether the human hits an obstacle or not
   * @return
   */
  public boolean getMoveLock() {
    return moveLock;
  }

  /**getFrame
   * A method used to get the frame number of the sprite
   * @return the integer value of the frame number of the sprite
   */
  public int getFrame() {
    return frame;
  }
}
