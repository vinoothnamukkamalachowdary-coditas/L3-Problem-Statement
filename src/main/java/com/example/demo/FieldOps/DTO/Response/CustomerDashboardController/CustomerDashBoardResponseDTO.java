package com.example.demo.FieldOps.DTO.Response.CustomerDashboardController;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CustomerDashBoardResponseDTO {
    private Long customerId;
    private List<?> activities; // Replace ? with a custom Activity DTO class if needed
    private int currentPage;
    private long totalItems;
    private int totalPages;
}
