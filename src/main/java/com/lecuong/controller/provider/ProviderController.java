package com.lecuong.controller.provider;

import com.lecuong.entity.BaseEntity;
import com.lecuong.entity.Provider;
import com.lecuong.modal.request.provider.ProviderSaveRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.provider.ProviderResponse;
import com.lecuong.service.ProviderService;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> save(@RequestBody ProviderSaveRequest providerSaveRequest) {
        providerService.save(providerSaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProviderResponse>> findById(@PathVariable long id) {
        ProviderResponse providerResponse = providerService.findById(id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(providerResponse));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Page<ProviderResponse>>> getAll(@RequestParam int index,
                                                                      @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(index, size);
        Page<ProviderResponse> providerResponses = providerService.getAll(pageRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(providerResponses));
    }

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<Page<ProviderResponse>>> filter(@ModelAttribute ProviderSaveRequest providerSaveRequest) {
        Page<ProviderResponse> providerResponses = providerService.filter(providerSaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(providerResponses));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> update(@PathVariable long id,
                                                     @RequestBody ProviderSaveRequest providerSaveRequest) {
        providerService.update(providerSaveRequest, id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable long id) {
        providerService.delete(id);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }
}
