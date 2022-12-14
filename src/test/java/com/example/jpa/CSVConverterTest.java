package com.example.jpa;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static com.example.jpa.TestCSVRecords.getSingleParsedRecord;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class CSVConverterTest {

    private CSVConverter testee;

    @BeforeEach
    void setUp() {
        testee = new CSVConverter();
    }

    @Test
    void convertRecordToDto() throws Exception {
        CSVParser csvRecords = getSingleParsedRecord();
        List<CSVRecord> records = csvRecords.getRecords();
        assertThat(records).hasSize(1);
        CSVRecord csvRecord = records.get(0);

        CustomerDto expected = new CustomerDto(1L, "Jack", "Jones");

        CustomerDto actual = testee.convertToDto(csvRecord);
        assertThat(actual).isEqualTo(expected);
    }


}
