package org.kore.stammdaten.domain.versandkosten;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-20T18:33:26")
@StaticMetamodel(Versandkosten.class)
public class Versandkosten_ { 

    public static volatile SingularAttribute<Versandkosten, BigDecimal> betrag;
    public static volatile SingularAttribute<Versandkosten, BigDecimal> freibetrag;
    public static volatile SingularAttribute<Versandkosten, String> land;

}