package com.sgeumsaapi.sge_umsa_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgeumsaapi.sge_umsa_api.model.Salon;

public interface SalonRepository extends JpaRepository<Salon, Long> {
    List<Salon> findByPredio_IdPredio(Long idPredio);

}