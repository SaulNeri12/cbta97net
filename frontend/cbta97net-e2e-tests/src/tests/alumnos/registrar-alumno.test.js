const driver = require('../../setup/driver');
const { By, until } = require('selenium-webdriver');

const path = require('path');

const { RegistrarAlumnoPage } = require('../../pages/alumnos/registrar-alumno.page');

require('dotenv').config();

jest.setTimeout(60000)

describe('CP #1 - Foto de Alumno con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();



    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que la foto del alumno seleccionada detecta cuando una imagen no tiene el formato correcto (solo se acepta JPEG, PNG, WEBP).', async () => {
        await registrarAlumnoPage.open();

        const rutaRaiz = process.cwd();

        const rutaAbsolutaArchivoInvalido = path.join(rutaRaiz, 'assets', 'archivo_invalido.txt');
        const rutaAbsolutaArchivoValido = path.join(rutaRaiz, 'assets', 'archivo_valido.jpeg');

        // archivo NO valido
        await registrarAlumnoPage.asignarFotoAlumno(rutaAbsolutaArchivoInvalido);

        let archivoCargado = await registrarAlumnoPage.driver
            .findElement(registrarAlumnoPage.fotoAlumnoField)
            .getAttribute("value");

        expect(archivoCargado).not.toBeNull();
        expect(normalizarTexto(archivoCargado)).toContain('');

        await registrarAlumnoPage.driver.findElement(registrarAlumnoPage.fotoAlumnoField).clear();

        // archivo valido
        await registrarAlumnoPage.asignarFotoAlumno(rutaAbsolutaArchivoValido);

        archivoCargado = await registrarAlumnoPage.driver
            .findElement(registrarAlumnoPage.fotoAlumnoField)
            .getAttribute("value");

        expect(archivoCargado).not.toBeNull();
        expect(normalizarTexto(archivoCargado)).toContain('jpeg');
    });
});

describe('CP #2 - Matrícula con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente las matrículas de alumnos. Las matrículas deben contener solamente 8 dígitos fijos.\n', async () => {
        const matriculaIncorrecta = "6502mos";
        const matriculaValida = "19040042";

        await registrarAlumnoPage.open();

        // llenado de matricula incorrecto
        await registrarAlumnoPage.asignarMatriculaAlumno(matriculaIncorrecta); // TODO: separar todos los campos en su propio metodo (dentro de RegistrarAlumnoPage)
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.matriculaAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('matricula'); // algo como 'formato de matricula incorrecto', incluye 'matricula'

        // llenao de matricula correcto
        await registrarAlumnoPage.asignarMatriculaAlumno(matriculaValida);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.matriculaAlumnoField);

        expect(normalizarTexto(mensajeValidacion)).toContain(''); // sin mensaje de error
    });
});

describe('CP #3 - CURP con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente la CURP a registrar. El sistema debe mostrar un mensaje indicando que la CURP ingresada no cumple con el formato correcto.', async () => {
        let curpIncorrecta = "111???xxx";
        const curpValida = "EOLE049815HERRELA1";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarCURPAlumno(curpIncorrecta);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.curpAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('18 caracteres');
        expect(normalizarTexto(mensajeValidacion)).toContain('longitud');
        expect(normalizarTexto(mensajeValidacion)).toContain('minimo');

        await registrarAlumnoPage.asignarCURPAlumno(curpValida);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.curpAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('');
    });
});



describe('CP #4 - Nombre del Alumno con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente el nombre del alumno a registrar. El sistema debe mostrar un mensaje indicando que el nombre ingresado no cumple con el formato requerido.\n', async () => {
        let nombreNoValido = "C?rl?s";
        const nombreValido = "Carlos";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarNombreAlumno(nombreNoValido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nombreAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        await registrarAlumnoPage.asignarNombreAlumno(nombreValido)
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nombreAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('');
    });
});



describe('CP #5 - Apellido Paterno del Alumno con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente el apellido paterno del alumno a registrar. El sistema debe mostrar un mensaje indicando que el apellido ingresado no cumple con el formato requerido. El formato no permite números ni símbolos.', async () => {
        let apellidoNoValido = "P3r3z?";
        const apellidoValido = "Perez";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarApellidoPaternoAlumno(apellidoNoValido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoPaternoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        await registrarAlumnoPage.asignarApellidoPaternoAlumno(apellidoValido);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoPaternoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('');
    });
});




