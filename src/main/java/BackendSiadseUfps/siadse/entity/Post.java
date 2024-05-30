package BackendSiadseUfps.siadse.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
   
   @Lob
    private String contenido;
   
    private Date fechaCreacion;
    private String tag;
    
   @Lob
    private String encabezados;
    
    @Lob
    private String imagenEncabezado; // La imagen codificada en base64
    
    @Column(unique = true)
    private String uniqueTitleId; //ejemplo: Titulo: Hola como estas -> hola-como-estas

}