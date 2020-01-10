/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.TransportGraph;

public class DijkstraShortestPath extends AbstractPathSearch {

	private double[] distTo;          // distTo[v] = distance  of shortest s->v path
	private IndexMinPQ<Double> pq;    // priority queue of vertices
	private final int startIndex;
	protected final int endIndex;
	private double pathWeight = 0.0;

	public DijkstraShortestPath(TransportGraph graph, String start, String end) {
		super(graph, start, end);
		startIndex = graph.getIndexOfStationByName(start);
		endIndex = graph.getIndexOfStationByName(end);
		distTo = new double[graph.getNumberOfStations()];
		for (int v = 0; v < graph.getNumberOfStations(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[startIndex] = 0.0;
	}

	@Override
	public void search() {
		// relax vertices in order of distance from s
		pq = new IndexMinPQ<>(graph.getNumberOfStations());
		pq.insert(startIndex, distTo[startIndex]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			nodesVisited.add(graph.getStation(v));
			nodesVisitedAmount++;
			for (Integer vertex : graph.getAdjacentVertices(v)) {	
				relax(graph.getConnection(v, vertex));
			}
		}
		pathWeight = distTo[endIndex];
		pathTo(endIndex);
	}

	// relax edge e and update pq if changed
	private void relax(Connection connection) {
		int v = graph.getIndexOfStationByName(connection.getFrom().getStationName()), w = graph.
				getIndexOfStationByName(connection.getTo().getStationName());
		if (distTo[w] > distTo[v] + connection.getWeight()) {
			distTo[w] = distTo[v] + connection.getWeight();
			edgeTo[w] = v;
			if (pq.contains(w)) {
				pq.decreaseKey(w, distTo[w]);
			} else {
				pq.insert(w, distTo[w]);
			}
		}
	}

	/**
	 * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
	 *
	 * @param v the destination vertex
	 * @return if there is a path from the source vertex
	 */
	@Override
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public double getTotalWeight() {
		return pathWeight;
	}

	public int getNodesVisitedAmount() {
		return nodesVisitedAmount;
	}

}
