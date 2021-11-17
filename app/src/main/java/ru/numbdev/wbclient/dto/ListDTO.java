package ru.numbdev.wbclient.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDTO implements Serializable {

    private String id;
    private Long productId;
    private String productName;
    private String brand;
    private List<Statistic> statistics = new ArrayList<>();

}
