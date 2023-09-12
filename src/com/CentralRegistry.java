package com;

import java.util.ArrayList;
import java.util.HashSet;

public class CentralRegistry {

    private static ArrayList<Airport> airports = new ArrayList<>();
    private static ArrayList<Flight>  flights = new ArrayList<>();

    public static void addAirport(Airport airport) {
        airports.add(airport);
    }

    public static void addFlight(Flight flight) {
        flights.add(flight);
        flight.getAirportA().setAirlineName(flight.getAirline());
        flight.getAirportB().setAirlineName(flight.getAirline());
    }

    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static Airport getLargestHub() {

        Airport biggestAirport = null;
        int maxCount = 0;

        for (Airport airportA : airports){
            int count = 0;
            for (Airport airportB: airports){
                if (airportA.isDirectlyConnectedTo(airportB) && !airportA.equals(airportB)){
                    count++;
                }
            }
            if (count > maxCount){
                maxCount = count;
                biggestAirport = airportA;
            }
        }
        return biggestAirport;
    }

    public static ArrayList<Airport> getAirports() {
        return airports;
    }

    public static Flight getLongestFlight() {

        int maxMinutes = 0;
        Flight longestFlight = null;

        for(Flight flight : flights)
        {
            if(maxMinutes < flight.getMinutes())
            {
                maxMinutes = flight.getMinutes();
                longestFlight = flight;
            }
        }

        return longestFlight;
    }

    public static Airport getAirport(String cityName){

        for (Airport airport : airports){
            if (cityName.equals(airport.getCity())){
                return airport;
            }
        }
        return null;
    }

    public static String getDirectFlightsDetails(Airport airportA, Airport airportB){

        ArrayList<Flight> directFlights= new ArrayList<>();

        for (Flight flight : flights){
            if (airportA.equals(flight.getAirportA()) && airportB.equals(flight.getAirportB()) ||
                    airportA.equals(flight.getAirportB()) && airportB.equals(flight.getAirportA())){
                directFlights.add(flight);
            }
        }

        int i = 1;
        String answer = "";

        for (Flight flight : directFlights){
            answer += "[" + i + "]" + flight.toString();
            i++;
        }
        return answer;
    }

    public static String getInDirectFlightsDetail(Airport airportA, Airport airportB){

        ArrayList<Airport> commonAirports = airportA.getCommonConnections(airportB);

        HashSet<Airport> hashSet = new HashSet<Airport>();

        hashSet.addAll(commonAirports);

        String answer = "";
        int i = 1;

        for (Airport airport : hashSet){
            answer += "[" + i + "]" + airport.getCity() + ", " + airport.getCodeName() + "\n";
            i++;
        }

        return answer;
    }

}
