package org.kore.stammdaten.domain.lager;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.kore.stammdaten.domain.lager.Lagerraum;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-08-02T17:54:41")
@StaticMetamodel(Lager.class)
public class Lager_ { 

    public static volatile SingularAttribute<Lager, String> bezeichnung;
    public static volatile SingularAttribute<Lager, String> adressid;
    public static volatile SingularAttribute<Lager, String> email;
    public static volatile SetAttribute<Lager, Lagerraum> lagerraumSet;
    public static volatile SingularAttribute<Lager, String> beschreibung;
    public static volatile SingularAttribute<Lager, String> adressidTelefon;
    public static volatile SingularAttribute<Lager, String> adressidFax;
    public static volatile SingularAttribute<Lager, Short> lagerId;

}