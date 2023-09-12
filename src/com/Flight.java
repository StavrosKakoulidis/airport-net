package com;

public class Flight {
    private Airport airportA;
    private Airport airportB;
    private int minutes;
    private String airline;


    public Flight(Airport airportA, Airport airportB, int minutes, String airline)
    {
        this.airportA = airportA;
        this.airportB = airportB;
        this.minutes = minutes;
        this.airline = airline;
    }

    @Override
    public String toString() {
        return ("com.Flight operated by "+ this.airline +", "+ this.minutes +" minutes\n");
    }

    public Airport getAirportA() {
        return airportA;
    }

    public void setAirportA(Airport airportA) {
        this.airportA = airportA;
    }

    public Airport getAirportB() {
        return airportB;
    }

    public void setAirportB(Airport airportB) {
        this.airportB = airportB;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
