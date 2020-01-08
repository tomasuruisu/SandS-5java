/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

import java.util.LinkedList;

import model.TransportGraph;

/**
 *  The {@code BreadthFirstPath} class represents a data type for finding
 *  shortest paths (number of edges) from a source vertex <em>s</em>
 *  (or a set of source vertices)
 *  to every other vertex in an undirected graph.
 *  <p>
 *  This implementation uses breadth-first search.
 *  The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in the
 *  worst case, where <em>V</em> is the number of vertices and <em>E</em>
 *  is the number of edges.
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the graph).
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
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

