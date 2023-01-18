package com.emsi.pfa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkDTO
{
    private String publicId;
    private int mark;
    private String passengerPublicId;
}
