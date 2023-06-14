/**
 * Class responsible for the exhibition and control of every String message used into the terminal
 * 
 * @author Bruno Concli
 * @author Miguel Lopes
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Console {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Clears all the string in the terminal
     */
    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    /**
     * Clears all the string in the terminal printing a message afterwards
     * 
     * @param message
     */
    public static void clearWithMessage(String message) {
        clear();
        Console.println(message);
    }

    /**
     * Uses System.out.print() to print a line
     * 
     * @param message
     */
    public static void print(String message) {
        System.out.print(Colors.RESET+message);
    }

    /** 
     * Uses System.out.println() to print a line
     */
    public static void println(String message) {
        System.out.println(Colors.RESET+message);
    }

    /**
     * Uses BufferedReader to read the input to the message passed as parameter 
     * 
     * @param message
     * @return
     * @throws Exception from bufferedReader class
     */
    public static String getInputOf(String message) throws Exception {
        System.out.print(message);
        return bufferedReader.readLine();
    }

    /**
     * Waits for the [ENTER] key to be pressed, using the getInputOf() method 
     * 
     * @throws Exception according to getInputOf() method
     */
    public static void pressEnterToContinue() throws Exception {
        Console.getInputOf("\nPressione [Enter] para continuar...");
    }

    /**
     * Waits for a input after printint a numerated list containing every element of the passed list as parameter
     * 
     * @param list
     * @return
     * @throws Exception
     */
    public static String listOptions(String[] list) throws Exception {
        for (int i = 0; i < list.length; i++) {
            System.out.println((i+1) + " - " + list[i]);
        }
        return bufferedReader.readLine();
    }
}
