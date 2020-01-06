import java.util.Random;

class Map {

  //class variable (non-static)
  static double x, y;
  private int[][] map = new int[125][125];
  
  Map() {
    Random random = new Random();

    for(int i = 0; i < 125; i++) {
      for(int j = 0; j < 125; j++) {
        int choice = random.nextInt(100)+1;
        if(choice < 90) {
          map[i][j] = 1;
        } else if ((choice >= 90) && (choice < 95)) {
          map[i][j] = 2;
        } else {
          map[i][j] = 3;
        }
      }
    }
  }
  
  public int[][] getMap(){
    return map;
  }
  
}
