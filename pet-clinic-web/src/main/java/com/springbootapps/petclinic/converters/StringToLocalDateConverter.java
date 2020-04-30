package com.springbootapps.petclinic.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter dd_MM_yyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public StringToLocalDateConverter() {
    }

    @Override
    public LocalDate convert(String s) {
        if (!s.isEmpty()) {
            try {
                return LocalDate.parse(s, yyyy_MM_dd);
            } catch (Exception e) {
                return LocalDate.parse(s, dd_MM_yyyy);
            }
        }
        return LocalDate.now();
    }
}
