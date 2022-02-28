/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.author;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Konrad Renner
 */
public class BookTest {

    private Book underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Book(new ISBN("9783866801929"), "Testbook", "Testdesc", LocalDateTime.now(), new Price(BigDecimal.TEN, Currency.GCRDT), (short) 2);
    }

    @Test
    public void addPositivePriceValue() {
        Price newPrice = underTest.addValueToPrice(BigDecimal.ONE);

        assertEquals(new BigDecimal("11.00"), newPrice.value());
    }

    @Test
    public void addNegativePriceValue() {
        Price newPrice = underTest.addValueToPrice(new BigDecimal("-1"));

        assertEquals(new BigDecimal("9.00"), newPrice.value());
    }

}
