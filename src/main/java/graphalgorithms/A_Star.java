/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.TransportGraph;

/**
 *
 * @author Eigenaar
 */
public class A_Star extends AbstractPathSearch {

	private final int startIndex;
	protected final int endIndex;
	private final double[] distTo;
	private IndexMinPQ<Double> pq;
	private double travelTime;

	public A_Star(TransportGraph graph, String start, String end) {
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
		pq = new IndexMinPQ<>(graph.getNumberOfStations());
		pq.insert(startIndex, distTo[startIndex]);

		while (!pq.isEmpty()) {
			int v = pq.delMin();
			nodesVisited.add(graph.getStation(v));
			if (v == endIndex) {
				pathTo(endIndex);
				travelTime = distTo[endIndex];
			} else {
				for (Integer vertex : graph.getAdjacentVertices(v)) {
					relax(graph.getConnection(v, vertex));
				}
			}
		}
	}

	// relax edge e and update pq if changed
	private void relax(Connection connection) {
		int v = graph.getIndexOfStationByName(connection.getFrom().getStationName()), w = graph.
				getIndexOfStationByName(connection.getTo().getStationName());
		if (distTo[w] > distTo[v] + graph.getStation(w).getLocation().travelTime(graph.getStation(v).getLocation())) {
			distTo[w] = distTo[v] + graph.getStation(w).getLocation().travelTime(graph.getStation(v).getLocation());
			edgeTo[w] = v;
			if (!pq.contains(w)) {
				pq.insert(w, distTo[w]);
			} else {
				pq.changeKey(w, distTo[w]);
			}
		}
	}

	public double getTravelTime() {
		return travelTime;
	}

	public int getTransfers() {
		return transfers;
	}

}
