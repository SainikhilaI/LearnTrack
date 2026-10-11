package com.airtribe.learntrack.util;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.exceptions.InvalidInputException;

public class InputValidator {

    public static void validateNonEmpty(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void validateDate(String date) throws InvalidInputException {
        if (date == null || !date.matches(AppConstants.DATE_FORMATE_REGEX)) {
            throw new InvalidInputException("Invalid date format. Expected format: YYYY-MM-DD.");
        }
    }
}