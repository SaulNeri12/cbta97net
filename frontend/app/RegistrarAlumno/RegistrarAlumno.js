document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form-registrar-alumno");
    const statusMessage = document.getElementById("status-message");
    const checkCondicion = document.getElementById("condicionEspecial");
    const campoDescCondicion = document.getElementById("campo-descripcion-condicion");
    const selectTutorAcademico = document.getElementById("tutorAcademico");

    // Lógica para mostrar/ocultar descripción de condición
    checkCondicion.addEventListener("change", () => {
        if (checkCondicion.checked) {
            campoDescCondicion.style.display = "block";
        } else {
            campoDescCondicion.style.display = "none";
            document.getElementById("condicionDescripcion").value = ""; // Limpiar si se desmarca
        }
    });

    // Cargar docentes en el select
    async function cargarTutoresAcademicos() {
        try {
            // Asumimos que cualquier docente puede ser tutor
            const response = await fetch("http://localhost:8080/docentes/materia/0"); // Se usa /materia/0 como truco para traer todos, idealmente sería un endpoint /docentes
            if (!response.ok) throw new Error("No se pudieron cargar los docentes");
            
            const docentes = await response.json();
            
            docentes.forEach(docente => {
                const option = document.createElement("option");
                option.value = docente.id;
                option.textContent = `${docente.nombre} ${docente.apellidoPaterno} ${docente.apellidoMaterno}`;
                selectTutorAcademico.appendChild(option);
            });

        } catch (error) {
            console.error(error);
            selectTutorAcademico.innerHTML = '<option value="">Error al cargar docentes</option>';
        }
    }
    
    // Descomentar cuando el endpoint /docentes/materia/0 (o /docentes) esté listo
    // cargarTutoresAcademicos();

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        
        const btn = document.getElementById("btn-registrar");
        btn.disabled = true;
        btn.textContent = "Registrando...";
        
        clearStatus();

        // 1. Recopilar datos del alumno
        const tutorAcademicoId = selectTutorAcademico.value;

        const alumno = {
            matricula: document.getElementById("matricula").value,
            curp: document.getElementById("curp").value.toUpperCase(),
            nombre: document.getElementById("nombre").value,
            apellidoPaterno: document.getElementById("apellidoPaterno").value,
            apellidoMaterno: document.getElementById("apellidoMaterno").value,
            fechaNacimiento: document.getElementById("fechaNacimiento").value,
            
            // Nuevos campos
            nombreTutor: document.getElementById("nombreTutor").value || null,
            tutorAcademico: tutorAcademicoId ? { id: parseInt(tutorAcademicoId) } : null,
            numeroSeguroSocial: document.getElementById("nss").value || null,
            numeroPolizaSeguro: document.getElementById("poliza").value || null,
            condicionEspecial: document.getElementById("condicionEspecial").checked,
            condicionEspecialDescripcion: document.getElementById("condicionDescripcion").value || null
        };

        const actaFile = document.getElementById("actaNacimiento").files[0];
        const certFile = document.getElementById("certificadoSecundaria").files[0];
        const curpFile = document.getElementById("documentoCurp").files[0]; // <-- Nuevo archivo

        if (!actaFile || !certFile || !curpFile) {
            showStatus("Debe adjuntar los 3 documentos requeridos.", "error");
            btn.disabled = false;
            btn.textContent = "Registrar Alumno";
            return;
        }

        try {
            // 2. Registrar los datos del alumno
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
            showStatus(`Alumno ${matricula} registrado. Subiendo documentos...`, "success");

            // 3. Subir documentos (ahora 3)
            await uploadDocument(matricula, actaFile, "ACTA_NACIMIENTO");
            showStatus(`Acta de nacimiento subida...`, "success");

            await uploadDocument(matricula, certFile, "CERTIFICADO_SECUNDARIA");
            showStatus(`Certificado de secundaria subido...`, "success");

            await uploadDocument(matricula, curpFile, "CURP"); // <-- Nueva subida
            showStatus(`Documento CURP subido. ¡Registro completo!`, "success");

            // 4. Limpiar formulario
            form.reset();
            campoDescCondicion.style.display = "none";
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
        formData.append("tipo", tipo.toUpperCase()); // Asegura mayúsculas

        const response = await fetch(`http://localhost:8080/alumnos/${matricula}/documentos`, {
            method: "POST",
            body: formData
            // No se pone Content-Type, el navegador lo hace solo con FormData
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
    });
});