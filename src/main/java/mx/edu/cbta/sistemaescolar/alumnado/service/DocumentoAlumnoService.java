package mx.edu.cbta.sistemaescolar.alumnado.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface DocumentoAlumnoService {

    /**
     * Guarda el archivo especificado en el servidor de archivos, devolviendo la ruta del documento
     * almacenado en el servidor.
     * @param matriculaAlumno Matricula del alumno a asociar con el documento.
     * @param actaNacimiento Documento a guardar en el servidor de archivos.
     * @return Ruta del recurso en el servidor de archivos.
     */
    String guardarActaNacimiento(String matriculaAlumno, MultipartFile actaNacimiento);

    /**
     * Guarda el certificado de secundaria del alumno en el servidor de archivos,
     * asociándolo con la matrícula especificada.
     * @param matriculaAlumno Matricula del alumno a asociar con el documento.
     * @param certificado Archivo del certificado de secundaria a guardar.
     * @return Ruta del recurso en el servidor de archivos.
     */
    String guardarCertificadoSecundaria(String matriculaAlumno, MultipartFile certificado);

    /**
     * Guarda el documento de CURP del alumno en el servidor de archivos,
     * asociándolo con la matrícula correspondiente.
     * @param matriculaAlumno Matricula del alumno a asociar con el documento.
     * @param curp Archivo del documento de CURP a guardar.
     * @return Ruta del recurso en el servidor de archivos.
     */
    String guardarCURP(String matriculaAlumno, MultipartFile curp);

    /**
     * Guarda la fotografía del alumno en el servidor de archivos,
     * asociándola con la matrícula correspondiente.
     * @param matriculaAlumno Matricula del alumno a asociar con la fotografía.
     * @param foto Archivo de imagen de la fotografía del alumno.
     * @return Ruta del recurso en el servidor de archivos.
     */
    String guardarFotoAlumno(String matriculaAlumno, MultipartFile foto);

    /**
     * Obtiene el documento de acta de nacimiento del alumno con la matrícula especificada,
     * devolviendo el documento como un objeto de tipo Resource para su lectura o descarga.
     * @param matriculaAlumno Matricula del alumno del cual se desea obtener el acta de nacimiento.
     * @return Recurso correspondiente al acta de nacimiento almacenada.
     */
    Resource obtenerActaNacimiento(String matriculaAlumno);

    /**
     * Obtiene el certificado de secundaria del alumno con la matrícula especificada,
     * devolviendo el documento como un objeto de tipo Resource.
     * @param matriculaAlumno Matricula del alumno del cual se desea obtener el certificado.
     * @return Recurso correspondiente al certificado de secundaria almacenado.
     */
    Resource obtenerCertificadoSecundaria(String matriculaAlumno);

    /**
     * Obtiene el documento de CURP del alumno con la matrícula especificada,
     * devolviendo el documento como un objeto de tipo Resource.
     * @param matriculaAlumno Matricula del alumno del cual se desea obtener el CURP.
     * @return Recurso correspondiente al documento de CURP almacenado.
     */
    Resource obtenerCURP(String matriculaAlumno);

    /**
     * Obtiene la fotografía del alumno con la matrícula especificada,
     * devolviendo la imagen como un objeto de tipo Resource.
     * @param matriculaAlumno Matricula del alumno del cual se desea obtener la fotografía.
     * @return Recurso correspondiente a la fotografía almacenada.
     */
    Resource obtenerFotoAlumno(String matriculaAlumno);

}
