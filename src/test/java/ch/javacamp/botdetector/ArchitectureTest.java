package ch.javacamp.botdetector;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.theClass;

@AnalyzeClasses(packages = "ch.javacamp.botdetector")
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule protectApi = noClasses().that()//
            .resideInAPackage("ch.javacamp.botdetector")//
            .should().accessClassesThat().resideInAnyPackage("ch.javacamp.botdetector.impl..")//
            .andShould().notBe(Bots.class);

    @ArchTest
    public static final ArchRule protectFactory = theClass(Detector.class)//
            .should().onlyAccessClassesThat().resideInAnyPackage("ch.javacamp.botdetector", "ch.javacamp.botdetector.impl");

    @ArchTest
    public static final ArchRule utilsSouldbeIndependent = noClasses().that()//
            .resideInAPackage("ch.javacamp.botdetector.impl.utils")//
            .should()//
            .accessClassesThat().resideInAnyPackage("ch.javacamp.botdetector.impl");
}