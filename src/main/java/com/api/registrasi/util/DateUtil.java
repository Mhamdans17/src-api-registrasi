package com.api.registrasi.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {
    public static int calculateAge(String birthDateString){
        LocalDate birthDate = LocalDate.parse(birthDateString);
        LocalDate currenDate = LocalDate.now();
        return Period.between(birthDate, currenDate).getYears();
    }
}
