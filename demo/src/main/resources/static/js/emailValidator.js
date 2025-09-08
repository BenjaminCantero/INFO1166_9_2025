/**
 * Valida un correo electronico usando una expresion regular simple.
 * @param {string} email
 * @returns {boolean}
 */
function validarEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}