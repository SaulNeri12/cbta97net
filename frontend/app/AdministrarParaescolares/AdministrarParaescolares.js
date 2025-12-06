// URL base del API Gateway o Controlador
// Ajusta el puerto si tu backend corre en otro (ej. 8080)
const API_URL = 'http://localhost:8080/paraescolares';

document.addEventListener('DOMContentLoaded', () => {
    cargarParaescolares();
    setupEventListeners();
});

// Variables globales para control de estado
let paraescolarIdEliminar = null;

// ==========================================
// [CU: Consultar] Ref: Diagrama de Secuencia Consultar
// ==========================================
async function cargarParaescolares() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Error al conectar con el servidor');

        const paraescolares = await response.json();
        renderizarTabla(paraescolares);
    } catch (error) {
        console.error('Error:', error);
        alert('No se pudieron cargar las actividades paraescolares.');
    }
}

function renderizarTabla(lista) {
    const tbody = document.getElementById('cuerpoTabla');
    tbody.innerHTML = '';

    lista.forEach(p => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${p.nombre}</td>
            <td>${p.descripcion || '-'}</td>
            <td>${p.horario}</td>
            <td>${p.cupoMaximo}</td>
            <td>
                <button class="btn btn-warning btn-icon" onclick="abrirModalEditar(${p.id})">Editar</button>
                <button class="btn btn-danger btn-icon" onclick="confirmarEliminar(${p.id})">Eliminar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// ==========================================
// [CU: Crear y Modificar] Manejo del Formulario
// ==========================================
function setupEventListeners() {
    // Abrir modal para crear (Ref: SD_Crear Actividad - Botón "Nuevo Paraescolar")
    document.getElementById('btnNuevoParaescolar').addEventListener('click', () => {
        limpiarFormulario();
        document.getElementById('tituloModal').innerText = 'Nueva Actividad Paraescolar';
        document.getElementById('modalFormulario').style.display = 'block';
    });

    // Cerrar modal formulario
    document.getElementById('closeModalForm').addEventListener('click', cerrarModalForm);
    document.getElementById('btnCancelarForm').addEventListener('click', cerrarModalForm);

    // Envio del formulario (Ref: SD_Crear y SD_Modificar - Botón "Guardar")
    document.getElementById('formParaescolar').addEventListener('submit', guardarParaescolar);

    // Modal Eliminación
    document.getElementById('btnCancelarEliminar').addEventListener('click', cerrarModalConfirmacion);
    document.getElementById('btnConfirmarEliminar').addEventListener('click', ejecutarEliminacion);
}

async function guardarParaescolar(event) {
    event.preventDefault();

    // Obtener datos del formulario
    const id = document.getElementById('paraescolarId').value;
    const paraescolarDTO = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        horario: document.getElementById('horario').value,
        cupoMaximo: parseInt(document.getElementById('cupo').value)
    };

    // Validación básica de frontend (Ref: CU Paso 7 - Cupo > 0)
    if (paraescolarDTO.cupoMaximo <= 0) {
        alert("El cupo debe ser mayor a 0");
        return;
    }

    try {
        let response;
        if (id) {
            // [CU: Modificar] Ref: SD_Modificar - PATCH /paraescolares/{id}
            paraescolarDTO.id = parseInt(id);
            response = await fetch(`${API_URL}/${id}`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(paraescolarDTO)
            });
        } else {
            // [CU: Crear] Ref: SD_Crear - POST /paraescolares
            response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(paraescolarDTO)
            });
        }

        if (response.ok) {
            alert(id ? 'Actividad actualizada correctamente' : 'Actividad creada con éxito');
            cerrarModalForm();
            cargarParaescolares(); // Actualizar tabla (Ref: Postcondición 5.1)
        } else {
            const errorData = await response.json();
            // Ref: CU Paso 9 (Nombre duplicado)
            alert('Error: ' + (errorData.message || 'Error al guardar la actividad'));
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error de conexión.');
    }
}

// Función global para ser llamada desde el HTML onclick
window.abrirModalEditar = async (id) => {

    try {



        const response = await fetch(`${API_URL}/${id}`); // Necesitarías endpoint GET /{id} en controller
        const data = await response.json();

        document.getElementById('paraescolarId').value = data.id;
        document.getElementById('nombre').value = data.nombre;
        document.getElementById('descripcion').value = data.descripcion;
        document.getElementById('horario').value = data.horario;
        document.getElementById('cupo').value = data.cupoMaximo;

        document.getElementById('tituloModal').innerText = 'Editar Actividad Paraescolar';
        document.getElementById('modalFormulario').style.display = 'block';

    } catch (error) {
        console.error("Error al cargar datos para editar", error);
    }
};

// ==========================================
// [CU: Eliminar] Ref: SD_Eliminar Actividad
// ==========================================
window.confirmarEliminar = (id) => {
    paraescolarIdEliminar = id;
    document.getElementById('modalConfirmacion').style.display = 'block';
};

async function ejecutarEliminacion() {
    if (!paraescolarIdEliminar) return;

    try {
        // Ref: SD_Eliminar - DELETE /paraescolares/{id}
        const response = await fetch(`${API_URL}/${paraescolarIdEliminar}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('La actividad paraescolar ha sido eliminada con éxito.');
            cerrarModalConfirmacion();
            cargarParaescolares();
        } else {
            // Manejo de error si tiene alumnos inscritos (Ref: Subflujo Eliminar paso 2)
            const errorData = await response.json(); // Si el backend devuelve JSON en error
            alert('Error: ' + (errorData.message || 'No se pudo eliminar la actividad.'));
        }
    } catch (error) {
        console.error(error);
        alert('Error de conexión al intentar eliminar.');
    }
}

// Utilidades Modales
function cerrarModalForm() {
    document.getElementById('modalFormulario').style.display = 'none';
    limpiarFormulario();
}

function cerrarModalConfirmacion() {
    document.getElementById('modalConfirmacion').style.display = 'none';
    paraescolarIdEliminar = null;
}

function limpiarFormulario() {
    document.getElementById('formParaescolar').reset();
    document.getElementById('paraescolarId').value = '';
}

// Cerrar modales si se hace click fuera del contenido
window.onclick = function(event) {
    if (event.target.classList.contains('modal')) {
        cerrarModalForm();
        cerrarModalConfirmacion();
    }
}