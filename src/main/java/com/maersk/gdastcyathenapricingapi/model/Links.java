package com.maersk.gdastcyathenapricingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Links {
    private String linkSeqNo;
    private String linkStart;
    private String linkEnd;
    private String transMode;
    private String serviceCode;
    private String carrierCode;
    private String linkType;
    private BigInteger duration;
    private BigInteger layover;
}
