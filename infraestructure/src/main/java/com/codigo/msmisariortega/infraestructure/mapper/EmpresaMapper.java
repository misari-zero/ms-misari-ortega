package com.codigo.msmisariortega.infraestructure.mapper;

import com.codigo.msmisariortega.domain.aggregates.dto.EmpresaDto;
import com.codigo.msmisariortega.infraestructure.entity.EmpresaEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper{
    public static EmpresaDto fromEntity(EmpresaEntity entity) {
        EmpresaDto dto = new EmpresaDto();
        dto.setIdEmpresa(entity.getIdEmpresa());
        dto.setRazonSocial(entity.getRazonSocial());
        dto.setTipoDocumento(entity.getTipoDocumento());
        dto.setNumeroDocumento(entity.getNumeroDocumento());
        dto.setEstado(entity.getEstado());
        dto.setCondicion(entity.getCondicion());
        dto.setDireccion(entity.getDireccion());
        dto.setDistrito(entity.getDistrito());
        dto.setProvincia(entity.getProvincia());
        dto.setDepartamento(entity.getDepartamento());
        dto.setEsAgenteRetencion(entity.getEsAgenteRetencion());
        dto.setEstado(entity.getEstado());
        dto.setUsuaCrea(entity.getUsuaCrea());
        dto.setDateCreate(entity.getDateCreate());
        dto.setUsuaModif(entity.getUsuaModif());
        dto.setDateModif(entity.getDateModif());
        dto.setUsuaDelet(entity.getUsuaDelet());
        dto.setDateDelet(entity.getDateDelet());
        return dto;
    }
}
