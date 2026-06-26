package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.Config.UploadDirectoryConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final UploadDirectoryConfig config;
    private final FileStorageService fileStorageService;

    public String uploadFile(MultipartFile file) {

        try {

            Path uploadPath =
                    Paths.get(config.getUploadDirectory());

            if (!Files.exists(uploadPath)) {

                Files.createDirectories(uploadPath);

            }

            String fileName =
                    UUID.randomUUID()
                            + "_"
                            + file.getOriginalFilename();

            Path target =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return target.toString();

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to upload file"
            );
        }
    }

    public void deleteFile(String filePath) {

        try {

            Files.deleteIfExists(Paths.get(filePath));

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to delete file"
            );
        }

    }

}
