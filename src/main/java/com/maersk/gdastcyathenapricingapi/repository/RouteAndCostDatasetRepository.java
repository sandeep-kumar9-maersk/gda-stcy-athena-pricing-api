package com.maersk.gdastcyathenapricingapi.repository;

import com.maersk.gdastcyathenapricingapi.model.RouteAndCostDataset;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RouteAndCostDatasetRepository extends ReactiveCrudRepository<RouteAndCostDataset, String> {
    @Query("SELECT " +
            "ROUTE.SourceType " +
            ",ROUTE.drivercode " +
            ",ROUTE.POR " +
            ",ROUTE.POD " +
            ",ROUTE.Receipt_Service_Mode " +
            ",ROUTE.Delivery_Service_Mode " +
            ",ROUTE.ContainerSubSize " +
            ",ROUTE.ContainerSubType " +
            ",ROUTE.CompanyGroup " +
            ",ROUTE.LinkSeq_no " +
            ",ROUTE.LinkStart " +
            ",ROUTE.LinkEnd " +
            ",ROUTE.TransMode " +
            ",ROUTE.Service " +
            ",ROUTE.Carrier " +
            ",ROUTE.Linktype " +
            ",ROUTE.Duration " +
            ",ROUTE.Layover " +
            ",COST.Cost_OceanCore_ST " +
            ",COST.Cost_OceanTHC_ST " +
            ",COST.Cost_InlandHaulage_ST " +
            ",COST.CY_Cost " +
            "FROM athena.api_Cost COST " +
            "INNER JOIN athena.api_route ROUTE ON COST.DRIVERCODE = ROUTE.DRIVERCODE " +
            "WHERE ROUTE.receipt_service_mode = :receiptServiceMode " +
            "AND ROUTE.delivery_service_mode = :deliveryServiceMode " +
            "AND ROUTE.POR = :placeOfReceipt " +
            "AND ROUTE.POD = :placeOfDelivery " +
            "AND ROUTE.ContainerSubSize = :containerSubSize " +
            "AND ROUTE.ContainerSubType = :containerSubType " +
            "AND ROUTE.CompanyGroup = :companyGroup " +
            "ORDER BY drivercode, LinkSeq_no ASC ")
    Flux<RouteAndCostDataset> getRouteAndCostInfo(String receiptServiceMode,
                                           String deliveryServiceMode,
                                           String placeOfReceipt,
                                           String placeOfDelivery,
                                           String containerSubSize,
                                           String containerSubType,
                                           String companyGroup);
}
