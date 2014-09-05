package armani.anderson.sihts.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Report {
	final char FS = File.separatorChar;
	final String REPORT_FOLDER = "report";
	final String DEFAULT_PATH = System.getProperty("user.dir") + FS + REPORT_FOLDER + FS;
	
	private Document document = null;
	private Font font = null;	
	private PdfPTable table = null;
	
	/**
	 * 
	 * @param name - Sring with path of file
	 */
	public void Open(String name) {
		font = new Font(FontFamily.HELVETICA, 12, Font.NORMAL);
		document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(DEFAULT_PATH + name));
			document.open();
		}
		catch(DocumentException e) {
			System.out.println(e.getMessage());
		}
		catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
	}
	
	/**
	 * Fecha o arquivo corrente
	 */
	public void close() {
		document.close();
	}
	/**
	 * Printa um título principal na página do relatório
	 * @param title
	 */
	public void printTitle(String title) {
		Font bkpFont = font;
		font = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
		printLine(title);
		font = bkpFont;
	}
	
	/**
	 * Printa um subtitulo no relatório
	 * @param title
	 */
	public void printSubTitle(String subtitle) {
		Font bkpFont = font;
		font = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
		printLine(subtitle);
		font = bkpFont;
	}
	
	/**
	 * Escreve uma linha no arquivo
	 * @param line
	 */
	public void printLine(String line) {
		Paragraph p = new Paragraph(line, font);
		try {
			document.add(p);
		} catch (DocumentException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Cria uma tabela no documento corrente
	 */
	public void createTable(String header, int colNum) {
		if(table != null) {
			try {
				document.add(table);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}

		table = new PdfPTable(colNum);
		PdfPCell head = new PdfPCell(new Paragraph(header));
		head.setColspan(colNum);
		table.addCell(head);	
	}
	
	public void tableCell(String text) {
		if(table != null) {
			table.addCell(text); 
		}
	}
	
	public void closeTable() {
		try {
			if(table != null) {
				document.add(table);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param title
	 */
	public void printTableLine(String title) {
		Font bkpFont = font;
		
		font = new Font(FontFamily.HELVETICA, 20, Font.BOLD);
		printLine(title);
		font = bkpFont;
	}
	
}
