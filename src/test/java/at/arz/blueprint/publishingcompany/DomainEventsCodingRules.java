/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 *
 * @author rpri182
 */
@AnalyzeClasses(packages = "at.arz.blueprint.publishingcompany.entity.events")
public class DomainEventsCodingRules {
    
    @ArchTest
    ArchRule domain_events_are_not_allowed_to_access_other_packages = noClasses().that().resideInAPackage("..events..")
            .should().accessClassesThat().resideOutsideOfPackage("at.arz.blueprint.publishingcompany.entity.events");
}
