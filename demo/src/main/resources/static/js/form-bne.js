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

document.addEventListener('DOMContentLoaded', function() {
    const btnEstudio = document.getElementById('btn-agregar-estudio');
    if (btnEstudio) btnEstudio.onclick = agregarEstudio;
});
// ...existing code...
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

document.addEventListener('DOMContentLoaded', function() {
    const btnAdd = document.getElementById('btn-agregar-experiencia');
    if (btnAdd) btnAdd.onclick = agregarExperienciaLaboral;
});
// ...existing code...