package ru.variousvar.timebalancer.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ru.variousvar.timebalancer.entity.TimeMark;
import ru.variousvar.timebalancer.repository.TimeMarkCustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class TimeMarkRepositoryImpl implements TimeMarkCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public TimeMark findLast(@Param("timingId") Long timingId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TimeMark> query = cb.createQuery(TimeMark.class);
        Root<TimeMark> entity = query.from(TimeMark.class);
        query.select(entity).where(cb.equal(entity.get("timing").get("id"), timingId)).orderBy(cb.desc(entity.get("mark")));

        List<TimeMark> marks = entityManager.createQuery(query).setMaxResults(1).getResultList();

        return marks.isEmpty() ? null : marks.get(0);
    }
}
