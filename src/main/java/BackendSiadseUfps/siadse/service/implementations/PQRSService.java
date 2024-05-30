package BackendSiadseUfps.siadse.service.implementations;

import BackendSiadseUfps.siadse.dto.PQRSDTO;
import BackendSiadseUfps.siadse.entity.CambioEstRad;
import BackendSiadseUfps.siadse.entity.EstadosPQRS;
import BackendSiadseUfps.siadse.entity.PQRS;

import BackendSiadseUfps.siadse.entity.TiposPQRS;
import BackendSiadseUfps.siadse.repository.EstPQRSRepo;
import BackendSiadseUfps.siadse.repository.PQRSRepo;
import BackendSiadseUfps.siadse.repository.TipoPQRSRepo;
import BackendSiadseUfps.siadse.service.interfaces.IPQRSService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("pqrsService")
public class PQRSService implements IPQRSService {

    @Autowired
    PQRSRepo pqrsRepo;

    @Autowired
    EstPQRSRepo estPQRSRepo;

    @Autowired
    TipoPQRSRepo tipoPQRSRepo;

    @Autowired
    public EmailService emailService;

    /**
     * Método para crear los radicados PQRS, a los PQRS nuevos se les asignará el estado de PENDIENTE
     * No es necesario asignarle rol por lo que es público en la web.
     *
     * @params El pqrsDTO y el ID del tipo de PQRS que se radicó
     * @return El PQRS creado y guardado en la BD.
     */
    @Override
    public PQRSDTO createPQRS(PQRSDTO pqrsDTO, Integer tipoPQRSID){
        PQRS pqrs = new PQRS();
        BeanUtils.copyProperties(pqrsDTO, pqrs);

        TiposPQRS tiposPQRS = tipoPQRSRepo.findById(tipoPQRSID).orElse(null);
        if (tiposPQRS == null)
            throw new IllegalArgumentException("Ese tipo de PQRS no existe");
        EstadosPQRS estado = estPQRSRepo.findByEstado("PENDIENTE");

        pqrs.setTipoPqrs(tiposPQRS);
        pqrs.setEstadoRadicado(estado);
        pqrs.setFechaRadicado(new Date());
        pqrs.setSemillero("SIREDSE");
        pqrs.setCodigoRadicado(generateRandomCode(10));

        if(!pqrsDTO.getAnonimo()){
            if (pqrs.getNombre() == null || pqrs.getNombre() == "") {
                throw new IllegalArgumentException("El nombre de la persona no puede estar vacío.");
            }
            if (pqrs.getApellido() == null || pqrs.getApellido() == "") {
                throw new IllegalArgumentException("El apellido de la persona no puede estar vacío.");
            }
            if (pqrs.getCedula() == null || pqrs.getCedula() == "") {
                throw new IllegalArgumentException("La cédula de la persona no puede estar vacío.");
            }
        }else{
            pqrs.setNombre("No Aplica");
            pqrs.setApellido("No Aplica");
            pqrs.setCedula("No Aplica");
        }


        PQRS pqrsRadicado = pqrsRepo.save(pqrs);

        cambiosEstadoPQRS(pqrsRadicado, estado);

        PQRSDTO creadoPQRS = new PQRSDTO();
        BeanUtils.copyProperties(pqrsRadicado, creadoPQRS);

        emailService.sendListEmail(creadoPQRS, 1, "");
        return creadoPQRS;
    }

    @Override
    public void respuestaPQRS(Integer pqrsId, String mensaje){
        PQRSDTO pqrsDTO = listarPQRSporId(pqrsId);
        emailService.sendListEmail(pqrsDTO, 2, mensaje);
    }

