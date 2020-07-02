//FORM
const form = document.getElementById('form-inserimento');

//INSERIMENTO
const codiceIns = document.getElementById('codice-inserimento');
const intestatarioIns = document.getElementById('intestatario-inserimento');
const tipologiaIns = document.getElementById('tipologia-inserimento');
const scadenzaIns = document.getElementById('scadenza-inserimento');

const checkFormInserimento = () => {
    const codiceValue = codiceIns.value.trim();
    const intestatarioValue = intestatarioIns.value.trim();
    const tipologiaValue = tipologiaIns.value.trim();
    const scadenzaValue = scadenzaIns.value.trim();

    //VALIDITA
    let validitaCodice = false;
    let validitaIntestatario = false;
    let validitaTipologia = false;
    let validitaScandenza = false;


    if(codiceValue === ''){
    	validitaCodice = false;
        setError(codiceIns, 'La via deve essere corretta.');
    }else{
    	validitaCodice = true;
        setSuccess(codiceIns);
    }
    
    if(intestatarioValue === ''){
    	validitaIntestatario = false;
        setError(intestatarioIns, 'La citta deve essere corretta.');
    }else{
    	validitaIntestatario = true;
        setSuccess(intestatarioIns);
    }
    
    if(tipologiaValue === ''){
    	validitaTipologia = false;
        setError(tipologiaIns, 'Il CAP deve essere corretto.');
    }else{
    	validitaTipologia = true;
        setSuccess(tipologiaIns);
    }
    
    if(scadenzaValue === ''){
    	validitaScandenza = false;
        setError(scadenzaIns, 'Il CAP deve essere corretto.');
    }else{
    	validitaScandenza = true;
        setSuccess(scadenzaIns);
    }

    return validitaCodice && validitaIntestatario && validitaTipologia && validitaScandenza;
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
    if(checkFormInserimento())
       form.submit();
});