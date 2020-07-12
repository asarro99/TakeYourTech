<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%
   String sidemenu = (String)request.getAttribute("sidemenu");
   if(sidemenu == null) {
   	response.sendRedirect("./Product?page=/assistenza.jsp");	
   	return;
   }
   %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/assistenza.css" />
      <link
         rel="stylesheet"
         href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
         />
      <link
         rel="stylesheet"
         href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
         />
   </head>
   <body>
      <!-- TOP NAV BAR -->
      <jsp:include page="./utility/header.jsp"/>
      <section class="header">
         <!-- SIDE MENU -->
         <jsp:include page="./utility/sidemenu.jsp">
            <jsp:param value="<%=sidemenu%>" name="categorie"/>
         </jsp:include>
      </section>
      <!-- FAQ -->
      <section class="assistenza">
         <div class="faq_copy">
            <h2>FAQ</h2>
         </div>
         <div class="faq">
            <h4>COME POSSO ACQUISTARE DAL SITO DI TAKEYOURTECH?</h4>
            <p>
               Per acquistare dal sito TakeYourTech e' necessario iscriversi sul nostro sito registrando il proprio account con tutti i dati richiesti.
               Dopo essersi registrati e' necessario selezionare i prodotti desiderati ed inserirli nel carrello.
               E' possibile poi procedere all'acquisto scegliendo tra i vari metodi di pagamento accettati.
            </p>
            <h4>FATE ANCHE FATTURE?</h4>
            <p>
               Si certamente, la fattura potra' essere scaricata dopo aver effettuato un acquisto nella sezione ordini dell'account.
            </p>
            <h4>COME FACCIO A CAMBIARE L'INDIRIZZO DI SPEDIZIONE?</h4>
            <p>
               Per modificare l'indirizzo di spedizione basta accedere all'apposita voce all'interno del proprio account.
            </p>
            <h4>E' POSSIBILE ORDINARE UN PRODOTTO CHE RISULTA ESSERE 'NON DISPONIBILE' SUL SITO?</h4>
            <p>
               I prodotti indicati come 'non disponibili' sul sito solitamente sono momentaneamente in attesa di restock o fuori produzione.
            </p>
            <h4>COSA SIGNIFICA DISPONIBILE PER UN PRODOTTO?</h4>
            <p>
               Prodotto disponibile presso magazzino esterno, con tempi di consegna di 3 o 4 giorni lavorativi.
            </p>
         </div>
      </section>
      <jsp:include page="./utility/footer.jsp"/>
      <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
      <script src="./js/app.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
      <!---------------------------------ASSISTENZA----------------------------------------->
      <script> 
         window.intergramId = "49403884";
         window.intergramCustomizations = {
            titleClosed: 'Contattaci!',
            titleOpen: 'Supporto TakeYourTech',
            introMessage: 'Ciao! Come possiamo aiutarti?',
            autoResponse: 'Grazie per averci contattato! Un nostro operatore ti rispondera\' a breve.',
            autoNoResponse: 'Ci dispiace per l\'attesa i nostri operatori sono tutti occupati,'+
                            ' tra pochi minuti sarai contattato.',
            mainColor: "#ff5722", // Can be any css supported color 'red', 'rgb(255,87,34)', etc
            alwaysUseFloatingButton: false // Use the mobile floating button also on large screens
         };
      </script>
      <script id="intergram" type="text/javascript" src="https://www.intergram.xyz/js/widget.js"></script>
   </body>
</html>