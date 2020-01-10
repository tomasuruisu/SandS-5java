/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

import model.TransportGraph;

/**
 *  This implementation uses depth-first search.
 * @author Thomas & Donovan
 */
public class DepthFirstPath extends AbstractPathSearch {

	private TransportGraph graph;
	protected final int startIndex;
    protected final int endIndex;

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param graph
	 * @param start
	 * @param end
     */
    public DepthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
		this.graph = graph;
		this.startIndex = graph.getIndexOfStationByName(start);
        this.endIndex = graph.getIndexOfStationByName(end);
    }

    // depth first search
	@Override
    public void search() {
        marked[startIndex] = true;
        nodesVisited.add(graph.getStation(startIndex));
        DepthFirstSearch(startIndex);
        pathTo(endIndex);
    }

	private void DepthFirstSearch(int vertex) {
        marked[vertex] = true;

        for (int w : graph.getAdjacentVertices(vertex)) {
            if (!marked[w]) {
                edgeTo[w] = vertex;
                nodesVisited.add(graph.getStation(w));
                DepthFirstSearch(w);
            }
        }

    }

}
