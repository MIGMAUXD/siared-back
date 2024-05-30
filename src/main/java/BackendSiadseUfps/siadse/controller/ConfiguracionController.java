package BackendSiadseUfps.siadse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BackendSiadseUfps.siadse.dto.ConfiguracionDTO;
import BackendSiadseUfps.siadse.entity.Configuracion;
import BackendSiadseUfps.siadse.service.interfaces.ConfiguracionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/configuracion")
public class ConfiguracionController {

    @Autowired
    private ConfiguracionService configuracionService;

    @GetMapping
    public ResponseEntity<Configuracion> obtenerConfiguracionUnica() {
        Configuracion configuracion = configuracionService.obtenerConfiguracionUnica();
        return new ResponseEntity<>(configuracion, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Configuracion> actualizarConfiguracion(@RequestBody ConfiguracionDTO configuracionDTO) {
        Configuracion configuracionActualizada = configuracionService.actualizarConfiguracion(configuracionDTO);
        return new ResponseEntity<>(configuracionActualizada, HttpStatus.OK);
    }
}
