package com.emsi.pfa.controller;

import com.emsi.pfa.request.CommentRequest;
import com.emsi.pfa.request.MarkRequest;
import com.emsi.pfa.dto.CommentDTO;
import com.emsi.pfa.dto.DriverEvaluationDTO;
import com.emsi.pfa.dto.MarkDTO;
import com.emsi.pfa.service.DriverEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EvaluationController {

    @Autowired
    DriverEvaluationService evaluationService;

    @PostMapping(value = "/evaluation/drivers/comments")
    public CommentDTO commentDriver(@RequestBody CommentRequest commentRequest)
    {
       return evaluationService.commentDriver(commentRequest);
    }
    @PostMapping(value = "/evaluation/drivers/marks")
    public MarkDTO markDriver(@RequestBody MarkRequest markRequest)
    {
        return evaluationService.markDriver(markRequest);
    }

    @GetMapping(value = "/evaluation/drivers/{driverPublicId}/evaluations")
    public DriverEvaluationDTO getDriverEvaluation(@PathVariable(name = "driverPublicId") String driverPublicId)
    {
        return  evaluationService.getDriverEvaluation(driverPublicId);
    }

    @GetMapping(value = "/evaluation/drivers/{driverPublicId}/comments")
    public List<CommentDTO> getDriverComments(@PathVariable(name = "driverPublicId") String driverPublicId)
    {
        return  evaluationService.getDriverComments(driverPublicId);
    }

    @GetMapping(value = "/evaluation/drivers/{driverPublicId}/mark")
    public float getDriverMark(@PathVariable(name = "driverPublicId") String driverPublicId)
    {
        return  evaluationService.getDriverMark(driverPublicId);
    }




}
