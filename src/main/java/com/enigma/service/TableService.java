package com.enigma.service;

import com.enigma.model.BTable;
import org.springframework.data.domain.Page;

public interface TableService {
    Page<BTable> tableList(Integer page, Integer size, String direction, String sortBy) throws Exception;
    BTable create(BTable btable) throws Exception;
    BTable get(String id) throws Exception;
    void delete(String id) throws Exception;
}
