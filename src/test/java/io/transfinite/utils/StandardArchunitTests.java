package io.transfinite.utils;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

public final class StandardArchunitTests {

    @ArchTest
    static final ArchRule classesDoNotAccessStandardStreams
            = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    static final ArchRule classesDoNotUseJavaUtilLogging
            = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    public static ArchRule codeIsFreeOfPackageCyclesRule(String basePackage) {
        return slices().matching(basePackage + ".(**)")
                .namingSlices(basePackage + ".$1")
                .should().beFreeOfCycles()
                .as("Code is free of package cycles");
    }

}
