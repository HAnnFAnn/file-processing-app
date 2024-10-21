package com.example.processing.dto;

import com.example.processing.entity.FileStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileLinkStatusDTO {

    private String fileLink;
    private FileStatus status;

}
