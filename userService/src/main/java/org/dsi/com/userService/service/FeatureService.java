package org.dsi.com.userService.service;

import org.dsi.com.userService.dto.FeatureRequestDto;
import org.dsi.com.userService.model.Feature;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FeatureService {
    List<Feature> getAllFeature();
    ResponseEntity<?> getFeatureById(Long Id);
    Feature save(FeatureRequestDto featureRequest);

}
