
import DataStructure.LinkedList.LinkedList;
import Console.Colors;
import Console.Console;
public class Runner {
    static LinkedList<Airport> airportList = new LinkedList<Airport>();

    public static void start() throws NumberFormatException, Exception {
        Console.clear();
        try {
            
            int runFor = Integer.parseInt(Console.getInputOf("Quantos aeroportos deseja criar?: "));
            String message = "";

            for (int i = 0; i < runFor; i++) {
                Console.clearWithMessage(message);
                message = "";

                Airport newAirport = registerAirport(i);
                if (!alreadyExists(newAirport)) {
                    airportList.addIntoLast(newAirport);
                }
                else {
                    message = Colors.YELLOW+"O aeroporto passado já existe\n"+Colors.RESET;
                    i--;
                }
            }
            menu();
        } catch(NumberFormatException ne) {
            start();
        }
    }

    public static boolean alreadyExists(Airport airport) throws Exception {
        int index = airportList.indexOf(airport);
        if (index < 0) return false;
        return true;
    }

    public static void menu() throws Exception {
        String[] options = {"Adicionar voo", "Remover voo", "Listar voos de Aeroporto", "Listar todos os voos"}; 

        Console.clear();
        Console.println("Escolha o que deseja fazer: ");
        switch(Console.listOptions(options)) {
            case "1": addFlight();
                      break;

            case "2": removeFlight();
                      break;

            case "3": listFlight();
                      break;

            case "4": listAllFlights();
                      break;

            default: System.out.println("Digite uma opção válida");
                     menu();
                     break;
        }
    }

    public static void addFlight() throws Exception {
        Console.clear();
        Console.println("--- Adicionar Voo ---");
        Runner.showLinkedList(airportList, false);

        int index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a adição?: "));
        if (index == 0 || index > airportList.getSize()+1) {
            Console.println(Colors.YELLOW+"O código passado é inválido"+Colors.RESET);
            Console.pressEnterToContinue();
            addFlight();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println(
            "\n--- Escolhido: " + 
            chosen + " ---\nVoos atuais: " + 
            chosen.getFlightList().toString());

        String airportCode = Console.getInputOf("\nAeroporto destino: ").toUpperCase();
        String flightNumber = Console.getInputOf("Digite o número do voo: ");


        chosen.pushLast(new Flight(airportCode, flightNumber));
        Console.println("\nVoo adicionado com sucesso");

        Console.pressEnterToContinue();
        menu();
    }

    public static void removeFlight() throws Exception {
        Console.clear();
        Console.println("--- Remover Voo ---");
        Runner.showLinkedList(airportList, false);

        int index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a remoção?: "));
        if (index == 0 || index > airportList.getSize()+1) {
            Console.println(Colors.YELLOW+"O código passado é inválido"+Colors.RESET);
            Console.pressEnterToContinue();
            removeFlight();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println(
            "\n--- Escolhido: " + 
            chosen + " ---\nVoos atuais: " + 
            chosen.getFlightList().toString());

        if (!chosen.getFlightList().isEmpty()) {
            try {
                String flightCode = Console.getInputOf("Digite o número do voo: ");
                chosen.popFlightByCode(flightCode);
                Console.println("\nVoo removido com sucesso.");

            } catch(Exception e) {
                Console.println("O voo digitado não existe nesse aeroporto");
            }
        }
        else 
            Console.println("Não há voos agendados neste aeroporto, nada para remover");

        Console.pressEnterToContinue();
        menu();
    }

    public static void listAllFlights() throws Exception {
        Console.clear();
        Console.println("--- Listar todos os voos ---");

        Airport chosen;
        for (int i = 0; i <= airportList.getSize(); i++) {
            chosen = airportList.getElementAt(i);
            Console.println(i + " - " + chosen + ", Lista de voos: " + chosen.getFlightList());
        }
        Console.pressEnterToContinue();
        menu();
    }

    public static void listFlight() throws Exception {
        Console.clear();
        Console.println("--- Listar voos ---");
        Runner.showLinkedList(airportList, false);

        int index = Integer.parseInt(Console.getInputOf("Qual aeroporto deseja listar?: "));
        if (index == 0 || index > airportList.getSize()+1) {
            Console.println("O código passado é inválido");
            Console.pressEnterToContinue();
            listFlight();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println("Escolhido: " + chosen + ", Lista de voos: " + chosen.getFlightList());

        Console.pressEnterToContinue();
        menu();
    }

    public static Airport registerAirport(int i) throws Exception {
        Airport createdAirport = new Airport("STANDBY", "APT");
        String city = "", code = "1234";
        int missFillCount = 0;


        while (code.length() != 3 || city.isBlank() || code.isBlank()) {
            if (missFillCount > 0)
            System.out.println("O campo 'Cidade' ou 'Sigla' não foi preenchido corretamente\n");

            System.out.println("---" + (i+1) + "º AEROPORTO ---\n");
            city = Console.getInputOf("Cidade a qual pertence: ").trim();
            code = Console.getInputOf("Sigla do aerporto (3 letras): ").trim().toUpperCase();
            
            missFillCount++;
            Console.clear();
        }

        createdAirport = new Airport(
            city.substring(0, 1).toUpperCase() + 
            city.substring(1), code
        );
    
        return createdAirport;

    }

    public static <X> void showLinkedList(LinkedList<X> list, boolean cls) throws Exception {
        if (cls) Console.clear();
        for (int i = 0; i <= list.getSize(); i++)
            System.out.println((i+1) + " - " + list.getElementAt(i));
    }
}
