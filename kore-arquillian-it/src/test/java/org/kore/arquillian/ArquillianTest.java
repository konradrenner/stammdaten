/*
 * Copyright (C) 2018 koni.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.kore.arquillian;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.is;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author koni
 */
@RunWith(Arquillian.class)
public class ArquillianTest {
    
    @Deployment
    public static Archive<?> createDeployment(){
        JavaArchive ejbArchive = ShrinkWrap.create(JavaArchive.class, "myejbs.jar").addClass(ArquillianTestBean.class).addClass(TestdataEntity.class);
        JavaArchive ejbClientArchive = ShrinkWrap.create(JavaArchive.class, "myejbclients.jar").addClass(ArquillianTestService.class).addClass(Testdata.class);
        
        //Because the whole archive is build on our own here, it is also necessary to add the Test-class on our own
        WebArchive testClass = ShrinkWrap.create(WebArchive.class, "testclass.war").addClass(ArquillianTest.class);
        
        EnterpriseArchive earArchive = ShrinkWrap
                .create(EnterpriseArchive.class,"myear.ear")
                .addAsModule(ejbArchive)
                .addAsModule(testClass)
                .addAsLibrary(ejbClientArchive);
        
        System.out.println("output:"+earArchive.toString(true));
        
        return earArchive;
    }
    
    @EJB
    private ArquillianTestService testservice;
    
    @Test
    public void tryit(){
        List<Testdata> testdata = testservice.getTestdata();
        
        assertThat(testdata.get(0).getId(), is(1));
        assertThat(testdata.get(0).getText(), is("Hallo"));
        
        assertThat(testdata.get(1).getId(), is(2));
        assertThat(testdata.get(1).getText(), is("Welt"));
    }
}
