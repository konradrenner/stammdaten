/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.arquillian;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author martin
 */
@Stateless
public class ArquillianTestBean implements ArquillianTestService{

    @Override
    public List<Testdata> getTestdata() {
        ArrayList<Testdata> ret = new ArrayList<>();
        ret.add(new TestdataEntity(1, "Hallo"));
        ret.add(new TestdataEntity(2, "Welt"));
        return ret;
    }


}
