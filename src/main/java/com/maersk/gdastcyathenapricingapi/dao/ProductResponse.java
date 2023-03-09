package com.maersk.gdastcyathenapricingapi.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maersk.gdastcyathenapricingapi.model.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String por;
    private String pod;
    private String containerSubSize;
    private String containerSubType;
    private String receiptServiceMode;
    private String deliveryServiceMode;
    private String companyGroup;
    @JsonProperty("Products")
    private List<Products> products;
}
