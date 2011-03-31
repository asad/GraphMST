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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @param <Node> 
 * @author Asad
 */
public class DisjointSet<Node> {

    private List<List<Node>> set = new ArrayList<List<Node>>();

    public void createSubsets(Node[] items) {
        createSubsets(Arrays.asList(items));
    }

    public void createSubsets(Collection<Node> items) {
        for (Node item : items) {
            List<Node> subset = new ArrayList<Node>();
            subset.add(item);
            getSet().add(subset);
        }
    }

    public void merge(int setA, int setB) {
        getSet().get(setA).addAll(getSet().get(setB));
        getSet().remove(setB);
    }

    public int find(Node searchfor) {
        for (int i = 0; i < getSet().size(); i++) {
            if (getSet().get(i).contains(searchfor)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return the set
     */
    public List<List<Node>> getSet() {
        return set;
    }

    /**
     * @param set the set to set
     */
    public void setSet(List<List<Node>> set) {
        this.set = set;
    }
}