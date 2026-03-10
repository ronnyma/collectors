package io.transfinite;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static io.transfinite.utils.StandardArchunitTests.codeIsFreeOfPackageCyclesRule;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import com.tngtech.archunit.lang.ArchRule;
import io.transfinite.utils.StandardArchunitTests;

@AnalyzeClasses(packages = "io.transfinite", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    @ArchTest
    public static final ArchTests standardTests = ArchTests.in(StandardArchunitTests.class);

    @ArchTest
    static final ArchRule collectors_should_reside_in_collectors_package =
        classes().that().haveSimpleNameEndingWith("Collectors")
            .should().resideInAPackage("..collectors..");

    @ArchTest
    static final ArchRule no_cycles =
        codeIsFreeOfPackageCyclesRule(ArchitectureTest.class.getPackageName());
}
