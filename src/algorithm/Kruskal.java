/* Copyright (C) 2009-2011  Syed Asad Rahman <asad@ebi.ac.uk>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/*
 * 
 * Kruskal's algorithm finds a minimum spanning tree for a connected, weighted, undirected graph.
 */
package algorithm;

import helper.DisjointSet;
import helper.Edge;
import helper.Node;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Asad
 */
public class Kruskal {

    /**
     * 
     * @param nodes
     * @param edges
     * @return
     */
    public List<Edge> getMST(Collection<Node> nodes, List<Edge> edges) {
        java.util.Collections.sort(edges);
        List<Edge> MST = new ArrayList<Edge>();
        DisjointSet<Node> nodeset = new DisjointSet<Node>();
        nodeset.createSubsets(nodes);
        for (Edge e : edges) {
            if (nodeset.find(e.getFrom()) != nodeset.find(e.getTo())) {
                nodeset.merge(nodeset.find(e.getFrom()), nodeset.find(e.getTo()));
                MST.add(e);
            }
        }
        return MST;
    }
}
