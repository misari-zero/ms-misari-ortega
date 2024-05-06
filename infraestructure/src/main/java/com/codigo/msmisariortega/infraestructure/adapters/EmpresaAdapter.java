package com.codigo.msmisariortega.infraestructure.adapters;

import com.codigo.msmisariortega.domain.aggregates.constants.Constant;
import com.codigo.msmisariortega.domain.aggregates.dto.EmpresaDto;
import com.codigo.msmisariortega.domain.aggregates.dto.SunatDto;
import com.codigo.msmisariortega.domain.aggregates.request.EmpresaRequest;
import com.codigo.msmisariortega.domain.ports.out.EmpresaServiceOut;
import com.codigo.msmisariortega.infraestructure.client.ClientSunat;
import com.codigo.msmisariortega.infraestructure.dao.EmpresaRepository;
import com.codigo.msmisariortega.infraestructure.entity.EmpresaEntity;
import com.codigo.msmisariortega.infraestructure.mapper.EmpresaMapper;
import com.codigo.msmisariortega.infraestructure.redis.RedisService;
import com.codigo.msmisariortega.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaAdapter implements EmpresaServiceOut {

    private final EmpresaRepository empresaRepository;
    private final ClientSunat clientSunat;
    private final RedisService redisService;

    @Value("${token.sunat}")
    private String tokenSunat;
    @Override
    public EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity = getEntity(empresaRequest, false, null);
        return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
    }

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, boolean actualiza, Long id){
        //Exec servicio
        SunatDto sunatDto = getExecSunat(empresaRequest.getNumDoc());
        EmpresaEntity entity = new EmpresaEntity();
        entity.setRazonSocial(sunatDto.getRazonSocial());
        entity.setTipoDocumento(sunatDto.getTipoDocumento());
        entity.setNumeroDocumento(sunatDto.getNumeroDocumento());
        entity.setEstado(Constant.STATUS_ACTIVE);
        entity.setCondicion(sunatDto.getCondicion());
        entity.setDireccion(sunatDto.getDireccion());
        entity.setDistrito(sunatDto.getDistrito());
        entity.setProvincia(sunatDto.getProvincia());
        entity.setDepartamento(sunatDto.getDepartamento());
        entity.setEsAgenteRetencion(Boolean.valueOf(sunatDto.getEsAgenteRetencion()));
        //Datos de auditoria donde corresponda

        if(actualiza){
            //si Actualizo hago esto
            entity.setIdEmpresa(id);
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());

        }else{
            //Sino Actualizo hago esto
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }

        return entity;
    }

    private SunatDto getExecSunat(String numDoc){
        String authorization = "Bearer "+tokenSunat;
        return clientSunat.getInfoSunat(numDoc, authorization);
    }

    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
    @Override
    public Optional<EmpresaDto> buscarIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id);
        if(redisInfo!= null){
            EmpresaDto empresaDto = Util.convertirDesdeString(redisInfo,EmpresaDto.class);
            return Optional.of(empresaDto);
        }else{
            EmpresaDto empresaDto = EmpresaMapper.fromEntity(empresaRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(empresaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id,dataForRedis,10);
            return Optional.of(empresaDto);
        }
    }

    @Override
    public List<EmpresaDto> obtenerTodosOut() {
        List<EmpresaDto> listaDto = new ArrayList<>();
        List<EmpresaEntity> entidades = empresaRepository.findAll();
            for (EmpresaEntity dato : entidades){
                listaDto.add(EmpresaMapper.fromEntity(dato));
            }
        return listaDto;
    }

    @Override
    public EmpresaDto actualizarOut(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if(datoExtraido.isPresent()){
            EmpresaEntity empresaEntity = getEntity(empresaRequest,true, id);
            return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
            //return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public EmpresaDto deleteOut(Long id) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return EmpresaMapper.fromEntity(empresaRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();
        }
    }
}
