package BackendSiadseUfps.siadse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "configuracion")
public class Configuracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String sigla;

    private String mision;

    private String vision;

    private String palabrasClave;

    private String director;

    private String whatsapp;

    private String instagram;

    private String facebook;

    private String correo;


}

