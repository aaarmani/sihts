package armani.anderson.sihts.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class Report {
	final char FS = File.separatorChar;
	final String REPORT_FOLDER = "report";
	final String DEFAULT_PATH = System.getProperty("user.dir") + FS + REPORT_FOLDER + FS;
	final String SUFIX = "_BKP";
	final String SUFIX_RES = "_RES";
	
	private Document document = null;
	private Font font = null;	
	private PdfPTable table = null;
	
	private boolean append = false;
	private String name;
	
	/**
	 * 
	 * @param name - Sring with path of file
	 */
	public void Open(String name, boolean append) {
		this.append = append;
		this.name = name;

		font = new Font(FontFamily.HELVETICA, 12, Font.NORMAL);
		document = new Document();
		byte[] streamBytes = new byte[10000];
		
		try {
			if(append == true) {
				moveFileToBKP(DEFAULT_PATH + name);
			}
			
			PdfWriter.getInstance(document, new FileOutputStream(DEFAULT_PATH + name, true));
			document.open();
		}
		catch(DocumentException e) {
			System.out.println(e.getMessage());
		}
		catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
	}
	
	private boolean moveFileToBKP(String filePath) {
		File f = new File(filePath);
		File f2 = new File(filePath + SUFIX);
		
		if(f.exists() && f.isFile()) {
			if(f.renameTo(f2) == true) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Fecha o arquivo corrente
	 */
	public void close() {
		document.close();
		
		if(append == true) {
			try {
				manipulatePdf(DEFAULT_PATH + name, DEFAULT_PATH + name + SUFIX);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
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
	
	/**
	 * Append pdf src to dest		
	 * @param src
	 * @param dest
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        
		File fbkp = new File(dest);
		if(fbkp.exists() == false) {
			return;
		}
		
        // using previous examples to create PDFs
        String[] files = { dest, src };
        // step 1
        Document document = new Document();
        // step 2
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(DEFAULT_PATH + name + SUFIX_RES));
        // step 3
        document.open();
        // step 4
        PdfReader reader;
        int n;
        // loop over the documents you want to concatenate
        for (int i = 0; i < files.length; i++) {
            reader = new PdfReader(files[i]);
            // loop over the pages in that document
            n = reader.getNumberOfPages();
            for (int page = 0; page < n; ) {
                copy.addPage(copy.getImportedPage(reader, ++page));
            }
            copy.freeReader(reader);
            reader.close();
        }
        // step 5
        document.close();
        
        
        File f1 = new File(DEFAULT_PATH + name);
        File f2 = new File(DEFAULT_PATH + name + SUFIX);
        File f3 = new File(DEFAULT_PATH + name + SUFIX_RES);
        
        f1.delete();
        f2.delete();
        f3.renameTo(f1);
    }
	
}
