/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.author;

import java.util.UUID;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Konrad Renner
 */
public class AuthorFactoryTest {

    static ValidatorFactory factory;

    AuthorFactory underTest;

    @BeforeAll
    public static void beforeClass() {
        factory = Validation.buildDefaultValidatorFactory();
    }

    @BeforeEach
    public void setUp() {
        underTest = new AuthorFactory(factory.getValidator());
    }

    @Test
    public void unsuccessfulCreation() {
        AuthorFactory.WithPublicationBuilder builder = underTest.createAuthor(UUID.randomUUID()).firstname("").lastname("");

        assertThrows(ConstraintViolationException.class, builder::build);
    }

    @Test
    public void successfulCreation() {
        Author author = underTest.createAuthor(UUID.randomUUID()).firstname("Max").lastname("Mustermann").build();

        assertEquals("Max", author.getName().getFirstName());
        assertEquals("Mustermann", author.getName().getLastName());
    }
}
