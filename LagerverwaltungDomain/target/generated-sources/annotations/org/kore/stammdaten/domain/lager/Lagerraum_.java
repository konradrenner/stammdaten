package org.kore.stammdaten.domain.lager;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.kore.stammdaten.domain.lager.Lager;
import org.kore.stammdaten.domain.lager.Lagerraumbestand;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-18T19:28:59")
@StaticMetamodel(Lagerraum.class)
public class Lagerraum_ { 

    public static volatile SingularAttribute<Lagerraum, String> bezeichnung;
    public static volatile SingularAttribute<Lagerraum, BigDecimal> volumen;
    public static volatile SingularAttribute<Lagerraum, String> typ;
    public static volatile CollectionAttribute<Lagerraum, Lagerraumbestand> lagerraumbestandCollection;
    public static volatile SingularAttribute<Lagerraum, Short> raumId;
    public static volatile SingularAttribute<Lagerraum, Lager> lagerId;

}