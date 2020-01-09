package controller;

import java.text.DecimalFormat;

import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import graphalgorithms.DijkstraShortestPath;
import graphalgorithms.A_Star;
import model.TransportGraph;
import model.Station;
import model.TransportGraph.Builder;

public class TransportGraphLauncher {

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.#");
		
		String[] redLine = {"red", "metro", "A", "B", "C", "D"};
		String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
		String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
		String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

		Builder builder = new Builder();

		// uses a line information String array to add the a Line object to a list of lines
		// and add all the stations of the line to the list of stations in the Line object
		builder.addLine(redLine);
		builder.addLine(blueLine);
		builder.addLine(greenLine);
		builder.addLine(yellowLine);

		System.out.println("");

		// Using a set ensures that stations will be added only once as a vertex in the list of Stations of the Graph.
		// You will have to loop through all lines and for every
		// line loop through the stations on the line to make the set of stations complete
		builder.buildStationSet();

		System.out.println("");

		// After building the set of stations, you add lines to the stations. More than one line can run through a station. The
		// method addLinesToStations() adds the correct lines to all stations.
		builder.addLinesToStations();

		System.out.println("");

		// Next you will have to use a buildConnections() method that uses the ordered list of stations in a line to make
		// connection object of consecutive stations. Again loop through all the lines and it’s stations to make the set of
		// connections complete.
		builder.buildConnections();

		// Finally the build() method uses the set of stations and the set of connections to add all vertices and edges
		// to the graph. Of course after initializing the Graph object with the proper size.
		TransportGraph transportGraph = new TransportGraph(10);
		transportGraph = builder.build();

		System.out.println("");

		System.out.println(transportGraph.toString());
		
		// Uncommented to test the DepthFirstPath algorithm

		DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();


		BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "E", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();


