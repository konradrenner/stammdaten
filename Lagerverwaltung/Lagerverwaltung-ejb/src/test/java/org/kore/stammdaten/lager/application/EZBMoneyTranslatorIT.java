package org.kore.stammdaten.lager.application;

import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Inject;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;

@RunWith(Arquillian.class)
public class EZBMoneyTranslatorIT {

   @Inject
    private MoneyTranslator ezbmoneytranslator;

   @Deployment
    public static JavaArchive createDeployment() {
       //Als WebArchive kanns ned deployed werden, da es den EJB TimerService benutzt und der folgende Ausdruck funktioniert auch nicht, weil aktuell kein ejb packaging unterstuetzt wird
       //return ShrinkWrap.create(MavenImporter.class).offline().loadPomFromFile("pom.xml").importBuildOutput().as(JavaArchive.class);
       //Aber in diesem Fall ist es eh gscheiter so, da ja nicht soviele Abhaengigkeiten in diesem Fall benoetigt werden
       JavaArchive archive = ShrinkWrap.create(JavaArchive.class);
       archive.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
       archive.addClass(EZBMoneyTranslator.class);
       archive.addClass(MoneyTranslator.class);
       archive.addClass(Money.class);

       return archive;
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
