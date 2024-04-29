package org.dsi.com.userService.service;

import jakarta.ws.rs.core.GenericEntity;

import java.util.List;

public interface GenericService {

    List<?> getAll();
    GenericEntity<?> get(Long Id);
    GenericEntity<?> save (GenericEntity<?> entity);


}
