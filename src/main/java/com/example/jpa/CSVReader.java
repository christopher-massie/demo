package com.example.jpa;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CSVReader {

    private File file;
    private final CSVFormat csvFormat;

    public CSVReader(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
    }

    public List<CSVRecord> parse(String filePath) throws IOException {
        file = new File(filePath);

        CSVParser csvParser = csvFormat.parse(new FileReader(file));
        return csvParser.getRecords();
    }
}
