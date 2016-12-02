var assert = chai.assert;

describe( "app", function() {

  it( "santiy", function() {
    var text = index.buildGreeting()
    assert.equal( text, "hello, world!" )
  } )

} )

