
import DataStructure.LinkedList.LinkedList;
import Console.Colors;
import Console.Console;
public class Runner {
    static LinkedList<Airport> airportList = new LinkedList<Airport>();
    static int airportsCreated = 0;
    static String message = "";

    public static void start() throws NumberFormatException, Exception {
        Console.clear();
        try {
            
            airportsCreated = Integer.parseInt(Console.getInputOf("Quantos aeroportos deseja criar?: "));

            for (int i = 0; i < airportsCreated; i++) {
                verifyAndRegister(i);
            }
        } catch(NumberFormatException ne) {
            start();
        }
        menu();
    }

    public static boolean alreadyExists(Airport airport) throws Exception {
        int index = airportList.indexOf(airport);
        if (index < 0) return false;
        return true;
    }

    public static boolean codeAlreadyExists(String code) throws Exception {
        for (int i = 0; i < airportList.getSize()+1; i++) {
            if (airportList.getElementAt(i).getCode().equals(code))
                return true;
        }
        return false;
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

            default: System.out.println(Colors.YELLOW + "Digite uma opção válida" + Colors.RESET);
                     menu();
                     break;
        }
    }

    public static void addFlight() throws Exception {
        Console.clear();
        Console.println("--- Adicionar Voo ---");
        Console.println("0 - Sair");
        Runner.showLinkedList(airportList, false);
        int index = 0;
        try {
            index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a adição?: "));
        }
        catch(NumberFormatException ne) {
            Console.println(Colors.YELLOW + "O código passado deve ser um número inteiro" + Colors.RESET);
            Console.pressEnterToContinue();
            addFlight();
        }

        if (index < 0 || index > airportList.getSize()+1) {
            Console.println(Colors.YELLOW+"O código passado é inválido"+Colors.RESET);
            Console.pressEnterToContinue();
            addFlight();
        }
        else if (index == 0) {
            menu();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println(
            "\n--- Escolhido: " + 
            chosen + " ---\nVoos atuais: " + 
            chosen.getFlightList().toString());

        String airportCode = Console.getInputOf("\nAeroporto destino: ").toUpperCase();

        if (!codeAlreadyExists(airportCode)) {
            if (Console.getInputOf(Colors.YELLOW + "O aerporto passado não existe" + Colors.RESET + "\nDeseja criá-lo? (s/n):").toUpperCase().equals("S")) {
                Console.clear();
                verifyAndRegister(airportsCreated);
            }
            Console.pressEnterToContinue();
            addFlight();
        }
        if (airportCode.equals(chosen.getCode())) {
            Console.println(Colors.YELLOW + "O aeroporto passado deve ser distinto do aeroporto escolhido" + Colors.RESET);
            Console.pressEnterToContinue();
            addFlight();
        }
        String flightNumber = Console.getInputOf("Digite o número do voo: ");


        chosen.pushLast(new Flight(airportCode, flightNumber));
        Console.println(Colors.GREEN + "\nVoo adicionado com sucesso" + Colors.RESET);

        Console.pressEnterToContinue();
        menu();
    }

    public static void removeFlight() throws Exception {
        Console.clear();
        Console.println("--- Remover Voo ---");
        Console.println("0 - Sair");
        Runner.showLinkedList(airportList, false);
        int index = 0;
        try {
            index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a remoção?: "));
        }
        catch(NumberFormatException ne) {
            Console.println(Colors.YELLOW + "O código passado deve ser um número inteiro" + Colors.RESET);
            Console.pressEnterToContinue();
            removeFlight();
        }
        if (index < 0 || index > airportList.getSize()+1) {
            Console.println(Colors.YELLOW+"O código passado é inválido"+Colors.RESET);
            Console.pressEnterToContinue();
            removeFlight();
        }
        else if (index == 0) {
            menu();
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
                Console.println(Colors.GREEN + "\nVoo removido com sucesso." + Colors.RESET);

            } catch(Exception e) {
                Console.println("O voo digitado não existe nesse aeroporto");
            }
        }
        else 
            Console.println(Colors.YELLOW + "Não há voos agendados neste aeroporto, nada para remover" + Colors.RESET);

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
        Console.println("0 - Sair");
        Runner.showLinkedList(airportList, false);
        int index = 0;
        try {
            index = Integer.parseInt(Console.getInputOf("Qual aeroporto deseja listar?: "));
        }
        catch(NumberFormatException ne) {
            Console.println(Colors.YELLOW + "O código passado deve ser um número inteiro" + Colors.RESET);
            Console.pressEnterToContinue();
            listFlight();
        }
        if (index < 0 || index > airportList.getSize()+1) {
            Console.println("O código passado é inválido");
            Console.pressEnterToContinue();
            listFlight();
        }
        else if (index == 0) {
            menu();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println("Escolhido: " + chosen + ", Lista de voos: " + chosen.getFlightList());

        Console.pressEnterToContinue();
        menu();
    }
    public static void registerAirport(Airport airport) throws Exception {
        airportList.addIntoLast(airport);
    }
    public static Airport registerAirport(int i) throws Exception {
        Airport createdAirport = new Airport("STANDBY", "APT");
        String city = "", code = "1234";
        boolean missFilled = false;


        while (code.length() != 3 || city.isBlank() || code.isBlank()) {
            if (missFilled)
                System.out.println("O campo 'Cidade' ou 'Sigla' não foi preenchido corretamente\n");

            System.out.println("---" + (i+1) + "º AEROPORTO ---\n");
            city = Console.getInputOf("Cidade a qual pertence: ").trim();
            code = Console.getInputOf("Sigla do aerporto (3 letras): ").trim().toUpperCase();
            
            missFilled = true;
            Console.clear();
        }

        createdAirport = new Airport(
            city.substring(0, 1).toUpperCase() + 
            city.substring(1), code
        );
    
        return createdAirport;

    }
    public static void verifyAndRegister(int i) throws Exception {
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

    public static <X> void showLinkedList(LinkedList<X> list, boolean cls) throws Exception {
        if (cls) Console.clear();
        for (int i = 0; i <= list.getSize(); i++)
            System.out.println((i+1) + " - " + list.getElementAt(i));
    }
}
