package BackendSiadseUfps.siadse.service.interfaces;

import BackendSiadseUfps.siadse.dto.ConfiguracionDTO;
import BackendSiadseUfps.siadse.entity.Configuracion;

import java.util.List;
import java.util.Optional;

public interface ConfiguracionService {

    Configuracion obtenerConfiguracionUnica();

    Configuracion actualizarConfiguracion(ConfiguracionDTO configuracionDTO);
    List<Configuracion> obtenerTodasLasConfiguraciones();

    Optional<Configuracion> obtenerConfiguracionPorId(Integer id);

    Configuracion guardarConfiguracion(ConfiguracionDTO configuracionDTO);

    void eliminarConfiguracionPorId(Integer id);

}