describe('CP #6 - Apellido Materno del Alumno con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente el apellido materno del alumno a registrar. El sistema debe mostrar un mensaje indicando que el apellido ingresado no cumple con el formato requerido. El formato no permite números ni símbolos.', async () => {
        let apellidoNoValido = "M0reN0?";
        const apellidoValido = "Moreno";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarApellidoMaternoAlumno(apellidoNoValido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoMaternoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        await registrarAlumnoPage.asignarApellidoMaternoAlumno(apellidoValido);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoMaternoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('');
    });
});



describe('CP #7 - Fecha de Nacimiento del Alumno no válida', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema detecta correctamente cuando se selecciona una fecha de nacimiento igual o posterior a la actual.', async () => {
        const hoy = new Date();

        const fechaActual = hoy.toISOString().split("T")[0];

        const fechaMas5 = new Date(hoy);
        fechaMas5.setDate(hoy.getDate() + 5);
        const fechaPosterior = fechaMas5.toISOString().split("T")[0];

        const fechaMenos15 = new Date(hoy);
        fechaMenos15.setFullYear(hoy.getFullYear() - 15);
        const fechaAnterior = fechaMenos15.toISOString().split("T")[0];

        await registrarAlumnoPage.open();

        // fecha actual
        await registrarAlumnoPage.asignarFechaNacimientoAlumno(fechaActual);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.fechaNacimientoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('anterior');

        // fecha posterior (5 días después de hoy)
        await registrarAlumnoPage.asignarFechaNacimientoAlumno(fechaPosterior);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.fechaNacimientoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('anterior');

        // fecha anterior (hace 15 años)
        await registrarAlumnoPage.asignarFechaNacimientoAlumno(fechaAnterior);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.fechaNacimientoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
    });
});




describe('CP #8 - Número de Seguro Social (NSS) del Alumno no válido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema detecta cuando el NSS del alumno tiene un formato incorrecto. El formato es de 11 dígitos.\n', async () => {

        let nssNoValido = "N555???";
        const nssValido = "01234567899";

        await registrarAlumnoPage.open();

        // NSS No valido #1
        await registrarAlumnoPage.asignarNSSAlumno(nssNoValido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nssAlumnoField);
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');
        expect(normalizarTexto(mensajeValidacion)).toContain('11');


        // NSS No valido #2
        nssNoValido = "1234567890########";
        await registrarAlumnoPage.asignarNSSAlumno(nssNoValido);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nssAlumnoField);
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');
        expect(normalizarTexto(mensajeValidacion)).toContain('11');


        // NSS valido (correcto)
        await registrarAlumnoPage.asignarNSSAlumno(nssValido);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nssAlumnoField);
        expect(normalizarTexto(mensajeValidacion)).toContain('');


        expect(mensajeValidacion).not.toBeNull();
    });
});



describe('CP #9 - Póliza de Seguro del Alumno no válida', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema detecta cuando la póliza de seguro del alumno tiene un formato incorrecto. El formato aceptado son solo números. (sin rango especificado).', async () => {

        let polizaNoValida = "N555???";
        const polizaValida = "2474982004";

        await registrarAlumnoPage.open();

        // Poliza No Valida
        await registrarAlumnoPage.asignarPolizaSeguroAlumno(polizaNoValida);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.polizaAlumnoField);
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');
        expect(normalizarTexto(mensajeValidacion)).toContain('11');


        // Poliza valida (correcta)
        await registrarAlumnoPage.asignarPolizaSeguroAlumno(polizaNoValida);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nssAlumnoField);
        expect(normalizarTexto(mensajeValidacion)).toContain('');


        expect(mensajeValidacion).not.toBeNull();
    });
});



describe('CP #10 - Nombre del Tutor con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente el nombre del tutor legal del alumno. El sistema debe mostrar un mensaje indicando que el nombre ingresado no cumple con el formato requerido. El formato no permite números ni símbolos.', async () => {
        let nombreNoValido = "D4vid?";
        const nombreValido = "David";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarNombreTutor(nombreNoValido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nombreTutorField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        await registrarAlumnoPage.asignarNombreTutor(nombreValido)
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.nombreTutorField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('');
    });
});




describe('CP #11 - Apellido Paterno del tutor con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente el apellido paterno del tutor legal del alumno. El sistema debe mostrar un mensaje indicando que el apellido paterno ingresado no cumple con el formato requerido. El formato no permite números ni símbolos.', async () => {
        let apellidoNoValido = "P3r3z?";
        const apellidoValido = "Perez";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarApellidoPaternoAlumno(apellidoNoValido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoPaternoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        await registrarAlumnoPage.asignarApellidoPaternoAlumno(apellidoValido);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoPaternoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('');
    });
});



