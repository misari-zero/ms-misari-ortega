package com.codigo.msmisariortega.domain.ports.out;

import com.codigo.msmisariortega.domain.aggregates.dto.EmpresaDto;
import com.codigo.msmisariortega.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarIdOut(Long id);
    List<EmpresaDto> obtenerTodosOut();
    EmpresaDto actualizarOut(Long id, EmpresaRequest empresaRequest);
    EmpresaDto deleteOut(Long id);
}
