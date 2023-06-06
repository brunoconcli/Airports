import Console.Console;
public class Main {
    public static void main(String[] args) throws NumberFormatException, Exception {
        try {
            Airport[] airportList = {
                new Airport("Brasilia", "BSB"),
                new Airport("Belo Horizonte", "CNF"),
                new Airport("Rio de Janeiro", "GIG"), 
                new Airport("Sao Paulo", "GRU"),
                new Airport("Salvador", "SSA"),
            };
            Console.clear();
            if (Console.getInputOf("Deseja importar lista padrão de aeroportos? (s/n): ").toUpperCase().equals("S")) {
                for (int i = 0; i < airportList.length; i++) 
                    Runner.registerAirport(airportList[i]);
            }
            Runner.start();
        }
        catch (NumberFormatException ne) {
            Runner.menu();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
