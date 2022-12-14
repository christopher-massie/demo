package com.example.jpa;

public class DtoConverter {
    private DtoConverter(){}
    public static Customer convertDtoToEntity(CustomerDto customerDto){
        return new Customer(customerDto.id(), customerDto.firstName(), customerDto.lastName());
    }
}
