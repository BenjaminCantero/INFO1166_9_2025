// Configuración del backend
const BACKEND_URL = 'http://localhost:8080/api';

// JS para agregar múltiples estudios dinámicamente en el formulario
function agregarEstudio() {
    const container = document.getElementById('estudios-container');
    const index = container.children.length;
    const template = `
    <div class="form-grid estudio-item">
        <div><label>Nivel educativo</label>
            <select name="nivel_educativo_${index}">
                <option value="">Seleccione...</option>
                <option value="Educación básica">Educación básica</option>
                <option value="Educación media">Educación media</option>
                <option value="Técnico">Técnico</option>
                <option value="Universitario">Universitario</option>
                <option value="Postgrado">Postgrado</option>
            </select>
        </div>
        <div><label>Institución</label><input type="text" name="institucion_${index}"></div>
        <div><label>Carrera/Título</label><input type="text" name="carrera_${index}"></div>
        <div><label>Año inicio</label><input type="number" name="anio_inicio_${index}"></div>
        <div><label>Año fin</label><input type="number" name="anio_fin_${index}"></div>
        <div><label>Estado</label>
            <select name="estado_estudio_${index}">
                <option value="">Seleccione...</option>
                <option value="Completo">Completo</option>
                <option value="Incompleto">Incompleto</option>
                <option value="En curso">En curso</option>
            </select>
        </div>
        <button type="button" onclick="this.parentElement.remove()">Eliminar</button>
    </div>`;
    container.insertAdjacentHTML('beforeend', template);
}

// JS para agregar múltiples experiencias laborales dinámicamente en el formulario
function agregarExperienciaLaboral() {
    const container = document.getElementById('experiencias-laborales');
    const index = container.children.length;
    const template = `
    <div class="form-grid experiencia-laboral">
        <div><label>Empresa</label><input type="text" name="empresa_${index}" required></div>
        <div><label>Cargo</label><input type="text" name="cargo_${index}" required></div>
        <div><label>Rubro</label><input type="text" name="rubro_${index}" required></div>
        <div><label>Fecha inicio</label><input type="date" name="fecha_inicio_${index}" required></div>
        <div><label>Fecha fin</label><input type="date" name="fecha_fin_${index}"></div>
        <div><label>Tipo contrato</label><select name="tipo_contrato_${index}" required><option value="">Seleccione...</option><option value="Plazo fijo">Plazo fijo</option><option value="Indefinido">Indefinido</option><option value="Honorarios">Honorarios</option></select></div>
        <div><label>Sueldo</label><input type="text" name="sueldo_${index}" required></div>
        <div><label>Motivo término</label><input type="text" name="motivo_${index}" required></div>
        <div style="grid-column: span 2;"><label>Descripción</label><textarea name="descripcion_${index}" required></textarea></div>
        <button type="button" onclick="this.parentElement.remove()">Eliminar</button>
    </div>`;
    container.insertAdjacentHTML('beforeend', template);
}

