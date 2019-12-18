import java.util.Random;
public class Main {
  public static void main(String[] args) {
    Random random = new Random();

    int[][] map = new int[25][25];
    for(int i = 0; i < 25; i++) {
      for(int j = 0; j < 25; j++) {
        int choice = random.nextInt(100)+1;
        if(choice < 95) map[i][j] = 1;
        else map[i][j] = 2;
      }
    }

    Map panel = new Map("Our Game", map);
    while(true) {
      panel.refresh();
    }
  }
}
