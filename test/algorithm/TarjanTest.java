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
package algorithm;

import helper.AdjacencyList;
import helper.Node;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.smiles.SmilesParser;

/**
 *
 * @author Asad
 */
public class TarjanTest {

    @Test
    public void testInstance() throws Exception {
        SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
        IAtomContainer ac = sp.parseSmiles("Nc1ccccc1");
        IAtomContainer g1 = new AtomContainer(ac);

        AdjacencyList adj = new AdjacencyList();

        List<Node> nodes = new ArrayList<Node>();
        int counter = 0;
        for (IAtom atom0 : g1.atoms()) {
            nodes.add(counter++, new Node(atom0));
        }

        for (Node atom0 : nodes) {
            for (Node atom1 : nodes) {
                IBond bond = g1.getBond(atom0.getAtom(), atom1.getAtom());
                if (bond != null) {
                    adj.addEdge(atom0, atom1, bond);
                }
            }
        }

        List<List<Node>> tarjan = new Tarjan().tarjan(nodes.get(1), adj);
        Assert.assertEquals(1, tarjan.size());
        Assert.assertEquals(7, tarjan.get(0).size());

    }
}
