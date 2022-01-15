package com.lecuong.controller.contact;

import com.lecuong.modal.request.contact.ContactFilterRequest;
import com.lecuong.modal.request.contact.ContactSaveRequest;
import com.lecuong.modal.response.BaseResponse;
import com.lecuong.modal.response.contact.ContactResponse;
import com.lecuong.service.ContactService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> save(@RequestBody ContactSaveRequest contactSaveRequest) {
        contactService.save(contactSaveRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Page<ContactResponse>>> getAll(@RequestParam int index,
                                                                      @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(index, size);
        Page<ContactResponse> contactResponses = contactService.getAll(pageRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(contactResponses));
    }

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<Page<ContactResponse>>> filter(@ModelAttribute ContactFilterRequest contactFilterRequest) {
        Page<ContactResponse> contactResponses = contactService.filter(contactFilterRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(contactResponses));
    }
}
