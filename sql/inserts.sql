-- drop database cbta97net;
-- create database cbta97net;

-- use cbta97net;

INSERT INTO ciclos_escolares (fecha_inicio, fecha_fin)
VALUES ('2025-08-01', '2025-12-30');

INSERT INTO areas_propedeuticas (nombre, descripcion) VALUES
('Físico-Matemática', 'Área enfocada en las ciencias exactas, la física, las matemáticas y la ingeniería.'),
('Químico-Biológica', 'Área centrada en el estudio de la química, la biología y las ciencias de la salud.'),
('Económico-Administrativa', 'Área relacionada con la economía, administración, contabilidad y finanzas.'),
('Humanidades y Ciencias Sociales', 'Área dedicada al análisis del comportamiento humano y las estructuras sociales.'),
('Artes y Diseño', 'Área enfocada en la creatividad, las artes visuales, la música y el diseño.'),
('Informática y Computación', 'Área que abarca programación, desarrollo de software y tecnologías de la información.'),
('Agropecuaria', 'Área orientada a las ciencias agrícolas, ganaderas y el manejo sustentable del campo.'),
('Industrial', 'Área enfocada en los procesos de producción, manufactura y control de calidad.'),
('Turismo y Gastronomía', 'Área relacionada con los servicios turísticos, hotelería y gastronomía.'),
('Educación y Pedagogía', 'Área dedicada al estudio y aplicación de teorías de la enseñanza y aprendizaje.');

INSERT INTO carreras_tecnicas (nombre, descripcion) VALUES
('Técnico en Soporte y Mantenimiento de Equipo de Cómputo', 
 'Forma profesionales capaces de instalar, diagnosticar y dar mantenimiento preventivo y correctivo a equipos de cómputo, redes y periféricos, garantizando su óptimo funcionamiento.'),

('Técnico en Producción Agrícola', 
 'Prepara al estudiante en el manejo sostenible de cultivos agrícolas, aplicando técnicas modernas de siembra, fertilización, riego y control de plagas para mejorar la productividad del campo.'),

('Técnico en Producción Pecuaria', 
 'Capacita para la crianza, alimentación y manejo sanitario de animales domésticos y de granja, optimizando la producción de carne, leche, huevo y otros derivados.'),

('Técnico Agropecuario', 
 'Integra conocimientos de agricultura y ganadería para el aprovechamiento eficiente de los recursos naturales y el desarrollo sustentable de las unidades de producción rural.'),

('Técnico en Desarrollo Integral Comunitario', 
 'Forma profesionales con habilidades para promover el bienestar social, económico y ambiental en comunidades rurales y urbanas, mediante proyectos participativos y de desarrollo sostenible.');


-- Materias de Primer Semestre
INSERT INTO materias (nombre, grado) VALUES
('Matemáticas I', 'Primer_Semestre'),
('Química I', 'Primer_Semestre'),
('Biología General', 'Primer_Semestre'),
('Comunicación Oral y Escrita', 'Primer_Semestre'),
('Tecnologías de la Información y la Comunicación', 'Primer_Semestre'),
('Inglés I', 'Primer_Semestre'),
('Formación Sociocultural I', 'Primer_Semestre'),
('Introducción a las Ciencias Agropecuarias', 'Primer_Semestre');

-- MATERIAS SEGUNDO SEMESTRE

-- Carrera 1: Técnico en Soporte y Mantenimiento de Equipo de Cómputo
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas II', 'Segundo_Semestre', 1),
('Inglés II', 'Segundo_Semestre', 1),
('Formación Sociocultural II', 'Segundo_Semestre', 1),
('Electricidad y Electrónica Básica', 'Segundo_Semestre', 1),
('Mantenimiento Preventivo de Equipos de Cómputo', 'Segundo_Semestre', 1),
('Sistemas Operativos', 'Segundo_Semestre', 1),
('Ensambles y Desensambles de PC', 'Segundo_Semestre', 1),
('Redes de Computadoras I', 'Segundo_Semestre', 1);


-- Carrera 2: Técnico en Producción Agrícola
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas II', 'Segundo_Semestre', 2),
('Química Agrícola', 'Segundo_Semestre', 2),
('Biología Vegetal', 'Segundo_Semestre', 2),
('Edafología (Ciencia del Suelo)', 'Segundo_Semestre', 2),
('Fisiología Vegetal', 'Segundo_Semestre', 2),
('Maquinaria y Herramientas Agrícolas', 'Segundo_Semestre', 2),
('Propagación de Plantas', 'Segundo_Semestre', 2),
('Riego y Drenaje Agrícola', 'Segundo_Semestre', 2);


