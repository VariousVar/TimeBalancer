package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.variousvar.timebalancer.entity.TimeMark;

import java.time.Instant;
import java.util.List;

@Repository
public interface TimeMarkRepository extends JpaRepository<TimeMark, Long> {

    @Query("select t from TimeMark t where t.timing.id = :timingId and t.mark between :fromDate and :toDate")
    List<TimeMark> findMarks(@Param("fromDate") Instant fromDate, @Param("toDate") Instant toDate, @Param("timingId") Long timingId);
}
