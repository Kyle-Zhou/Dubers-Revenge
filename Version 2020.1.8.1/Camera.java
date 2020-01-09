class Camera {
  private static float xCamera, yCamera;
  private int maxX, maxY;
  
  Camera(int maxX, int maxY) {
    xCamera = 0;
    yCamera = 0;
    this.maxX = maxX;
    this.maxY = maxY;
  }
  
  public void xFollow(Human duber, int xAdjust) {
    xCamera = -duber.getxCentre() + xAdjust;
    if (xCamera > 0) {
      xCamera = 0;
    } else if (xCamera < -4500 + maxX) {
      xCamera = -4500 + maxX;
    }
  }
  
  public void yFollow(Human duber, int yAdjust) {
    yCamera = -duber.getyCentre() + yAdjust;
    if (yCamera > 0) {
      yCamera = 0;
    } else if (yCamera < -4500 + maxY) {
      yCamera = -4500 + maxY;
    }
  }
  
  public float getXCamera() {
    return xCamera;
  }
  
  public float getYCamera() {
    return yCamera;
  }
}