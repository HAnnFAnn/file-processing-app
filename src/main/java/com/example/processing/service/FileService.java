package com.example.processing.service;

import com.example.processing.dto.FileLinkStatusDTO;
import com.example.processing.dto.FileLoadingRequestDTO;
import com.example.processing.dto.FileStatusResponse;
import com.example.processing.entity.FileLink;
import com.example.processing.entity.FileLoadingRequest;
import com.example.processing.entity.FileStatus;
import com.example.processing.repo.FileRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final FileRepository requestRepository;
    private final RestTemplate restTemplate;


    @Transactional
    @Async
    public void processFiles(FileLoadingRequestDTO requestDTO) {

        FileLoadingRequest request = new FileLoadingRequest();
        request.setRequestId(requestDTO.getRequestId());
        request.setConsumer(requestDTO.getConsumer());

        List<FileLink> fileLinks = requestDTO.getFileLinks().stream()
                .map(link -> new FileLink(link.getFileLink(), FileStatus.IN_PROGRESS, request))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        request.setFileLinks(fileLinks);
        requestRepository.save(request);

        fileLinks.forEach(this::processFile);
    }

    @Async
    public void processFile(FileLink fileLink) {
        try {
            // Имитация вызова REST API для загрузки данных
            restTemplate.getForObject("https://file-api.com/download/" + fileLink.getFileLink(), String.class);
            fileLink.setStatus(FileStatus.DONE);
        } catch (Exception e) {
            fileLink.setStatus(FileStatus.IN_PROGRESS);
        } finally {
            requestRepository.save(fileLink.getRequest());
        }
    }

    public FileStatusResponse getFileProcessingStatus(String requestId) {
        FileLoadingRequest request = requestRepository.findByRequestId(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        List<FileLinkStatusDTO> fileLinks = request.getFileLinks().stream()
                .map(link -> new FileLinkStatusDTO(link.getFileLink(), link.getStatus()))
                .collect(Collectors.toList());

        FileStatusResponse response = new FileStatusResponse();
        response.setRequestId(request.getRequestId());
        response.setFileLinks(fileLinks);

        return response;
    }
}
