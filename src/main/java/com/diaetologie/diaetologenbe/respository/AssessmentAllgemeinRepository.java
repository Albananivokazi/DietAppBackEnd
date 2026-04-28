package com.diaetologie.diaetologenbe.respository;
import com.diaetologie.diaetologenbe.entity.AssessmentAllgemein;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
public interface AssessmentAllgemeinRepository extends JpaRepository<AssessmentAllgemein, UUID> {
    List<AssessmentAllgemein> findByFallId(UUID fallId);
}