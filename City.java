/**
 * Models a city
 * Cities contain a name and a value and can be visited by the player
 * @author Jakob Skøt Nielsen 202407223
 * @author Daniel Dupont 202407440
 * @version 06-11-2024
 *
 */

import java.util.*;

public class City implements Comparable<City> {
    private String name; // Name of the city
    private int value; // Value of the city
    private int initialValue; // Initial value of the city
    private Country country; // The country the city is in

    /**
     * Creates a new city
     * @param name    The name of the city
     * @param value   The initial value of the city
     * @param country The country to create the city in
     */
    public City(String name, int value, Country country) {
        this.name = name;
        this.value = value;
        this.initialValue = value;
        this.country = country;
    }

    /**
     * Returns the city name
     * @return city name
     */
    public String getName() { return name; }

    /**
     * Returns the city value
     * @return city value
     */
    public int getValue() { return value; }

    /**
     * Returns the city's initial value
     * @return city's initial value
     */
    public int getInitialValue() { return initialValue; }

    /**
     * Returns the country the city is placed in
     * @return country the city is placed in
     */
    public Country getCountry() { return country; }

    /**
     * Add amount to the city value
     * @param amount    Amount to add
     */
    public void changeValue(int amount) {
        value += amount;
    }

    /**
     * Get bonus and withdraw from city value
     * @return withdrew bonus
     */
    public int arrive() {
        int bonus = country.bonus(value);
        if(bonus > 0) {
            value -= bonus;
        }
        return bonus;
    }

    public int arrive(Player p){
        return arrive();
    }

    /**
     * Reset city's value to initial value
     */
    public void reset() {
        value = initialValue;
    }

    /**
     * Returns the city's fields in String form
     * @return name followed by the value in parentheses
     */
    @Override
    public String toString() {
        return name + " (" + value + ")";
    }

    /**
     * Compares cities based on name, alphabetically
     * @param other  Other city object to compare to
     * @return int based on comparing name field
     */
    @Override
    public int compareTo(City other) {
        return name.compareTo(other.name);
    }

    /**
     * Generate hash code based on name field, country field and prime numbers
     * @return generated int hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode() * 31 +
        country.hashCode() * 13;
    }

    /**
     * Check if Object is equal to this based on name and country fields
     * @param obj    Object to check if equal to
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        City other = (City) obj;
        return name.equals(other.name) && country.equals(other.country);
    }
}
