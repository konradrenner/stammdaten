package org.kore.stammdaten.domain.artikel;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.kore.stammdaten.domain.artikel.Artikelgruppe;
import org.kore.stammdaten.domain.lager.Lagerraumbestand;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-25T19:46:47")
@StaticMetamodel(Artikel.class)
public class Artikel_ { 

    public static volatile SingularAttribute<Artikel, String> bezeichnung;
    public static volatile SingularAttribute<Artikel, Integer> artikelId;
    public static volatile SingularAttribute<Artikel, BigDecimal> preis;
    public static volatile SingularAttribute<Artikel, String> beschreibung;
    public static volatile CollectionAttribute<Artikel, Lagerraumbestand> lagerraumbestandCollection;
    public static volatile SingularAttribute<Artikel, String> waehrung;
    public static volatile SingularAttribute<Artikel, Serializable> bild;
    public static volatile SetAttribute<Artikel, Artikelgruppe> artikelgruppeSet;

}