import requests
import json

URL_BASE = "http://localhost:8080"

SEMESTRES = [
        "Primer_Semestre",
        "Segundo_Semestre",
        "Tercer_Semestre",
        "Cuarto_Semestre",
        "Quinto_Semestre",
        "Sexto_Semestre"
]

AREAS_PROPEDEUTICAS = []
CARRERAS_TECNICAS   = []
MATERIAS            = []
DOCENTES            = []

CICLOS_ESCOLARES_URL    = f"{URL_BASE}/ciclos-escolares"
MATERIAS_URL            = f"{URL_BASE}/materias"
AREAS_PROPEDEUTICAS_URL = f"{URL_BASE}/areas-propedeuticas"
CARRERAS_URL            = f"{URL_BASE}/carreras-tecnicas"
DOCENTES_URL            = f"{URL_BASE}/docentes"

def mostrar_semestres():
    print("\n\n---[SEMESTRES]---")
    i = 0
    for sem in SEMESTRES:
        print(f"{i}. {sem}")
        i += 1

def mostrar_carreras():
    print("\n\n---[CARRERAS TECNICAS]---")
    i = 0
    for carrera in CARRERAS_TECNICAS:
        print(f"{i}. {carrera["nombre"]}")
        i += 1

def mostrar_areas():
    print("\n\n---[AREAS PROPEDEUTICAS]---")
    i = 0
    for area in AREAS_PROPEDEUTICAS:
        print(f"{i}. {area["nombre"]}")
        i += 1

def mostrar_materias():
    print("\n\n---[MATERIAS]---")
    i = 0
    for mat in MATERIAS:
        print(f"{i}. {mat["nombre"]}")
        i += 1

def mostrar_docentes():
    """Muestra la lista de docentes disponibles."""
    print("\n\n---[DOCENTES QUE IMPARTEN LA MATERIA]---")
    if not DOCENTES:
        print("No se encontraron docentes para la materia seleccionada.")
        return
    i = 0
    for docente in DOCENTES:
        # Asumiendo que el DTO del docente tiene 'nombre' y 'apellidoPaterno'
        nombre_completo = f"{docente.get('nombre', '')} {docente.get('apellidoPaterno', '')}"
        print(f"{i}. {nombre_completo.strip()}")
        i += 1


def obtener_materias(grado_idx=None, carrera_id=None, area_id=None):
    """
    Obtiene materias construyendo la URL con los filtros proporcionados.
    Usa None para indicar que un filtro no debe aplicarse.
    """
    global MATERIAS
    url = MATERIAS_URL
    
    print()

    if grado_idx is not None and 0 <= grado_idx < len(SEMESTRES):
        url += f"/grado/{SEMESTRES[grado_idx]}"
        
        if carrera_id is not None and carrera_id > 0:
            url += f"/carrera/{carrera_id}"
            
            if area_id is not None and area_id > 0:
                url += f"/area/{area_id}"

    elif area_id is not None and area_id > 0 and grado_idx is None and carrera_id is None:
         url += f"/area/{area_id}"

    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        MATERIAS = data
    else:
        print(f"[=!=] Error al obtener las materias. Código: {response.status_code}")
        print(f"Respuesta: {response.text}") 
        MATERIAS = [] 

def obtener_docentes_por_materia(materia_id): # <-- NUEVA FUNCIÓN
    """Obtiene los docentes calificados para impartir una materia específica."""
    global DOCENTES
    url = f"{DOCENTES_URL}/materia/{materia_id}"
    
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        DOCENTES = data
    else:
        print(f"[=!=] No se pudieron obtener los docentes. Código: {response.status_code}")
        print(f"Respuesta del servidor: {response.text}")
        DOCENTES = []

def obtener_areas_propedeuticas():
    global AREAS_PROPEDEUTICAS
    url = AREAS_PROPEDEUTICAS_URL
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        #print(data)
        AREAS_PROPEDEUTICAS = data
    else:
        print("[=!=] No se pudieron obtener las areas propedeuticas.")
        exit(1)

def obtener_carreras_tecnicas():
    global CARRERAS_TECNICAS
    url = CARRERAS_URL
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        #print(data)
        CARRERAS_TECNICAS = data
    else:
        print("[=!=] No se pudieron obtener las carreras tecnicas.")
        exit(1)


obtener_areas_propedeuticas()
obtener_carreras_tecnicas();

mostrar_semestres()
mostrar_carreras()
mostrar_areas()

grado = int(input('Escribe el grado (0,1,2,etc): '))

obtener_materias(grado, 0, 0)
mostrar_materias()

if MATERIAS:
    indice_materia = int(input('Escribe el índice de la materia para ver sus docentes: '))
    if 0 <= indice_materia < len(MATERIAS):
        materia_seleccionada = MATERIAS[indice_materia]
        materia_id = materia_seleccionada['id']
       
        obtener_docentes_por_materia(materia_id)
        mostrar_docentes()
    else:
        print("[=!=] Índice de materia no válido.")

