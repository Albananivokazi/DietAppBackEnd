package com.diaetologie.diaetologenbe.respository;
import com.diaetologie.diaetologenbe.entity.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
public interface MonitoringRepository extends JpaRepository<Monitoring, UUID> {
    List<Monitoring> findByFallIdOrderByDatumDesc(UUID fallId);
}