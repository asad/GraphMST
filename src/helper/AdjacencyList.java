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
package helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openscience.cdk.interfaces.IBond;

/**
 *
 * @author Asad
 */
public class AdjacencyList {

    private Map<Node, List<Edge>> adjacencies = new HashMap<Node, List<Edge>>();

    public AdjacencyList() {
    }

    /**
     * 
     * @param adjacencies
     */
    public void setAdjacencyList(Map<Node, List<Edge>> adjacencies) {
        this.adjacencies.putAll(adjacencies);
    }

    /**
     * 
     * @param source
     * @param target
     * @param bond  
     */
    public void addEdge(Node source, Node target, IBond bond) {
        List<Edge> list;
        if (!adjacencies.containsKey(source)) {
            list = new ArrayList<Edge>();
            adjacencies.put(source, list);
        } else {
            list = adjacencies.get(source);
        }
        list.add(new Edge(source, target, bond));
    }

    /**
     * 
     * @param source
     * @return
     */
    public List<Edge> getAdjacent(Node source) {
        return adjacencies.get(source);
    }

    /**
     * 
     * @param e
     */
    public void reverseEdge(Edge e) {
        adjacencies.get(e.getFrom()).remove(e);
        addEdge(e.getTo(), e.getFrom(), e.getBond());
    }

    /**
     * 
     */
    public void reverseGraph() {
        setAdjacencies(getReversedList().adjacencies);
    }

    /**
     * 
     * @return
     */
    public AdjacencyList getReversedList() {
        AdjacencyList newlist = new AdjacencyList();
        for (List<Edge> edges : adjacencies.values()) {
            for (Edge e : edges) {
                newlist.addEdge(e.getTo(), e.getFrom(), e.getBond());
            }
        }
        return newlist;
    }

    /**
     * 
     * @return
     */
    public Set<Node> getSourceNodeSet() {
        return adjacencies.keySet();
    }

    /**
     * 
     * @return
     */
    public Collection<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<Edge>();
        for (List<Edge> e : adjacencies.values()) {
            edges.addAll(e);
        }
        return edges;
    }

    /**
     * @param adjacencies the adjacencies to set
     */
    private void setAdjacencies(Map<Node, List<Edge>> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public void clear() {
        if (this.adjacencies != null) {
            adjacencies.clear();
        }
    }
}
