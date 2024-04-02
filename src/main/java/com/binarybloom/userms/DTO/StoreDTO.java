package com.binarybloom.userms.DTO;

import lombok.Data;

@Data
public class StoreDTO {
    private Integer id;
    private String name;
    private String address;

    @Override
    public String toString() {
       return (id + " " + name + " " + address);
    }
}
