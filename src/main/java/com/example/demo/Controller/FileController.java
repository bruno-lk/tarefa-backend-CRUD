package com.example.demo.Controller;

import com.example.demo.Entity.FileDB;
import com.example.demo.Repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;

    @PostMapping("/upload")
    public FileDB store(MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileDB file = new FileDB();

        file.setNome(fileName);
        file.setTipo(multipartFile.getOriginalFilename());
        file.setData(multipartFile.getBytes());

        return fileRepository.save(file);
    }

    @GetMapping("/files")
    public Stream<FileDB> getAllFiles()  {
        return fileRepository.findAll().stream();
    }

    @GetMapping("/files/{id}")
    public FileDB getFile(Integer id) {
        return fileRepository.findById(id).get();
    }
}
