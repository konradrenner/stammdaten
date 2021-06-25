/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany;

import at.arz.blueprint.publishingcompany.entity.constraints.ValueObject;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 *
 * @author rpri182
 */
@AnalyzeClasses(packages = "at.arz.blueprint.publishingcompany.entity", importOptions = {ImportOption.DoNotIncludeTests.class})
public class DDDConstraintsTest {

    @ArchTest
    ArchRule value_objects_must_have_immuatble_fields = fields().that().areDeclaredInClassesThat().areAnnotatedWith(ValueObject.class)
            .and().areNotStatic()
            .should().bePrivate()
            .andShould().beFinal()
            .because("value objects must be immutable");
}
