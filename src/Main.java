public class Main {
    public static void main(String[] args) {
        try {
            Runner.start();
        } 
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
