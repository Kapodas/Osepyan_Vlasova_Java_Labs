package com.electronicsstore.lab3restful.controller;

import com.electronicsstore.lab3restful.repository.CategoryRepository;
import com.electronicsstore.lab3restful.table.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(produces = "application/xml")
    public ResponseEntity<String> getAllCategoriesXml(HttpServletResponse response) {
        List<Category> categories = categoryRepository.findAll();

        // Преобразуем список категорий в XML
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"/categories.xsl\"?>\n" +
                "<List>\n";
        for (Category category : categories) {
            xmlContent += "    <item>\n" +
                    "        <id>" + category.getId() + "</id>\n" +
                    "        <name>" + category.getName() + "</name>\n" +
                    "    </item>\n";
        }
        xmlContent += "</List>";

        // Добавляем ссылку на XSLT в заголовок ответа
        response.addHeader("Content-Type", "application/xml");
        response.addHeader("Link", "</categories.xsl>; rel=\"stylesheet\"; type=\"text/xsl\"");

        return new ResponseEntity<>(xmlContent, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryRepository.save(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryDetails.getName());
            Category updatedCategory = categoryRepository.save(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int id) {
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}