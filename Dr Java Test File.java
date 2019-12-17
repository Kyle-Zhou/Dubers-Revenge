import java.util.Scanner;

class test {
    
    public static void main(String[] args) {
        
        Scanner a = new Scanner(System.in);
        
        System.out.println("Please enter integer");
        
        int x = a.nextInt();
        String str = "*";
        for (int i = x; i > 0; i--){
          for (int j = 1; j <= i; j++){
            System.out.printf(str);
          }
          System.out.println();
        }
    }
}