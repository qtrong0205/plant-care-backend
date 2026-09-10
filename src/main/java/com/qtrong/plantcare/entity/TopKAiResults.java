package com.qtrong.plantcare.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopKAiResults {

    private List<Result> results;

    @Getter
    @Setter
    public static class Result {
        private String label;
        private Float confidence;
    }
}
