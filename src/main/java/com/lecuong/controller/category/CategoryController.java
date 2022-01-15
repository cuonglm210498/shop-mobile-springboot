package com.lecuong.controller.category;

import com.lecuong.modal.request.category.CategoryFilterRequest;
import com.lecuong.modal.request.category.CategorySaveRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.category.CategoryResponse;
import com.lecuong.service.CategoryService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> save(@RequestBody CategorySaveRequest categorySaveRequest) {
        categoryService.save(categorySaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> update(@RequestBody CategorySaveRequest categorySaveRequest,
                                                     @PathVariable long id) {
        categoryService.update(categorySaveRequest, id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Page<CategoryResponse>>> getAll(@RequestParam int index,
                                                                       @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(index, size);
        Page<CategoryResponse> categoryResponses = categoryService.getAll(pageRequest.previousOrFirst());
        return ResponseEntity.ok(BaseResponse.ofSuccess(categoryResponses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryResponse>> findById(@PathVariable long id) {
        return ResponseEntity.ok(BaseResponse.ofSuccess(categoryService.findById(id)));
    }

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<Page<CategoryResponse>>> filter(@ModelAttribute CategoryFilterRequest filterRequest) {
        Page<CategoryResponse> categoryResponses = categoryService.filter(filterRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(categoryResponses));
    }
}
