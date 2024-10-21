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
public class FileStatusResponse {
    private String requestId;
    private List<FileLinkStatusDTO> fileLinks;
}
