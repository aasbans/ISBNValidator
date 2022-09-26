package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {

    @Test
    void checkAValidIsbn() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkIsbn("0198526636");
        assertTrue(result);
    }

    @Test
    void checkAnInvalidIsbn() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkIsbn("0198526635");
        assertFalse(result);
    }

    @Test
    void checkNineDigitIsbnIsInvalid() {
        ValidateISBN validateISBN = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> {
            validateISBN.checkIsbn("019852663");
        });
    }

    @Test
    void checkNonNumericIsbnIsInvalid() {
        ValidateISBN validateISBN = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> {
            validateISBN.checkIsbn("Hello World");
        });
    }
    @Test
    void checkIsbnEndingWithXIsValid() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkIsbn("012000030X");
        assertTrue(result);
    }
}