		String[] redLineB = {"red", "metro", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
		String[] blueLineB = {"blue", "metro", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
		String[] purpleLineB = {"purple", "metro", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
		String[] greenLineB = {"green", "metro", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
		String[] yellowLineB = {"yellow", "bus", "Grote Sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote Sluis"};
		
		builder = new Builder();
		System.out.println("");
		builder.addLine(redLineB);
		builder.addLine(blueLineB);
		builder.addLine(purpleLineB);
		builder.addLine(greenLineB);
		builder.addLine(yellowLineB);
		System.out.println("");
		builder.buildStationSet();
		System.out.println("");
		builder.addLinesToStations();
		System.out.println("");
		builder.buildConnections();
		System.out.println("");
		transportGraph = new TransportGraph(17);
		transportGraph = builder.build();
		System.out.println(transportGraph.toString());

		// A 4
		System.out.println("DFP");
		dfpTest = new DepthFirstPath(transportGraph, "Steigerplein", "Grote Sluis");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

		System.out.println("BFP");
		bfsTest = new BreadthFirstPath(transportGraph, "Steigerplein", "Grote Sluis");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
		System.out.println();

		// A 5
		System.out.println("\nOverview for shortest paths from every station to every station");
		System.out.println("\nDepthFirst:");
		for (Station station: transportGraph.getStationList()) {
			for(int i = 0; i < transportGraph.getStationList().size(); i++) {
				if (transportGraph.getStation(i) != station) {
					dfpTest = new DepthFirstPath(transportGraph, station.getStationName(), transportGraph.getStation(i).getStationName());
					dfpTest.search();
					System.out.println(dfpTest);
				}
			}
		}
		System.out.println("\nBreadthFirst:");
		for (Station station: transportGraph.getStationList()) {
			for(int i = 0; i < transportGraph.getStationList().size(); i++) {
				if (transportGraph.getStation(i) != station) {
					bfsTest = new BreadthFirstPath(transportGraph, station.getStationName(), transportGraph.getStation(i).getStationName());
					bfsTest.search();
					System.out.println(bfsTest);
				}
			}
		}


		System.out.println("");

		// breadth first search of every station to every other station
		for (Station station: transportGraph.getStationList()) {
			for(int i = 0; i < transportGraph.getStationList().size(); i++) {
				if (transportGraph.getStation(i) != station) {
					bfsTest = new BreadthFirstPath(transportGraph, station.getStationName(), transportGraph.getStation(i).getStationName());
					bfsTest.search();
					System.out.println(bfsTest);
				}
			}
		}

		System.out.println("");

		double[] redLineWeight = {4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
		double[] blueLineWeight = {6.0, 5.3, 5.1, 3.3};
		double[] purpleLineWeight = {6.2, 5.2, 3.8, 3.6};
		double[] greenLineWeight = {5.0, 3.7, 6.9, 3.9, 3.4};
		double[] yellowLineWeight = {26.0, 19.0, 37.0, 25.0, 22.0, 28.0};

		transportGraph.addWeight(redLineB, redLineWeight);
		transportGraph.addWeight(blueLineB, blueLineWeight);
		transportGraph.addWeight(purpleLineB, purpleLineWeight);
		transportGraph.addWeight(greenLineB, greenLineWeight);
		transportGraph.addWeight(yellowLineB, yellowLineWeight);

		System.out.println("");
		System.out.println("\nDijkstra");
		DijkstraShortestPath dspTest = new DijkstraShortestPath(transportGraph, "Dukdalf", "Grote Sluis");
		dspTest.search();
		System.out.println(dspTest);
		System.out.println("Total weight of path: " + df.format(dspTest.getTotalWeight()));
		dspTest.printNodesInVisitedOrder();

		System.out.println("");

		int[] redLineCoordinates = {14, 1, 12, 3, 10, 5, 8, 8, 6, 9, 3, 10, 0, 11};
		int[] blueLineCoordinates = {9, 3, 7, 6, 6, 9, 5, 14};
		int[] purpleLineCoordinates = {2, 3, 4, 6, 7, 6, 8, 8, 10, 9};
		int[] greenLineCoordinates = {9, 0, 9, 3, 10, 5, 10, 9, 11, 11, 12, 13};
		int[] yellowLineCoordinates = {2, 3, 9, 0, 14, 1, 12, 13, 5, 14, 0, 11, 2, 3};

		transportGraph.addLocation(redLineB, redLineCoordinates);
		transportGraph.addLocation(blueLineB, blueLineCoordinates);
		transportGraph.addLocation(purpleLineB, purpleLineCoordinates);
		transportGraph.addLocation(greenLineB, greenLineCoordinates);
		transportGraph.addLocation(yellowLineB, yellowLineCoordinates);

		System.out.println("");

		// C 5
		System.out.println("\nA*");
		A_Star aStarTest = new A_Star(transportGraph, "Steigerplein", "Grote Sluis");
		aStarTest.search();
		System.out.println(aStarTest);
		aStarTest.printNodesInVisitedOrder();

		// Dijkstra and A* search of every station to every other station
		// C 6
		System.out.println("\nOverview for shortest paths from every station to every station");
		int nodesVisitedAmountDijkstra = 0;
		int nodesVisitedAmountAStar = 0;
		String winner;

		// Dijkstra
		System.out.println("\nDijkstra");
		for (Station station: transportGraph.getStationList()) {
			for(int i = 0; i < transportGraph.getStationList().size(); i++) {
				if (transportGraph.getStation(i) != station) {
					dspTest = new DijkstraShortestPath(transportGraph, station.getStationName(), transportGraph.getStation(i).getStationName());
					dspTest.search();
					nodesVisitedAmountDijkstra += dspTest.getNodesVisitedAmount();
					System.out.println(dspTest);
				}
			}
		}

		// A_Star
		System.out.println("\nA_Star");
		for (Station station: transportGraph.getStationList()) {
			for(int i = 0; i < transportGraph.getStationList().size(); i++) {
				if (transportGraph.getStation(i) != station) {
					aStarTest = new A_Star(transportGraph, station.getStationName(), transportGraph.getStation(i).getStationName());
					aStarTest.search();
					nodesVisitedAmountAStar += aStarTest.getNodesVisitedAmount();
					System.out.println(aStarTest);
				}
			}
		}

		// Compare to find the most efficient path
		if (nodesVisitedAmountAStar < nodesVisitedAmountDijkstra) {
			winner = "A*";
		} else {
			winner = "Dijkstra";
		}

		System.out.println("\nTotal nodes visited with A*: " + nodesVisitedAmountAStar);
		System.out.println("Total nodes visited with Dijkstra: " + nodesVisitedAmountDijkstra);
		System.out.println("The winner with the most efficient paths is: " + winner);

	}
}
