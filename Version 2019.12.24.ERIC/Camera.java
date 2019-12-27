class Camera {
  private static float xCamera, yCamera;
  
  Camera(float xCamera, float yCamera) {
    this.xCamera = xCamera;
    this.yCamera = yCamera;
  }
  
  ig my god ajdnada
  
  public void follow(Human duber, int xAdjust, int yAdjust) {
    xCamera = -duber.xCentre + xAdjust;
    yCamera = -duber.yCentre + yAdjust;
  }
  
  public float getXCamera() {
    return xCamera;
  }
  
  public float getYCamera() {
    return yCamera;
  }
}
