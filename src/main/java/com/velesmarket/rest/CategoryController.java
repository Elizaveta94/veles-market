package com.velesmarket.rest;

import com.velesmarket.domain.CategoryDto;
import com.velesmarket.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }
}
