/**
 * Class responsible for the exibition of the usage interface 
 * containing methods to manage users input and respective 
 * responses and exhibitions
 * 
 * The class contains methods that manage the Menu, flight 
 * addition, flight removal and airports exhibitions 
 * 
 * Sirlene de Fátima da Silva 
 * @author Bruno Concli
 * @author Miguel Lopes
 */
import DataStructure.LinkedList.LinkedList;
public class Runner {
    static LinkedList<Airport> airportList = new LinkedList<Airport>();
    static int airportsToBeCreated = 0;
    static String message = "";

    /**
     * Allows the inserption of new Airport objects into the airportList, 
     * repeating according to the received input that represent the amount
     * of objects
     * 
     * @throws NumberFormatException
     * @throws Exception in case the input received is passed incorrectly
     */
    public static void start() throws NumberFormatException, Exception {
        Console.clear();
        Console.println(message);
        try {
            
            airportsToBeCreated = Integer.parseInt(Console.getInputOf("Quantos aeroportos deseja criar?: "));

            if (airportsToBeCreated == 0 && airportList.isEmpty()) {
                message = Colors.YELLOW + "É necessário haver pelo menos um aeroporto para iniciar o programa" + Colors.RESET;   
                start();
            }
            for (int i = 0; i < airportsToBeCreated; i++) {
                verifyAndRegister(i);
            }
        } catch(NumberFormatException ne) {
            start();
        }
        menu();
    }

    /**
     * 
     * @param airport
     * @return True in case the Airport object passed is already in the list
     * @throws Exception in case the parameter is passed incorrectly
     */
    public static boolean alreadyExists(Airport airport) throws Exception {
        int index = airportList.indexOf(airport);
        if (index < 0) return false;
        return true;
    }

    /**
     * 
     * @param code
     * @return true in case the Flight code is already present in the airport list
     * @throws Exception in case the index is passed incorrectly
     */
    public static boolean codeAlreadyExists(String code) throws Exception {
        for (int i = 0; i < airportList.getSize(); i++) {
            if (airportList.getElementAt(i).getCode().equals(code))
                return true;
        }
        return false;
    }

    /**
     * 
     * @param airportCode
     * @return the attribute name of the airport that contais the passed code, present in the airportList
     * @throws Exception in case the airport is passed incorrectly
     */
    public static String getCityByAirportCode(String airportCode) throws Exception {
        if (!codeAlreadyExists(airportCode)) {
            return "O aeroporto passado não existe";
        }
        Airport airport = airportList.getFirstElement();
        for (int i = 0; i < airportList.getSize(); i++) {
            airport = airportList.getElementAt(i);
            if (airport.getCode().equals(airportCode))
                break;
        }

        return airport.getCity();
    }

    /**
     * Method that exhibes all the functionalities in the program and allow the navigation through each one of them
     * 
     * @throws Exception according to the incorrect usage of the methods called in the swtch case sctructure
     */
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