    /**
     * Método para crear el código alfanumérico de radicado por la cual se identifican los PQRS
     * Método utilizado en la creación del PQRS
     *
     * @params Cantidad de caracteres que tendrá el código
     * @return El código alfanumérico creado.
     */
    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }

    /**
     * Método para cambiar el estado del PQRS, ya sea de PENDIENTE a REVISION o REVISION a PENDIENTE (solo aplica en este orden).
     * Solo utilizable por el rol de ADMINISTRADOR o DIRECTOR si lo hay
     *
     * @params El ID del PQRS y el ID del nuevo estado a asignarsele
     * @return No retorna nada.
     */
    @Override
    public void cambioEstadoPQRS(Integer pqrsID, Integer nuevoEstadoID){

        PQRS pqrs = pqrsRepo.findById(pqrsID).orElseThrow(() -> new IllegalArgumentException("PQRS not found"));
        EstadosPQRS nuevoEstado = estPQRSRepo.findById(nuevoEstadoID).orElseThrow(() -> new IllegalArgumentException("Estado not found"));

        switch (nuevoEstadoID) {

            case 2:
                if (!pqrs.getEstadoRadicado().getEstado().equals("PENDIENTE")) {
                    throw new IllegalArgumentException(
                            "No se puede revisar este PQRS si no está en pendiente");
                }
                break;

            case 3:
                if (!pqrs.getEstadoRadicado().getEstado().equals("REVISION")) {
                    throw new IllegalArgumentException(
                            "No se puede dar por resuelto este PQRS si no está en revisión");
                }
                break;

            default:
                throw new IllegalArgumentException("Something is wrong with states");

        }
        pqrs.setEstadoRadicado(nuevoEstado);
        cambiosEstadoPQRS(pqrs, nuevoEstado);
        pqrsRepo.save(pqrs);
    }

    /**
     * Método para listar los PQRS registrados en la BD
     * Solo utilizable por el rol de ADMINISTRADOR o DIRECTOR si lo hay
     *
     * @params
     * @return Lista de los PQRS.
     */
    @Override
    public List<PQRSDTO> listarPQRS() {
        return pqrsRepo.findAll().stream().map(pqrs -> {
            new PQRSDTO();
            return PQRSDTO.builder()
                    .id(pqrs.getId())
                    .titulo(pqrs.getTitulo())
                    .descripcion(pqrs.getDescripcion())
                    .fechaRadicado(pqrs.getFechaRadicado())
                    .estadoRadicado(pqrs.getEstadoRadicado())
                    .correo(pqrs.getCorreo())
                    .tipoPqrs(pqrs.getTipoPqrs())
                    .anonimo(pqrs.getAnonimo())
                    .nombre(pqrs.getNombre())
                    .apellido(pqrs.getApellido())
                    .cedula(pqrs.getCedula())
                    .codigoRadicado(pqrs.getCodigoRadicado())
                    .semillero(pqrs.getSemillero())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public PQRSDTO listarPQRSporId (Integer Id){
        PQRS pqrs = pqrsRepo.findById(Id).orElseThrow(() -> new IllegalArgumentException("PQR not found"));
        PQRSDTO pqrsDTO = new PQRSDTO();
        BeanUtils.copyProperties(pqrs, pqrsDTO);
        return pqrsDTO;
    }

    @Override
    public PQRSDTO listarByRadCode (String radCode){
        PQRS pqrs = pqrsRepo.findByCodigoRadicado(radCode);
        if(pqrs == null){
            throw new IllegalArgumentException("PQR not found");
        }
        PQRSDTO pqrsDTO = new PQRSDTO();
        BeanUtils.copyProperties(pqrs, pqrsDTO);
        return pqrsDTO;
    }

    /**
     * Método para listar los PQRS registrados en la BD filtrados por el tipo de PQRS suministrado
     * Solo utilizable por el rol de ADMINISTRADOR o DIRECTOR si lo hay
     *
     * @params El ID del tipo de PQRS a filtrar
     * @return Lista de los PQRS que sean del tipo de PQRS suministrado.
     */
    @Override
    public List<PQRSDTO> listarPQRSporTipo(Integer tipoID) {
        TiposPQRS tiposPQRS = tipoPQRSRepo.findById(tipoID).orElse(null);
        List<PQRS> pqrss = pqrsRepo.findByTipoPqrs(tiposPQRS);

        List<PQRSDTO> PQRSDTOs = new ArrayList<>();

        for (PQRS pqrs : pqrss) {
            PQRSDTO pqrsdto = new PQRSDTO();
            pqrsdto.setFechaRadicado(pqrs.getFechaRadicado());
            pqrsdto.setEstadoRadicado(pqrs.getEstadoRadicado());
            BeanUtils.copyProperties(pqrs, pqrsdto);
            PQRSDTOs.add(pqrsdto);
        }

        return PQRSDTOs;
    }

    /**
     * Método para listar los PQRS registrados en la BD filtrados por el estado de PQRS suministrado
     * Solo utilizable por el rol de ADMINISTRADOR o DIRECTOR si lo hay
     *
     * @params El ID del estado de PQRS a filtrar
     * @return Lista de los PQRS que sean del estado de PQRS suministrado.
     */
    @Override
    public List<PQRSDTO> listarPQRSporEstado(Integer estadoID) {
        EstadosPQRS estadosPQRS = estPQRSRepo.findById(estadoID).orElse(null);
        List<PQRS> pqrss = pqrsRepo.findByEstadoRadicado(estadosPQRS);

        List<PQRSDTO> PQRSDTOs = new ArrayList<>();

        for (PQRS pqrs : pqrss) {
            PQRSDTO pqrsdto = new PQRSDTO();
            pqrsdto.setFechaRadicado(pqrs.getFechaRadicado());
            pqrsdto.setEstadoRadicado(pqrs.getEstadoRadicado());
            BeanUtils.copyProperties(pqrs, pqrsdto);
            PQRSDTOs.add(pqrsdto);
        }

        return PQRSDTOs;
    }

    /**
     * Método para eliminar un registro de PQRS de la BD, solo se puede eliminar si el estado del PQRS es RESUELTO
     * Solo utilizable por el rol de ADMINISTRADOR o DIRECTOR si lo hay
     *
     * @params El ID del PQRS a eliminar
     * @return No retorna nada.
     */
    @Override
    public boolean eliminarPQRS (Integer pqrsID){
        PQRS pqrs = pqrsRepo.findById(pqrsID).orElseThrow(() -> new IllegalArgumentException("PQRS not found"));
        EstadosPQRS estado = estPQRSRepo.findByEstado("PENDIENTE");
        if(pqrs.getEstadoRadicado().equals(estado)){
            throw new IllegalArgumentException("No se puede eliminar el PQRS si no esta resuelto");
        }

        try {
            pqrsRepo.delete(pqrs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Método para guardar el cambio de estado de los PQRS cuando se llame el endpoint correspondiente.
     * Método utilizado en la creación del PQRS y en los cambios de estado del PQRS
     *
     * @params El PQRS que cambió de estado y el ID del nuevo estado que se le fue asignado
     * @return No retorna nada.
     */
    public void cambiosEstadoPQRS(PQRS pqrs, EstadosPQRS estado){
        CambioEstRad cambioEstRad = new CambioEstRad();
        cambioEstRad.setFecha_cambio(new Date());
        cambioEstRad.setPqrs(pqrs);
        cambioEstRad.setEstado(estado);
    }

}
