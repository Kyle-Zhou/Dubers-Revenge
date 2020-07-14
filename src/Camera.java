/**
* [Camera.java]
* moves the camera around according to duber's location
* stops following duber if a border is met
* @author Ethan Zhang
**/

class Camera {
  private static float xCamera, yCamera;
  private int maxX, maxY;
  
  
  /**
  * Camera constructor 
  * defaults camera coordinates to (0, 0)
  * takes maximum screen size in to account for when to stop along the right and bottom edges
  */
  Camera(int maxX, int maxY) {
    xCamera = 0;
    yCamera = 0;
    this.maxX = maxX;
    this.maxY = maxY;
  }
  
  
  /**
  * xFollow 
  * moves the camera along the x-axis according to duber's location
  * @param duber, a Human class that is tracked by the camera
  * @param xAdjust, an integer that adjusts the camera's x-coordinate so that duber is at the centre along the x-axis
  */
  public void xFollow(Human duber, int xAdjust) {
    xCamera = -duber.getxCentre() + xAdjust;
    if (xCamera > 0) {
      xCamera = 0;
    } else if (xCamera < -4500 + maxX) {
      xCamera = -4500 + maxX;
    }
  }
  
  /**
  * yFollow 
  * moves the camera along the y-axis according to duber's location
  * @param duber, a Human class that is tracked by the camera
  * @param yAdjust, an integer that adjusts the camera's y-coordinate so that duber is at the centre along the y-axis
  */
  public void yFollow(Human duber, int yAdjust) {
    yCamera = -duber.getyCentre() + yAdjust;
    if (yCamera > 0) {
      yCamera = 0;
    } else if (yCamera < -4500 + maxY) {
      yCamera = -4500 + maxY;
    }
  }
  
  /**
  * getXCamera
  * @return the x-coordinate of the camera, which is found at the top left corner
  */
  public float getXCamera() {
    return xCamera;
  }
  
  /**
  * getYCamera
  * @return the y-coordinate of the camera, which is found at the top left corner
  */
  public float getYCamera() {
    return yCamera;
  }
}