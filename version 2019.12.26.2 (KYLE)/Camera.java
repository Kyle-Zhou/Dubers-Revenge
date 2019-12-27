class Camera {
  private static float xCamera, yCamera;
  
  Camera(float xCamera, float yCamera) {
    this.xCamera = xCamera;
    this.yCamera = yCamera;
  }
  
  public void xFollow(Human duber, int xAdjust) {
    xCamera = -duber.getxCentre() + xAdjust;
    if (xCamera > 0) {
      xCamera = 0;
    } else if (xCamera < -2995) {
      xCamera = -2995;
    }
  }
  
  public void yFollow(Human duber, int yAdjust) {
    yCamera = -duber.getyCentre() + yAdjust;
    if (yCamera > 0) {
      yCamera = 0;
    } else if (yCamera < -3560) {
      yCamera = -3560;
    }
  }
  
  public float getXCamera() {
    return xCamera;
  }
  
  public float getYCamera() {
    return yCamera;
  }
}