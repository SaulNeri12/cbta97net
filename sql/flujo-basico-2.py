import requests
import json

URL_BASE = "http://localhost:8080"

# --- Listas Globales ---
SEMESTRES = [
        "Primer_Semestre", "Segundo_Semestre", "Tercer_Semestre",
        "Cuarto_Semestre", "Quinto_Semestre", "Sexto_Semestre"
]
AREAS_PROPEDEUTICAS = []
CARRERAS_TECNICAS   = []
MATERIAS            = []
DOCENTES            = []
AULAS               = []
CICLO_ESCOLAR_ACTIVO_ID = None # <-- NUEVA VARIABLE GLOBAL

# --- URLs ---
CICLOS_ESCOLARES_URL    = f"{URL_BASE}/ciclos-escolares" # <-- URL para ciclos
MATERIAS_URL            = f"{URL_BASE}/materias"
AREAS_PROPEDEUTICAS_URL = f"{URL_BASE}/areas-propedeuticas"
CARRERAS_URL            = f"{URL_BASE}/carreras-tecnicas"
DOCENTES_URL            = f"{URL_BASE}/docentes"
AULAS_URL               = f"{URL_BASE}/aulas"
GRUPOS_URL              = f"{URL_BASE}/grupos"

# --- Funciones para Mostrar (sin cambios) ---
def mostrar_semestres():
    print("\n--- SEMESTRES ---")
    for i, sem in enumerate(SEMESTRES):
        print(f"{i}. {sem}")

def mostrar_carreras():
    print("\n--- CARRERAS TÉCNICAS ---")
    for i, carrera in enumerate(CARRERAS_TECNICAS):
        print(f"{i}. {carrera['nombre']}")

def mostrar_areas():
    print("\n--- ÁREAS PROPEDÉUTICAS ---")
    for i, area in enumerate(AREAS_PROPEDEUTICAS):
        print(f"{i}. {area['nombre']}")

def mostrar_materias():
    print("\n--- MATERIAS DISPONIBLES ---")
    if not MATERIAS: print("No se encontraron materias.")
    for i, mat in enumerate(MATERIAS):
        print(f"{i}. {mat['nombre']}")

def mostrar_docentes():
    print("\n--- DOCENTES DISPONIBLES ---")
    if not DOCENTES: print("No se encontraron docentes.")
    for i, docente in enumerate(DOCENTES):
        nombre_completo = f"{docente.get('nombre', '')} {docente.get('apellidoPaterno', '')}"
        print(f"{i}. {nombre_completo.strip()}")

def mostrar_aulas():
    print("\n--- AULAS DISPONIBLES ---")
    if not AULAS: print("No se encontraron aulas.")
    for i, aula in enumerate(AULAS):
        print(f"{i}. {aula['clave']}")

# --- Funciones para Obtener Datos (GET) ---

def obtener_ciclo_escolar_activo(): # <-- NUEVA FUNCIÓN
    """Obtiene el ciclo escolar activo y almacena su ID globalmente."""
    global CICLO_ESCOLAR_ACTIVO_ID
    url = f"{CICLOS_ESCOLARES_URL}/activo"
    print(f"Verificando ciclo escolar activo en: {url}")
    try:
        response = requests.get(url)
        response.raise_for_status()
        ciclo = response.json()
        CICLO_ESCOLAR_ACTIVO_ID = ciclo.get('id')
        if CICLO_ESCOLAR_ACTIVO_ID:
            print(f"--> Ciclo Escolar Activo encontrado. ID: {CICLO_ESCOLAR_ACTIVO_ID}")
        else:
            print("[=!=] No se encontró un ID en la respuesta del ciclo activo.")
            exit(1)
    except requests.exceptions.RequestException as e:
        print(f"[=!=] ERROR: No se pudo obtener el ciclo escolar activo. {e}")
        print("Asegúrate de que haya un ciclo activo en la base de datos y que el endpoint /ciclos-escolares/activo exista.")
        exit(1)


# ... (resto de funciones GET sin cambios) ...


# --- FUNCIÓN POST MODIFICADA ---
def crear_y_enviar_grupo():
    """Función principal para construir el objeto Grupo y enviarlo al servidor."""
    global CICLO_ESCOLAR_ACTIVO_ID
    if not CICLO_ESCOLAR_ACTIVO_ID:
        print("[=!=] No se puede continuar sin un ciclo escolar activo.")
        return

    print("\n=====================================")
    print(f"  CREACIÓN DE GRUPO (Ciclo ID: {CICLO_ESCOLAR_ACTIVO_ID})")
    print("=====================================")

    # 1. Recopilar datos básicos del grupo (se elimina la petición del ciclo_id)
    mostrar_semestres()
    grado_idx = int(input("Selecciona el índice del semestre: "))
    letra = input("Introduce la letra del grupo (ej. A, B): ").upper()
    turno = input("Introduce el turno (MATUTINO o VESPERTINO): ").upper()
    nota = input("Añade una nota para el grupo: ")
    
    # ... (lógica para carrera y área sin cambios) ...

    # 2. Construir la lista de clases (sin cambios)
    clases_para_grupo = []
    # ... (el bucle while para añadir clases se mantiene igual)

    # 3. Construir el payload final del Grupo
    grupo_payload = {
        "nota": nota,
        "letra": letra,
        "activo": True,
        "semestre": SEMESTRES[grado_idx],
        "turno": turno,
        "cicloEscolarId": CICLO_ESCOLAR_ACTIVO_ID, # <-- SE USA LA VARIABLE GLOBAL
        # ... resto del payload
    }

    # 4. Enviar la petición POST (sin cambios)
    # ...


# --- Flujo Principal MODIFICADO ---
print("Cargando datos iniciales...")
obtener_ciclo_escolar_activo() # <-- PRIMER PASO: VERIFICAR CICLO
# ... (resto de las llamadas a obtener_datos)

# Iniciar el proceso de creación de grupo
crear_y_enviar_grupo()
