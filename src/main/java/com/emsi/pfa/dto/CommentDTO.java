package com.emsi.pfa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO
{
    private String publicId;
    private String comment;
    private String firstName;
    private String lastName;
}
