package com.maersk.gdastcyathenapricingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteAndCostDataset {
    @Column("SourceType")
    private String sourceType;
    @Column("drivercode")
    private String driverCode;
    @Column("POR")
    private String POR;
    @Column("POD")
    private String POD;
    @Column("Receipt_Service_Mode")
    private String Receipt_Service_Mode;
    @Column("Delivery_Service_Mode")
    private String Delivery_Service_Mode;
    @Column("ContainerSubSize")
    private String ContainerSubSize;
    @Column("ContainerSubType")
    private String ContainerSubType;
    @Column("CompanyGroup")
    private String CompanyGroup;
    @Column("LinkSeq_no")
    private Integer LinkSeq_no;
    @Column("LinkStart")
    private String LinkStart;
    @Column("LinkEnd")
    private String LinkEnd;
    @Column("TransMode")
    private String TransMode;
    @Column("Service")
    private String Service;
    @Column("Carrier")
    private String Carrier;
    @Column("Linktype")
    private String Linktype;
    @Column("Duration")
    private BigInteger Duration;
    @Column("Layover")
    private BigInteger Layover;
    @Column("Cost_OceanCore_ST")
    private double oceanCore_STCost;
    @Column("Cost_OceanTHC_ST")
    private double oceanTHC_STCost;
    @Column("Cost_InlandHaulage_ST")
    private double inlandHaulage_STCost;
    @Column("CY_Cost")
    private double total_CYCost;
}
