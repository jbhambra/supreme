var utils = ( function () {
  var exports = {}

  exports.generateId = ( function() {
    var ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var ID_LENGTH = 8

    return function() {
      var rtn = ''
      for ( var i = 0; i < ID_LENGTH; i++ ) {
        rtn += ALPHABET.charAt( Math.floor( Math.random() * ALPHABET.length ) )
      }
      return rtn
    }
  } )()

  return exports
} )()
