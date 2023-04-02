/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Checkt gegen eine Whitelist ob nur erlaubte Technologien im Domain-Layer
 * eingesetzt werden (bspw. nur Bean Validation und CDI ist erlaubt, sonst muss
 * es Plain Java sein).
 *
 * @author Konrad Renner
 */
@AnalyzeClasses(packages = "org.kore.blueprint.publishingcompany.boundary", importOptions = {ImportOption.DoNotIncludeTests.class})
public class CorrectTechnologyUsageInBoundaryTest {

    @ArchTest
    ArchRule just_jaxrs_is_allowed_in_jaxrs_package = classes()
            .that().resideInAPackage("..jaxrs..")
            .should()
            .onlyAccessClassesThat()
            .resideInAnyPackage("java..",
                    "jakarta.validation..",
                    "jakarta.enterprise..",
                    "jakarta.inject..",
                    "jakarta.ws.rs..",
                    "io.opentelemetry.api..",
                    "..jaxrs..",
                    "org.kore.blueprint.publishingcompany.control..",
                    "org.kore.blueprint.publishingcompany.entity..");

    @ArchTest
    ArchRule just_jpa_is_allowed_in_jpa_package = classes()
            .that().resideInAPackage("..jpa..")
            .should()
            .onlyAccessClassesThat()
            .resideInAnyPackage("java..",
                    "jakarta.validation..",
                    "jakarta.enterprise..",
                    "jakarta.inject..",
                    "jakarta.persistence..",
                    "jakarta.transaction..",
                    "org.kore.blueprint.publishingcompany.boundary.jpa..",
                    "org.kore.blueprint.publishingcompany.control..",
                    "org.kore.blueprint.publishingcompany.entity..");
}
