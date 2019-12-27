import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

class Camera {
  private static float xCamera, yCamera;
  private static int maxX, maxY, currency, xLock, yLock;
  
  Camera(float xCamera, float yCamera, int maxX, int maxY) {
    this.xCamera = xCamera;
    this.yCamera = xCamera;
    this.maxX = maxX;
    this.maxY = maxY;
    currency = 0;
  }
  
  public void xFollow(Human duber) {
    xCamera = -duber.getxCentre() + maxX / 2;
    if (xCamera > 0) {
      xCamera = 0;
      xLock = 0;
    } else if (xCamera < -4500 + maxX) {
      xCamera = -4500 + maxX;
      xLock = 0;
    } else {
      xLock = duber.getxDirection();
    }
  }
  
  public void yFollow(Human duber) {
    yCamera = -duber.getyCentre() + maxY / 2;
    if (yCamera > 0) {
      yCamera = 0;
      yLock = 0;
    } else if (yCamera < -4500 + maxY) {
      yCamera = -4500 + maxY;
      yLock = 0;
    } else {
      yLock = duber.getyDirection();
    }
  }
  
  public float getXCamera() {
    return xCamera;
  }
  
  public float getYCamera() {
    return yCamera;
  }
  
  private void loadSprites() {
  }
  
  public void draw(Graphics g, Human duber) { //make tweaks later
    int ratio = 3;
    g.setColor(Color.RED);
    g.fillRect((int)-xCamera + 50 - xLock, (int)-yCamera + 30 - yLock, duber.getHealth() * ratio, 25);
    g.setColor(Color.BLACK);
    g.drawRect((int)-xCamera + 50 - xLock, (int)-yCamera + 30 - yLock, 100 * ratio, 25);
    Font font = new Font("Calibri", Font.PLAIN, 50);  
    g.setColor(Color.white);
    g.setFont(font);
    g.drawString(Integer.toString(currency), (int)-xCamera + 1000 - xLock, (int)-yCamera + 60 -yLock);
  }
  
  public void setCurrency(int currency) {
    this.currency = currency;
  }
  
  public int getCurrency() {
    return currency;
  }
}