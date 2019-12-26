import java.awt.Rectangle;
import java.awt.Graphics;

abstract class Obstacle extends Entity {
  private int adjust;

  //constructor
  Obstacle(int xCord, int yCord) {
    super(xCord, yCord);
  }
  
  Obstacle(int xCord, int yCord, int eWidth, int eHeight) {
    super(xCord, yCord);
    super.seteWidth(eWidth);
    super.seteHeight(eHeight);
  }
  
  Obstacle(int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    super(xCord, yCord);
    super.seteWidth(eWidth);
    super.seteHeight(eHeight);
    this.adjust = adjust;
  }
  
  abstract void draw(Graphics g);

  public int getxCord() {
    return super.getxCord();
  }

  public int getyCord() {
    return super.getyCord();
  }

  public int geteWidth() {
    return super.geteWidth();
  }

  public int geteHeight() {
    return super.geteHeight();
  }

  public int getAdjust() {
    return adjust;
  }

  public Rectangle getHitbox() {
    return super.getHitbox();
  }

  public void setxCord(int xCord) {
    super.setxCord(xCord);
  }

  public void setyCord(int yCord) {
    super.setyCord(yCord);
  }

  public void seteWidth(int eWidth) {
    super.seteWidth(eWidth);
  }

  public void seteHeight(int eHeight) {
    super.seteHeight(eHeight);
  }

  public void setAdjust(int adjust) {
    this.adjust = adjust;
  }

  public void setHitbox(Rectangle hitbox) {
    super.setHitbox(hitbox);
  }
}