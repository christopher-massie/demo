package com.example.jpa;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class CSVReaderTest {

    private CSVFormat csvFormat = CSVFormatFactory.CSV_FORMAT();

    @Test
    void whenFileDoesNotExist_throwsException() throws Exception{
        CSVReader csvReader = new CSVReader(csvFormat);
        assertThatThrownBy(() -> csvReader.parse("NOT A FILE")).isInstanceOf(FileNotFoundException.class);
    }

    @Test
    void whenFileExists_doesNotThrowException() {
        assertThatNoException().isThrownBy(() -> new CSVReader(csvFormat));
    }

    @Test
    void parsesFileSuccessfully() throws Exception {
        CSVReader testee = new CSVReader(csvFormat);
        List<CSVRecord> results = testee.parse("src/test/resources/test.csv");
        assertThat(results.size()).isEqualTo(3);
    }
}
