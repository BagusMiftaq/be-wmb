package com.enigma.controller;

import com.enigma.model.BMenu;
import com.enigma.model.request.RequestMenu;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.MenuService;
import com.enigma.utils.UrlMapping;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlMapping.MENU)
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity getAllMenu(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "menuId") String sortBy
    ) throws Exception{
        Page<BMenu> menus = menuService.menuList(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all menus", menus));
    }

    @PostMapping
    public ResponseEntity createMenu(@RequestBody RequestMenu menu) throws Exception{
        System.out.println(menu);
        BMenu result = menuService.create(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create menu", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMenu(@PathVariable("id") String id) throws Exception{
        menuService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Delete Succes", null));
    }


}
