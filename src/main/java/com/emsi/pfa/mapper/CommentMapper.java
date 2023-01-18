package com.emsi.pfa.mapper;


import com.emsi.pfa.dto.CommentDTO;
import com.emsi.pfa.entity.Comment;
import com.emsi.pfa.restclient.AccountRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    @Autowired
    AccountRestClient accountRestClient;
    public CommentDTO toCommentDTO(Comment comment)
    {
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setComment(comment.getComment());
        commentDTO.setPublicId(comment.getPublicId());
        commentDTO.setFirstName(accountRestClient.getPassengerByPublicId(comment.getPassengerPublicId()).getFirstName());
        commentDTO.setLastName(accountRestClient.getPassengerByPublicId(comment.getPassengerPublicId()).getLastName());
        return commentDTO;
    }
}
