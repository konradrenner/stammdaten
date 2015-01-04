package org.kore.stammdaten.lager.application.lager;

import java.io.File;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LagerBeanIT {

   @Inject
   private LagerBean lagerbean;

   @Deployment
    public static WebArchive createDeployment()   {
       File[] mavenArtefakte = Maven.configureResolver().workOffline()
               .loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

       return ShrinkWrap.create(WebArchive.class)
               .addAsLibraries(mavenArtefakte)
               .addPackage("org/kore/stammdaten/lager/adapter")
               .addPackage("org/kore/stammdaten/lager/application/lager");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(lagerbean);
   }
}
