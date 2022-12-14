package com.example.jpa;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerInsertService {

    private CSVReader csvReader;
    private CSVConverter csvConverter;
    private CustomerRepository repository;

    @Autowired
    public CustomerInsertService(CSVReader csvReader, CSVConverter csvConverter, CustomerRepository repository) {
        this.csvReader = csvReader;
        this.csvConverter = csvConverter;
        this.repository = repository;
    }

    public List<CustomerDto> getAllCustomers(String filePath) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        try {
            List<CSVRecord> records = csvReader.parse(filePath);
            for (CSVRecord record : records) {
                CustomerDto customerDto = csvConverter.convertToDto(record);
                customerDtos.add(customerDto);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customerDtos;
    }

    public List<Customer> saveAllCustomers(List<CustomerDto> customerDtos){
        List<Customer> saved = new ArrayList<>();
        for (CustomerDto customerDto : customerDtos) {
            Customer customerToSave = DtoConverter.convertDtoToEntity(customerDto);
            saved.add(repository.save(customerToSave));
        }
        return saved;
    }


}
