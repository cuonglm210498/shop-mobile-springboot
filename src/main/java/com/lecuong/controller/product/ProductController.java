package com.lecuong.controller.product;

import com.lecuong.modal.request.product.ProductFilterRequest;
import com.lecuong.modal.request.product.ProductSaveRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.product.ProductResponse;
import com.lecuong.service.ProductService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> save(@RequestBody ProductSaveRequest productSaveRequest) {
        productService.save(productSaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> update(@RequestBody ProductSaveRequest productSaveRequest,
                                                     @PathVariable long id) {
        productService.update(productSaveRequest, id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Page<ProductResponse>>> getAll(@RequestParam int index,
                                                                      @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(index, size);
        Page<ProductResponse> productResponses = productService.getAll(pageRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(productResponses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> findById(@PathVariable long id) {
        ProductResponse productResponse = productService.findById(id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(productResponse));
    }

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<Page<ProductResponse>>> filter(@ModelAttribute ProductFilterRequest filterRequest) {
        Page<ProductResponse> productResponses = productService.filter(filterRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(productResponses));
    }

    @PostMapping("/export-excel")
    public ResponseEntity<BaseResponse<Void>> exportExcel() {
        productService.exportToExcel();
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @PostMapping("/export-csv")
    public ResponseEntity<BaseResponse<Void>> exportCsv() {
        productService.exportToCsv();
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }
}
