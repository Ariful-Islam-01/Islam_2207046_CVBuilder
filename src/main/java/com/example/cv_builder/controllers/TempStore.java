package com.example.cv_builder.controllers;

import com.example.cv_builder.model.CV;

public final class TempStore {
    private static CV current;

    private TempStore() {}

    public static void set(CV cv) { current = cv; }
    public static CV get() { return current; }
    public static void clear() { current = null; }
}
