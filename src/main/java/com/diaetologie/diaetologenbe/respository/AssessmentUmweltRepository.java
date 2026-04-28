package com.diaetologie.diaetologenbe.respository;
import com.diaetologie.diaetologenbe.entity.AssessmentUmwelt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
public interface AssessmentUmweltRepository extends JpaRepository<AssessmentUmwelt, UUID> {
    List<AssessmentUmwelt> findByFallId(UUID fallId);
}