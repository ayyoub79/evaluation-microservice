package com.emsi.pfa.repository;

import com.emsi.pfa.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Long> {

}
