import LinkedList.LinkedList;

public class Runner {
    static LinkedList<Airport> airportList = new LinkedList<Airport>();

    public static void start() throws NumberFormatException, Exception {
        Console.clear();
        int runFor = Integer.parseInt(Console.getInputOf("Quantos aeroportos deseja criar?: "));
        for (int i = 0; i < runFor; i++) {
            airportList.pushLast(Airport.registerAirport(i));
        }
        menu();
    }

    public static void menu() throws Exception {
        String[] options = {"Adicionar voo", "Remover voo", "Listar voos de Aeroporto", "Listar todos os voos"}; 

        Console.clear();
        Console.println("Escolha o que deseja fazer: ");
        switch(Console.listOptions(options)) {
            case "1": addFlight();
                      break;

            case "2": 
                      break;

            case "3": 
                      break;

            case "4": 
                      break;

            default: System.out.println("Digite uma opção válida");
                     menu();
                     break;
        }
    }

    public static void addFlight() throws Exception {
        Console.showLinkedList(airportList, true);

        int index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a adição?: "));
        Airport chosen = airportList.getNodeAt(index-1).getInfo();

        Console.println(
            "\n--- Escolhido: " + 
            chosen + " ---\nVoos atuais: " + 
            chosen.getFlightList().toString());

        chosen.pushLast(
            new Flight(Console.getInputOf("\nAeroporto destino: ").toUpperCase(), 
            Console.getInputOf("Digite o número do voo: ")));
        Console.println("\nVoo adicionado com sucesso");

        Console.pressEnterToContinue();
        menu();
    }

    public static void removeFlight() throws Exception {
        Console.println("--- Remover Voo ---");
        Console.showLinkedList(airportList, true);

        int index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a remoção?: "));
        Airport chosen = airportList.getNodeAt(index-1).getInfo();

        chosen.popFlightByCode(Console.getInputOf("Digite o número do voo: "));
        Console.println("\nVoo removido com sucesso.");

        Console.pressEnterToContinue();
        menu();
    }

    public static void listAllFlights() throws Exception {
        
    }

    public static void listflight() throws Exception {

    }
}
