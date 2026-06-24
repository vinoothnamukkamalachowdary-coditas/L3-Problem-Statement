package com.example.demo.FieldOps.Controller;

import com.example.demo.FieldOps.DTO.Request.AssetsRequestDTO;
import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Service.AssetsService;
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
    public ResponseEntity<AssetsResponseDTO> saveAsset(@RequestBody AssetsRequestDTO assets) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAsset(assets));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AssetsResponseDTO>> getAllAssets() {
        return ResponseEntity.ok(service.getAllAssets());
    }
}