-- Carrera 3: Técnico en Producción Pecuaria
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas II', 'Segundo_Semestre', 3),
('Anatomía y Fisiología Animal', 'Segundo_Semestre', 3),
('Nutrición Animal', 'Segundo_Semestre', 3),
('Manejo y Reproducción de Especies Pecuarias', 'Segundo_Semestre', 3),
('Sanidad Animal', 'Segundo_Semestre', 3),
('Producción de Alimentos para el Ganado', 'Segundo_Semestre', 3),
('Prácticas de Campo Pecuario', 'Segundo_Semestre', 3),
('Economía Pecuaria', 'Segundo_Semestre', 3);


-- Carrera 4: Técnico Agropecuario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas II', 'Segundo_Semestre', 4),
('Química del Suelo', 'Segundo_Semestre', 4),
('Zootecnia General', 'Segundo_Semestre', 4),
('Agronomía Básica', 'Segundo_Semestre', 4),
('Ecología y Medio Ambiente', 'Segundo_Semestre', 4),
('Manejo de Recursos Naturales', 'Segundo_Semestre', 4),
('Maquinaria Agropecuaria', 'Segundo_Semestre', 4),
('Prácticas Integrales de Campo', 'Segundo_Semestre', 4);

-- Carrera 5: Técnico en Desarrollo Integral Comunitario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas II', 'Segundo_Semestre', 5),
('Sociología Rural y Urbana', 'Segundo_Semestre', 5),
('Desarrollo Sustentable', 'Segundo_Semestre', 5),
('Comunicación para el Desarrollo', 'Segundo_Semestre', 5),
('Diseño y Gestión de Proyectos Comunitarios', 'Segundo_Semestre', 5),
('Educación Ambiental', 'Segundo_Semestre', 5),
('Economía Social y Solidaria', 'Segundo_Semestre', 5),
('Práctica Comunitaria I', 'Segundo_Semestre', 5);

-- MATERIAS DE TERCER SEMESTRE

-- Carrera 1: Técnico en Soporte y Mantenimiento de Equipo de Cómputo
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas III', 'Tercer_Semestre', 1),
('Inglés III', 'Tercer_Semestre', 1),
('Formación Sociocultural III', 'Tercer_Semestre', 1),
('Electrónica Digital', 'Tercer_Semestre', 1),
('Mantenimiento Correctivo de Equipos de Cómputo', 'Tercer_Semestre', 1),
('Redes de Computadoras II', 'Tercer_Semestre', 1),
('Arquitectura de Computadoras', 'Tercer_Semestre', 1),
('Seguridad y Respaldo de Información', 'Tercer_Semestre', 1);


-- Carrera 2: Técnico en Producción Agrícola
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas III', 'Tercer_Semestre', 2),
('Fertilidad de Suelos', 'Tercer_Semestre', 2),
('Fitotecnia', 'Tercer_Semestre', 2),
('Control de Plagas y Enfermedades Vegetales', 'Tercer_Semestre', 2),
('Topografía y Cartografía Agrícola', 'Tercer_Semestre', 2),
('Manejo de Invernaderos', 'Tercer_Semestre', 2),
('Horticultura', 'Tercer_Semestre', 2),
('Prácticas de Campo Agrícola I', 'Tercer_Semestre', 2);


-- Carrera 3: Técnico en Producción Pecuaria
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas III', 'Tercer_Semestre', 3),
('Fisiología de la Reproducción Animal', 'Tercer_Semestre', 3),
('Manejo de Pastos y Forrajes', 'Tercer_Semestre', 3),
('Producción Bovina', 'Tercer_Semestre', 3),
('Sanidad y Bioseguridad Pecuaria', 'Tercer_Semestre', 3),
('Producción Porcina', 'Tercer_Semestre', 3),
('Economía y Administración Pecuaria', 'Tercer_Semestre', 3),
('Prácticas de Campo Pecuario II', 'Tercer_Semestre', 3);


-- Carrera 4: Técnico Agropecuario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas III', 'Tercer_Semestre', 4),
('Biotecnología Agropecuaria', 'Tercer_Semestre', 4),
('Manejo Integral de Cultivos', 'Tercer_Semestre', 4),
('Producción Animal Sostenible', 'Tercer_Semestre', 4),
('Agroecología', 'Tercer_Semestre', 4),
('Economía y Comercialización Agropecuaria', 'Tercer_Semestre', 4),
('Sistemas de Producción Rural', 'Tercer_Semestre', 4),
('Prácticas Agropecuarias Integradas I', 'Tercer_Semestre', 4);


