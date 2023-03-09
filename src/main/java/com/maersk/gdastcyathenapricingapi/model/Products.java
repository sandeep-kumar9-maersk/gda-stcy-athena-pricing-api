package com.maersk.gdastcyathenapricingapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    private String productType;
    private String productCode;
    @JsonProperty("Links")
    private List<Links> links;
    @JsonProperty("Cost")
    private ProductCost cost;
}
