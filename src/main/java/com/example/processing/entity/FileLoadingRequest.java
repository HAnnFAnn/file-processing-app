package com.example.processing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "file_loading_requests")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileLoadingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "request_id", nullable = false)
    private String requestId;

    @Column(name = "consumer", nullable = false)
    private String consumer;

    @Column(name = "file_links", nullable = false)
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<FileLink> fileLinks;

    // Getters, setters, equals, hashCode
}
