import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Projectile {

  BufferedImage sprite;
    
  public int xCord, yCord, speed, eWidth, eHeight;
  public Rectangle hitbox;
    
  public int targetX, targetY, bulletX, bulletY;
  public float bulletSpeedX, bulletSpeedY;
  
  private boolean limit = false; 
  
  Projectile(int xCord, int yCord, int speed){
    this.xCord = xCord;
    this.yCord = yCord;
    this.speed = speed;
    loadSprite();
    eWidth = sprite.getWidth();
    eHeight = sprite.getHeight();
    hitbox = new Rectangle(xCord, yCord, eWidth, eHeight);
  }

  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("Bullet.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite...");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, xCord, yCord, null);
    g.setColor(Color.RED);
    g.drawRect(xCord, yCord, eWidth, eHeight);
  }
  
  public void firing(Human duber, int mouseX, int mouseY){
    
    float angle = (float) Math.atan2(mouseY - duber.yCord, mouseX - duber.xCord);
    int projectileSpeed = 10;
    if (limit == false){
      bulletSpeedX = (float) ((projectileSpeed) * Math.cos(angle));
      bulletSpeedY = (float) ((projectileSpeed) * Math.sin(angle));
      limit = true;
    } 
    hitbox.setLocation((int)hitbox.getX() + (int)this.bulletSpeedX, (int)hitbox.getY() + (int)this.bulletSpeedY);
    xCord += bulletSpeedX;
    yCord += bulletSpeedY; 
  
  }
    
    
    
    
}