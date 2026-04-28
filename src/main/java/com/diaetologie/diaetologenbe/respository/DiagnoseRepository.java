package com.diaetologie.diaetologenbe.respository;
import com.diaetologie.diaetologenbe.entity.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
public interface DiagnoseRepository extends JpaRepository<Diagnose, UUID> {
    List<Diagnose> findByFallId(UUID fallId);
}