package com.musala.dronemanagement.repository;
import com.musala.dronemanagement.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 1:57:38 PM
 */
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}