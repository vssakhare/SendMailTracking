
package in.emp.reports;
import com.itextpdf.text.BaseColor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import in.emp.vendor.bean.VendorInputBean;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
 public class PdfGeneration {
    public static void generatePdf(VendorInputBean v ) throws FileNotFoundException, DocumentException
        {
                Document document = new Document();
                  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
                   LocalDateTime now = LocalDateTime.now(); 
                @SuppressWarnings("unused")
                Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
                Font f3 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
            Font regular = new Font(Font.FontFamily.TIMES_ROMAN, 11);
                String filename=v.getVendorName();
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("D:/"+filename+".pdf"));
                
                document.open();
                
                
            Paragraph p = new Paragraph("\n" , f2);
//            p.setAlignment(Element.ALIGN_LEFT);
            Paragraph p1 = new Paragraph("Subject : PDF Generation and Attachment Test", f3);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setSpacingAfter(10);
            PdfPTable tabHeader = new PdfPTable(2);
            tabHeader.setWidthPercentage(100);
            float[] wH = {7f , 3f} ;
            tabHeader.setWidths(wH);
            
            
            tabHeader.addCell(getNewCellWithoutBorder("To,", Element.ALIGN_LEFT, f2, 1));
            tabHeader.addCell(getNewCellWithoutBorder("Report Date : "+dtf.format(now), Element.ALIGN_RIGHT, regular, 1));
            
            tabHeader.addCell(getNewCellWithoutBorder(v.getVendorName(), Element.ALIGN_LEFT, f2, 1));
            tabHeader.addCell(getNewCellWithoutBorder("", Element.ALIGN_LEFT, regular, 1));

                PdfPTable tab1 = new PdfPTable(2);
            tab1.setWidthPercentage(100);
            float[] columnWidth_tab1 = {5f, 5f}; //{1.5f, 6f, 2.5f, 1.5f, 2.5f, 2f, 2.5f}; //{1f, 6f, 1.5f, 1f, 1.5f, 1f, 1.5f};
            tab1.setWidths(columnWidth_tab1);

           /* PdfPCell c01 = new PdfPCell(new Phrase("Mail Subject. :- " + v.getMAIL_SUBJECT(), regular));
            c01.setHorizontalAlignment(Element.ALIGN_LEFT);
//            c01.setBorder(Rectangle.NO_BORDER);
            tab1.addCell(c01);
 PdfPCell c02 = new PdfPCell(new Phrase("Mail Body.:- " + v.getMAIL_BODY(), regular));
            c02.setHorizontalAlignment(Element.ALIGN_LEFT);
//            c01.setBorder(Rectangle.NO_BORDER);
            tab1.addCell(c02);*/

                 document.add(tabHeader);
                  document.add(p1);
                  document.add(tab1);
                document.close();
        }
      private static PdfPCell getNewCellWithoutBorder(String data, int alignment, Font f2, int colSpan) {
        PdfPCell cell = new PdfPCell(new Phrase(data, f2));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(colSpan);
        return cell;

    }

    private static PdfPCell getNewCell(String data, int alignment, Font f2, int colSpan) {
        PdfPCell cell = new PdfPCell(new Phrase(data, f2));
        cell.setHorizontalAlignment(alignment);
        cell.setColspan(colSpan);
        return cell;

    }
   }

