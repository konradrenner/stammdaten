/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.*;
import com.tngtech.archunit.core.importer.ImportOption;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.ws.rs.Path;

/**
 *
 * @author Konrad Renner
 */
@AnalyzeClasses(packages = "org.kore.blueprint.publishingcompany", importOptions = {ImportOption.DoNotIncludeTests.class})
public class CleanArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("..entity..")
            .domainServices("..control..")
            .applicationServices("..control..")
            .adapter("external", "..boundary.externalservice..")
            .adapter("rest", "..boundary.jaxrs..")
            .adapter("jsf", "..boundary.jsf..")
            .adapter("jpa", "..boundary.jpa..");

    @ArchTest
    static final ArchRule jpaentities_must_reside_in_a_jpa_package
            = classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..boundary.jpa..")
                    .as("JPA Entities should reside in a package '..jpa..'");

    @ArchTest
    static final ArchRule jpaembeddables_must_reside_in_a_jpa_package
            = classes().that().areAnnotatedWith(Embeddable.class).should().resideInAPackage("..boundary.jpa..")
                    .as("JPA Embeddables should reside in a package '..jpa..'").allowEmptyShould(true);

    @ArchTest
    static final ArchRule jaxrsresources_must_reside_in_a_jaxrs_package
            = classes().that().areAnnotatedWith(Path.class).should().resideInAPackage("..jaxrs..")
                    .as("JAX RS Resources should reside in a package '..jaxrs..'");
}
