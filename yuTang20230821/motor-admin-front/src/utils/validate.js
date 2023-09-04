/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  return /^[\w]{4,16}$/.test(str)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validPwd(str) {
  return /^[\w]{6,16}$/.test(str)
}
