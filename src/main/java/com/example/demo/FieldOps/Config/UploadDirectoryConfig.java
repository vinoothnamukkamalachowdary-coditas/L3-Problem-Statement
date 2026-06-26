package com.example.demo.FieldOps.Config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class UploadDirectoryConfig {

    @Value("${file.upload-dir}")
    private String uploadDirectory;
}
