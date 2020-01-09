import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

class Shop {
  
  private int maxX, maxY, xAdjust, yAdjust, xHalf, yHalf, xLock, yLock, xCorrection, yCorrection;
  private int eWidth, eHeight;
  
  private Camera scroll;
  private HeadUpDisplay hud;
  
  private BufferedImage[] sprites;
  BufferedImage sprite;
  
  private boolean addedShop;

    
  Shop(int maxX, int maxY, Camera scroll, HeadUpDisplay hud){

    this.maxX = maxX;
    this.maxY = maxY;
    this.scroll = scroll;
    this.hud = hud;
//    //sprites = new BufferedImage[1];
//    xHalf = maxX / 2;
//    yHalf = maxY / 2;
    loadSprites();

  }
  
  private void loadSprites() {
    try {
      sprite = ImageIO.read(new File("weaponShop.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprites...");}
  }
  
  public void update(Graphics g) {
    
    eWidth = sprite.getWidth();
    eHeight = sprite.getHeight();
    g.drawImage(sprite, (int)-scroll.getXCamera() + maxX/2 - eWidth/2, (int)-scroll.getYCamera() + maxY/2 - eHeight/2, null);

  }
  
  public int geteWidth(){
    return eWidth;
  }
  
  public int geteHeight(){
    return eHeight;
  }
    
  public boolean getAddedShop(){
    return addedShop;
  }
  
  public void setAddedShop(boolean addedShop){
    this.addedShop = addedShop; 
  }
}