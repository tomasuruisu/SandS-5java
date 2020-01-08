/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

import model.TransportGraph;

/**
 *
 * @author Eigenaar
 */
public class A_Star extends AbstractPathSearch {

	private final int startIndex;
	protected final int endIndex;

	public A_Star(TransportGraph graph, String start, String end) {
		super(graph, start, end);
		startIndex = graph.getIndexOfStationByName(start);
		endIndex = graph.getIndexOfStationByName(end);
	}

	@Override
	public void search() {
		
	}

}
