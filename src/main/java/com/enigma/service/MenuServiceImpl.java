package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.BMenu;
import com.enigma.model.request.RequestMenu;
import com.enigma.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{

    private MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Page<BMenu> menuList(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1), size, sort);
        Page<BMenu> result = menuRepository.findAll(pageable);

        return result;
    }

    @Override
    public BMenu create(RequestMenu menu) throws Exception {
        try {
            BMenu newMenu = new BMenu();
            newMenu.setTitle(menu.getTitle());
            newMenu.setPrice(menu.getPrice());
            newMenu.setCategory(menu.getCategory());
            return menuRepository.save(newMenu);
        } catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }

    @Override
    public BMenu get(String id) throws Exception {
        Optional<BMenu> result = menuRepository.findById(id);
        if (result.isEmpty()){
            throw new NotFoundException("Menu not Found");
        }
        return result.get();
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            BMenu existingMenu = get(id);
            menuRepository.delete(existingMenu);
        } catch (NotFoundException e){
            throw new NotFoundException("Delete Failed");
        }
    }
}
