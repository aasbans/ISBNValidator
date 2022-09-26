package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkIsbn(String isbn) {
        if (isbn.toCharArray().length == LONG_ISBN_LENGTH)
            return isThisAValidLongIsbn(isbn);
        if (isbn.toCharArray().length != SHORT_ISBN_LENGTH)
            return isThisAValidShortIsbn(isbn);
        throw new NumberFormatException("ISBN length should be 10 or 13");
    }

    private static boolean isThisAValidShortIsbn(String isbn) {
        int sum = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    sum += 10;
                } else throw new NumberFormatException("ISBN should be numeric");
            } else sum += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
        }
        return (sum % SHORT_ISBN_MULTIPLIER == 0);
    }

    private boolean isThisAValidLongIsbn(String isbn) {
        int sum = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i % 2 == 0)
                    sum += i;
                else sum += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return (sum % LONG_ISBN_MULTIPLIER == 0);
    }

}
