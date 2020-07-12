//FORM
const form = document.getElementById('reg-form');
const form2 = document.getElementById('log-form');
//REGISTRAZIONE
const email = document.getElementById('reg-email');
const pass = document.getElementById('reg-pass');
const pass2 = document.getElementById('reg-pass2');
const nome = document.getElementById('reg-nome');
const cognome = document.getElementById('reg-cognome');
//LOGIN
const emailLog = document.getElementById('log-email');
const passLog = document.getElementById('log-password');

const checkFormReg = () => {
    const emailValue = email.value.trim();
    const passValue = pass.value.trim();
    const pass2Value = pass2.value.trim();
    const nomeValue = nome.value.trim();
    const cognomeValue = cognome.value.trim();
    let pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/; //ESPRESSIONE REGOLARE

    //VALIDITA
    let validitaEmail = false;
    let validitaPass = false;
    let validitaPass2 = false;
    let validitaNome = false;
    let validitaCognome = false;

    if (!emailValue.match(pattern)) {
        validitaEmail = false;
        setError(email, 'L\'indirizzo e-mail deve essere corretto.');
    } else {
        validitaEmail = true;
        setSuccess(email);
    }

    if (passValue === '') {
        validitaPass = false;
        setError(pass, 'Il campo password non puo\' essere vuoto');
    } else {
        validitaPass = true;
        setSuccess(pass);
    }

    if (pass2Value !== passValue || pass2Value === '') {
        validitaPass2 = false;
        setError(pass2, 'Le due password devono coincidere');
    } else {
        validitaPass2 = true;
        setSuccess(pass2);
    }

    if (nomeValue === '') {
        validitaNome = false;
        setError(nome, 'Il nome non puo\' essere vuoto');
    } else {
        validitaNome = true;
        setSuccess(nome);
    }

    if (cognomeValue === '') {
        validitaCognome = false;
        setError(cognome, 'Il cognome non puo\' essere vuoto');
    } else {
        validitaCognome = true;
        setSuccess(cognome);
    }
    return validitaEmail && validitaPass && validitaPass2 && validitaNome && validitaCognome;
}

const checkFormLog = () => {
    const emailValue = emailLog.value.trim();
    const passValue = passLog.value.trim();
    let pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    //VALIDITA
    let validitaEmail = false;
    let validitaPass = false;

    if (!emailValue.match(pattern)) {
        validitaEmail = false;
        setError(emailLog, 'L\'indirizzo e-mail deve essere corretto.');
    } else {
        validitaEmail = true;
        setSuccess(emailLog);
    }

    if (passValue === '') {
        validitaPass = false;
        setError(passLog, 'Il campo password non puo\' essere vuoto');
    } else {
        validitaPass = true;
        setSuccess(passLog);
    }
    return validitaEmail && validitaPass;
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
    if (checkFormReg())
        form.submit();
});

form2.addEventListener('submit', (e) => {
    e.preventDefault();
    if (checkFormLog())
        form2.submit();
});