/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.domain.versandkosten;

import java.math.BigDecimal;
import java.util.Currency;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author koni
 */
public class VersandkostenServiceTest {

    //TODO umbau auf DeltaSpike
    VersandkostenService service;
    MoneyTranslator translator;
    Versandkosten testObject;

    @Before
    public void setUp() {
        translator = new TestMoneyTranslator();
        service = new VersandkostenService(translator);
        Money money = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
        Land land = new Land("AT");
        testObject = new Versandkosten(land, money);
    }

    @Test
    public void testChangeBetrag() {
        Money newmoney = new Money(BigDecimal.TEN, Currency.getInstance("EUR"));
        service.changeBetrag(testObject, newmoney);
        
        assertEquals(newmoney, testObject.getBetrag());
    }

    @Test
    public void testChangeBetragWithDifferentCurrency() {
        Money newmoney = new Money(BigDecimal.TEN, Currency.getInstance("USD"));
        service.changeBetrag(testObject, newmoney);
        
        assertEquals(new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR")), testObject.getBetrag());
    }

    @Test
    public void testChangeFreibetrag() {
        Money newmoney = new Money(BigDecimal.TEN, Currency.getInstance("EUR"));
        service.changeFreibetrag(testObject, newmoney);

        assertEquals(newmoney, testObject.getFreibetrag());
    }

    @Test
    public void testChangeFreibetragNull() {
        service.changeFreibetrag(testObject, null);

        assertNull(testObject.getFreibetrag());
    }

    @Test
    public void testChangeFreibetragWithDifferentCurrency() {
        Money newmoney = new Money(BigDecimal.TEN, Currency.getInstance("USD"));
        service.changeFreibetrag(testObject, newmoney);

        assertEquals(new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR")), testObject.getFreibetrag());
    }

    @Test
    public void testChangeCurrencyFreibetragNull() {
        service.changeCurrency(testObject, Currency.getInstance("USD"));

        assertEquals(new Money(BigDecimal.ONE, Currency.getInstance("USD")), testObject.getBetrag());
        assertNull(testObject.getFreibetrag());
    }
    
    @Test
    public void testChangeCurrency() {
        testObject.setFreibetrag(BigDecimal.TEN);
        service.changeCurrency(testObject, Currency.getInstance("USD"));
        
        assertEquals(new Money(BigDecimal.ONE, Currency.getInstance("USD")), testObject.getBetrag());
        assertEquals(new Money(BigDecimal.valueOf(11), Currency.getInstance("USD")), testObject.getFreibetrag());
    }

    @Test
    public void testChangeCurrencyNoChange() {
        service.changeCurrency(testObject, Currency.getInstance("EUR"));

        assertEquals(new Money(BigDecimal.ZERO, Currency.getInstance("EUR")), testObject.getBetrag());
    }
}