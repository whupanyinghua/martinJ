package com.pyh.openfeign;

import feign.Feign;
import feign.gson.GsonDecoder;

public class MyApp {

    public static void main(String[] args) {
        OpenFeignInter github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(OpenFeignInter.class, "https://api.github.com");

        System.out.println(github.getContributors("OpenFeign", "feign"));

    }

}
