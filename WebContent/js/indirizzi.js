//FORM
const form = document.getElementById('form-inserimento');

//INSERIMENTO
const viaIns = document.getElementById('via-inserimento');
const cittaIns = document.getElementById('citta-inserimento');
const capIns = document.getElementById('cap-inserimento');

const checkFormInserimento = () => {
    const viaValue = viaIns.value.trim();
    const cittaValue = cittaIns.value.trim();
    const capValue = capIns.value.trim();

    //VALIDITA
    let validitaVia = false;
    let validitaCitta = false;
    let validitaCap = false;


    if (viaValue === '') {
        validitaVia = false;
        setError(viaIns, 'La via deve essere corretta.');
    } else {
        validitaVia = true;
        setSuccess(viaIns);
    }

    if (cittaValue === '') {
        validitaCitta = false;
        setError(cittaIns, 'La citta deve essere corretta.');
    } else {
        validitaCitta = true;
        setSuccess(cittaIns);
    }

    if (capValue === '') {
        validitaCap = false;
        setError(capIns, 'Il CAP deve essere corretto.');
    } else {
        validitaCap = true;
        setSuccess(capIns);
    }

    return validitaVia && validitaCitta && validitaCap;
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
    if (checkFormInserimento())
        form.submit();
});