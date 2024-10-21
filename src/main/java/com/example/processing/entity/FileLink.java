package com.example.processing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file_links")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "link", nullable = false)
    private String fileLink;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private FileStatus status;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private FileLoadingRequest request;

    public FileLink(String fileLink, FileStatus status, FileLoadingRequest request) {
        this.fileLink = fileLink;
        this.status = status;
        this.request = request;
    }
}
