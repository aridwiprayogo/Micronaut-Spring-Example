package com.opencourse;

import io.micronaut.http.MediaType;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/bintray")
public class BintrayController {

    private final BintrayLowLevelClient bintrayLowLevelClient;

    private final BintrayClient bintrayClient;

    public BintrayController(BintrayLowLevelClient bintrayLowLevelClient,
                             BintrayClient bintrayClient) {
        this.bintrayLowLevelClient = bintrayLowLevelClient;
        this.bintrayClient = bintrayClient;
    }

    @GetMapping("/packages-lowlevel")
    Maybe<List<BintrayPackage>> packagesWithLowLevelClient() {
        return bintrayLowLevelClient.fetchPackages();
    }

    @GetMapping(value = "/packages", produces = MediaType.APPLICATION_JSON_STREAM)
    Flowable<BintrayPackage> packages() {
        return bintrayClient.fetchPackages();
    }
}