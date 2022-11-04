package com.deviddle.phonecheck.phone;

import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Service
public class PhoneLibService {
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public CheckResponse check(CheckRequest checkRequest) {
        try {
            PhoneNumber pn = phoneUtil.parse(checkRequest.getNumber(), null);
            CheckResponse checkResponse = new CheckResponse();
            checkResponse.setValid(phoneUtil.isValidNumber(pn));
            checkResponse.setCountryCode(pn.getCountryCode());
            checkResponse.setNationalNumber(pn.getNationalNumber());
            return checkResponse;
        } catch (Exception e) {
            return new CheckResponse();
        }
    }

}
