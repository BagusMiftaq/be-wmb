package com.enigma.controller;

import com.enigma.model.BMenu;
import com.enigma.model.BTable;
import com.enigma.model.request.RequestMenu;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.TableService;
import com.enigma.utils.UrlMapping;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.TABLE)
public class TableController {

    private TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity getAllMenu(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "tableNum") String sortBy
    ) throws Exception{
        Page<BTable> result = tableService.tableList(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all menus", result));
    }

    @PostMapping
    public ResponseEntity createTable(@RequestBody BTable table) throws Exception{
        System.out.println(table);
        BTable result = tableService.create(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create menu", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMenu(@PathVariable("id") String id) throws Exception{
        tableService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Delete Succes", null));
    }
}
