package com.maersk.gdastcyathenapricingapi.controller;

import com.maersk.gdastcyathenapricingapi.dao.ProductRequest;
import com.maersk.gdastcyathenapricingapi.dao.ProductResponse;
import com.maersk.gdastcyathenapricingapi.service.RouteAndCostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-pricing")
public class RouteAndCostController {

    private final RouteAndCostService routeAndCostService;

    @PostMapping ("/getRouteAndCost")
    Mono<ProductResponse> getRouteDetail(@RequestBody ProductRequest productRequest) {
        return routeAndCostService.getCostAndRouteInfo(productRequest);
    }
}
