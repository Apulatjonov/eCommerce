package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneNumberValidationCheckModel {
    private String phoneNumberEntered;
    private String defaultCountryEntered;
    private String languageEntered;
    private String countryCode;
    private String nationalNumber;
    private String extension;
    private String countryCodeSource;
    private String italianLeadingZero;
    private String rawInput;
    private boolean isPossibleNumber;
    private boolean isValidNumber;
    private boolean isValidNumberForRegion;
    private String phoneNumberRegion;
    private String numberType;
    private String E164Format;
    private String originalFormat;
    private String nationalFormat;
    private String internationalFormat;
    private String outOfCountryFormatFromUS;
    private String outOfCountryFormatFromCH;
    private String location;
    private String timeZone_s;
    private String carrier;
}
