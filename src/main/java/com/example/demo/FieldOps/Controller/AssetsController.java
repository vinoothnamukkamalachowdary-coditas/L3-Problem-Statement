package com.example.demo.FieldOps.Controller;

import com.example.demo.FieldOps.DTO.Request.AssetAttachmentRequestDTO;
import com.example.demo.FieldOps.DTO.Request.AssetUpdateRequestDTO;
import com.example.demo.FieldOps.DTO.Request.AssetsRequestDTO;
import com.example.demo.FieldOps.DTO.Response.AssetAttachmentResponseDTO;
import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Service.AssetsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/assets")
public class AssetsController {

    private final AssetsService service;

    @PostMapping("/save")
    public ResponseEntity<AssetsResponseDTO> saveAsset(@Valid @RequestBody AssetsRequestDTO assets) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAsset(assets));
    }

    @PostMapping("/addphoto/assetId/{id}")
    public ResponseEntity<AssetAttachmentResponseDTO> addPhoto(@Valid@RequestBody AssetAttachmentRequestDTO assetsPhoto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addPhoto(assetsPhoto,id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AssetsResponseDTO>> getAllAssets() {
        return ResponseEntity.ok(service.getAllAssets());
    }

    @GetMapping("/getAsset/byId/{assetId}")
    public ResponseEntity<AssetsResponseDTO> getAssetById(@PathVariable Long assetId) {
        return ResponseEntity.ok(service.getAssetById(assetId));
    }

    @PutMapping("/update/{assetId}")
    public ResponseEntity<AssetsResponseDTO> modifyAsset(@RequestBody AssetUpdateRequestDTO RequestDTO, @PathVariable Long assetId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.modifyAsset(RequestDTO,assetId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        service.deleteAsset(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

