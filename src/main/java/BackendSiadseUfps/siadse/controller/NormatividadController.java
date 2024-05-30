package BackendSiadseUfps.siadse.controller;

import BackendSiadseUfps.siadse.dto.AlbumDTO;

import BackendSiadseUfps.siadse.dto.NormatividadDTO;
import BackendSiadseUfps.siadse.service.interfaces.NormatividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/public/regulation")
public class NormatividadController {

    @Autowired
    private NormatividadService normatividadService;

    @PostMapping
    public ResponseEntity<NormatividadDTO> uploadRegulation(@RequestParam Integer semilleroId, @RequestParam("file") MultipartFile file) throws IOException {
        NormatividadDTO normatividad = normatividadService.upload(semilleroId, file);
        return new ResponseEntity<>(normatividad, HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<AlbumDTO> listRegulations(@RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
