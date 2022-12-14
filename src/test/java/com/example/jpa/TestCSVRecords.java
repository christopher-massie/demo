package com.example.jpa;

import org.apache.commons.csv.CSVParser;

import java.io.IOException;

public class TestCSVRecords {
    private TestCSVRecords() {
    }

    public static CSVParser getSingleParsedRecord() throws IOException {
        String headerRow = "id,firstname,lastname\r\n";
        return CSVParser.parse(headerRow + "1,Jack,Jones\r\n", CSVFormatFactory.CSV_FORMAT());
    }

    public static CSVParser getMultipleParsedRecords() throws IOException {
        String headerRow = "id,firstname,lastname\r\n";
        String row1 = "1,Jack,Jones\r\n";
        String row2 = "2,Bill,Smith\r\n";
        return CSVParser.parse(headerRow + row1+ row2, CSVFormatFactory.CSV_FORMAT());
    }
}
