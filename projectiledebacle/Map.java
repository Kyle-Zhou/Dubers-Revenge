import java.util.Random;

class Map {

  //class variable (non-static)
  static double x, y;
  private int[][] map = new int[25][25];
  
  Map() {

    Random random = new Random();
    for(int i = 0; i < 25; i++) {
      for(int j = 0; j < 25; j++) {
        int choice = random.nextInt(100)+1;
        if(choice < 95) {
          map[i][j] = 1;
        } else {
          map[i][j] = 2;
        }
      }
    }
        
  }
  
  public int[][] getMap(){
    return map;
  }
  
}