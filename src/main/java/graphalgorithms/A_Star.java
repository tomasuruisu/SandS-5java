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
 * @author Thomas & Donovan
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
			nodesVisitedAmount++;
			if (v == endIndex) {
				pathTo(endIndex);
				break;
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
		double cost = distTo[v] + connection.getWeight();
		double fCost = cost + graph.getStation(v).getLocation().travelTime(graph.getStation(w).getLocation());
		if (distTo[w] > fCost) {
			distTo[w] = fCost;
			edgeTo[w] = v;
			if (pq.contains(w)) {
				pq.decreaseKey(w, distTo[w]);
			} else {
				pq.insert(w, distTo[w]);
			}
		}
	}

	public double getTravelTime() {
		return travelTime;
	}

	public int getNodesVisitedAmount() {
		return nodesVisitedAmount;
	}

}
