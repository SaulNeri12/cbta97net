document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form-registrar-alumno");
    const statusMessage = document.getElementById("status-message");
    const checkCondicion = document.getElementById("condicionEspecial");
    const campoDescCondicion = document.getElementById("campo-descripcion-condicion");

    // Ya no se necesita cargar tutores académicos
    // const selectTutorAcademico = document.getElementById("tutorAcademico");

    // --- LÓGICA PARA VISTA PREVIA DE FOTO ---
    const fotoInput = document.getElementById("fotoAlumno");
    const fotoPreview = document.getElementById("fotoPreview");
    const placeholderImage = "../resources/placeholder-user.webp";

    fotoInput.addEventListener("change", () => {
        const file = fotoInput.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                fotoPreview.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            fotoPreview.src = placeholderImage;
        }
    });
    // --- FIN DE LÓGICA DE VISTA PREVIA ---


    // Lógica para mostrar/ocultar descripción de condición
    checkCondicion.addEventListener("change", () => {
        if (checkCondicion.checked) {
            campoDescCondicion.style.display = "block";
        } else {
            campoDescCondicion.style.display = "none";
            document.getElementById("condicionDescripcion").value = "";
        }
    });

    // --- FUNCIÓN CARGAR TUTORES ACADÉMICOS ELIMINADA ---

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const btn = document.getElementById("btn-registrar");
        btn.disabled = true;
        btn.textContent = "Registrando...";

        clearStatus();

        // --- RECOPILACIÓN DE DATOS DEL TUTOR LEGAL ---
        const tutorLegal = {
            nombre: document.getElementById("tutor_nombre").value,
            apellidoPaterno: document.getElementById("tutor_apellidoPaterno").value,
            apellidoMaterno: document.getElementById("tutor_apellidoMaterno").value,
            telefono: document.getElementById("tutor_telefono").value,
            fechaNacimiento: document.getElementById("tutor_fechaNacimiento").value || null
        };

        // 1. Recopilar datos del alumno (DTO)
        const alumno = {
            matricula: document.getElementById("matricula").value,
            curp: document.getElementById("curp").value.toUpperCase(),
            nombre: document.getElementById("nombre").value,
            apellidoPaterno: document.getElementById("apellidoPaterno").value,
            apellidoMaterno: document.getElementById("apellidoMaterno").value,
            fechaNacimiento: document.getElementById("fechaNacimiento").value,

            numeroSeguroSocial: document.getElementById("nss").value || null,
            numeroPolizaSeguro: document.getElementById("poliza").value || null,

            condicionEspecial: document.getElementById("condicionEspecial").checked,
            condicionEspecialDescripcion: document.getElementById("condicionDescripcion").value || null,

            tutorLegal: tutorLegal, // <-- Objeto TutorDTO anidado
            // tutorAcademicoId ya no se envía
        };

        // --- RECOPILAR TODOS LOS ARCHIVOS ---
        const fotoFile = document.getElementById("fotoAlumno").files[0];
        const actaFile = document.getElementById("actaNacimiento").files[0];
        const certFile = document.getElementById("certificadoSecundaria").files[0];
        const curpFile = document.getElementById("documentoCurp").files[0];

        if (!fotoFile || !actaFile || !certFile || !curpFile) {
            showStatus("Debe adjuntar la foto y los 3 documentos requeridos.", "error");
            btn.disabled = false;
            btn.textContent = "Registrar Alumno";
            return;
        }

        try {
            // 2. Registrar los datos del alumno (esto también creará al tutor)
            const alumnoResponse = await fetch("http://localhost:8080/alumnos", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(alumno)
            });

            if (!alumnoResponse.ok) {
                const err = await alumnoResponse.json();
                throw new Error(err.error || "Error al registrar los datos del alumno.");
            }

            const alumnoCreado = await alumnoResponse.json();
            const matricula = alumnoCreado.matricula;
            showStatus(`Alumno ${matricula} registrado. Subiendo archivos...`, "success");

            // 3. Subir todos los documentos (4 archivos)
            await uploadDocument(matricula, fotoFile, "FOTO_ALUMNO"); // <--- TIPO CAMBIADO
            showStatus(`Foto de perfil subida...`, "success");

            await uploadDocument(matricula, actaFile, "ACTA_NACIMIENTO");
            showStatus(`Acta de nacimiento subida...`, "success");

            await uploadDocument(matricula, certFile, "CERTIFICADO_SECUNDARIA");
            showStatus(`Certificado de secundaria subido...`, "success");

            await uploadDocument(matricula, curpFile, "CURP");
            showStatus(`Documento CURP subido. ¡Registro completo!`, "success");

            // 4. Limpiar formulario
            form.reset();
            campoDescCondicion.style.display = "none";
            fotoPreview.src = placeholderImage;
            btn.disabled = false;
            btn.textContent = "Registrar Alumno";

        } catch (error) {
            console.error("Error en el registro:", error);
            showStatus(error.message, "error");
            btn.disabled = false;
            btn.textContent = "Registrar Alumno";
        }
    });

    async function uploadDocument(matricula, file, tipo) {
        const formData = new FormData();
        formData.append("file", file);
        formData.append("tipo", tipo.toUpperCase());

        const response = await fetch(`http://localhost:8080/alumnos/${matricula}/documentos`, {
            method: "POST",
            body: formData
        });

        if (!response.ok) {
            const err = await response.json();
            throw new Error(err.error || `Error al subir ${tipo}`);
        }
    }

    function showStatus(message, type) {
        statusMessage.textContent = message;
        statusMessage.className = `status-message status-${type}`;
        statusMessage.style.display = "block";
    }

    function clearStatus() {
        statusMessage.textContent = "";
        statusMessage.style.display = "none";
    }

    document.getElementById("btn-cancelar").addEventListener("click", (e) => {
        e.preventDefault();
        form.reset();
        clearStatus();
        campoDescCondicion.style.display = "none";
        fotoPreview.src = placeholderImage;
    });
});