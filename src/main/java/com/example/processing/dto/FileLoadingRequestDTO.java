package com.example.processing.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileLoadingRequestDTO {
    private String requestId;
    private String consumer;
    private List<FileLinkDTO> fileLinks;
}
