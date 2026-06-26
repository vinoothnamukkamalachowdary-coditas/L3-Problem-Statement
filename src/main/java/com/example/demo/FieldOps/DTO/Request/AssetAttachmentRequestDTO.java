package com.example.demo.FieldOps.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AssetAttachmentRequestDTO {

    @NotNull
    private MultipartFile file;

}