// Función para recopilar los datos del formulario
function recopilarDatosFormulario() {
    // Datos personales básicos
    const datosPersonales = {
        nombres: document.querySelector('[name="nombres"]')?.value || '',
        apellidos: document.querySelector('[name="apellidos"]')?.value || '',
        rut: document.querySelector('[name="rut"]')?.value || '',
        fechaNacimiento: document.querySelector('[name="fecha_nacimiento"]')?.value || '',
        sexo: document.querySelector('[name="sexo"]')?.value || '',
        nacionalidad: document.querySelector('[name="nacionalidad"]')?.value || '',
        estadoCivil: document.querySelector('[name="estado_civil"]')?.value || '',
        telefono: document.querySelector('[name="telefono"]')?.value || '',
        correo: document.querySelector('[name="correo"]')?.value || '',
        direccion: document.querySelector('[name="direccion"]')?.value || '',
        discapacidad: document.querySelector('[name="discapacidad"]')?.value === 'Si' ? 'Si' : 'No'
    };

    // Recopilar estudios
    const estudios = [];
    const estudiosItems = document.querySelectorAll('.estudio-item');
    estudiosItems.forEach((item, index) => {
        const estudio = {
            nivelEducativo: item.querySelector(`[name*="nivel_educativo"]`)?.value || '',
            institucion: item.querySelector(`[name*="institucion"]`)?.value || '',
            carrera: item.querySelector(`[name*="carrera"]`)?.value || '',
            anioInicio: item.querySelector(`[name*="anio_inicio"]`)?.value || '',
            anioFin: item.querySelector(`[name*="anio_fin"]`)?.value || '',
            estadoEstudio: item.querySelector(`[name*="estado_estudio"]`)?.value || ''
        };
        if (estudio.nivelEducativo || estudio.institucion) {
            estudios.push(estudio);
        }
    });

    // Recopilar experiencias laborales
    const experiencias = [];
    const experienciasItems = document.querySelectorAll('.experiencia-laboral');
    experienciasItems.forEach((item, index) => {
        const experiencia = {
            empresa: item.querySelector(`[name*="empresa"]`)?.value || '',
            cargo: item.querySelector(`[name*="cargo"]`)?.value || '',
            rubro: item.querySelector(`[name*="rubro"]`)?.value || '',
            fechaInicio: item.querySelector(`[name*="fecha_inicio"]`)?.value || '',
            fechaFin: item.querySelector(`[name*="fecha_fin"]`)?.value || '',
            tipoContrato: item.querySelector(`[name*="tipo_contrato"]`)?.value || '',
            sueldo: item.querySelector(`[name*="sueldo"]`)?.value || '',
            motivo: item.querySelector(`[name*="motivo"]`)?.value || '',
            descripcion: item.querySelector(`[name*="descripcion"]`)?.value || ''
        };
        if (experiencia.empresa || experiencia.cargo) {
            experiencias.push(experiencia);
        }
    });

    return {
        ...datosPersonales,
        informacionEstudios: estudios,
        antecedentesLaborales: experiencias
    };
}

// Función para enviar datos al backend
async function enviarDatosAlBackend(datos) {
    try {
        console.log('Enviando datos:', datos);
        
        const response = await fetch(`${BACKEND_URL}/datos-personales/guardar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datos)
        });

        const resultado = await response.json();
        
        if (response.ok && resultado.success) {
            alert('✅ Datos enviados correctamente al usuario!');
            console.log('Respuesta del servidor:', resultado);
            
            // Redirigir a la página de confirmación con el ID
            window.location.href = `/confirmacion?id=${resultado.id}`;
        } else {
            alert('❌ Error al guardar: ' + (resultado.message || 'Error desconocido'));
            console.error('Error del servidor:', resultado);
        }
        
    } catch (error) {
        console.error('Error de conexión:', error);
        alert('❌ Error de conexión con el servidor. Verifica que el backend esté ejecutándose en ' + BACKEND_URL);
    }
}

// Función para probar la conexión con el backend
async function probarConexion() {
    try {
        const response = await fetch(`${BACKEND_URL}/datos-personales/test`);
        const resultado = await response.json();
        
        if (response.ok) {
            console.log('✅ Backend conectado:', resultado);
            return true;
        } else {
            console.error('❌ Backend no responde correctamente');
            return false;
        }
    } catch (error) {
        console.error('❌ Error conectando con backend:', error);
        return false;
    }
}

// Event listeners
document.addEventListener('DOMContentLoaded', function() {
    // Botones existentes
    const btnEstudio = document.getElementById('btn-agregar-estudio');
    if (btnEstudio) btnEstudio.onclick = agregarEstudio;
    
    const btnExperiencia = document.getElementById('btn-agregar-experiencia');
    if (btnExperiencia) btnExperiencia.onclick = agregarExperienciaLaboral;
    
    // Manejar envío del formulario - funciona con cualquier form
    const formulario = document.querySelector('form');
    if (formulario) {
        formulario.addEventListener('submit', async function(e) {
            e.preventDefault(); // Prevenir envío tradicional
            
            // Validar email si existe
            const correo = document.querySelector('[name="correo"]')?.value;
            if (correo && !validarEmail(correo)) {
                alert('Por favor ingresa un correo electrónico válido');
                return;
            }
            
            // Recopilar y enviar datos
            const datos = recopilarDatosFormulario();
            await enviarDatosAlBackend(datos);
        });
        
        console.log('📝 Formulario encontrado y listener agregado');
    } else {
        console.error('❌ No se encontró el formulario');
    }
    
    // Probar conexión al cargar la página
    probarConexion().then(conectado => {
        if (conectado) {
            console.log('🟢 Sistema conectado correctamente');
        } else {
            console.warn('🟡 Verificar que el backend esté ejecutándose en http://localhost:8080');
        }
    });
});