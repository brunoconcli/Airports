import LinkedList.LinkedList;

public class Runner {
    static LinkedList<Airport> airportList = new LinkedList<Airport>();

    public static void start() throws NumberFormatException, Exception {
        Console.clear();
        try {
            int runFor = Integer.parseInt(Console.getInputOf("Quantos aeroportos deseja criar?: "));
            for (int i = 0; i < runFor; i++) {
                Console.clear();
                airportList.pushLast(Airport.registerAirport(i));
            }
            menu();
        } catch(NumberFormatException ne) {
            start();
        }
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
        Console.showLinkedList(airportList, false);

        int index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a adição?: "));
        if (index == 0 || index > airportList.getSize()) {
            Console.println("O código passado é inválido");
            Console.pressEnterToContinue();
            addFlight();
        }
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
        Console.clear();
        Console.println("--- Remover Voo ---");
        Console.showLinkedList(airportList, false);

        int index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a remoção?: "));
        if (index == 0 || index > airportList.getSize()) {
            Console.println("O código passado é inválido");
            Console.pressEnterToContinue();
            addFlight();
        }
        Airport chosen = airportList.getNodeAt(index-1).getInfo();

        Console.println(
            "\n--- Escolhido: " + 
            chosen + " ---\nVoos atuais: " + 
            chosen.getFlightList().toString());

        if (!chosen.getFlightList().isEmpty()) {
            chosen.popFlightByCode(Console.getInputOf("Digite o número do voo: "));
            Console.println("\nVoo removido com sucesso.");
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
        for (int i = 0; i < airportList.getSize(); i++) {
            chosen = airportList.getNodeAt(i).getInfo();
            Console.println(i + " - " + chosen + ", Lista de voos: " + chosen.getFlightList());
        }
        Console.pressEnterToContinue();
        menu();
    }

    public static void listFlight() throws Exception {
        Console.clear();
        Console.println("--- Listar voos ---");
        Console.showLinkedList(airportList, false);

        int index = Integer.parseInt(Console.getInputOf("Qual aeroporto deseja listar?: "));
        if (index == 0 || index > airportList.getSize()) {
            Console.println("O código passado é inválido");
            Console.pressEnterToContinue();
            addFlight();
        }
        Airport chosen = airportList.getNodeAt(index-1).getInfo();

        Console.println("Escolhido: " + chosen + ", Lista de voos: " + chosen.getFlightList());

        Console.pressEnterToContinue();
        menu();
    }
}
