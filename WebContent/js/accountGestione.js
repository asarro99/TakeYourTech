//FORM
const form = document.getElementById('form-inserimento');
const form2 = document.getElementById('form-modifica');
const form3 = document.getElementById('form-rimuovi');

//INSERIMENTO
const nomeIns = document.getElementById('nome-inserimento');
const categoriaIns = document.getElementById('categoria-inserimento');
const ivaIns = document.getElementById('iva-inserimento');
const descrizioneIns = document.getElementById('descrizione-inserimento');
const prezzoIns = document.getElementById('prezzo-inserimento');
const quantitaIns = document.getElementById('quantita-inserimento');

//MODIFICA
const codiceMod = document.getElementById('codice-modifica');
const nomeMod = document.getElementById('nome-modifica');
const prezzoMod = document.getElementById('prezzo-modifica');
const quantitaMod = document.getElementById('quantita-modifica');

//RIMUOVI
const codiceRim = document.getElementById('codice-rimuovi');

const categorie = ['Alimentatori','Case','Dissipatori','Hard_Disk','Portatili','Processori','RAM','Schede_Madri','Schede_Video','Telefoni'];

const checkFormInserimento = () => {
    const nomevalue = nomeIns.value.trim();
    const categoriaValue = categoriaIns.value.trim();
    const ivaValue = ivaIns.value.trim();
    const descrizioneValue = descrizioneIns.value.trim();
    const prezzoValue = prezzoIns.value.trim();
    const quantitaValue = quantitaIns.value.trim();

    //VALIDITA
    let validitaNome = false;
    let validitaCategoria = false;
    let validitaIva = false;
    let validitaDescrizione = false;
    let validitaPrezzo = false;
    let validitaQuantita = false;

    if(nomevalue === ''){
        validitaNome = false;
        setError(nomeIns, 'Il nome deve essere corretto.');
    }else{
        validitaNome = true;
        setSuccess(nomeIns);
    }

    if(!categorie.includes(categoriaValue)){
        validitaCategoria = false;
        setError(categoriaIns, 'Il campo categoria deve essere corretto');
    }else{
        validitaCategoria = true;
        setSuccess(categoriaIns);
    }

    if(ivaValue === ''){
        validitaIva = false;
        setError(ivaIns, 'L\'iva inserita deve essere corretta');
    }else{
        validitaIva = true;
        setSuccess(ivaIns);
    }

    if(descrizioneValue === ''){
        validitaDescrizione = false;
        setError(descrizioneIns, 'La descrizione deve essere valida');
    }else{
    	validitaDescrizione = true;
        setSuccess(descrizioneIns);
    }

    if(prezzoValue === ''){
        validitaPrezzo = false;
        setError(prezzoIns, 'Il prezzo deve essere valido');
    }else{
        validitaPrezzo = true;
        setSuccess(prezzoIns);
    }

    if(quantitaValue === ''){
        validitaQuantita = false;
        setError(quantitaIns, 'La quantita deve essere valida');
    }else{
        validitaQuantita = true;
        setSuccess(quantitaIns);
    }
    console.log(validitaNome,validitaCategoria,validitaIva,validitaDescrizione,validitaPrezzo,validitaQuantita);
    return validitaNome && validitaCategoria && validitaIva && validitaDescrizione && validitaPrezzo && validitaQuantita;
}

const checkFormModifica = () => {
    const codiceValue = codiceMod.value.trim();
    const nomeValue = nomeMod.value.trim();
    const prezzoValue = prezzoMod.value.trim();
    const quantitaValue = quantitaMod.value.trim();

    //VALIDITA
    let validitaCodice = false;
    let validitaNome = false;
    let validitaPrezzo = false;
    let validitaQuantita = false;

    if(codiceValue === ''){
        validitaCodice = false;
        setError(codiceMod, 'Il codice deve essere corretto.');
    }else{
        validitaCodice = true;
        setSuccess(codiceMod);
    }

    if(nomeValue === ''){
        validitaNome = false;
        setError(nomeMod, 'L\'iva inserita deve essere corretta');
    }else{
        validitaNome = true;
        setSuccess(nomeMod);
    }

    if(prezzoValue === ''){
        validitaPrezzo = false;
        setError(prezzoMod, 'Il prezzo deve essere valido');
    }else{
        validitaPrezzo = true;
        setSuccess(prezzoMod);
    }

    if(quantitaValue === ''){
        validitaQuantita = false;
        setError(quantitaMod, 'La quantita deve essere valida');
    }else{
        validitaQuantita = true;
        setSuccess(quantitaMod);
    }
    return validitaCodice && validitaNome && validitaPrezzo && validitaQuantita;
}

const checkFormRimuovi = () => {
    const codiceValue = codiceRim.value.trim();


    //VALIDITA
    let validitaCodice = false;


    if(codiceValue === ''){
    	validitaCodice = false;
        setError(codiceRim, 'Il codice deve essere corretto.');
    }else{
    	validitaCodice = true;
        setSuccess(codiceRim);
    }

    return validitaCodice;
}

const setError = (input, message) => {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    const div = formControl.querySelector('div');

    small.innerText = message;
    div.className = 'div-visualize'
    formControl.className = 'form-controller error';
}

const setSuccess = (input) => {
    const formControl = input.parentElement;
    const div = formControl.querySelector('div');

    div.className = 'div-non-visualize'
    formControl.className = 'form-controller success';

}

form.addEventListener('submit', (e) => {
    e.preventDefault();
    if(checkFormInserimento()){
    	form.submit();
    }
       
});

form2.addEventListener('submit', (e) => {
    e.preventDefault();
    if(checkFormModifica())
       form2.submit();
});

form3.addEventListener('submit', (e) => {
    e.preventDefault();
    if(checkFormRimuovi()){
    	form3.submit();
    }
});


