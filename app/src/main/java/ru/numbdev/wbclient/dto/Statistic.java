package ru.numbdev.wbclient.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Statistic implements Serializable {

    private String id;
    public Integer price;
    public Long countOfSales;
    public Integer countOfProductType;
    private Date createdDate;

}
