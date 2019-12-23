class Camera {
  private float xCamera, yCamera;
  
  Camera(float xCamera, float yCamera) {
    this.xCamera = xCamera;
    this.yCamera = yCamera;
  }
  
  public void follow(Human duber, int screenWidth, int screenHeight) {
    xCamera = -duber.xCord + screenWidth;
    yCamera = -duber.yCord + screenHeight;
  }
  
  public float getXCamera() {
    return xCamera;
  }
  
  public float getYCamera() {
    return yCamera;
  }
}
