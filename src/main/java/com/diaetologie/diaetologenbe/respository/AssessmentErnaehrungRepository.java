package com.diaetologie.diaetologenbe.respository;

import com.diaetologie.diaetologenbe.entity.AssessmentErnaehrung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AssessmentErnaehrungRepository extends JpaRepository<AssessmentErnaehrung, UUID> {
    List<AssessmentErnaehrung> findByFallId(UUID fallId);
}