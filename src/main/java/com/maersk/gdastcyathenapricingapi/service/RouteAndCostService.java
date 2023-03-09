package com.maersk.gdastcyathenapricingapi.service;

import com.maersk.gdastcyathenapricingapi.dao.ProductRequest;
import com.maersk.gdastcyathenapricingapi.dao.ProductResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface RouteAndCostService {

    Mono<ProductResponse> getCostAndRouteInfo(ProductRequest productRequest);

}
