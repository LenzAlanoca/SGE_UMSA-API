package com.sgeumsaapi.sge_umsa_api.repository;

import com.sgeumsaapi.sge_umsa_api.model.Asistencia;
import com.sgeumsaapi.sge_umsa_api.model.AsistenciaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, AsistenciaId> {
    List<Asistencia> findByIdCi(String ci);
    List<Asistencia> findByIdIdEvento(Long idEvento);
}