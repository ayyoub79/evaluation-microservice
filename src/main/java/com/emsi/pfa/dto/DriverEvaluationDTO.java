package com.emsi.pfa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverEvaluationDTO{
    private String publicId;
    private String driverPublicId;
    private List<CommentDTO> comments;
    private Long mark;
}