-- Carrera 5: Técnico en Desarrollo Integral Comunitario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas III', 'Tercer_Semestre', 5),
('Psicología Comunitaria', 'Tercer_Semestre', 5),
('Planeación del Desarrollo Local', 'Tercer_Semestre', 5),
('Gestión Social y Participación Ciudadana', 'Tercer_Semestre', 5),
('Educación para la Salud Comunitaria', 'Tercer_Semestre', 5),
('Evaluación de Proyectos Comunitarios', 'Tercer_Semestre', 5),
('Economía del Desarrollo', 'Tercer_Semestre', 5),
('Práctica Comunitaria II', 'Tercer_Semestre', 5);

-- MATERIAS DE CUARTO SEMESTRE

-- Carrera 1: Técnico en Soporte y Mantenimiento de Equipo de Cómputo
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas IV', 'Cuarto_Semestre', 1),
('Inglés IV', 'Cuarto_Semestre', 1),
('Formación Sociocultural IV', 'Cuarto_Semestre', 1),
('Diagnóstico y Reparación de Fallas en Hardware', 'Cuarto_Semestre', 1),
('Administración de Redes de Computadoras', 'Cuarto_Semestre', 1),
('Instalación y Configuración de Software Especializado', 'Cuarto_Semestre', 1),
('Soporte Técnico a Usuarios', 'Cuarto_Semestre', 1),
('Proyecto Integrador de Mantenimiento de Cómputo', 'Cuarto_Semestre', 1);


-- Carrera 2: Técnico en Producción Agrícola
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas IV', 'Cuarto_Semestre', 2),
('Agrometeorología', 'Cuarto_Semestre', 2),
('Cultivos Básicos y Estratégicos', 'Cuarto_Semestre', 2),
('Manejo Integrado de Plagas y Enfermedades', 'Cuarto_Semestre', 2),
('Producción en Ambientes Controlados', 'Cuarto_Semestre', 2),
('Postcosecha y Comercialización de Productos Agrícolas', 'Cuarto_Semestre', 2),
('Agroindustria de Transformación', 'Cuarto_Semestre', 2),
('Prácticas de Campo Agrícola II', 'Cuarto_Semestre', 2);


-- Carrera 3: Técnico en Producción Pecuaria
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas IV', 'Cuarto_Semestre', 3),
('Producción Avícola', 'Cuarto_Semestre', 3),
('Producción Caprina y Ovina', 'Cuarto_Semestre', 3),
('Procesamiento de Productos Pecuarios', 'Cuarto_Semestre', 3),
('Administración de Unidades de Producción Pecuaria', 'Cuarto_Semestre', 3),
('Mejoramiento Genético Animal', 'Cuarto_Semestre', 3),
('Sanidad y Bienestar Animal', 'Cuarto_Semestre', 3),
('Prácticas de Campo Pecuario III', 'Cuarto_Semestre', 3);


-- Carrera 4: Técnico Agropecuario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas IV', 'Cuarto_Semestre', 4),
('Producción Sustentable de Cultivos', 'Cuarto_Semestre', 4),
('Producción Animal Intensiva', 'Cuarto_Semestre', 4),
('Manejo de Recursos Hidráulicos', 'Cuarto_Semestre', 4),
('Economía Rural y Mercados Agropecuarios', 'Cuarto_Semestre', 4),
('Gestión Ambiental Agropecuaria', 'Cuarto_Semestre', 4),
('Innovación Tecnológica en el Campo', 'Cuarto_Semestre', 4),
('Prácticas Agropecuarias Integradas II', 'Cuarto_Semestre', 4);


-- Carrera 5: Técnico en Desarrollo Integral Comunitario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas IV', 'Cuarto_Semestre', 5),
('Intervención Comunitaria', 'Cuarto_Semestre', 5),
('Diseño de Estrategias de Desarrollo', 'Cuarto_Semestre', 5),
('Gestión de Recursos y Financiamiento Social', 'Cuarto_Semestre', 5),
('Cultura y Diversidad Comunitaria', 'Cuarto_Semestre', 5),
('Liderazgo y Trabajo en Equipo', 'Cuarto_Semestre', 5),
('Evaluación del Impacto Social de Proyectos', 'Cuarto_Semestre', 5),
('Práctica Comunitaria III', 'Cuarto_Semestre', 5);


