package mx.edu.cbta.sistemaescolar.alumnado.service.impl;

import mx.edu.cbta.sistemaescolar.alumnado.repository.DocumentoAlumnoRepository;
import mx.edu.cbta.sistemaescolar.alumnado.service.DocumentoAlumnoService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

@Service
public class DocumentoAlumnoServiceImpl implements DocumentoAlumnoService {

    private DocumentoAlumnoRepository documentoAlumnoRepository;

    public DocumentoAlumnoServiceImpl(DocumentoAlumnoRepository documentoAlumnoRepository) {
        this.documentoAlumnoRepository = documentoAlumnoRepository;
    }

    @Override
    public String guardarActaNacimiento(String matriculaAlumno, MultipartFile actaNacimiento) {
        return "";
    }

    @Override
    public String guardarCertificadoSecundaria(String matriculaAlumno, MultipartFile certificado) {
        return "";
    }

    @Override
    public String guardarCURP(String matriculaAlumno, MultipartFile curp) {
        return "";
    }

    @Override
    public String guardarFotoAlumno(String matriculaAlumno, MultipartFile foto) {
        return "";
    }

    @Override
    public Resource obtenerActaNacimiento(String matriculaAlumno) {
        return null;
    }

    @Override
    public Resource obtenerCertificadoSecundaria(String matriculaAlumno) {
        return null;
    }

    @Override
    public Resource obtenerCURP(String matriculaAlumno) {
        return null;
    }

    @Override
    public Resource obtenerFotoAlumno(String matriculaAlumno) {
        return null;
    }
}
