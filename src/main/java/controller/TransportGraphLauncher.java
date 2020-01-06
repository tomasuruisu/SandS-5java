package controller;

import graphalgorithms.BreadthFirstPath;
import model.TransportGraph;
import model.TransportGraph.Builder;

public class TransportGraphLauncher {

	public static void main(String[] args) {
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
		/*
		DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();
		*/
		// Uncommented to test the BreadthFirstPath algorithm

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

		bfsTest = new BreadthFirstPath(transportGraph, "Steigerplein", "Grote Sluis");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
	}
}
