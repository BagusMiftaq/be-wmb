package com.enigma.service;

import com.enigma.model.BMenu;
import com.enigma.model.request.RequestMenu;
import org.springframework.data.domain.Page;

public interface MenuService {
    Page<BMenu> menuList(Integer page, Integer size, String direction, String sortBy) throws Exception;
    BMenu create(RequestMenu menu) throws Exception;
    BMenu get(String id) throws Exception;
    void delete(String id) throws Exception;
}
