package com.example.cv_builder.controllers;

import com.example.cv_builder.service.CVService;

/**
 * Simple service holder used by controllers to reuse the same CVService.
 */
public final class AppServices {
    private static final CVService CV_SERVICE = new CVService();

    private AppServices() {}

    public static CVService getCvService() {
        return CV_SERVICE;
    }
}
