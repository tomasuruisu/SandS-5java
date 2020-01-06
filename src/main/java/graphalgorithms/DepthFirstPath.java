/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

import model.TransportGraph;

/**
 *  The {@code DepthFirstPath} class represents a data type for finding
 *  paths from a source vertex <em>s</em> to every other vertex
 *  in an undirected graph.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in the
 *  worst case, where <em>V</em> is the number of vertices and
 *  <em>E</em> is the number of edges.
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the graph).
 *  <p>
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DepthFirstPath extends AbstractPathSearch {

	private TransportGraph graph;
	protected final int startIndex;
    protected final int endIndex;

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param G the graph
	 * @param start
	 * @param end
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
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
        
    }

	private void DepthFirstSearch(int vertex) {
		
	}

}
