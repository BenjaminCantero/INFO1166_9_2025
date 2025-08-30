
-- =====================================
-- V1__init.sql - Creación de tablas
-- =====================================

-- Rubros
CREATE TABLE IF NOT EXISTS rubros (
    id_rubros BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_rubro VARCHAR(255)
);

-- Tipos de Contrato
CREATE TABLE IF NOT EXISTS tipos_contrato (
    id_tipo_contrato BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(255)
);

-- Empresas
CREATE TABLE IF NOT EXISTS empresas (
    id_empresa BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(255),
    id_rubro BIGINT,
    CONSTRAINT fk_empresas_rubro FOREIGN KEY (id_rubro) REFERENCES rubros(id_rubros)
);

-- Estado de Estudio
CREATE TABLE IF NOT EXISTS estado_estudio (
    id_estado_estudio BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255)
);

-- Carreras
CREATE TABLE IF NOT EXISTS carreras (
    id_carrera BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_carrera VARCHAR(255)
);

-- Instituciones
CREATE TABLE IF NOT EXISTS instituciones (
    id_institucion BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_institucion VARCHAR(255)
);

-- Datos Personales
CREATE TABLE IF NOT EXISTS datos_personales (
    id_datos_personales BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    rut VARCHAR(50) UNIQUE,
    fecha_nacimiento DATE,
    sexo VARCHAR(50),
    nacionalidad VARCHAR(50),
    estado_civil VARCHAR(50),
    telefono VARCHAR(50),
    correo VARCHAR(255) UNIQUE,
    direccion VARCHAR(255),
    discapacidad BOOLEAN
);

-- Antecedentes Laborales
CREATE TABLE IF NOT EXISTS antecedentes_laborales (
    id_antecedentes_laborales BIGINT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(255),
    fecha_inicio DATE,
    fecha_fin DATE,
    sueldo DOUBLE,
    motivo VARCHAR(255),
    descripcion VARCHAR(255),
    id_datos_personales BIGINT,
    id_empresa BIGINT,
    id_tipo_contrato BIGINT,
    CONSTRAINT fk_antecedentes_datos_personales FOREIGN KEY (id_datos_personales) REFERENCES datos_personales(id_datos_personales),
    CONSTRAINT fk_antecedentes_empresa FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa),
    CONSTRAINT fk_antecedentes_tipo_contrato FOREIGN KEY (id_tipo_contrato) REFERENCES tipos_contrato(id_tipo_contrato)
);

-- Información Estudios
CREATE TABLE IF NOT EXISTS informacion_estudios (
    id_informacion_estudio BIGINT AUTO_INCREMENT PRIMARY KEY,
    anio_inicio INT,
    anio_fin INT,
    id_datos_personales BIGINT,
    id_institucion BIGINT,
    id_carrera BIGINT,
    id_estado_estudio BIGINT,
    CONSTRAINT fk_infoestudios_datos_personales FOREIGN KEY (id_datos_personales) REFERENCES datos_personales(id_datos_personales),
    CONSTRAINT fk_infoestudios_institucion FOREIGN KEY (id_institucion) REFERENCES instituciones(id_institucion),
    CONSTRAINT fk_infoestudios_carrera FOREIGN KEY (id_carrera) REFERENCES carreras(id_carrera),
    CONSTRAINT fk_infoestudios_estado_estudio FOREIGN KEY (id_estado_estudio) REFERENCES estado_estudio(id_estado_estudio)
);
