package com.codigo.msmisariortega.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "empresa_info")
@Getter
@Setter
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEmpresa;

    @Column(name = "razonSocial", nullable = false, length = 255)
    private String razonSocial;

    @Column(name = "tipoDocumento", nullable = false, length = 5)
    private String tipoDocumento;

    @Column(name = "numeroDocumento", nullable = false, length = 20)
    private String numeroDocumento;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "condicion", nullable = false, length = 50)
    private String condicion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "distrito", length = 100)
    private String distrito;

    @Column(name = "provincia", length = 100)
    private String provincia;

    @Column(name = "departamento", length = 100)
    private String departamento;

    @Column(name = "esAgenteRetencion", nullable = false)
    private Boolean esAgenteRetencion = false;

    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "usua_modif", length = 45)
    private String usuaModif;

    @Column(name = "date_modif")
    private Timestamp dateModif;

    @Column(name = "usua_delet", length = 45)
    private String usuaDelet;

    @Column(name = "date_delet")
    private Timestamp dateDelet;
}