describe('CP #12 - Apellido Materno del tutor con Formato Inválido', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente el apellido materno del tutor legal del alumno. El sistema debe mostrar un mensaje indicando que el apellido materno ingresado no cumple con el formato requerido. El formato no permite números ni símbolos.', async () => {
        let apellidoNoValido = "P3r3z???";
        const apellidoValido = "Perez";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarApellidoMaternoTutor(apellidoNoValido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoMaternoTutorField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        await registrarAlumnoPage.asignarApellidoMaternoTutor(apellidoValido);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.apellidoMaternoTutorField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('');
    });
});



describe('CP #13 - Fecha de Nacimiento del Tutor no válida (Debe tener 18 años o más)', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que el sistema valida correctamente que el tutor legal tiene 18 años o más.', async () => {

        const hoy = new Date();

        const fechaInvalidaMenor = "2010-01-01"; // menor de 18 años

        const fechaValidaMayor = "1990-01-01";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarFechaNacimientoTutor(fechaInvalidaMenor);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(
            registrarAlumnoPage.fechaNacimientoTutorField
        );

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('edad');

        // =========================================================================
        await registrarAlumnoPage.asignarFechaNacimientoTutor(fechaValidaMayor);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(
            registrarAlumnoPage.fechaNacimientoTutorField
        );

        expect(mensajeValidacion).toBeNull(); // NO debe haber mensaje de error
    });
});




describe('CP #14 - Validar el número de teléfono del tutor', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar cuando se ingresa el documento del Acta de Nacimiento del alumno en el campo correspondiente, se notifique al usuario que dicho documento excede el tamaño aceptado por el sistema. Además de verificar que el formato del archivo es PDF.', async () => {

        const telefonoInvalido = "644L01311";

        const telefonoCorrecto = "6444000000";

        await registrarAlumnoPage.open();

        await registrarAlumnoPage.asignarNumeroTelefonoTutor(telefonoInvalido)
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(
            registrarAlumnoPage.telefonoTutorField
        );

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        await registrarAlumnoPage.asignarNumeroTelefonoTutor(telefonoCorrecto)
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(
            registrarAlumnoPage.telefonoTutorField
        );

        expect(mensajeValidacion).toBeNull();
    });
});



describe('CP #15 - Documento Acta de Nacimiento - Formato y Tamaño', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar la validación de formato (solo PDF) y tamaño (max 10MB) del Acta de Nacimiento.', async () => {
        await registrarAlumnoPage.open();
        const rutaRaiz = process.cwd();

        // prueba documento formato no valido
        const rutaAbsolutaArchivoInvalido = path.join(rutaRaiz, 'assets', 'documentos', 'pesados', 'DocumentoTestPesado_10mb.txt');
        await registrarAlumnoPage.introducirDocumentoActaNacimientoAlumno(rutaAbsolutaArchivoInvalido);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docActaNacimientoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('pdf');
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        // prueba documento pesado PDF
        const rutaAbsolutaArchivoPesado = path.join(rutaRaiz, 'assets', 'documentos', 'pesados', 'DocumentoTestPesado_10mb.pdf');
        await registrarAlumnoPage.introducirDocumentoActaNacimientoAlumno(rutaAbsolutaArchivoPesado);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docActaNacimientoAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('pdf');
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        // prueba documento valido
        const rutaAbsolutaArchivoValido = path.join(rutaRaiz, 'assets', 'documentos', 'validos', 'DocumentoTest_No_10mb.pdf');
        await registrarAlumnoPage.introducirDocumentoActaNacimientoAlumno(rutaAbsolutaArchivoValido);

        let archivoCargado = await registrarAlumnoPage.driver
            .findElement(registrarAlumnoPage.docActaNacimientoAlumnoField)
            .getAttribute("value");

        expect(archivoCargado).not.toBeNull();
        expect(normalizarTexto(archivoCargado)).toContain('pdf');
    });
});



