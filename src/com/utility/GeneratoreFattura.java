package com.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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

public class GeneratoreFattura {
	public static void createPDF(String pdfFilename,String logo) {
		try {
			OutputStream file = new FileOutputStream(new File(pdfFilename));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			
			//Inserimeto dell'immagine nel pdf
			Image image = Image.getInstance(logo);
			image.scaleAbsolute(170f, 72f);
			
			//Creazione tabella superiore
			PdfPTable irdTable = new PdfPTable(2);
			irdTable.addCell(getIRDCell("N. Fattura"));
			irdTable.addCell(getIRDCell("Data Fattura"));
			irdTable.addCell(getIRDCell("12345"));  //Numero della fattura/Ordine
			irdTable.addCell(getIRDCell("16-11-1999"));	//Data della fattura/Ordine
			
			//Creazione tabella superiore 2
			PdfPTable irhTable = new PdfPTable(3);
			irhTable.setWidthPercentage(100);
			irhTable.addCell(getIRHCell("",PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("",PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("Fattura",PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("",PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("",PdfPCell.ALIGN_RIGHT));
			PdfPCell invoiceTable = new PdfPCell(irdTable);
			invoiceTable.setBorder(0);
			irhTable.addCell(invoiceTable);
			
			FontSelector fs = new FontSelector();
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD);
			fs.addFont(font);
			
			//Informazioni spedizione
			Phrase bill = fs.process("Spedito a");
			Paragraph name = new Paragraph("Antonio Sarro");
			name.setIndentationLeft(20);
			Paragraph contact = new Paragraph("sarroantonio1999@gmail.com");
			contact.setIndentationLeft(20);
			Paragraph address = new Paragraph("Via Petrulli 3");
			address.setIndentationLeft(20);
			
			//Sezione dei prodotti
			PdfPTable billTable = new PdfPTable(6);  //Una pagina può contenere 15 prodotti
			billTable.setWidthPercentage(100);
			billTable.setWidths(new float[] {1,2,5,2,1,2});
			billTable.setSpacingBefore(30.0f);
			billTable.addCell(getBillHeaderCell("Indice"));
			billTable.addCell(getBillHeaderCell("Categoria"));
			billTable.addCell(getBillHeaderCell("Descrizione"));
			billTable.addCell(getBillHeaderCell("Prezzo Prodotto"));
			billTable.addCell(getBillHeaderCell("Quantità"));
			billTable.addCell(getBillHeaderCell("Prezzo Totale"));
			
			//INSERIMENTO PRODOTTI (USARE FOR)
			billTable.addCell(getBillRowCell("1"));
			billTable.addCell(getBillRowCell("Mobile"));
			billTable.addCell(getBillRowCell("Nokia Lumia 610 \n IMI:WQ361989213 "));
			billTable.addCell(getBillRowCell("12000.0"));
			billTable.addCell(getBillRowCell("1"));
			billTable.addCell(getBillRowCell("12000.0"));

			billTable.addCell(getBillRowCell("2"));
			billTable.addCell(getBillRowCell("Accessories"));
			billTable.addCell(getBillRowCell("Nokia Lumia 610 Panel \n Serial:TIN3720 "));
			billTable.addCell(getBillRowCell("200.0"));
			billTable.addCell(getBillRowCell("1"));
			billTable.addCell(getBillRowCell("200.0"));
			
			billTable.addCell(getBillRowCell(" "));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			
			billTable.addCell(getBillRowCell(" "));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			
			billTable.addCell(getBillRowCell(" "));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));
			billTable.addCell(getBillRowCell(""));

			
			
			
			//CREAZIONE PARTE BASSO-SINISTRA
			PdfPTable validity = new PdfPTable(1);
			validity.setWidthPercentage(100);
			validity.addCell(getValidityCell(" "));
			validity.addCell(getValidityCell("Garanzia"));
			validity.addCell(getValidityCell(" * Il prodotto acquistato arriva con 1 anno di garanzia nazionale \n   (se applicabile)"));
			validity.addCell(getValidityCell(" * La garanzia può essere presentata solo per il relativo prodotto"));		    
			PdfPCell summaryL = new PdfPCell (validity);
			summaryL.setColspan (3);
			summaryL.setPadding (1.0f);	                   
			billTable.addCell(summaryL);
			
			//CREAZIONE TOTALE FATTURA
			PdfPTable accounts = new PdfPTable(2);
			accounts.setWidthPercentage(100);
			accounts.addCell(getAccountsCell("Totale Parziale"));
			accounts.addCell(getAccountsCellR("12620.00"));
			accounts.addCell(getAccountsCell("Tasse(22%)"));
			accounts.addCell(getAccountsCellR("315.55"));
			accounts.addCell(getAccountsCell("Totale"));
			accounts.addCell(getAccountsCellR("11673.55"));			
			PdfPCell summaryR = new PdfPCell (accounts);
			summaryR.setColspan (3);         
			billTable.addCell(summaryR);
			
			PdfPTable describer = new PdfPTable(1);
			describer.setWidthPercentage(100);
			describer.addCell(getdescCell(" "));
			describer.addCell(getdescCell("Nel rispetto dalla normativa vigente, ivi incluso DL 196/03 e reg. UE 2016/679, informiamo che i Vs. dati saranno utilizzati ai soli fini connessi ai rapporti commerciali tra di noi in essere."
					+ " Contributo CONAI assolto ove dovuto - Vi preghiamo di controllare i Vs. dati anagrafici, la P. IVA e il Cod. Fiscale. Non ci riteniamo responsabili di eventuali errori."));	

			document.open();	//Apertura documento PDF

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
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PdfPCell getIRDCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
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
		PdfPCell cell = new PdfPCell (phrase);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		return cell;
	}
	
	public static PdfPCell getBillRowCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
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
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorder(0);
		return cell;
	}
	
	public static PdfPCell getAccountsCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding (5.0f);
		return cell;
	}
	public static PdfPCell getAccountsCellR(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
		cell.setPadding (5.0f);
		cell.setPaddingRight(20.0f);
		return cell;
	}
	

	public static PdfPCell getdescCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);	
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(0);
		return cell;
	}
	
}

