/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

import java.util.LinkedList;

import model.TransportGraph;

/**
 *  This implementation uses breadth-first search.
 * @author Thomas & Donovan
 */
public class BreadthFirstPath extends AbstractPathSearch {

	private TransportGraph graph;
	protected final int startIndex;
    protected final int endIndex;

	public BreadthFirstPath(TransportGraph graph, String start, String end) {
		super(graph, start, end);
		this.graph = graph;
		startIndex = graph.getIndexOfStationByName(start);
        endIndex = graph.getIndexOfStationByName(end);
	}

    

    // breadth-first search from a single source
	@Override
    public void search() {
        LinkedList<Integer> q = new LinkedList<>();
		
        marked[startIndex] = true;
        q.add(startIndex);
		nodesVisited.add(graph.getStation(startIndex));

        while (!q.contains(endIndex)) {
            int v = q.removeFirst();
            for (int w : graph.getAdjacentVertices(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    q.add(w);
					nodesVisited.add(graph.getStation(w));
                }
            }
        }
		pathTo(endIndex);
    }
}

