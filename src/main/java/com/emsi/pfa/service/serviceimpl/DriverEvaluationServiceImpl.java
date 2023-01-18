package com.emsi.pfa.service.serviceimpl;

import com.emsi.pfa.request.CommentRequest;
import com.emsi.pfa.request.MarkRequest;
import com.emsi.pfa.dto.CommentDTO;
import com.emsi.pfa.dto.DriverEvaluationDTO;
import com.emsi.pfa.dto.MarkDTO;
import com.emsi.pfa.entity.Comment;
import com.emsi.pfa.entity.DriverEvaluation;
import com.emsi.pfa.entity.Mark;
import com.emsi.pfa.feign.AccountRestClient;
import com.emsi.pfa.mapper.CommentMapper;
import com.emsi.pfa.mapper.DriverEvaluationMapper;
import com.emsi.pfa.mapper.MarkMapper;
import com.emsi.pfa.repository.CommentRepository;
import com.emsi.pfa.repository.EvaluationRepository;
import com.emsi.pfa.repository.MarkRepository;
import com.emsi.pfa.security.SecurityUtils;
import com.emsi.pfa.service.DriverEvaluationService;
import com.emsi.pfa.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DriverEvaluationServiceImpl implements DriverEvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    DriverEvaluationMapper evaluationMapper;
    @Autowired
    AccountRestClient accountRestClient;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    MarkMapper markMapper;
    @Autowired
    MarkRepository markRepository;
    @Autowired
    SecurityUtils securityUtils;

    @Override
    public DriverEvaluationDTO getDriverEvaluation(String driverPublicId) {
        DriverEvaluation evaluation=evaluationRepository.findByDriverPublicId(driverPublicId);
        return evaluationMapper.toDriverEvaluationDTO(evaluation);
    }

    @Override
    public CommentDTO commentDriver(CommentRequest commentRequest)
    {
        String email=securityUtils.getCurrentUserEmail();
        String passengerPublicId=accountRestClient.getPassengerByEmail(email).getPublicId();

        Comment comment=new Comment();
        comment.setPublicId(Utils.genereteRandomString(15));
        comment.setPassengerPublicId(passengerPublicId);
        comment.setComment(commentRequest.getComment());
        DriverEvaluation driverEvaluation=evaluationRepository.findByDriverPublicId(commentRequest.getDriverPublicId());
        if(driverEvaluation==null)
        {
            driverEvaluation=new DriverEvaluation();
            driverEvaluation.setPublicId(Utils.genereteRandomString(20));
            driverEvaluation.setDriverPublicId(commentRequest.getDriverPublicId());
            driverEvaluation=evaluationRepository.save(driverEvaluation);
        }
        comment.setDriverEvaluation(driverEvaluation);
        comment=commentRepository.save(comment);
        return commentMapper.toCommentDTO(comment);

    }

    @Override
    public MarkDTO markDriver(MarkRequest markRequest) {
        String email=securityUtils.getCurrentUserEmail();
        String passengerPublicId=accountRestClient.getPassengerByEmail(email).getPublicId();
        DriverEvaluation driverEvaluation=evaluationRepository.findByDriverPublicId(markRequest.getDriverPublicId());
        if(driverEvaluation==null)
        {
            driverEvaluation=new DriverEvaluation();
            driverEvaluation.setPublicId(Utils.genereteRandomString(20));
            driverEvaluation.setDriverPublicId(markRequest.getDriverPublicId());
            driverEvaluation=evaluationRepository.save(driverEvaluation);
        }
        else
        {
            for (Mark mark:driverEvaluation.getMarks())
            {
               if(mark.getPassengerPublicId().equals(passengerPublicId))
               {
                   mark.setMark(markRequest.getMark());
                   MarkDTO markDTO=markMapper.toMarkDTO(markRepository.save(mark));
                   markDTO.setPassengerPublicId(passengerPublicId);
                   return markDTO;
               }
            }
            Mark mark=new Mark();
            mark.setPublicId(Utils.genereteRandomString(15));
            mark.setPassengerPublicId(passengerPublicId);
            mark.setMark(markRequest.getMark());
            mark.setDriverEvaluation(driverEvaluation);
            mark=markRepository.save(mark);
            return markMapper.toMarkDTO(mark);
        }
        Mark mark=new Mark();
        mark.setPublicId(Utils.genereteRandomString(15));
        mark.setPassengerPublicId(passengerPublicId);
        mark.setMark(markRequest.getMark());
        mark.setDriverEvaluation(driverEvaluation);
        mark=markRepository.save(mark);
        MarkDTO markDTO=markMapper.toMarkDTO(mark);
        return markDTO;
    }

    @Override
    public List<CommentDTO> getDriverComments(String driverPublicId) {
        DriverEvaluation evaluation=evaluationRepository.findByDriverPublicId(driverPublicId);
        List<CommentDTO> commentDTOS=new ArrayList<CommentDTO>();
        for (Comment comment:evaluation.getComments())
        {
            commentDTOS.add(commentMapper.toCommentDTO(comment));
        }
        return commentDTOS;

    }

    @Override
    public float getDriverMark(String driverPublicId) {
        DriverEvaluation evaluation=evaluationRepository.findByDriverPublicId(driverPublicId);
        long sumMark=0;
        long countMark=0;
        for (Mark mark:evaluation.getMarks())
        {
            countMark++;
            sumMark+=mark.getMark();
        }
        float driverMark=countMark==0?0:sumMark/(countMark*5);
        return driverMark;
    }
}