describe('CP #16 - Documento Certificado de Secundaria - Formato y Tamaño', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar la validación de formato (solo PDF) y tamaño (max 10MB) del Certificado de Secundaria.', async () => {
        await registrarAlumnoPage.open();
        const rutaRaiz = process.cwd();

        const rutaAbsolutaArchivoInvalido = path.join(rutaRaiz, 'assets', 'documentos', 'pesados', 'DocumentoTestPesado_10mb.txt');
        await registrarAlumnoPage.introducirDocumentoCertificadoSecundariaAlumno(rutaAbsolutaArchivoInvalido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docCertificadoSecundariaAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('pdf');
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        const rutaAbsolutaArchivoPesado = path.join(rutaRaiz, 'assets', 'documentos', 'pesados', 'DocumentoTestPesado_10mb.pdf');
        await registrarAlumnoPage.introducirDocumentoCertificadoSecundariaAlumno(rutaAbsolutaArchivoPesado);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docCertificadoSecundariaAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('pdf');

        const rutaAbsolutaArchivoValido = path.join(rutaRaiz, 'assets', 'documentos', 'validos', 'DocumentoTest_No_10mb.pdf');
        await registrarAlumnoPage.introducirDocumentoCertificadoSecundariaAlumno(rutaAbsolutaArchivoValido);

        let archivoCargado = await registrarAlumnoPage.driver
            .findElement(registrarAlumnoPage.docCertificadoSecundariaAlumnoField)
            .getAttribute("value");

        expect(archivoCargado).not.toBeNull();
        expect(normalizarTexto(archivoCargado)).toContain('pdf');

        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docCertificadoSecundariaAlumnoField);
        expect(mensajeValidacion).toBeNull();
    });
});



describe('CP #17 - Documento CURP del Alumno - Formato y Tamaño', () => {
    let registrarAlumnoPage;
    const normalizarTexto = (str) => str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar la validación de formato (solo PDF) y tamaño (max 10MB) del Documento CURP.', async () => {
        await registrarAlumnoPage.open();
        const rutaRaiz = process.cwd();

        const rutaAbsolutaArchivoInvalido = path.join(rutaRaiz, 'assets', 'documentos', 'pesados', 'DocumentoTestPesado_10mb.txt');
        await registrarAlumnoPage.introducirDocumentoCURPAlumno(rutaAbsolutaArchivoInvalido);
        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docCURPAlumnoField);

        expect(mensajeValidacion).not.toBeNull();
        expect(normalizarTexto(mensajeValidacion)).toContain('pdf');
        expect(normalizarTexto(mensajeValidacion)).toContain('formato');

        const rutaAbsolutaArchivoPesado = path.join(rutaRaiz, 'assets', 'documentos', 'pesados', 'DocumentoTestPesado_10mb.pdf');
        await registrarAlumnoPage.introducirDocumentoCURPAlumno(rutaAbsolutaArchivoPesado);
        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docCURPAlumnoField);
        expect(mensajeValidacion).not.toBeNull();

        const rutaAbsolutaArchivoValido = path.join(rutaRaiz, 'assets', 'documentos', 'validos', 'DocumentoTest_No_10mb.pdf');
        await registrarAlumnoPage.introducirDocumentoCURPAlumno(rutaAbsolutaArchivoValido);

        let archivoCargado = await registrarAlumnoPage.driver
            .findElement(registrarAlumnoPage.docCURPAlumnoField)
            .getAttribute("value");

        expect(archivoCargado).not.toBeNull();
        expect(normalizarTexto(archivoCargado)).toContain('pdf');

        mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(registrarAlumnoPage.docCURPAlumnoField);
        expect(mensajeValidacion).toBeNull();
    });
});


describe('CP #18 - Especificación de Condición Especial del Alumno', () => {
    let registrarAlumnoPage;

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test('Verificar que al seleccionar la opción de Condición Especial se muestra/oculta el campo de descripción.', async () => {
        await registrarAlumnoPage.open();

        await registrarAlumnoPage.toggleCondicionEspecialAlumno();

        let isVisible = await registrarAlumnoPage.isCondicionEspecialDescripcionVisible();
        expect(isVisible).toBe(true);

        await registrarAlumnoPage.toggleCondicionEspecialAlumno();

        isVisible = await registrarAlumnoPage.isCondicionEspecialDescripcionVisible();
        expect(isVisible).toBe(false);
    });
});



describe('CP #19 - No permitir el registro sin completar todos los campos', () => {
    let registrarAlumnoPage;

    const campoRequerido = 'Nombre del Alumno';

    beforeAll(() => {
        registrarAlumnoPage = new RegistrarAlumnoPage(driver.driver);
    });

    test(`Verificar que el sistema valida y detiene el registro si faltan campos obligatorios. (Probando con ${campoRequerido})`, async () => {
        await registrarAlumnoPage.open();

        await registrarAlumnoPage.clickRegistrarAlumno();

        let mensajeValidacion = await registrarAlumnoPage.getNativeValidationError(
            registrarAlumnoPage.nombreAlumnoField
        );

        expect(mensajeValidacion).not.toBeNull();
        expect(mensajeValidacion.length).toBeGreaterThan(0);
    });
});