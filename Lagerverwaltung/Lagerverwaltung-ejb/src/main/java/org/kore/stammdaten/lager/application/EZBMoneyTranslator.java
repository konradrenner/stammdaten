/*
 * Copyright (C) 2014 Konrad Renner.
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
package org.kore.stammdaten.lager.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Currency;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Konrad Renner
 */
@Singleton
@ApplicationScoped
public class EZBMoneyTranslator implements MoneyTranslator, Serializable {

    private Map<Currency, BigDecimal> umrechnungsBetraege;

    @Override
    public Money translate(Money money, Currency crnc) {
        if (money.getCurrency().equals(crnc)) {
            return money;
        }

        BigDecimal umrechnungsBetrag = umrechnungsBetraege.get(crnc);
        if (umrechnungsBetrag == null) {
            umrechnungsBetrag = BigDecimal.ONE;
        }

        BigDecimal neuerBetrag = money.getAmount().multiply(umrechnungsBetrag);

        return new Money(neuerBetrag, crnc);
    }

    @PostConstruct
    public void init() {
        umrechnungsBetraege = new ConcurrentHashMap<>();
        refresh();
    }

    @Schedule(hour = "3", minute = "5", timezone = "Europe/Vienna")
    void refresh() {
        try {
            URLConnection connection = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openConnection();

            fillUmrechnungsbetrageFrom(connection.getInputStream());
        } catch (MalformedURLException ex) {
            Logger.getLogger(EZBMoneyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EZBMoneyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(EZBMoneyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void fillUmrechnungsbetrageFrom(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);
        doc.getDocumentElement().normalize();

        NodeList nodes = doc.getElementsByTagName("Cube");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            NamedNodeMap attributes = node.getAttributes();
            Attr currencyAttribute = (Attr) attributes.getNamedItem("currency");
            Attr rateAttribute = (Attr) attributes.getNamedItem("rate");
            if (currencyAttribute != null && rateAttribute != null) {
                Currency currency = Currency.getInstance(currencyAttribute.getValue());
                BigDecimal amount = new BigDecimal(rateAttribute.getValue());
                this.umrechnungsBetraege.put(currency, amount);
            }
        }

    }
}