-- MATERIAS DE QUINTO SEMESTRE

-- Carrera 1: Técnico en Soporte y Mantenimiento de Equipo de Cómputo
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas V', 'Quinto_Semestre', 1),
('Inglés V', 'Quinto_Semestre', 1),
('Formación Sociocultural V', 'Quinto_Semestre', 1),
('Mantenimiento de Equipos Portátiles y Dispositivos Móviles', 'Quinto_Semestre', 1),
('Administración de Servidores', 'Quinto_Semestre', 1),
('Redes Inalámbricas y Conectividad Avanzada', 'Quinto_Semestre', 1),
('Automatización y Control con Microcontroladores', 'Quinto_Semestre', 1),
('Proyecto Integrador de Redes y Soporte', 'Quinto_Semestre', 1);


-- Carrera 2: Técnico en Producción Agrícola
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas V', 'Quinto_Semestre', 2),
('Cultivos de Exportación', 'Quinto_Semestre', 2),
('Producción Orgánica y Sustentable', 'Quinto_Semestre', 2),
('Administración de Empresas Agropecuarias', 'Quinto_Semestre', 2),
('Gestión de la Calidad e Inocuidad Agroalimentaria', 'Quinto_Semestre', 2),
('Agroindustria y Valor Agregado', 'Quinto_Semestre', 2),
('Comercialización y Mercadotecnia Agrícola', 'Quinto_Semestre', 2),
('Prácticas de Campo Agrícola III', 'Quinto_Semestre', 2);


-- Carrera 3: Técnico en Producción Pecuaria
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas V', 'Quinto_Semestre', 3),
('Producción Apícola', 'Quinto_Semestre', 3),
('Producción Cunícola y de Especies Menores', 'Quinto_Semestre', 3),
('Procesamiento de Carne y Lácteos', 'Quinto_Semestre', 3),
('Gestión de la Producción Pecuaria', 'Quinto_Semestre', 3),
('Sistemas de Información para la Producción Pecuaria', 'Quinto_Semestre', 3),
('Normatividad y Bienestar Animal', 'Quinto_Semestre', 3),
('Prácticas de Campo Pecuario IV', 'Quinto_Semestre', 3);


-- Carrera 4: Técnico Agropecuario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas V', 'Quinto_Semestre', 4),
('Tecnologías Sustentables de Producción', 'Quinto_Semestre', 4),
('Gestión Empresarial Agropecuaria', 'Quinto_Semestre', 4),
('Biotecnología Aplicada al Campo', 'Quinto_Semestre', 4),
('Innovación y Transferencia de Tecnología', 'Quinto_Semestre', 4),
('Comercialización y Cadenas Productivas', 'Quinto_Semestre', 4),
('Normatividad Ambiental y Agropecuaria', 'Quinto_Semestre', 4),
('Prácticas Agropecuarias Integradas III', 'Quinto_Semestre', 4);


-- Carrera 5: Técnico en Desarrollo Integral Comunitario
INSERT INTO materias (nombre, grado, carrera_tecnica_id) VALUES
('Matemáticas V', 'Quinto_Semestre', 5),
('Desarrollo Económico Local', 'Quinto_Semestre', 5),
('Administración de Proyectos Sociales', 'Quinto_Semestre', 5),
('Educación y Capacitación Comunitaria', 'Quinto_Semestre', 5),
('Gestión Cultural y Patrimonio Comunitario', 'Quinto_Semestre', 5),
('Diseño y Evaluación de Políticas Públicas', 'Quinto_Semestre', 5),
('Comunicación Estratégica para el Desarrollo', 'Quinto_Semestre', 5),
('Práctica Comunitaria IV', 'Quinto_Semestre', 5);

-- MATERIAS DE SEXTO SEMESTRE

-- Carrera 1: Técnico en Soporte y Mantenimiento de Equipo de Cómputo
INSERT INTO materias (nombre, grado, carrera_tecnica_id, area_propedeutica_id) VALUES
('Matemáticas VI', 'Sexto_Semestre', 1, 6),
('Inglés VI', 'Sexto_Semestre', 1, 6),
('Formación Sociocultural VI', 'Sexto_Semestre', 1, 6),
('Virtualización y Computación en la Nube', 'Sexto_Semestre', 1, 6),
('Administración de Seguridad Informática', 'Sexto_Semestre', 1, 6),
('Diagnóstico y Solución de Fallas Avanzadas', 'Sexto_Semestre', 1, 6),
('Soporte Técnico Empresarial', 'Sexto_Semestre', 1, 6),
('Proyecto Integrador de Mantenimiento y Redes', 'Sexto_Semestre', 1, 6);


