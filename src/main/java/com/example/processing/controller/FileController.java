package com.example.processing.controller;


import com.example.processing.dto.FileLoadingRequestDTO;
import com.example.processing.dto.FileStatusRequest;
import com.example.processing.dto.FileStatusResponse;
import com.example.processing.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/send")
    public ResponseEntity<String> sendFiles(@RequestBody FileLoadingRequestDTO requestDTO) {
        fileService.processFiles(requestDTO);
        return ResponseEntity.ok("Request received for processing");
    }

    @PostMapping("/get")
    public ResponseEntity<FileStatusResponse> getStatus(@RequestBody FileStatusRequest request) {
        return ResponseEntity.ok(fileService.getFileProcessingStatus(request.getRequestId()));
    }
}
