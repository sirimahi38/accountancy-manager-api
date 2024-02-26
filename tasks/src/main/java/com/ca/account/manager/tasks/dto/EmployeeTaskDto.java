package com.ca.account.manager.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTaskDto {
    private String taskId;
    private String taskGroup;
    private String taskName;
    private String taskCheckList;
    private String taskDueDate;
    private String advanceIntimation;
    private String remarks;
    private String taskType;
    private String taskAssignedTo;
    private String taskAssignedDate;
    private String taskReviewedDate;
    private String taskReviewedBy;
    private String taskApprovedBy;
    private String taskApprovedDate;
    private String taskStatus;
}