-- Carrera 2: Técnico en Producción Agrícola
INSERT INTO materias (nombre, grado, carrera_tecnica_id, area_propedeutica_id) VALUES
('Matemáticas VI', 'Sexto_Semestre', 2, 7),
('Producción de Semillas Certificadas', 'Sexto_Semestre', 2, 7),
('Sistemas Agroforestales', 'Sexto_Semestre', 2, 7),
('Manejo Postcosecha y Control de Calidad', 'Sexto_Semestre', 2, 7),
('Gestión Ambiental en la Producción Agrícola', 'Sexto_Semestre', 2, 7),
('Planeación de Proyectos Productivos Agrícolas', 'Sexto_Semestre', 2, 7),
('Innovación y Tecnología Agrícola', 'Sexto_Semestre', 2, 7),
('Prácticas Profesionales Agrícolas', 'Sexto_Semestre', 2, 7);


-- Carrera 3: Técnico en Producción Pecuaria
INSERT INTO materias (nombre, grado, carrera_tecnica_id, area_propedeutica_id) VALUES
('Matemáticas VI', 'Sexto_Semestre', 3, 7),
('Producción de Peces y Acuacultura', 'Sexto_Semestre', 3, 7),
('Procesamiento y Conservación de Productos Pecuarios', 'Sexto_Semestre', 3, 7),
('Gestión Ambiental Pecuaria', 'Sexto_Semestre', 3, 7),
('Administración de la Producción Animal', 'Sexto_Semestre', 3, 7),
('Innovación Tecnológica Pecuaria', 'Sexto_Semestre', 3, 7),
('Proyectos de Emprendimiento Pecuario', 'Sexto_Semestre', 3, 7),
('Prácticas Profesionales Pecuarias', 'Sexto_Semestre', 3, 7);


-- Carrera 4: Técnico Agropecuario
INSERT INTO materias (nombre, grado, carrera_tecnica_id, area_propedeutica_id) VALUES
('Matemáticas VI', 'Sexto_Semestre', 4, 7),
('Gestión Integral de Unidades de Producción', 'Sexto_Semestre', 4, 7),
('Emprendimiento Agropecuario', 'Sexto_Semestre', 4, 7),
('Sistemas Agroecológicos Sustentables', 'Sexto_Semestre', 4, 7),
('Planeación Estratégica en el Campo', 'Sexto_Semestre', 4, 7),
('Innovación y Tecnología Rural', 'Sexto_Semestre', 4, 7),
('Normas de Inocuidad Agroalimentaria', 'Sexto_Semestre', 4, 7),
('Prácticas Profesionales Agropecuarias', 'Sexto_Semestre', 4, 7);


-- Carrera 5: Técnico en Desarrollo Integral Comunitario
INSERT INTO materias (nombre, grado, carrera_tecnica_id, area_propedeutica_id) VALUES
('Matemáticas VI', 'Sexto_Semestre', 5, 4),
('Gestión de Políticas Públicas Locales', 'Sexto_Semestre', 5, 4),
('Evaluación de Programas Comunitarios', 'Sexto_Semestre', 5, 4),
('Mediación y Resolución de Conflictos Sociales', 'Sexto_Semestre', 5, 4),
('Educación para la Transformación Social', 'Sexto_Semestre', 5, 4),
('Sustentabilidad y Responsabilidad Social', 'Sexto_Semestre', 5, 4),
('Proyectos de Innovación Comunitaria', 'Sexto_Semestre', 5, 4),
('Prácticas Profesionales Comunitarias', 'Sexto_Semestre', 5, 4);

INSERT INTO aulas (clave) VALUES 
('AV-100'),
('AV-101'),
('AV-102'),
('AV-103'),
('AV-104'),
('AV-105'),
('AV-106'),
('AV-107'),
('AV-108'),
('AV-109'),
('AV-110'),
('AV-111'),
('AV-112'),
('AV-113'),
('AV-114'),
('AV-115'),
('AV-116'),
('AV-117'),
('AV-118'),
('AV-119'),
('AV-120'),
('AV-121'),
('AV-122'),
('AV-123'),
('AV-124'),
('AV-125'),
('AV-126'),
('AV-127'),
('AV-128'),
('AV-129');


