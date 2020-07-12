let searchParams = new URLSearchParams(window.location.search);
if (searchParams.has('codiceprod')) {
    let productParam = searchParams.get('codiceprod');

    $.ajax({
        url: "ProdottoAjax?codiceprod=" + productParam,
        dataType: 'json',
        error: () => console.log("Errore Ajax"),
        success: (data) => {
            let prodotto = data;
            $('#brand').append('<b>Marca: </b>' + prodotto.caratteristiche.Marca);
            let descrizione = "";

            for (prop in prodotto) {
                if (prop == 'descrizione') {
                    descrizione += prodotto.descrizione + "\n<br>\n";
                }
                if (prop == 'caratteristiche') {
                    for (propCar in prodotto[prop]) {
                        descrizione += "<p><b>" + propCar + ": </b>" + prodotto[prop][propCar] + "</p>\n";
                    }
                }
            }
            $('#descrizione').append(descrizione);
        }
    });
}

/*
{
    "descrizione": "iPhone SE ha lo stesso, straordinario chip di iPhone 11 Pro: si chiama A13 Bionic, ed è il più veloce mai visto su uno smart­phone. Tutto è incredibilmente fluido quando apri le app, ti lanci nei videogiochi e scopri nuovi modi di lavorare e divertirti con la realtà aumentata.",
    "caratteristiche": {
        "RAM": "3GB",
        "Memoria": "128GB",
        "Pollici": "4.7",
        "Processore": "Apple A13 Bionic"
    }
}
*/