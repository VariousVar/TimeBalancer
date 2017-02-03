package ru.variousvar.timebalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.variousvar.timebalancer.entity.TimeMark;

import java.time.Instant;
import java.util.List;

@Repository
public interface TimeMarkRepository extends JpaRepository<TimeMark, Long>, TimeMarkCustomRepository {

    @Query("select t from TimeMark t where t.timing.id = :timingId and t.mark between :fromDate and :toDate")
    List<TimeMark> findMarks(@Param("fromDate") Instant fromDate, @Param("toDate") Instant toDate, @Param("timingId") Long timingId);

    /**
     * Finds last (ordered by date) mark in current timing.
     * @param timingId
     * @return
     */
    TimeMark findLast(@Param("timingId") Long timingId);

    /**
     * Inserts a full period. It opens on {@code fromDate} and closes on {@code toDate}.
     *
     * @param fromDate period open date
     * @param toDate period close date
     * @param timingId
     */
    @Query("insert into TimeMark t (timing.id, mark, start) values (:timingId, :fromDate, true),(:timingId, :toDate, false)")
    void pushPeriod(@Param("fromDate") Instant fromDate, @Param("toDate") Instant toDate, @Param("timingId") long timingId);

    /**
     * Inserts a full period. Instead of {@code pushPeriod}, it closes on {@code fromDate} and opens on {@code toDate}.
     * There is useful then you need to insert an empty time into a registered period.
     *
     * @param fromDate period close date
     * @param toDate period open date
     * @param timingId
     */
    @Query("insert into TimeMark t (timing.id, mark, start) values (:timingId, :fromDate, false),(:timingId, :toDate, true)")
    void pushInversePeriod(@Param("fromDate") Instant fromDate, @Param("toDate") Instant toDate, @Param("timingId") long timingId);
}
