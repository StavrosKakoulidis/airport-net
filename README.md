# Airport Network Simulator

Welcome to the Airport Network Simulator! This Java application allows users to simulate an airport network, create airports and flights, and perform various operations on the network.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Methods](#methods)
- [Graph Representation](#graph-representation)
- [GUI](#gui)
- [Contributing](#contributing)

## Features

- Create airport objects representing nodes in the airport network.
- Create flight objects to represent connections between two airports.
- Check if two airports are directly connected.
- Check if two airports are indirectly connected via a meddling airport.
- Find the airport with the most directly connected flights.
- Find the longest flight in the network.
- View a simple graph representation of the airport network.
- Use a graphical user interface (GUI) to find flights between two airports.

## Getting Started

1. Clone the repository to your local machine.
2. Compile the Java program.
3. Run the program.

## Usage
In the 'main' method of the program, users can create airport objects and flight objects to build the airport network. They can then use various methods to perform operations on the network.

## Methods

- isDirectlyConnectedTo(): Users can check if two airports are directly connected or not.
- isInDirectlyConnectedTo(): Users can check if two airports are indirectly connected via a meddling airport or not.
- getLargestHub(): Returns the airport with the most directly connected flights.
- getLongestFlight(): Returns the longest flight in the network.

## Graph Representasion

The application provides a simple graph representation of the airport network, allowing users to visualize the connections between airports.

## GUI

The GUI (Graphical User Interface) provides an interactive way for users to find flights between two airports. It offers a user-friendly experience for exploring the airport network.

## Contributing

Contributions to this project are welcome! Feel free to submit issues, suggest improvements, or contribute code to enhance the functionality of the Airport Network Simulator.
