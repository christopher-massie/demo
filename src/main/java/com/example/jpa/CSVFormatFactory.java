package com.example.jpa;

import org.apache.commons.csv.CSVFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CSVFormatFactory {

    @Bean
    public static CSVFormat CSV_FORMAT(){
        return CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build();
    }
}