INSERT INTO usuarios (curp, nombre, apellido_paterno, apellido_materno, email, telefono, activo, contrasena)
VALUES
('CURP0001', 'Carlos', 'Gomez', 'Lopez', 'carlos.gomez@cbta97.edu.mx', '5551000001', true, 'pass123'),
('CURP0002', 'Ana', 'Martinez', 'Sanchez', 'ana.martinez@cbta97.edu.mx', '5551000002', true, 'pass123'),
('CURP0003', 'Luis', 'Ramirez', 'Torres', 'luis.ramirez@cbta97.edu.mx', '5551000003', true, 'pass123'),
('CURP0004', 'Marta', 'Hernandez', 'Diaz', 'marta.hernandez@cbta97.edu.mx', '5551000004', true, 'pass123'),
('CURP0005', 'Jose', 'Lopez', 'Gonzalez', 'jose.lopez@cbta97.edu.mx', '5551000005', true, 'pass123'),
('CURP0006', 'Laura', 'Perez', 'Ortiz', 'laura.perez@cbta97.edu.mx', '5551000006', true, 'pass123'),
('CURP0007', 'Miguel', 'Vazquez', 'Rios', 'miguel.vazquez@cbta97.edu.mx', '5551000007', true, 'pass123'),
('CURP0008', 'Sofia', 'Santos', 'Cruz', 'sofia.santos@cbta97.edu.mx', '5551000008', true, 'pass123'),
('CURP0009', 'Diego', 'Morales', 'Mendez', 'diego.morales@cbta97.edu.mx', '5551000009', true, 'pass123'),
('CURP0010', 'Elena', 'Ruiz', 'Alvarez', 'elena.ruiz@cbta97.edu.mx', '5551000010', true, 'pass123');

INSERT INTO docentes (usuario_id, cedula_profesional)
VALUES
(1, 'CED1234561'),
(2, 'CED1234562'),
(3, 'CED1234563'),
(4, 'CED1234564'),
(5, 'CED1234565'),
(6, 'CED1234566'),
(7, 'CED1234567'),
(8, 'CED1234568'),
(9, 'CED1234569'),
(10, 'CED1234570');

INSERT INTO docentes_materias (docente_id, materia_id)
VALUES
-- Docente 1 (Juan Pérez) - Especialista en Matemáticas y Física
(1, 1),  -- Álgebra
(1, 2),  -- Geometría y Trigonometría
(1, 3),  -- Cálculo Diferencial
(1, 4),  -- Cálculo Integral
(1, 5),  -- Probabilidad y Estadística
(1, 14), -- Física I
(1, 19), -- Física II

-- Docente 2 (Ana Gómez) - Especialista en Comunicación y Lenguas
(2, 6),  -- Inglés I
(2, 7),  -- LEOyE I
(2, 11), -- Inglés II
(2, 12), -- LEOyE II
(2, 16), -- Inglés III
(2, 21), -- Inglés IV
(2, 25), -- Inglés V

-- Docente 3 (Carlos Sánchez) - Especialista en Soporte y Mantenimiento / Ofimática
(3, 18), -- Mantenimiento de Hardware
(3, 23), -- Redes de Computadoras
(3, 27), -- Software de Aplicación
(3, 30), -- Sistemas Operativos
(3, 31), -- Hojas de Cálculo Avanzadas
(3, 32), -- Presentaciones Gráficas

-- Docente 4 (María Rodríguez) - Especialista en Ciencias Químico-Biológicas
(4, 8),  -- Química I
(4, 13), -- Química II
(4, 17), -- Ecología
(4, 22), -- Biología

-- Docente 5 (Luis Martínez) - Especialista en Programación y Bases de Datos
(5, 10), -- Algoritmos
(5, 15), -- Programación
(5, 20), -- Bases de Datos
(5, 24), -- Desarrollo de Aplicaciones Web
(5, 28), -- Programación Orientada a Objetos

-- Docente 6 (Laura Pérez) - Especialista en Humanidades y Ciencias Sociales
(6, 9),  -- Ética
(6, 26), -- Ciencias Sociales
(6, 29), -- Temas de Filosofía

-- Docente 7 (Miguel Vázquez) - Docente de Apoyo en Informática y Matemáticas
(7, 1),  -- Álgebra (Puede cubrir)
(7, 18); -- Mantenimiento de Hardware (Puede cubrir)

