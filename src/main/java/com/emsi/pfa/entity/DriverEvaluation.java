package com.emsi.pfa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drivers_evaluations")
@Data @AllArgsConstructor @NoArgsConstructor
public class DriverEvaluation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String publicId;
    private String driverPublicId;
    @OneToMany(mappedBy = "driverEvaluation")
    private List<Comment> comments;
    @OneToMany(mappedBy = "driverEvaluation")
    private List<Mark> marks;
}
