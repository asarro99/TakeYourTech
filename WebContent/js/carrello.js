$('input').change((event)=>{
	if(event.target.value <= 0){
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
				let totale = prezzi[2];
				let value = prezzi[1]
				let prodotto = prezzi[0];
				$('#prezzoTotale').text(totale + " Euro");
				$('#'+idPrezzo).text(prodotto + " Euro");
				event.target.value = value;
			}
		})
});



