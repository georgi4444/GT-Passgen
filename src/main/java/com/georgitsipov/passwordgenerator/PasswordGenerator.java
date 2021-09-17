package com.georgitsipov.passwordgenerator;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private static final char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] UPPERCASE = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
    private static final char[] NUMBERS = "0123456789".toCharArray();
    private static final char[] SYMBOLS = "^$?!@#%&".toCharArray();

    private double length = 6;
    private boolean uppercase = false;
    private boolean lowercase = false;
    private boolean numbers = false;
    private boolean symbols = false;

    public void setLength(double length) {
        this.length = length;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }

    public void setLowercase(boolean lowercase) {
        this.lowercase = lowercase;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public void setSymbols(boolean symbols) {
        this.symbols = symbols;
    }

    /**
     * Sets the include options
     *
     * @param all boolean for all include options
     */
    public void setAll(boolean all) {
        uppercase = all;
        lowercase = all;
        numbers = all;
        symbols = all;
    }

    /**
     * Generates random password from the allowed chars specified in the options from the user
     *
     * @return Random password
     */
    public String generatePassword() {
        List<char[]> allAllowed = new ArrayList<>();
        if (uppercase) {
            allAllowed.add(UPPERCASE);
        }
        if (lowercase) {
            allAllowed.add(LOWERCASE);
        }
        if (numbers) {
            allAllowed.add(NUMBERS);
        }
        if (symbols) {
            allAllowed.add(SYMBOLS);
        }

        //Use cryptographically secure random number generator
        Random random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        // Random chars
        for (int i = 0; i < length - allAllowed.size(); i++) {
            char[] characterType = allAllowed.get(random.nextInt(allAllowed.size()));
            password.append(characterType[random.nextInt(characterType.length)]);
        }

        //Ensure password policy is met by inserting required random chars in random positions
        for (char[] category : allAllowed) {
            password.insert(random.nextInt(password.length()), category[random.nextInt(category.length)]);
        }
        return password.toString();
    }

    /**
     * Calculates the strength of a password
     * 0 Weak        （guesses < ^ 3 10）
     * 1 Fair        （guesses <^ 6 10）
     * 2 Good        （guesses <^ 8 10）
     * 3 Strong      （guesses < 10 ^ 10）
     * 4 Very strong （guesses >= 10 ^ 10）
     *
     * @param password The password as String
     * @return Integer indicating password strength
     */
    public static int checkStrength(String password) {
        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(password);
        return strength.getScore();
    }
}
