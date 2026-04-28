package com.diaetologie.diaetologenbe.respository;
import com.diaetologie.diaetologenbe.entity.PatientStammdaten;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
public interface PatientStammdatenRepository extends JpaRepository<PatientStammdaten, UUID> {
    Optional<PatientStammdaten> findByAnonymerCode(String code);
    boolean existsByAnonymerCode(String code);
}