var index = ( function () {
  var exports = {}

  exports.start = function() {

    console.log( "Main::start" )

    $( "#frm" ).on( "submit", function( event ) {
      var newGreeting = {
        id: utils.generateId(),
        text: $( "#text" ).val()
      }

      var jsonData = JSON.stringify( newGreeting )

      $.post( "/api/greetings", jsonData, function( data ) {
        $( "#greetings" ).append( `<li>${newGreeting.text}</li>` )
      }, "json" )


      event.preventDefault()
    } )

    $.get( "/api/greetings", function( greetings ) {
      $( "#greetings" ).html( greetings.map( function( greeting ) {
        return `<li>${ greeting.text }</li>`
      } ) )
    }, "json" )
  }

  exports.buildGreeting = function() {
    return "hello, world!"
  }

  return exports
} )()
