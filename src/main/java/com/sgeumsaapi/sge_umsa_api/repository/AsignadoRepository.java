package com.sgeumsaapi.sge_umsa_api.repository;

import com.sgeumsaapi.sge_umsa_api.model.Asignado;
import com.sgeumsaapi.sge_umsa_api.model.AsignadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsignadoRepository extends JpaRepository<Asignado, AsignadoId> {
    List<Asignado> findByIdIdEvento(Long idEvento);
    List<Asignado> findByIdIdSalon(Long idSalon);
}