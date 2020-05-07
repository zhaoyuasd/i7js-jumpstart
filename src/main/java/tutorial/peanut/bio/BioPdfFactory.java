package tutorial.peanut.bio;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLineAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;

public class BioPdfFactory {
    public static final String DEST = "cmpfiles/my/bio.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(DEST);

        //Initialize PDF document
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        PdfPage page=
                BioPdfPage.addBioPage(pdfDoc,1,document);
        Rectangle pageSize = page.getPageSize();


        Paragraph title = new Paragraph("BIO-DATA OF FOREIGN DOMESTIC WORKER (FDW)" )
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setBold()
                .setFontSize(15)
                .setFontColor(ColorConstants.BLACK);

        Div div = new Div();
        div.add(title);
        div.setPaddingTop(25);
        div.setTextAlignment(TextAlignment.CENTER);
        document.add(div);

        Paragraph info = new Paragraph("Please ensure that you run through the information within the biodata as it is an important document to help you select a suitable FDW" )
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);


        Div divInfo = new Div();
        divInfo.add(info);
        divInfo.setMarginTop(-8);
        divInfo.setTextAlignment(TextAlignment.CENTER);
        document.add(divInfo);


        String A="(A) Profile of FDW";
        Paragraph AP = new Paragraph(A)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setBold()
                .setFontSize(13)
                .setFontColor(new DeviceRgb(45,112, 214));

        Div divA = new Div();
        divA.add(AP);
        divA.setMarginTop(8);
        divA.setTextAlignment(TextAlignment.LEFT);
        document.add(divA);

        String A1="A1 Personal Information";
        Paragraph AP1 = new Paragraph(A1)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setBold()
                .setFontSize(13)
                .setFontColor(ColorConstants.BLACK);

        Div divA1 = new Div();
        divA1.add(AP1);
        divA1.setMarginTop(16);
        divA1.setTextAlignment(TextAlignment.LEFT);
        document.add(divA1);





        pdfDoc.close();
    }
}
