package Console;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Console {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static void clearWithMessage(String message) {
        clear();
        Console.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static String getInputOf(String message) throws Exception {
        System.out.print(message);
        return bufferedReader.readLine();
    }

    public static void pressEnterToContinue() throws Exception {
        Console.getInputOf("\nPress [Enter] to continue...");
    }

    public static String listOptions(String[] list) throws Exception {
        for (int i = 0; i < list.length; i++) {
            System.out.println((i+1) + " - " + list[i]);
        }
        return bufferedReader.readLine();
    }
}
