/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Checkt gegen eine Whitelist ob nur erlaubte Technologien im Domain-Layer
 * eingesetzt werden (bspw. nur Bean Validation ist erlaubt, sonst muss es Plain
 * Java sein).
 *
 * @author rpri182
 */
@AnalyzeClasses(packages = "at.arz.blueprint.publishingcompany.entity", importOptions = {ImportOption.DoNotIncludeTests.class})
public class TechnologyFreeDomainTest {

    @ArchTest
    ArchRule domain_objects_must_use_plain_java_or_bean_validation = classes()
            .should()
            .onlyAccessClassesThat()
            .resideInAnyPackage("java..", "javax.validation..", "at.arz.blueprint.publishingcompany.entity..");
}
