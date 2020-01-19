public class Main {
  public static boolean menu = true, running = true, end = false;
  public static Game game;

  public static void main(String[] args) {
    while (true) {
      Menu mainmenu = new Menu();
      while (menu) {
        mainmenu.refresh();
      }
      //game = new Game("Duber's Revenge");
      while (running) {
        game.refresh();
      }
      System.out.println("You died");
      System.exit(0);
    }
  }
}