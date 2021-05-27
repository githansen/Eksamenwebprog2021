function registrer(){
    const pakkeinformasjon = {
        fornavn: $("#fornavn").val(),
        etternavn: $("#etternavn").val(),
        adresse: $("#adresse").val(),
        postnr: $("#postnr").val(),
        telefonnr: $("#telefonnr").val(),
        epost: $("#epost").val(),
        volum: $("#volum").val(),
        vekt: $("#vekt").val()
    }
console.log(validerfornavn(pakkeinformasjon.fornavn))
    console.log(valideretternavn(pakkeinformasjon.etternavn))
    console.log(validerpostnr(pakkeinformasjon.postnr))
    console.log(sjekkpostnr(pakkeinformasjon.postnr))
    if(validerfornavn(pakkeinformasjon.fornavn) && valideretternavn(pakkeinformasjon.etternavn) && validerpostnr(pakkeinformasjon.postnr) && sjekkpostnr(pakkeinformasjon.postnr)){
        console.log("Sjekk")
        $.post("/Lagresending", pakkeinformasjon, function () {
        });
    }

}

function validerfornavn(fornavn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{3,15}$/;
    if(regexp.test(fornavn)){
        $("#fornavnfeil").html("")
        return true;
    }
    else{
        $("#fornavnfeil").html("Fornavn kan kun være bokstaver, mellom 3 og 15")
        return false;
    }
}
function valideretternavn(etternavn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{3,15}$/;
    if(regexp.test(etternavn)){
        $("#etternavnfeil").html("")
        return true;
    }
    else{
        $("#etternavnfeil").html("Etternavn kan kun være bokstaver, mellom 3 og 15")
        return false
    }
}
function validerpostnr(postnr){
    const regexp = /^[0-9]{4}$/;
    if(regexp.test(postnr)){
        $("#postnrfeil").html("")
        return true;
    }
    else{
        $("#postnrfeil").html("Postnr må være 4 siffer")
        return false;
    }
}

function sjekkpostnr(postnr){
    const url = "/Sjekkpostnr?postnr=" + postnr
let result = null;
    $.ajax({
        url: url,
        type: 'get',

        async: false,
        success: function(data){
             result = data
        }
    });
    return result;


}