    /**
     * Method that exhibes options to and allows the creation of new aiports added into the airportlist
     * 
     * @throws Exception in case of miss-passed informations 
     */
    public static void addFlight() throws Exception {
        Console.clear();
        Console.println(Colors.GREEN + "--- Adicionar Voo ---" + Colors.RESET);
        Console.println("0 - Sair");
        Runner.showLinkedList(airportList, false);
        int index = 0;
        try {
            index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a adição?: "));
        }
        catch(NumberFormatException ne) {
            Console.println(Colors.YELLOW + "O ínidce passado deve ser um número inteiro" + Colors.RESET);
            Console.pressEnterToContinue();
            addFlight();
        }

        if (index < 0 || index > airportList.getSize()) {
            Console.println(Colors.YELLOW+"O ínidce passado é inválido"+Colors.RESET);
            Console.pressEnterToContinue();
            addFlight();
        }
        else if (index == 0) {
            menu();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println(
            Colors.CYAN +
            "\n--- Escolhido: " + 
            chosen + " ---\nVoos atuais: " + 
            chosen.getFlightList().toString() +
            Colors.RESET
        );

        String airportCode = Console.getInputOf("\nAeroporto destino: ").trim().toUpperCase();

        if (!codeAlreadyExists(airportCode)) {
            if (Console.getInputOf(Colors.YELLOW + "O aerporto passado não existe" + Colors.RESET + "\nDeseja criá-lo? (s/n):").toUpperCase().equals("S")) {
                Console.clear();
                verifyAndRegister(airportsToBeCreated);
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
        try {
            Integer.valueOf(flightNumber);
            chosen.getFlightByCode(flightNumber);
            Console.println(Colors.YELLOW + "\nO código de voo passado já existe, insira outro código" + Colors.RESET);
            Console.pressEnterToContinue();
            addFlight();
        }
        catch (NumberFormatException ne) {
            Console.println(Colors.YELLOW + "\nO código passado deve conter apenas números" + Colors.RESET);
            Console.pressEnterToContinue();

            addFlight();
        }
        catch(Exception e) {
            if (chosen.getFlightList().isEmpty())
                chosen.pushFirst(new Flight(airportCode, flightNumber));
            else
                chosen.pushLast(new Flight(airportCode, flightNumber));
    
            Console.println(Colors.GREEN + "\nVoo adicionado com sucesso a cidade " + getCityByAirportCode(airportCode) + Colors.RESET);
    
            Console.pressEnterToContinue();
            menu();
        }
    }

    /**
     * Method that exhibes options to and allows the removal of aiports present in the airportlist
     * 
     * @throws Exception in case of miss-passed informations 
     */
    public static void removeFlight() throws Exception {
        Console.clear();
        Console.println(Colors.RED + "--- Remover Voo ---" + Colors.RESET);
        Console.println("0 - Sair");
        Runner.showLinkedList(airportList, false);
        int index = 0;
        try {
            index = Integer.parseInt(Console.getInputOf("Qual o aeroporto em que deseja fazer a remoção?: "));
        }
        catch(NumberFormatException ne) {
            Console.println(Colors.YELLOW + "O índice passado deve ser um número inteiro" + Colors.RESET);
            Console.pressEnterToContinue();
            removeFlight();
        }
        if (index < 0 || index > airportList.getSize()) {
            Console.println(Colors.YELLOW+"O índice passado é inválido"+Colors.RESET);
            Console.pressEnterToContinue();
            removeFlight();
        }
        else if (index == 0) {
            menu();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println(
            Colors.MAGENTA + 
            "\n--- Escolhido: " + 
            chosen + " ---\nVoos atuais: " + 
            chosen.getFlightList().toString() +
            Colors.RESET
            );

        if (!chosen.getFlightList().isEmpty()) {
            try {
                String flightCode = Console.getInputOf("Digite o número do voo: ");
                chosen.popFlightByCode(flightCode);
                Console.println(Colors.GREEN + "\nVoo removido com sucesso." + Colors.RESET);

            } catch(Exception e) {
                Console.println(Colors.YELLOW + "O voo digitado não existe nesse aeroporto" + Colors.RESET);
            }
        }
        else 
            Console.println(Colors.YELLOW + "Não há voos agendados neste aeroporto, nada para remover" + Colors.RESET);

        Console.pressEnterToContinue();
        menu();
    }

    /**
     * Method that exhibes every airport and their respective flight lists present into the airportList
     * 
     * @throws Exception in case of incorrectly passed parameter in the getElement() method 
     */
    public static void listAllFlights() throws Exception {
        Console.clear();
        Console.println(Colors.BLUE_UNDERLINED + "--- Listar todos os voos ---\n" + Colors.RESET);

        Airport chosen;
        for (int i = 0; i < airportList.getSize(); i++) {
            chosen = airportList.getElementAt(i);
            Console.println(i + " - " + chosen + ", Lista de voos: " + chosen.getFlightList());
        }
        Console.pressEnterToContinue();
        menu();
    }

    /**
     * Method that exhibes the informations of the selected airport by users input present in the aiportlist
     * 
     * @throws Exception according to Exceptions into showLinkedList() method
     */
    public static void listFlight() throws Exception {
        Console.clear();
        Console.println(Colors.BLUE + "--- Listar voos ---" + Colors.RESET);
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
        if (index < 0 || index > airportList.getSize()) {
            Console.println("O código passado é inválido");
            Console.pressEnterToContinue();
            listFlight();
        }
        else if (index == 0) {
            menu();
        }
        Airport chosen = airportList.getElementAt(index-1);

        Console.println(Colors.CYAN + "Escolhido: " + chosen + ", Lista de voos: " + chosen.getFlightList() + Colors.RESET);

        Console.pressEnterToContinue();
        menu();
    }

    /**
     * Allows the creation of new airports
     * 
     * @param airport
     * @throws Exception in case of the parameter airport being passed incorrectly
     */
    public static void registerAirport(Airport airport) throws Exception {
        airportList.addIntoLast(airport);
    }
    public static Airport registerAirport(int i) throws Exception {
        Airport createdAirport = new Airport("STANDBY", "APT");
        String city = "", code = "1234";
        boolean missFilled = false;


        while (code.length() != 3 || city.isBlank() || code.isBlank()) {
            if (missFilled)
                System.out.println(Colors.YELLOW + "O campo 'Cidade' ou 'Sigla' não foi preenchido corretamente\n" + Colors.RESET);

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

    /**
     * Verifies wheter every information has been passed correctly and if so creates the airport and adds it to the list
     * 
     * @param i
     * @throws Exception
     */
    public static void verifyAndRegister(int i) throws Exception {
        Console.clearWithMessage(message);
        message = "";

        Airport newAirport = registerAirport(i);
        if (!alreadyExists(newAirport)) {
            if (!codeAlreadyExists(newAirport.getCode()))
                airportList.addIntoLast(newAirport);
            else {
                message = Colors.YELLOW + "Cidades diferentes não podem possuir uma mesma sigla de aeroporto" + Colors.RESET;
                verifyAndRegister(i);
            }
        }
        
        else {
            message = Colors.YELLOW + "O aeroporto passado já existe\n" + Colors.RESET;
            verifyAndRegister(i);
        }
    }

    /**
     * Exhibes every airport present in the airportList, one by line
     * 
     * @param <X>
     * @param list
     * @param cls
     * @throws Exception in case of miss-passed parameter of the method getElementAt(int index)
     */
    public static <X> void showLinkedList(LinkedList<X> list, boolean cls) throws Exception {
        if (cls) Console.clear();
        for (int i = 0; i < list.getSize(); i++)
            System.out.println((i+1) + " - " + list.getElementAt(i));
    }
}
