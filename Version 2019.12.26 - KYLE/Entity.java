import java.awt.Rectangle;

abstract class Entity {

  private int xCord, yCord, eWidth, eHeight;
  private Rectangle hitbox;

  Entity (int x, int y) {
    this.xCord = x;
    this.yCord = y;
  }

  public void setxCord(int xCord) {
    this.xCord = xCord;
  }

  public void setyCord(int yCord) {
    this.yCord = yCord;
  }

  public void seteWidth(int eWidth) {
    this.eWidth = eWidth;
  }

  public void seteHeight(int eHeight) {
    this.eHeight = eHeight;
  }

  public Rectangle getHitbox() {
    return hitbox;
  }

  public void setHitbox(Rectangle hitbox) {
    this.hitbox = hitbox;
  }

  public int getxCord() {
    return xCord;
  }

  public int getyCord() {
    return yCord;
  }

  public int geteWidth() {
    return eWidth;
  }

  public int geteHeight() {
    return eHeight;
  }
}
