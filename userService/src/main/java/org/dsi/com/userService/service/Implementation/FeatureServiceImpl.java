package org.dsi.com.userService.service.Implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.FeatureRequestDto;
import org.dsi.com.userService.model.Feature;
import org.dsi.com.userService.repository.FeatureRepository;
import org.dsi.com.userService.service.FeatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {
    final FeatureRepository featureRepository;


    /**
     * @return 
     */
    @Override
    public List<Feature> getAllFeature() {
        return featureRepository.findAll();
    }

    /**
     * @param Id
     * @return
     */
    @Override
    public ResponseEntity<?> getFeatureById(Long Id) {
        Optional<Feature>  feature =featureRepository.findById(Id);
        if(feature.isPresent()){
            return ResponseEntity.ok(feature.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param featureRequest 
     * @return
     */
    @Override
    public Feature save(FeatureRequestDto featureRequest) {
        return null;
    }
}
