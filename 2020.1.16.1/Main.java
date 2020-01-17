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
/*        if(running==false) {
          System.out.println("You died");
          game.ending();
          while(end) game.refresh2();
        }*/
      }
      System.out.println("You died");
      game.ending();
      while(end) game.refresh();

      if(!menu) System.exit(0);
    }
  }

}