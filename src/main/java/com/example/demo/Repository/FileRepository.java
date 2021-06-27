package com.example.demo.Repository;

import com.example.demo.Entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileDB, Integer> {
}
