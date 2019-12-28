import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

class HeadUpDisplay {
  private Camera scroll;
  private int RATIO;
  private int maxX, maxY, xAdjust, yAdjust, xHalf, yHalf, xLock, yLock, vitality, currency, waveNum, zombieCounter;
  
  HeadUpDisplay(Camera scroll, int maxX, int maxY) {
    this.scroll = scroll;
    this.maxX = maxX;
    this.maxY = maxY;
    xHalf = maxX / 2;
    yHalf = maxY / 2;
    loadSprites();
    RATIO = 3;
    vitality = 100;
    currency = 0;
    waveNum = 0;
    zombieCounter = 0;
  }
  
  private void loadSprites() {
  }
  
  public void update(Camera scroll, Human duber) {
    this.scroll = scroll;
    
    xAdjust = -duber.getxCentre() + xHalf;
    yAdjust = -duber.getyCentre() + yHalf;
    
    if (xAdjust > 0) {
      xLock = 0;
    } else if (xAdjust < -4500 + maxX) {
      xLock = 0;
    } else {
      xLock = duber.getxDirection();
    }
    
    if (yAdjust > 0) {
      yLock = 0;
    } else if (yAdjust < -4500 + maxY) {
      yLock = 0;
    } else {
      yLock = duber.getyDirection();
    }
  }
  
  public void draw(Graphics g, Human duber) {
    RATIO = 3;
    
    if (vitality > duber.getHealth()) {
      vitality--;
    } else if (vitality < duber.getHealth()) {
      vitality++;
    }
    
    g.setColor(Color.RED);
    g.fillRect((int)-scroll.getXCamera() + 50 - xLock, (int)-scroll.getYCamera() + 30 - yLock, vitality * RATIO, 25);
    g.setColor(Color.BLACK);
    g.drawRect((int)-scroll.getXCamera() + 50 - xLock, (int)-scroll.getYCamera() + 30 - yLock, 100 * RATIO, 25);
    
    Font font = new Font("Calibri", Font.PLAIN, 30);  
    g.setColor(Color.white);
    g.setFont(font);
    //Currency
    g.drawString("$" + Integer.toString(currency), (int)-scroll.getXCamera() + 1000 - xLock, (int)-scroll.getYCamera() + 60 -yLock);
    //Wave num
    g.drawString("Wave Number: " + Integer.toString(waveNum), (int)-scroll.getXCamera() + 500 - xLock, (int)-scroll.getYCamera() + 60 - yLock);  
    //Zombies left
    g.drawString("Zombies Remaining: " + Integer.toString(zombieCounter), (int)-scroll.getXCamera() + 800 - xLock, (int)-scroll.getYCamera() + 120 - yLock);
  }
  
  public void setCurrency(int currency) {
    this.currency = currency;
  }
  
  public int getCurrency() {
    return currency;
  }
  
  public void setWaveNum(int waveNum){
    this.waveNum = waveNum;
  }
  
  public int getWaveNum() {
    return waveNum;
  }
  
  public void setZombieCounter(int zombieCounter){
    this.zombieCounter = zombieCounter;
  }
  
  public int getZombieCounter() {
    return zombieCounter;
  }
}