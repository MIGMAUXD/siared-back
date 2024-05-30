package BackendSiadseUfps.siadse.service.interfaces;


import BackendSiadseUfps.siadse.dto.PQRSDTO;

import java.util.List;

public interface IPQRSService {

    public PQRSDTO createPQRS(PQRSDTO pqrsDTO, Integer tipoPQRSID);
    public void cambioEstadoPQRS(Integer pqrsID, Integer nuevoEstadoID);
    public List<PQRSDTO> listarPQRS();
    public List<PQRSDTO> listarPQRSporTipo(Integer tipoID);
    public List<PQRSDTO> listarPQRSporEstado(Integer estadoID);
    public PQRSDTO listarPQRSporId(Integer Id);
    public boolean eliminarPQRS (Integer pqrsID);
    public PQRSDTO listarByRadCode(String radCode);
    public void respuestaPQRS(Integer pqrsId, String mensaje);
}
