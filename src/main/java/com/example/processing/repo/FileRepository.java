package com.example.processing.repo;

import com.example.processing.entity.FileLoadingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileLoadingRequest, Long> {
    Optional<FileLoadingRequest> findByRequestId(String requestId);
}
