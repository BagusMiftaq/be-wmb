package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.BTable;
import com.enigma.repository.TableRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TableServiceImpl implements TableService{

    private TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public Page<BTable> tableList(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1), size, sort);
        Page<BTable> result = tableRepository.findAll(pageable);
        return result;
    }

    @Override
    public BTable create(BTable btable) throws Exception {
        try{
            return tableRepository.save(btable);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public BTable get(String id) throws Exception {
        Optional<BTable> result = tableRepository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Table not Found");
        }
        return result.get();
    }

    @Override
    public void delete(String id) throws Exception {
        try{
            BTable bTable = get(id);
            tableRepository.delete(bTable);
        } catch (NotFoundException e){
            throw new NotFoundException("Delete failed");
        }

    }
}
