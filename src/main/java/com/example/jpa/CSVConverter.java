package com.example.jpa;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CSVConverter {
    public CustomerDto convertToDto(CSVRecord input) {
        try {
            long id = Long.parseLong(input.get("id"));
            String firstName = input.get("firstname");
            String lastName = input.get("lastname");
            return new CustomerDto(id,firstName,lastName);
        } catch (NumberFormatException e) {
            throw new InvalidCSVRecordException(e.getMessage());
        }
    }
}
