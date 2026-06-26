package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.AssetRepairResponseDTO;
import com.example.demo.FieldOps.Entity.AssetRepair;
import org.springframework.stereotype.Component;

@Component
public class AssetRepairMapper {

    public AssetRepairResponseDTO toDto(AssetRepair repair){

        return AssetRepairResponseDTO.builder()

                .id(repair.getId())

                .technicianId(
                        repair.getRepairedBy()!=null ?
                                repair.getRepairedBy().getId()
                                : null)

                .assetId(
                        repair.getRepairedAsset()!=null ?
                                repair.getRepairedAsset().getId()
                                : null)

                .jobId(
                        repair.getJob()!=null ?
                                repair.getJob().getId()
                                : null)

                .beforePhoto(repair.getBeforePhoto())

                .afterPhoto(repair.getAfterPhoto())

                .remarks(repair.getRemarks())

                .status(repair.getStatus())

                .startedAt(repair.getStartedAt())

                .endedAt(repair.getEndedAt())

                .build();
    }
}