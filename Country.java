import java.util.*;

/*
 * Models a country
 * A country consists of a name and a road network between cities
 * @author Jakob Skøt Nielsen 202407223
 * @author Daniel Dupont 202407440
 * @version 06-11-2024
 *
 */

public class Country {
    private String name; // Name of the country
    private Map<City, Set<Road>> network; // HashMap with cities as keys and a set of roads that start in that city as values
    private Game game; // Game reference

    /*
    * Creates a Country 
    * @param name   Country name
     */
    public Country(String name) {
        this.name = name;
        network = new TreeMap<>(); // Initiate empty TreeMap
    }
    
    /*
    * Returns name of country
    * @return name of country
     */
    public String getName() { return name; }

    /*
    * Returns the game field
    * @return game reference
     */
    public Game getGame() { return game; }

    /*
    * Sets the game field
    * @param game   Game reference to set field to
     */
    public void setGame(Game game) { this.game = game; }

    /*
    * Adds a new city to the country
    * @param city  City to add
     */
    public void addCity(City city) {
        network.put(city, new TreeSet<>());
    }

    /*
    * Adds roads between two cities
     */
    public void addRoads(City a, City b, int length) {
        if(length > 0 && !a.equals(b)) {
            if(network.containsKey(a)) {
                network.get(a).add(new Road(a, b, length));
            }
            if (network.containsKey(b)) {
                network.get(b).add(new Road(b, a, length));
            }
        }
    }
    /*
    * Returns the position of the city
    * @param city   City to get the position for
     */
    public Position position(City city) {
        return new Position(city, city, 0);
    }

    /*
    * Returns position object with travel info to next town
    * @param from   City to travel from
    * @param to     City to travel to
     */
    public Position readyToTravel(City from, City to) {
        Road travelRoad = null;
        if(network.containsKey(from)) {
            for(Road road : network.get(from)) {
                if(road.getTo().equals(to)) {
                    travelRoad = road;
                    break;
                }
            }
        } else {
            return position(from);
        }
        if(travelRoad == null || from.equals(to)) {
            return position(from);
        }
        return new Position(from, to, travelRoad.getLength());
    }

    /*
     * Returns Set of cities in road network
     * @return Set of cities
     */
    public Set<City> getCities() { return network.keySet(); }

    /*
    * Searches network for certain city
    * @param name   City name to find
    * @return city if found else null
     */
    public City getCity(String name) {
        for (City city : network.keySet()) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    /*
    * Returns road network of certain city
    * @param city   City's road network to get
    * @return Set of roads if found else empty TreeMap
    */
    public Set<Road> getRoads(City c) {
        if(network.containsKey(c)) {
            return network.get(c);
        }
        return new TreeSet<>();
    }

    /*
    * Resets the road network
     */
    public void reset() {
        for(City city : network.keySet()) {
            city.reset();
        }
    }

    public int bonus(int value) {
        if(value > 0) {
            Random rand = game.getRandom();
            return rand.nextInt(value + 1);
        }
        return 0;
    }

    /*
     * Creates a String representation of country
     * @return String model of object
     */
    @Override
    public String toString() {
        return name;
    }

    /*
     * Generate hash code based on name field and prime number
     * @return generated int hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode() * 31;
    }

    /*
     * Check if Object is equal to this based on name field
     * @param obj    Object to check if equal to
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Country other = (Country) obj;
        return name.equals(other.name);
    }
}
