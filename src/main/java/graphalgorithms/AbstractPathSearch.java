package graphalgorithms;

import model.Station;
import model.TransportGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Line;


/**
 * Abstract class that contains methods and attributes shared by the DepthFirstPath en BreadthFirstPath classes
 */
public abstract class AbstractPathSearch {

    protected boolean[] marked; // marked[v] = is there an s-v path
    protected int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
    protected int transfers = 0;
    protected List<Station> nodesVisited;
    protected List<Station> nodesInPath;
    protected LinkedList<Integer> verticesInPath;
    protected TransportGraph graph;
    protected final int startIndex;
    protected final int endIndex;
	protected int nodesVisitedAmount = 0;

    public AbstractPathSearch(TransportGraph graph, String start, String end) {
        startIndex = graph.getIndexOfStationByName(start);
        endIndex = graph.getIndexOfStationByName(end);
        this.graph = graph;
        nodesVisited = new ArrayList<>();
        marked = new boolean[graph.getNumberOfStations()];
        edgeTo = new int[graph.getNumberOfStations()];
    }

    public abstract void search();

    /**
     * @param vertex Determines whether a path exists to the station as an index from the start station
     * @return
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }


    /**
     * Method to build the path to the vertex, index of a Station.
     * First the LinkedList verticesInPath, containing the indexes of the stations, should be build, used as a stack
     * Then the list nodesInPath containing the actual stations is build.
     * Also the number of transfers is counted.
     * @param vertex The station (vertex) as an index
     */
    public void pathTo(int vertex) {
		verticesInPath = new LinkedList();
		nodesInPath = new LinkedList();
        for (int x = vertex; x != startIndex; x = edgeTo[x]) {
            verticesInPath.push(x);
		}
		
        verticesInPath.push(startIndex);
		for (int stationIndex: verticesInPath) {
			Station station = graph.getStation(stationIndex);
			nodesInPath.add(station);
		}
		// if you're going from one station to another then you won't transfer
		if (nodesInPath.size() > 2) {
			countTransfers();
		}
    }

    /**
     * Method to count the number of transfers in a path of vertices.
     * Uses the line information of the connections between stations.
     * If two consecutive connections are on different lines there was a transfer.
     */
    public void countTransfers() {
		// if the station that is 2 stations further does not have the same line as the current station then
		// there will be a transfer at the next station
		for (int i = 0; i < nodesInPath.size() - 2; i++) {
			boolean needTransfer = true;
			for (Line line: nodesInPath.get(i).getLines()) {
				if (nodesInPath.get(i + 1).hasLine(line) && nodesInPath.get(i + 2).hasLine(line)) {
					// station that is 2 stations further still has the same line so no need to transfer
					needTransfer = false;
				}
			}
			if (needTransfer) {
				transfers++;
			}
		}
    }


    /**
     * Method to print all the nodes that are visited by the search algorithm implemented in one of the subclasses.
     */
    public void printNodesInVisitedOrder() {
        System.out.print("Nodes in visited order: ");
        for (Station vertex : nodesVisited) {
            System.out.print(vertex.getStationName() + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder(String.format("Path from %s to %s: ", graph.getStation(startIndex), graph.getStation(endIndex)));
        resultString.append(nodesInPath).append(" with " + transfers).append(" transfers");
        return resultString.toString();
    }

}
