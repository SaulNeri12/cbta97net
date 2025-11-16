const { By, until } = require('selenium-webdriver');
const { BASE_URL } = require('../../utils/config');

class RegistrarAlumnoPage {

    constructor(driver) {
        this.driver = driver;
        this.url = `${BASE_URL}`;

        // informacion basica alumno
        this.fotoAlumnoField = By.id('fotoAlumno');
        this.matriculaAlumnoField = By.id('matricula');
        this.nombreAlumnoField = By.id('matricula');
        this.curpAlumnoField = By.id('curp');
        this.nombreAlumnoField = By.id('nombre');
        this.apellidoPaternoAlumnoField = By.id('apellidoPaterno');
        this.apellidoMaternoAlumnoField = By.id('apellidoMaterno');
        this.fechaNacimientoAlumnoField = By.id('fechaNacimiento');
        this.nssAlumnoField = By.id('nss');
        this.polizaAlumnoField = By.id('poliza');
        this.condicionEspecialAlumnoCheckbox = By.id('condicionEspecial');
        this.condicionEspecialDescripcionAlumnoField = By.id('condicionDescripcion');

        // documentos del alumno
        this.docActaNacimientoAlumnoField = By.id('actaNacimiento');
        this.docCertificadoSecundariaAlumnoField = By.id('certificadoSecundaria');
        this.docCURPAlumnoField = By.id('documentoCurp');

        // tutor legal del alumno.
        this.nombreTutorField = By.id('tutor_nombre');
        this.apellidoPaternoTutorField = By.id('tutor_apellidoPaterno');
        this.apellidoMaternoTutorField = By.id('tutor_apellidoMaterno');
        this.telefonoTutorField = By.id('tutor_telefono');
        this.fechaNacimientoTutorField = By.id('tutor_fechaNacimiento');

        this.registrarAlumnoBtn = By.css('button[type="submit"]');
    }

    async open() {
        await this.driver.get(this.url);
    }

    /*
    async login(email, password) {
        // 1. Espera a que el campo de email aparezca y sea visible/interactuable
        await this.driver.wait(until.elementLocated(this.emailField), 5000, 'El campo de email no apareció en 5 segundos');
        // Nota: findElement(this.emailField) ya te da el elemento, no necesitas pasarlo como parámetro a findElement
        await this.driver.wait(until.elementIsVisible(this.driver.findElement(this.emailField)), 5000, 'El campo de email no es visible en 5 segundos');

        // 2. Interacción: usa los campos definidos
        await this.driver.findElement(this.emailField).sendKeys(email);
        await this.driver.findElement(this.passwordField).sendKeys(password);
        await this.driver.findElement(this.loginButton).click();
    }

    async llenarInformacionBasicaAlumno(matricula, curp, nombres, apellidoPaterno, apellidoMaterno) {
        await this.driver.findElement(this.matriculaAlumnoField).sendKeys(matricula);
        await this.driver.findElement(this.curpAlumnoField).sendKeys(curp);
        await this.driver.findElement(this.nombreAlumnoField).sendKeys(nombres);
        await this.driver.findElement(this.apellidoPaternoAlumnoField).sendKeys(apellidoPaterno);
        await this.driver.findElement(this.apellidoMaternoAlumnoField).sendKeys(apellidoMaterno);
    }*/

    async asignarMatriculaAlumno(matricula) {
        await this.driver.findElement(this.matriculaAlumnoField).sendKeys(matricula);
    }

    async asignarCURPAlumno(curp) {
        await this.driver.findElement(this.curpAlumnoField).sendKeys(curp);
    }

    async asignarNombreAlumno(nombre) {
        await this.driver.findElement(this.nombreAlumnoField).sendKeys(nombre);
    }

    async asignarApellidoPaternoAlumno(apellidoPaterno) {
        await this.driver.findElement(this.apellidoPaternoAlumnoField).sendKeys(apellidoPaterno);
    }

    async asignarApellidoMaternoAlumno(apellidoMaterno) {
        await this.driver.findElement(this.apellidoMaternoAlumnoField).sendKeys(apellidoMaterno);
    }

    async asignarFechaNacimientoAlumno(fechaNacimiento) {
        await this.driver.findElement(this.fechaNacimientoAlumnoField).sendKeys(fechaNacimiento);
    }

    async asignarNSSAlumno(nss) {
        await this.driver.findElement(this.nssAlumnoField).sendKeys(nss);
    }

    /**
     * Espera un tiempo determinado por una alerta "alert()" de JavaScript.
     * @param timeout
     * @returns {Promise<!Promise<string>|null>}
     */
    async waitForAlert(timeout = 5000) {
        // ... (rest of the code is fine)
        try {
            // Espera hasta que aparezca un alert
            await this.driver.wait(until.alertIsPresent(), timeout);

            // Cambia el foco al alert
            const alert = await this.driver.switchTo().alert();

            // Lee el texto del alert (puede variar)
            const text = await alert.getText();

            // Cierra el alert (aceptar)
            await alert.accept();

            // Devuelve el texto por si lo necesitas en el test
            return text;
        } catch (err) {
            //console.error('[!] No apareció ningún alert dentro del tiempo esperado');
            return null;
        }
    }

    /**
     * Obtiene el mensaje de validación HTML5 de un campo.
     * Esto funciona para inputs con required, type="email", etc.
     */
    async getNativeValidationError(locator) {
        const element = await this.driver.findElement(locator);
        const validationMessage = await this.driver.executeScript(
            // El script usa el API de validación nativa del elemento DOM
            'return arguments[0].validationMessage;',
            element // Pasa el elemento de Selenium como argumento[0]
        );

        return validationMessage;
    }

    /**
     * Intercepta la funcion nativa de XMLHttpRequest del navegador para hacer que las peticiones
     * se pierdan y cause un error con el servidor (500). Simulando un fallo de conexion.
     */
    async setupConnectionRefusedMock() {
        await this.driver.executeScript(`
      if (window.XMLHttpRequest) {
          // Guardamos la función original (no crítico para este caso, pero buena práctica)
          const originalXHRSend = XMLHttpRequest.prototype.send;
          
          XMLHttpRequest.prototype.send = function() {
              // 1. Opcional: Ejecuta el handler de error si existe
              if (this.onerror) {
                  const fakeErrorEvent = new Event('error');
                  this.onerror(fakeErrorEvent);
              }

              // 2. Simular un estado de conexión fallida para el frontend.
              // El error de red puro a menudo ocurre antes de que la petición se envíe realmente.
              // Ponemos el readyState en 4 (DONE) y status 0 para simular "No se pudo conectar".
              Object.defineProperty(this, 'readyState', { value: 4 });
              Object.defineProperty(this, 'status', { value: 0 });

              // 3. Simular que la petición terminó con error (llamando a onLoadEnd o onError)
              if (this.onloadend) {
                  this.onloadend(); 
              }
              
              console.log('Interceptado XHR. Forzando fallo de conexión.');
              // No llamamos a originalXHRSend.apply(this, arguments);
              // para evitar que la petición real salga.
          };
          
          console.log('Función XMLHttpRequest.send SOBRESCRITA para fallar.');
      } else {
          console.warn('XMLHttpRequest no disponible globalmente, el mock falló.');
      }
    `);
    }
}

module.exports = { RegistrarAlumnoPage };