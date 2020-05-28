package com.pyh.openfeign;

import java.util.List;

import feign.Param;
import feign.RequestLine;

public interface OpenFeignInter {
    
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> getContributors(@Param("owner") String owner, @Param("repo") String repository);
    
    class Contributor {
      String login;
      int contributions;
    }

}
