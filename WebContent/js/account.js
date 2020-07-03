$.ajax({
		url: "Account",
		dataType: 'json',
		error : () => console.log("Errore Ajax"),
		success : (data) => {
			let account = data;
			let profilo = "";
			for(prop in account){
				profilo += "<p><b>"+prop+": </b>"+account[prop] + "</p>\n";
			}
			$('#profilo').append(profilo);
		}
	});
