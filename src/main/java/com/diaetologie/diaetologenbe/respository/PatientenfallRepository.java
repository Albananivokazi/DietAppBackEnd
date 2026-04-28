package com.diaetologie.diaetologenbe.respository;

import com.diaetologie.diaetologenbe.entity.Patientenfall;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientenfallRepository extends JpaRepository<Patientenfall, UUID> {
}