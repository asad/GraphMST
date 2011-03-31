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

import junit.framework.Assert;
import org.junit.Test;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.smiles.SmilesParser;

/**
 *
 * @author Asad
 */
public class GraphAtomContainerTest {

    @Test
    public void testInstance() throws Exception {
        SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
        IAtomContainer ac = sp.parseSmiles("Nc1ccccc1");
        int size = ac.getAtomCount();
        Assert.assertEquals(size, new AtomContainer(ac).getAtomCount());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testNeighbours() throws Exception {
        SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
        IAtomContainer ac = sp.parseSmiles("Nc1ccccc1");
        int counter = 0;
        for (IAtom atom : ac.atoms()) {
            atom.setID(Integer.toString(counter++));
        }
        IAtom atom = ac.getAtom(0);
        Assert.assertEquals(ac.getConnectedAtomsList(atom), new AtomContainer(ac).getConnectedAtomsList(atom));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testClone() throws Exception {
        SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
        IAtomContainer ac = sp.parseSmiles("Nc1ccccc1");
        IAtomContainer g1 = new AtomContainer(ac);
        IAtomContainer g2 = (AtomContainer) g1.clone();

        Node atomG1AC1 = new Node(g1.getAtom(0));
        Node atomG2AC1 = new Node(g2.getAtom(0));

        Assert.assertNotSame(atomG1AC1, atomG2AC1);
        Assert.assertEquals(g1.getAtomCount(), g2.getAtomCount());
    }
}
