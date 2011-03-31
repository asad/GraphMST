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

import org.openscience.cdk.interfaces.IBond;

/**
 *
 * @author Asad
 */
public class Edge implements Comparable<Edge> {

    private final Node from;
    private final Node to;
    private final int weight;
    private final IBond bond;

    /**
     * 
     * @param argFrom
     * @param argTo
     * @param bond 
     */
    public Edge(final Node argFrom, final Node argTo, final IBond bond) {
        this.from = argFrom;
        this.to = argTo;
        this.bond = bond;
        this.weight = bond.getOrder().ordinal() + 1;
    }

    /**
     * 
     * @param argEdge
     * @return
     */
    @Override
    public int compareTo(final Edge argEdge) {
        return getWeight() - argEdge.getWeight();
    }

    /**
     * 
     * @param otherObject
     * @return
     */
    @Override
    public boolean equals(Object otherObject) {
        // Not strictly necessary, but often a good optimization
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof Edge)) {
            return false;
        }
        Edge otherA = (Edge) otherObject;
        return this.bond.equals(otherA.getBond());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.bond != null ? this.bond.hashCode() : 0);
        return hash;
    }

    /**
     * @return the from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * @return the to
     */
    public Node getTo() {
        return to;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        String s = "{Bond "
                + NEW_LINE + " From Node : " + this.from.toString()
                + NEW_LINE + " To Node: " + this.to.toString()
                + NEW_LINE + " weight: " + this.weight + "}" + NEW_LINE;
        result.append(s);
        return result.toString();
    }

    /**
     * 
     * @return
     */
    public IBond getBond() {
        return bond;
    }
}