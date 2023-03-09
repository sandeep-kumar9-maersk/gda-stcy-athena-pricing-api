package com.maersk.gdastcyathenapricingapi.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String receiptServiceMode;
    private String deliveryServiceMode;
    private String placeOfReceipt;
    private String placeOfDelivery;
    private String containerSubSize;
    private String containerSubType;
    private String companyGroup;
}
