package com.codigo.msmisariortega.domain.impl;

import com.codigo.msmisariortega.domain.aggregates.dto.EmpresaDto;
import com.codigo.msmisariortega.domain.aggregates.request.EmpresaRequest;
import com.codigo.msmisariortega.domain.ports.in.EmpresaServiceIn;
import com.codigo.msmisariortega.domain.ports.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;
    @Override
    public EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest) {
        return empresaServiceOut.crearEmpresaOut(empresaRequest);
    }

    @Override
    public Optional<EmpresaDto> buscarIdIn(Long id) {
        return empresaServiceOut.buscarIdOut(id);
    }

    @Override
    public List<EmpresaDto> obtenerTodosIn() {
        return empresaServiceOut.obtenerTodosOut();
    }

    @Override
    public EmpresaDto actualizarIn(Long id, EmpresaRequest empresaRequest) {
        return empresaServiceOut.actualizarOut(id, empresaRequest);
    }

    @Override
    public EmpresaDto deleteIn(Long id) {
        return empresaServiceOut.deleteOut(id);
    }
}
