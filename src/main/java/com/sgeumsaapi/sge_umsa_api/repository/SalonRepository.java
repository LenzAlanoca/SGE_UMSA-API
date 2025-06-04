package com.sgeumsaapi.sge_umsa_api.repository;

import com.sgeumsaapi.sge_umsa_api.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Long> {
    List<Salon> findByPredioId(Long predioId);
}