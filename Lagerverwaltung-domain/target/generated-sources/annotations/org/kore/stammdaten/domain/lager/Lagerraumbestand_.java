package org.kore.stammdaten.domain.lager;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.kore.stammdaten.domain.artikel.Artikel;
import org.kore.stammdaten.domain.lager.Lagerraum;
import org.kore.stammdaten.domain.lager.LagerraumbestandPK;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-08-02T17:54:41")
@StaticMetamodel(Lagerraumbestand.class)
public class Lagerraumbestand_ { 

    public static volatile SingularAttribute<Lagerraumbestand, Lagerraum> lagerraum;
    public static volatile SingularAttribute<Lagerraumbestand, LagerraumbestandPK> lagerraumbestandPK;
    public static volatile SingularAttribute<Lagerraumbestand, BigDecimal> volumenVerbrauch;
    public static volatile SingularAttribute<Lagerraumbestand, Artikel> artikel;

}