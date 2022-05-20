/**
 * @package Quarkus-Arch-Testing-Showcase
 *
 * @file Contrived tests with ArchUnit
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.architecture.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = "dev.unexist.showcase")
public class AssortedArchUnitTest {

    @ArchTest
    static final ArchRule testShouldBePrefixedWithTest =
            methods()
                .that()
                    .areAnnotatedWith(Test.class)
                .should()
                    .haveNameMatching("^should.*")
                .because("Test classes must be prefixed with 'should'");

    @ArchTest
    static final ArchRule loggersShouldBeStaticAndPrivate =
            fields()
                .that()
                    .haveRawType(Logger.class)
                .should()
                    .bePrivate()
                    .andShould().beStatic()
                    .andShould().beFinal()
                .because("Loggers have to be that way");

    @ArchTest
    static final ArchRule checkPlacement =
            classes()
                .that()
                    .resideInAnyPackage("..test..")
                .should()
                    .bePublic();
}
