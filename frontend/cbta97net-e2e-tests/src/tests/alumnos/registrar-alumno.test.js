const driver = require('../../setup/driver');
const { By, until } = require('selenium-webdriver');

const { RegistrarAlumnoPage } = require('../../pages/alumnos/registrar-alumno.page');

require('dotenv').config();

jest.setTimeout(60000)

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