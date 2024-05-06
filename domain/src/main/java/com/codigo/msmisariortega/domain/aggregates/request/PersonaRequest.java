package com.codigo.msmisariortega.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaRequest {
    private String tipoDoc;
    private String numDoc;
    private String empresa;
}
