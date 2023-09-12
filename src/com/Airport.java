package com;

import java.util.ArrayList;

public class Airport {

    private String name;
    private String codeName;
    private String city;
    private String country;
    private ArrayList<String> airlineNames = new ArrayList<>();


    public Airport(String name, String codeName, String city, String country) {
        this.name = name;
        this.codeName = codeName;
        this.city = city;
        this.country = country;
    }


    public void setAirlineName(String airline) {

        if (!airlineNames.contains(airline)){
            airlineNames.add(airline);
        }

    }

    public ArrayList<String> getAirlineName(){
        return airlineNames;
    }

    public boolean isDirectlyConnectedTo(Airport anAirport)
    {
        for(Flight flight : CentralRegistry.getFlights()){
            Airport airportA = flight.getAirportA();
            Airport airportB = flight.getAirportB();
            if (airportA.equals(this) && airportB.equals(anAirport) || airportA.equals(anAirport) && airportB.equals(this)){
                return true;
            }
        }
        return false;
    }


    public boolean isInDirectlyConnectedTo(Airport anAirport) {

        for (Flight flightA: CentralRegistry.getFlights()){
            if (this.equals(flightA.getAirportA()) || this.equals(flightA.getAirportB())){
                for (Flight flightB : CentralRegistry.getFlights()){
                    if (flightA.getAirportB().equals(flightB.getAirportB()) || flightA.getAirportA().equals(flightB.getAirportA())){
                        if (flightB.getAirportA().equals(anAirport) || flightB.getAirportB().equals(anAirport)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Airport> getCommonConnections(Airport anAirport) {

        ArrayList<Airport> airportsA = new ArrayList<>();
        ArrayList<Airport> airportsB = new ArrayList<>();
        ArrayList<Airport> commonAirports = new ArrayList<>();

        for (Flight flight : CentralRegistry.getFlights()){

            if (this.equals(flight.getAirportA())){
                airportsA.add(flight.getAirportB());
            }else if (this.equals(flight.getAirportB())){
                airportsA.add(flight.getAirportA());
            }

            if (anAirport.equals(flight.getAirportA())){
                airportsB.add(flight.getAirportB());
            }else if (anAirport.equals(flight.getAirportB())){
                airportsB.add(flight.getAirportA());
            }
        }

        for (Airport airportA : airportsA){
            for (Airport airportB : airportsB){
                if (airportA.equals(airportB)){
                    commonAirports.add(airportA);
                }
            }
        }

        return commonAirports;
    }


    public void printCompanies() {
        for(String air : airlineNames)
        {
            System.out.println(air);

        }
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



}
