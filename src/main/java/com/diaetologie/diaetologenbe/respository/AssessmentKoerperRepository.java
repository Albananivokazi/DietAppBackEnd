package com.diaetologie.diaetologenbe.respository;

import com.diaetologie.diaetologenbe.entity.AssessmentKoerper;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AssessmentKoerperRepository extends JpaRepository<AssessmentKoerper, UUID> {
    List<AssessmentKoerper> findByFallId(UUID fallId);
}