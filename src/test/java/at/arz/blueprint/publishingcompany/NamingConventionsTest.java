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
@AnalyzeClasses(packages = "at.arz.blueprint.publishingcompany")
public class NamingConventionsTest {

    @ArchTest
    static final ArchRule interfaces_should_not_have_names_ending_with_the_word_interface
            = noClasses().that().areInterfaces().should().haveNameMatching(".*Interface");

    @ArchTest
    static final ArchRule interfaces_should_not_have_simple_class_names_containing_the_word_interface
            = noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface");
}
