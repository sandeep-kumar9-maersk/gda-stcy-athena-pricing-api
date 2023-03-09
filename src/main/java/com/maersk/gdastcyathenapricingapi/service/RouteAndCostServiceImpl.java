package com.maersk.gdastcyathenapricingapi.service;

import com.maersk.gdastcyathenapricingapi.dao.ProductRequest;
import com.maersk.gdastcyathenapricingapi.dao.ProductResponse;
import com.maersk.gdastcyathenapricingapi.model.Links;
import com.maersk.gdastcyathenapricingapi.model.ProductCost;
import com.maersk.gdastcyathenapricingapi.model.Products;
import com.maersk.gdastcyathenapricingapi.model.RouteAndCostDataset;
import com.maersk.gdastcyathenapricingapi.repository.RouteAndCostDatasetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Slf4j
@Service
public class RouteAndCostServiceImpl implements RouteAndCostService {

    @Autowired
    private RouteAndCostDatasetRepository routeAndCostDatasetRepository;

    @Override
    public Mono<ProductResponse> getCostAndRouteInfo(ProductRequest productRequest){

        return routeAndCostDatasetRepository.getRouteAndCostInfo(
                productRequest.getReceiptServiceMode(),
                productRequest.getDeliveryServiceMode(),
                productRequest.getPlaceOfReceipt(),
                productRequest.getPlaceOfDelivery(),
                productRequest.getContainerSubSize(),
                productRequest.getContainerSubType(),
                productRequest.getCompanyGroup()
        ).collectList().flatMap(list -> {
            Map<String, List<RouteAndCostDataset>> map = list.stream().collect(Collectors.groupingBy(RouteAndCostDataset::getDriverCode, TreeMap::new, Collectors.toList()));
            List<Products> collect = map.entrySet().stream().map(m -> RouteAndCostServiceImpl.getProducts(m.getKey(), m.getValue())).collect(Collectors.toList());
            ProductResponse body = new ProductResponse();
            body.setPod(productRequest.getPlaceOfDelivery());
            body.setPor(productRequest.getPlaceOfReceipt());
            body.setContainerSubSize(productRequest.getContainerSubSize());
            body.setContainerSubType(productRequest.getContainerSubType());
            body.setProducts(collect);
            body.setCompanyGroup(productRequest.getCompanyGroup());
            body.setReceiptServiceMode(productRequest.getReceiptServiceMode());
            body.setDeliveryServiceMode(productRequest.getDeliveryServiceMode());
            return Mono.just(body);
        });

    }

    private static Products getProducts(String driveCode, List<RouteAndCostDataset> stringListEntry) {
        Products p1 = new Products();
        p1.setProductCode(driveCode);
        p1.setLinks(new ArrayList<>());


        for(RouteAndCostDataset rd : stringListEntry)
        {
            p1.setProductType(rd.getSourceType());
            Links tmp = new Links();
            tmp.setLinkType(rd.getLinktype());
            tmp.setLinkSeqNo(rd.getLinkSeq_no().toString());
            tmp.setLinkStart(rd.getLinkStart());
            tmp.setLinkEnd(rd.getLinkEnd());
            tmp.setDuration(rd.getDuration());
            tmp.setLayover(rd.getLayover());
            tmp.setTransMode(rd.getTransMode());
            tmp.setServiceCode(rd.getService());
            tmp.setCarrierCode(rd.getCarrier());
            p1.getLinks().add(tmp);

            ProductCost tmpCost = new ProductCost();
            tmpCost.setOceanCore_STCost(rd.getOceanCore_STCost());
            tmpCost.setOceanTHC_STCost(rd.getOceanTHC_STCost());
            tmpCost.setInlandHaulage_STCost(rd.getInlandHaulage_STCost());
            tmpCost.setTotal_CYCost(rd.getTotal_CYCost());
            p1.setCost(tmpCost);
        }
        return p1;
    }

}
