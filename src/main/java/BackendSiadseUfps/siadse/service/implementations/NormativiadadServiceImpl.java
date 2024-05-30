package BackendSiadseUfps.siadse.service.implementations;

import BackendSiadseUfps.siadse.dto.ContenidoMutimediaDTO;
import BackendSiadseUfps.siadse.dto.NormatividadDTO;
import BackendSiadseUfps.siadse.entity.Normatividad;
import BackendSiadseUfps.siadse.entity.Semillero;
import BackendSiadseUfps.siadse.repository.NormatividadRepository;
import BackendSiadseUfps.siadse.repository.SemilleroRepository;
import BackendSiadseUfps.siadse.service.interfaces.NormatividadService;
import BackendSiadseUfps.siadse.service.interfaces.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class NormativiadadServiceImpl implements NormatividadService {

    private NormatividadRepository normatividadRepository;
    private SemilleroRepository semilleroRepository;
    private final ModelMapper modelMapper;
    private StorageService storageService;

    @Autowired
    public NormativiadadServiceImpl(SemilleroRepository semilleroRepository, ModelMapper modelMapper, StorageService storageService, NormatividadRepository normatividadRepository) {
        this.semilleroRepository = semilleroRepository;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
        this.normatividadRepository = normatividadRepository;
    }

    @Override
    public NormatividadDTO upload(Integer semilleroId, MultipartFile file) throws IOException {
        Semillero semillero = semilleroRepository.findById(semilleroId).get();

        if (semillero == null || semillero.getNombre() == null) {
            throw new IllegalStateException("No se puede obtener el semillero");
        }

        String albumFolderName = semillero.getNombre();
        String albumFolderPath = Paths.get(albumFolderName).toString();
        String fileName = file.getOriginalFilename();


        String storedFileName = storageService.store(file, albumFolderPath, fileName);
        String filePath = Paths.get(storageService.getRootLocation(), albumFolderName, fileName).toString();
        Normatividad normatividad = new Normatividad();
        normatividad.setTitulo(fileName);
        normatividad.setFechaSubida(new Date());
        normatividad.setUrl(storedFileName);
        normatividad.setFormato(obtenerFormatoArchivo(fileName));
        normatividad.setSemillero(semillero);
        normatividad.setRuta(filePath);

        Normatividad savedNormatividad = normatividadRepository.save(normatividad);

        NormatividadDTO normatividadDTO = new NormatividadDTO();
        BeanUtils.copyProperties(savedNormatividad, normatividadDTO);
        normatividadDTO.setIdSemillero(savedNormatividad.getSemillero().getId());
        return normatividadDTO;
    }

    private String obtenerFormatoArchivo(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index + 1);
        }
        return "";
    }
}
