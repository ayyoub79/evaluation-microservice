package com.emsi.pfa.mapper;

import com.emsi.pfa.dto.MarkDTO;
import com.emsi.pfa.entity.Mark;
import org.springframework.stereotype.Component;

@Component
public class MarkMapper {
    public MarkDTO toMarkDTO(Mark mark)
    {
      MarkDTO markDTO=new MarkDTO();
      markDTO.setMark(mark.getMark());
      markDTO.setPublicId(mark.getPublicId());
      mark.setPassengerPublicId(mark.getPassengerPublicId());
      return markDTO;
    }
}
