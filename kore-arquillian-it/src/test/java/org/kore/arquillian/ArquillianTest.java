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

import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import static org.hamcrest.CoreMatchers.is;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.assertThat;
import org.junit.Before;
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
        File h2Library = Maven.resolver().loadPomFromFile("pom.xml")
            .resolve("com.h2database:h2").withoutTransitivity()
            .asSingleFile();
        
        JavaArchive ejbArchive = ShrinkWrap
                .create(JavaArchive.class, "myejbs.jar")
                .addClass(ArquillianTestBean.class)
                .addClass(TestdataEntity.class)
                .addClass(DataSourceConfiguration.class)
                .addAsResource("create.sql","META-INF/create.sql")
                .addAsResource("test-persistence.xml","META-INF/persistence.xml");
        
        JavaArchive ejbClientArchive = ShrinkWrap.create(JavaArchive.class, "myejbclients.jar").addClass(ArquillianTestService.class).addClass(Testdata.class);
        
        //Because the whole archive is build on our own here, it is also necessary to add the Test-class on our own
        WebArchive testClass = ShrinkWrap.create(WebArchive.class, "testclass.war").addClass(ArquillianTest.class);
        
        EnterpriseArchive earArchive = ShrinkWrap
                .create(EnterpriseArchive.class,"myear.ear")
                .addAsModule(ejbArchive)
                .addAsModule(testClass)
                .addAsLibrary(h2Library)
                .addAsLibrary(ejbClientArchive);
        
        System.out.println("output:"+earArchive.toString(true));
        
        return earArchive;
    }
    
    @EJB
    private ArquillianTestService testservice;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    @Before
    public void prepareTestdata() throws Exception{
        clearData();
        insertData();
    }
          
    
    @Test
    public void tryit(){
        List<Testdata> testdata = testservice.getTestdata();
        
        assertThat(testdata.get(0).getId(), is(1));
        assertThat(testdata.get(0).getText(), is("Hallo"));
        
        assertThat(testdata.get(1).getId(), is(2));
        assertThat(testdata.get(1).getText(), is("Welt"));
    }
    
    private void clearData() throws Exception{
        utx.begin();
        em.joinTransaction();
        System.out.println("clearing data");
        em.createNativeQuery("delete from wildflytest").executeUpdate();
        utx.commit();
    }
    
    private void insertData() throws Exception{
        utx.begin();
        em.joinTransaction();
        System.out.println("inserting data");
        em.createNativeQuery("insert into wildflytest(id,text) values(1,'Hallo')").executeUpdate();
        em.createNativeQuery("insert into wildflytest(id,text) values(2,'Welt')").executeUpdate();
        utx.commit();
        em.clear();
    }
}
