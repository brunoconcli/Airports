import LinkedList.LinkedList;
import LinkedList.Node;

public class Airport {
    private String city;
    private String code;
    private LinkedList<Flight> flightList;

    public Airport(String city, String code) throws Exception {
        if (code.length() > 3)
            throw new Exception("Airport code cannot be bigger than 3 letters");
        this.city = city;
        this.code = code;
        this.flightList = new LinkedList<Flight>();
    }

    public Airport(String city, String code, LinkedList<Flight> flightList) throws Exception {
        if (code.length() > 3)
            throw new Exception("Airport code cannot be bigger than 3 letters");
        this.city = city;
        this.code = code;
        this.flightList = flightList;
    }

    public String getCity() {
        return this.city;
    }

    public String getCode() {
        return this.code;
    }

    public LinkedList<Flight> getFlightList() {
        return this.flightList;
    }

    public Flight getFlightByCode(String num) throws Exception {
        Node<Flight> current = this.flightList.getFirst(); 
        while(!current.getInfo().getFlightNumber().equals(num)) {
            if (current.getNext() != null)
                current = current.getNext();
            else
                throw new Exception("The passed flight has not been found in this airport");
        }
        return current.getInfo();
    }
    
    public Flight getFlightByDestination(String destination) throws Exception {
        Node<Flight> current;
        for (current = this.flightList.getFirst(); current.getInfo().getAirportCode() != destination; current = current.getNext());
        return current.getInfo();
    }

    public int getIndexOfFlight(Flight flight) throws Exception {
        return this.flightList.getIndexOf(flight);
    }

    public void pushLast(Flight flight) {
        this.flightList.pushLast(flight);
    }

    public void popFlightByCode(String flightCode) throws Exception {
        if (code.length() > 3)
            throw new Exception("Flight number passed is not valid");
            
        this.flightList.popNodeAt(this.getIndexOfFlight(this.getFlightByCode(flightCode)));
    }

    public static Airport registerAirport(int i) throws Exception {
        Airport createdAirport = new Airport("STANDBY", "APT");
        String city = "", code = "1234";
        int missFillCount = 0;


        while (code.length() > 3) {
            Console.clear();
            if (missFillCount > 0)
                System.out.println("O campo 'Sigla' não foi preenchido corretamente\n");
            System.out.println("---" + (i+1) + "º AEROPORTO ---\n");
            city = Console.getInputOf("Cidade a qual pertence: ").trim();
            code = Console.getInputOf("Sigla do aerporto (3 letras): ").trim().toUpperCase();

            missFillCount++;
        }

        createdAirport = new Airport(
            city.substring(0, 1).toUpperCase() + 
            city.substring(1), code
        );
    
        return createdAirport;

    }

    @Override
    public String toString() {
        return this.city + " " + this.code;
    }

    @Override
    public int hashCode() {
        int hash = 2;

        hash = 3 * hash + (this.city).hashCode();
        hash = 5 * hash + (this.code).hashCode();
        hash = 7 * hash + new LinkedList<Flight>().hashCode();
        
        if (hash < 0) hash = -hash;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;

        if (this.getClass() != obj.getClass()) return false;
        Airport a = (Airport) obj;
        if (!this.city.equals(a.city)) return false;
        if (!this.code.equals(a.code)) return false;
        if (!this.flightList.equals(a.flightList)) return false;

        return true;
    }
}
