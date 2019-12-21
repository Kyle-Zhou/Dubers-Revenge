public class Main {
  public static void main(String[] args) {

    Game game = new Game("Duber\'s Revenge");
    while(true) {
      game.refresh();
      try{ Thread.sleep(10);} catch (Exception exc){}  //delay
    }
    
  }
}