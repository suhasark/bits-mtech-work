package io.github.suhasark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptimalNetworkPartnerController {

    private final OptimalNetworkPartnerFinder finder;

    @Autowired
    public OptimalNetworkPartnerController(OptimalNetworkPartnerFinder finder) {
        this.finder = finder;
    }

    @GetMapping("/partner")
    public NetworkPartnerInfo getOptimalPartner(
            @RequestParam String filterCountryCode,
            @RequestParam String filterCurrency,
            @RequestParam String timeWeightage,
            @RequestParam String costWeightage,
            @RequestParam String failureWeightage) {

        InputParams inputParams = new InputParams(
                filterCountryCode.toUpperCase(),
                filterCurrency.toUpperCase(),
                timeWeightage.toUpperCase(),
                costWeightage.toUpperCase(),
                failureWeightage.toUpperCase()
        );

        return finder.process(inputParams);
    }
}

