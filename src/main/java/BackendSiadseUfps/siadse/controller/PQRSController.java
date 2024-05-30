package BackendSiadseUfps.siadse.controller;



import BackendSiadseUfps.siadse.dto.PQRSDTO;
import BackendSiadseUfps.siadse.models.Response;
import BackendSiadseUfps.siadse.service.implementations.PQRSService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pqrs") //Aca no se deja el request si manejan doble rol por url, sino en los propios métodos el mapping se deja el rol
public class PQRSController {

    @Autowired
    PQRSService pqrsService;

    @PostMapping("/create")
    public ResponseEntity<PQRSDTO> createRequest(@RequestBody PQRSDTO pqrsDTO, @RequestParam Integer tipoPQRSID) {

        PQRSDTO newPQRS = pqrsService.createPQRS(pqrsDTO, tipoPQRSID);

        return ResponseEntity.ok(newPQRS);
    }

    @PostMapping("/respuesta")
    public ResponseEntity<String> respuestaPQR(@RequestBody String mensaje, @RequestParam Integer pqrsId) {
        pqrsService.respuestaPQRS(pqrsId, mensaje);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{pqrsId}")
    public ResponseEntity<PQRSDTO> listPQRbyId(@PathVariable Integer pqrsId) {
        PQRSDTO pqrsdto = new PQRSDTO();
        BeanUtils.copyProperties(pqrsService.listarPQRSporId(pqrsId), pqrsdto);
        return ResponseEntity.ok(pqrsdto);
    }

    @GetMapping("/code/{codeRad}")
    public ResponseEntity<PQRSDTO> listPQRbyCodeRad(@PathVariable String codeRad) {
        PQRSDTO pqrsdto = new PQRSDTO();
        BeanUtils.copyProperties(pqrsService.listarByRadCode(codeRad), pqrsdto);
        return ResponseEntity.ok(pqrsdto);
    }

    @GetMapping
    public List<PQRSDTO> listarPQRSr() {
        return pqrsService.listarPQRS();
    }

    @GetMapping("/tipo")
    public List<PQRSDTO> listarPqrsTipo(@RequestParam Integer tipo) {
        return pqrsService.listarPQRSporTipo(tipo);
    }

    @GetMapping("/estado")
    public List<PQRSDTO> listarPqrsEstado(@RequestParam Integer estado) {
        return pqrsService.listarPQRSporEstado(estado);
    }

    @DeleteMapping("/delete")
    public Boolean deletePQRS(@RequestParam Integer pqrsID){
        return  pqrsService.eliminarPQRS(pqrsID);
    }

    @PostMapping("/revision")
    public Response reviewPQRS(@RequestParam Integer pqrsId) {
        Response response = new Response();
        try {
            pqrsService.cambioEstadoPQRS(pqrsId, 2);
            response.setMessage("PQRS pasada a Revision");
        } catch (IllegalArgumentException e) {
            response.setMessage("Error cambiando el estado: " + e.getMessage());
        }

        return response;
    }

    @PostMapping("/resuelto")
    public Response finishPQRS(@RequestParam Integer pqrsId) {
        Response response = new Response();
        try {
            pqrsService.cambioEstadoPQRS(pqrsId, 3);
            response.setMessage("PQRS Resuelta");
        } catch (IllegalArgumentException e) {
            response.setMessage("Error cambiando el estado: " + e.getMessage());
        }

        return response;
    }

}
