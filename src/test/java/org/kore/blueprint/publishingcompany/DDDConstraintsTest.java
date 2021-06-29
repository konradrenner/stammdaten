/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany;

import org.kore.blueprint.publishingcompany.entity.constraints.ValueObject;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 *
 * @author Konrad Renner
 */
@AnalyzeClasses(packages = "org.kore.blueprint.publishingcompany.entity", importOptions = {ImportOption.DoNotIncludeTests.class})
public class DDDConstraintsTest {

    @ArchTest
    ArchRule value_objects_must_have_immuatble_fields = fields().that().areDeclaredInClassesThat().areAnnotatedWith(ValueObject.class)
            .and().areNotStatic()
            .should().bePrivate()
            .andShould().beFinal()
            .because("value objects must be immutable");
}
