package com.codigo.msmisariortega.domain.ports.in;

import com.codigo.msmisariortega.domain.aggregates.dto.EmpresaDto;
import com.codigo.msmisariortega.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;
public interface EmpresaServiceIn {
    EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarIdIn(Long id);
    List<EmpresaDto> obtenerTodosIn();
    EmpresaDto actualizarIn(Long id, EmpresaRequest empresaRequest);
    EmpresaDto deleteIn(Long id);
}
