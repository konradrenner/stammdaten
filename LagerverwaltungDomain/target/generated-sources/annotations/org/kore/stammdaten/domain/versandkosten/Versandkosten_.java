package org.kore.stammdaten.domain.versandkosten;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.kore.stammdaten.core.adresse.Land;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-25T19:46:47")
@StaticMetamodel(Versandkosten.class)
public class Versandkosten_ { 

    public static volatile SingularAttribute<Versandkosten, BigDecimal> betrag;
    public static volatile SingularAttribute<Versandkosten, BigDecimal> freibetrag;
    public static volatile SingularAttribute<Versandkosten, Land> land;
    public static volatile SingularAttribute<Versandkosten, String> waehrung;

}