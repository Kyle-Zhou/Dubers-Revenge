import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

class Shop {
  
  private int maxX, maxY;
  private int eWidth, eHeight, buttonEWidth, buttonEHeight;
  private int currentShop = 1;
  
  private Camera scroll;
  private Human duber;
  private Spawner spawner;
 
  private HeadUpDisplay hud;
  
  private BufferedImage[] sprites;
  private Rectangle selfButtonHitbox, weaponButtonHitbox;
  
  private boolean addedShop;
    
  Shop(int maxX, int maxY, Camera scroll, HeadUpDisplay hud){

    this.maxX = maxX;
    this.maxY = maxY;
    this.scroll = scroll;
    this.hud = hud;
    sprites = new BufferedImage[6];
    selfButtonHitbox = new Rectangle(0,0,0,0);
    weaponButtonHitbox = new Rectangle(0,0,0,0);
      
    loadSprites();
  }
  
  private void loadSprites() {
    try {
      sprites[0] = ImageIO.read(new File("weaponShop.png"));
      sprites[1] = ImageIO.read(new File("SELFbutton.png"));
      sprites[2] = ImageIO.read(new File("WEAPONSbuttonSelected.png"));
      sprites[3] = ImageIO.read(new File("selfShop.png"));
      sprites[4] = ImageIO.read(new File("WeaponButton.png"));
      sprites[5] = ImageIO.read(new File("SELFbuttonSelected.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprites...");}
  }
  
  public void update(Graphics g) {
    eWidth = sprites[0].getWidth();
    eHeight = sprites[0].getHeight();   
    buttonEWidth = sprites[1].getWidth();
    buttonEHeight = sprites[1].getWidth();
    
    int buttonX = (int)-scroll.getXCamera() + maxX/2 - eWidth/2 + 15;
    int selfButtonY = (int)-scroll.getYCamera() + maxY/2 - eHeight/2 + maxX/11;
    int weaponButtonY = (int)-scroll.getYCamera() + maxY/2 - eHeight/2 + 160;
    
    if (currentShop == 1){
      //weapon shop
      g.drawImage(sprites[0], (int)-scroll.getXCamera() + maxX/2 - eWidth/2 - 10, (int)-scroll.getYCamera() + maxY/2 - eHeight/2 - 20, null);
      //self button
      g.drawImage(sprites[1], buttonX, selfButtonY, null);
      //weapon button SELECTED
      g.drawImage(sprites[2], buttonX, weaponButtonY, null);
                         ///////DONT KNOW IF I WANNA DO CONSTANTS (+15) OR PROPORTIONATE (+maxX/9)
    } else if (currentShop == 2){ 
      //self shop
      g.drawImage(sprites[3], (int)-scroll.getXCamera() + maxX/2 - eWidth/2 - 10, (int)-scroll.getYCamera() + maxY/2 - eHeight/2 - 20, null);
      //self button selected
      g.drawImage(sprites[5], buttonX, selfButtonY, null);
      //weapom button
      g.drawImage(sprites[4], buttonX, weaponButtonY, null);
    } else if (currentShop == 3){
    
    }
    
    selfButtonHitbox = new Rectangle(buttonX, selfButtonY, buttonEWidth, buttonEHeight);
    weaponButtonHitbox = new Rectangle(buttonX, weaponButtonY, buttonEWidth, buttonEHeight);
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
  
  public int getCurrentShop(){
    return currentShop;
  }
  
  public void setCurrentShop(int currentShop){
    this.currentShop = currentShop;
  }
  
  public Rectangle getSelfButtonHitbox() {
    return selfButtonHitbox;
  }

  public Rectangle getWeaponButtonHitbox() {
    return weaponButtonHitbox;
  }
}