$('input').change((event)=>{
	if(event.target.value <= 0){
		$('#snackbar').text("Non si possono inserire quantita' inferiori ad 1.");
		console.log(event.target.value);
		myFunction();
		event.target.value = 1;
	}
		let id = (event.target.id).slice(11,event.target.id.lenght);
		let quantita = (event.target.value);
		let idPrezzo = "prezzoProdotto"+id;
		$.ajax({
			url: 'Carrello',
			data: {
				id,
				quantita,
			},
			error: () => console.log('Errore AJAX Carrello'),
			success: (responseText) => {
				let prezzi = responseText.split(" ");
				let valueIniziale = prezzi[3];
				let totale = prezzi[2];
				let value = prezzi[1]
				let prodotto = prezzi[0];
				$('#prezzoTotale').text(totale + " Euro");
				$('#'+idPrezzo).text(prodotto + " Euro");
				if(value < valueIniziale){
					console.log(value);
					$('#snackbar').text("Il massimo disponibile nel magazzino e' " + value);
				}
				
				myFunction();
				event.target.value = value;
			}
		})
});

function myFunction() {
	  // Get the snackbar DIV
	  var x = document.getElementById("snackbar");

	  // Add the "show" class to DIV
	  x.className = "show";

	  // After 3 seconds, remove the show class from DIV
	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	  }