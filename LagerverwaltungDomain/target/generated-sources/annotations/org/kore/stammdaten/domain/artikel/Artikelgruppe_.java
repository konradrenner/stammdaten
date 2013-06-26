package org.kore.stammdaten.domain.artikel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.kore.stammdaten.domain.artikel.Artikel;
import org.kore.stammdaten.domain.artikel.ArtikelTyp;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-20T18:33:26")
@StaticMetamodel(Artikelgruppe.class)
public class Artikelgruppe_ { 

    public static volatile SingularAttribute<Artikelgruppe, String> bezeichnung;
    public static volatile SetAttribute<Artikelgruppe, Artikel> artikelSet;
    public static volatile SingularAttribute<Artikelgruppe, String> beschreibung;
    public static volatile SingularAttribute<Artikelgruppe, ArtikelTyp> typ;

}