package com.mex.shortestpath.input;

import com.mex.shortestpath.config.AppConfig;
import com.mex.shortestpath.exception.OutOfRangeException;

public class RangeChecker {

    private Integer width;
    private Integer height;

    public RangeChecker(Integer width, Integer height) {
        this.height = height;
        this.width = width;
    }

    public void check() throws OutOfRangeException {
        if (width < AppConfig.MIN_WIDTH_HEIGHT || width > AppConfig.MAX_WIDTH_HEIGHT) {
            throw new OutOfRangeException(String.format("Width is out of range: %s", width));
        }
        if (height < AppConfig.MIN_WIDTH_HEIGHT || height > AppConfig.MAX_WIDTH_HEIGHT) {
            throw new OutOfRangeException(String.format("Height is out of range: %s", height));
        }
    }
}
