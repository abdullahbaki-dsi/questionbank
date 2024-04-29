package org.dsi.com.userService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.FeatureRequestDto;
import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.model.Feature;
import org.dsi.com.userService.service.FeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/feature")
@RequiredArgsConstructor
@Slf4j
public class FeatureController {
    final FeatureService featureService;
    @GetMapping(value = "/", name = "get features api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllFeatures(){
       return (ResponseEntity<?>) featureService.getAllFeature();
    }

    @PostMapping(value = "/", name = "save features api")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponse<?> saveFeature(FeatureRequestDto featureRequestDto){
        return (HttpResponse<?>) featureService.save(featureRequestDto);
    }
    @GetMapping(value = "/{featureId}", name = "get single features api")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSingleFeature(@PathVariable Long featureId){
         return  featureService.getFeatureById(featureId);
    }

}
