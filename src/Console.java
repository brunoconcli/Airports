import java.io.BufferedReader;
import java.io.InputStreamReader;
import LinkedList.LinkedList;
import LinkedList.Node;

public class Console {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static String getInputOf(String message) throws Exception {
        String input;
        System.out.print(message);
        input = bufferedReader.readLine();
                
        return input;
    }

    public static void pressEnterToContinue() throws Exception {
        Console.getInputOf("\nPress [Enter] to continue...");
    }

    public static String listOptions(String[] list) throws Exception {
        String option;
        for (int i = 0; i < list.length; i++) {
            System.out.println((i+1) + " - " + list[i]);
        }
        option = bufferedReader.readLine();
        return option;
    }

    public static <X> void showLinkedList(LinkedList<X> list, boolean cls) throws Exception {
        if (cls) clear();
        Node<X> current = list.getFirst();
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println((i+1) + " - " + current.getInfo());
            current = current.getNext();
        }
    }
}
