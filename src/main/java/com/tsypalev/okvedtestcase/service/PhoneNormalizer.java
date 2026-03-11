package com.tsypalev.okvedtestcase.service;

/**
 * Utility class for phone normalization
 */
public class PhoneNormalizer {

  /**
   * Normalize Russian phone number to format +79XXXXXXXXX
   *
   * @param input raw phone number
   * @return normalized phone
   */
  public static String normalize(String input) {

    if (input == null || input.isBlank()) {
      throw new IllegalArgumentException("Phone number is empty");
    }

    String digits = input.replaceAll("\\D", "");

    if (digits.length() == 11 && digits.startsWith("8")) {
      digits = "7" + digits.substring(1);
    }

    if (digits.length() == 11 && digits.startsWith("7")) {
      return "+" + digits;
    }

    throw new IllegalArgumentException("Cannot normalize phone number");
  }
}
