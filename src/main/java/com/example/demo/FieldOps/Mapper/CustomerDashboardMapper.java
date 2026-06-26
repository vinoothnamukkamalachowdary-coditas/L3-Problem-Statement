//package com.example.demo.FieldOps.Mapper;
//
//import com.example.demo.FieldOps.Controller.CustomerDashboardController;
//import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerAssetAttachmentsResponseDTO;
//import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerAssetsResponseDTO;
//import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerDashBoardResponseDTO;
//import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerJobRequestsResponseDTO;
//import com.example.demo.FieldOps.Entity.AssetAttachments;
//import com.example.demo.FieldOps.Entity.Assets;
//import com.example.demo.FieldOps.Entity.JobRequest;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomerDashboardMapper {
////    public CustomerDashBoardResponseDTO toCustomerDashboardResponseDTO() {}
//
//    public CustomerJobRequestsResponseDTO toDto(JobRequest jobRequest) {
//        CustomerJobRequestsResponseDTO customerJobRequestsResponseDTO = new CustomerJobRequestsResponseDTO();
//        customerJobRequestsResponseDTO.setJobRequestId(jobRequest.getId());
//        customerJobRequestsResponseDTO.setTitle(jobRequest.getTitle());
//        customerJobRequestsResponseDTO.setNotes(jobRequest.getNotes());
//        customerJobRequestsResponseDTO.setAssetId(jobRequest.getAsset().getId());
//        return customerJobRequestsResponseDTO;
//    }
//
//    public CustomerAssetsResponseDTO responseDTO(Assets  assets) {
//        CustomerAssetsResponseDTO customerAssetsResponseDTO = new CustomerAssetsResponseDTO();
//        customerAssetsResponseDTO.setId(assets.getId());
//        customerAssetsResponseDTO.setAssetCode(assets.getAssetCode());
//        customerAssetsResponseDTO.setAssetName(assets.getAssetName());
//        customerAssetsResponseDTO.setAssetDescription(assets.getAssetDescription());
//        customerAssetsResponseDTO.setAssetType(String.valueOf(assets.getAssetType()));
//        return customerAssetsResponseDTO;
//    }
//
//    public CustomerAssetAttachmentsResponseDTO toResponse(AssetAttachments attachments){
//        CustomerAssetAttachmentsResponseDTO customerAssetAttachmentsResponseDTO = new CustomerAssetAttachmentsResponseDTO();
//        customerAssetAttachmentsResponseDTO.setId(attachments.getId());
//        customerAssetAttachmentsResponseDTO.setFileSize(attachments.getFileSize());
//        customerAssetAttachmentsResponseDTO.setFileName(attachments.getFileName());
//        customerAssetAttachmentsResponseDTO.setFileType(attachments.getFileType());
//        customerAssetAttachmentsResponseDTO.setFileUrl(attachments.getFileUrl());
//        return customerAssetAttachmentsResponseDTO;
//    }
//}
