abstract class Obstacle extends Entity {
  private int adjust;

  //constructor
  Obstacle(int xCord, int yCord) {
    super(xCord, yCord);
  }

  public int getAdjust() {
    return adjust;
  }

  public void setAdjust(int adjust) {
    this.adjust = adjust;
  }
}