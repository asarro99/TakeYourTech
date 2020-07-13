package com.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.model.bean.AccountBean;
import com.model.bean.OrdiniBean;
import com.model.bean.ProductBean;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Cart;

public class GeneratoreFattura {
    public static void createPDF(String pdfFilename, String logo, ArrayList < Object > lista) {
        try {
            OutputStream file = new FileOutputStream(new File(pdfFilename));
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            //DATI DA INSERIRE
            OrdiniBean ordine = (OrdiniBean) lista.get(0);
            AccountBean account = (AccountBean) lista.get(1);
            Cart prodottiOrdinatiCart = (Cart) lista.get(2);
            List < ProductBean > products = prodottiOrdinatiCart.getProducts();

            //Inserimeto dell'immagine nel pdf
            Image image = Image.getInstance(logo);
            image.scaleAbsolute(170f, 72f);

            //Creazione tabella superiore
            PdfPTable irdTable = new PdfPTable(2);
            irdTable.addCell(getIRDCell("N. Fattura"));
            irdTable.addCell(getIRDCell("Data Fattura"));
            irdTable.addCell(getIRDCell("" + ordine.getCode())); //Numero della fattura/Ordine
            irdTable.addCell(getIRDCell("" + ordine.getData())); //Data della fattura/Ordine

            //Creazione tabella superiore 2
            PdfPTable irhTable = new PdfPTable(3);
            irhTable.setWidthPercentage(100);
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("Fattura", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            PdfPCell invoiceTable = new PdfPCell(irdTable);
            invoiceTable.setBorder(0);
            irhTable.addCell(invoiceTable);

            FontSelector fs = new FontSelector();
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
            fs.addFont(font);

            //Informazioni spedizione
            Phrase bill = fs.process("Spedito a");
            Paragraph name = new Paragraph("" + account.getNome() + " " + account.getCognome());
            name.setIndentationLeft(20);
            Paragraph contact = new Paragraph("" + account.getEmail());
            contact.setIndentationLeft(20);
            Paragraph address = new Paragraph("" + ordine.getVia());
            address.setIndentationLeft(20);

            //Sezione dei prodotti
            PdfPTable billTable = new PdfPTable(6); //Una pagina può contenere 15 prodotti
            billTable.setWidthPercentage(100);
            billTable.setWidths(new float[] {
                2,
                3,
                5,
                2,
                2,
                3
            });
            billTable.setSpacingBefore(30.0f);
            billTable.addCell(getBillHeaderCell("Indice"));
            billTable.addCell(getBillHeaderCell("Categoria"));
            billTable.addCell(getBillHeaderCell("Descrizione"));
            billTable.addCell(getBillHeaderCell("Prezzo Prodotto"));
            billTable.addCell(getBillHeaderCell("Quantità"));
            billTable.addCell(getBillHeaderCell("Prezzo Totale"));

            //INSERIMENTO PRODOTTI (USARE FOR)
            int index = 0;
            for (ProductBean b: products) {
                billTable.addCell(getBillRowCell("" + (index + 1)));
                billTable.addCell(getBillRowCell("" + b.getCategoria()));
                billTable.addCell(getBillRowCell("" + b.getName()));
                billTable.addCell(getBillRowCell("" + b.getPrice() + " Euro"));
                billTable.addCell(getBillRowCell("" + b.getQuantity()));
                billTable.addCell(getBillRowCell("" + b.getPrice() * b.getQuantity() + " Euro"));
                index++;
            }

            //CREAZIONE PARTE BASSO-SINISTRA
            PdfPTable validity = new PdfPTable(1);
            validity.setWidthPercentage(100);
            validity.addCell(getValidityCell(" "));
            validity.addCell(getValidityCell("Garanzia"));
            validity.addCell(getValidityCell(" * Il prodotto acquistato arriva con 1 anno di garanzia nazionale \n   (se applicabile)"));
            validity.addCell(getValidityCell(" * La garanzia può essere presentata solo per il relativo prodotto"));
            PdfPCell summaryL = new PdfPCell(validity);
            summaryL.setColspan(3);
            summaryL.setPadding(1.0f);
            billTable.addCell(summaryL);

            //CREAZIONE TOTALE FATTURA
            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);
            accounts.addCell(getAccountsCell("Totale Parziale"));
            accounts.addCell(getAccountsCellR("" + ordine.getTotale() + " Euro"));
            accounts.addCell(getAccountsCell("IVA(22%)"));
            accounts.addCell(getAccountsCellR("" + ordine.getTotale() * 22 / 100 + " Euro"));
            accounts.addCell(getAccountsCell("Totale"));
            accounts.addCell(getAccountsCellR("" + (ordine.getTotale() + ordine.getTotale() * 22 / 100) + " Euro"));
            PdfPCell summaryR = new PdfPCell(accounts);
            summaryR.setColspan(3);
            billTable.addCell(summaryR);

            PdfPTable describer = new PdfPTable(1);
            describer.setWidthPercentage(100);
            describer.addCell(getdescCell(" "));
            describer.addCell(getdescCell("Nel rispetto dalla normativa vigente, ivi incluso DL 196/03 e reg. UE 2016/679, informiamo che i Vs. dati saranno utilizzati ai soli fini connessi ai rapporti commerciali tra di noi in essere." +
                " Contributo CONAI assolto ove dovuto - Vi preghiamo di controllare i Vs. dati anagrafici, la P. IVA e il Cod. Fiscale. Non ci riteniamo responsabili di eventuali errori."));

            document.open(); //Apertura documento PDF

            document.add(image);
            document.add(irhTable);
            document.add(bill);
            document.add(name);
            document.add(contact);
            document.add(address);
            document.add(billTable);
            document.add(describer);

            document.close();

            file.close();

            System.out.println("Fattura Creata!");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PdfPCell getIRDCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

    public static PdfPCell getIRHCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        /*	font.setColor(BaseColor.GRAY);*/
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        return cell;
    }

    public static PdfPCell getBillRowCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getValidityCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(0);
        return cell;
    }

    public static PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding(5.0f);
        return cell;
    }
    public static PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }


    public static PdfPCell getdescCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }

}