package org.kore.stammdaten.lager.application;

import java.io.File;
import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Inject;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kore.runtime.currency.Money;

@RunWith(Arquillian.class)
public class EZBMoneyTranslatorIT {

   @Inject
    private EZBMoneyTranslator ezbmoneytranslator;

   @Deployment
    public static WebArchive createDeployment()   {
       File[] mavenArtefakte = Maven.configureResolver().workOffline()
               .loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

       return ShrinkWrap.create(WebArchive.class).addAsLibraries(mavenArtefakte);
   }

   @Test
    public void testEuroUmrechnung() {
        Money umzurechnen = new Money(BigDecimal.ONE, Currency.getInstance("EUR"));
        Money translated = ezbmoneytranslator.translate(umzurechnen, Currency.getInstance("EUR"));
        
        assertThat(translated, is(equalTo(umzurechnen)));
    }

    @Test
    public void testFremdwaehrung() {
        Money umzurechnen = new Money(BigDecimal.ONE, Currency.getInstance("EUR"));
        Money translated = ezbmoneytranslator.translate(umzurechnen, Currency.getInstance("USD"));

        assertThat(translated, is(not(equalTo(umzurechnen))));
        assertThat(translated.getCurrency(), is(equalTo(Currency.getInstance("USD"))));
    }
}
