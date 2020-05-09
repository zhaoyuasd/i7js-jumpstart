package tutorial.peanut.bio;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BioPdfFactory {
    public static final String DEST = "cmpfiles/my/bio.pdf";


    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(DEST);

        //Initialize PDF document
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc,new PageSize(1190/2,1684/2));
        PdfPage page=
                BioPdfPage.addBioPage(pdfDoc,1,document);
        Rectangle pageSize = page.getPageSize();

        // 大标题
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

        // 副标题
        Paragraph info = new Paragraph("Please ensure that you run through the information within the biodata as it is an important document to help you select a suitable FDW" )
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        Div divInfo = new Div();
        divInfo.add(info);
        divInfo.setMarginTop(-8);
        divInfo.setTextAlignment(TextAlignment.CENTER);
        document.add(divInfo);


        // 内容
        fillH3Title(document,"(A) Profile of FDW");
        fillH4Title(document,"A1 Personal Information");


        Table personalInfo=new Table(2,false);
        Cell infoDetail=new Cell();
        infoDetail.setBorder(Border.NO_BORDER);
        infoDetail.setWidth(720/2);

        //name
        Table nameTable=generateDetailInfoWithOneInfo("1. Name:",84f,"TITI SUDIYANTI BINTI SUPRAPTO",632f);
        infoDetail.add(nameTable);

        // birthDay age
        Table dbTable=generateDetailInfoWithTwoInfo("2. Date of Birth:","Age:",156f,48f,"Jun 1, 1972","48",260f,260f);
        infoDetail.add(dbTable);


        //birth place
        Table bpTable=generateDetailInfoWithOneInfo("3. Place of Birth:",157f,"WONOSOBO",558f);
        infoDetail.add(bpTable);

        // height weight
        Table hwTable=generateDetailInfoWithTwoInfo("4. Height:","Weight:",100f,70f,"156cm","62kg",270f,280f);
        infoDetail.add(hwTable);


        //nationality
        Table nlTable=generateDetailInfoWithOneInfo("5. Nationality:",128f,"Indonesia",586f);
        infoDetail.add(nlTable);

        //Residential address
        Cell rdTitleCell=generateDetailTitleCell("6. Residential address in home country:",720f);
        rdTitleCell.setTextAlignment(TextAlignment.LEFT);
        rdTitleCell.setPaddingTop(10f);
        infoDetail.add(rdTitleCell);
        Cell rdValueCell=generateDetailValueCell("MERGOSARI RT 01 / 01 SUKOHARJO WONOSOBO, INDONESIA",728f);
        infoDetail.add(rdValueCell);

        //Name of port / airport
        Table naTable=generateDetailInfoWithOneInfo("7. Name of port / airport to be repatriated to:",386f,"INDONESIAN",326f);
        infoDetail.add(naTable);

        // Contact Number in Home Country
        Table cnhTable =generateDetailInfoWithOneInfo("8. Contact Number in Home Country:",336f,"Number in Home Countr",378f);
        infoDetail.add(cnhTable);

        // Religion  Education Level
        Table reTable =generateDetailInfoWithTwoInfo("9. Religion:","10. Education Level:",100f,190f,"Muslim","Secondary",226f,204f);
        infoDetail.add(reTable);

        //Number of Sibling  Marital Status
        Table nmTable =generateDetailInfoWithTwoInfo("11. Number of Siblings:","12. Marital Status:",212,168f,"5","Married",180f,164f);
        infoDetail.add(nmTable);

        //number child age child
        Table nacTable =generateDetailInfoWithTwoInfo("13. Number of Children:","Age(s) of Children (if any):",220f,246,"3","6,19,22",120f,140f);
        infoDetail.add(nacTable);


        Cell photo=new Cell();
        photo.setBorder(Border.NO_BORDER);
        photo.setHeight(466/2);
        photo.setMarginLeft(50*2);
        photo.setPaddingLeft(10);
        Image img=new  Image(ImageDataFactory.create("src/main/resources/my/photo.jpeg"));
        img.scaleToFit(300/2,400/2);
        photo.add(img);

        personalInfo.addCell(infoDetail);
        personalInfo.addCell(photo);
        document.add(personalInfo);


        fillH4Title(document,"A2 Medical History / Dietary Restrictions");

        //Allergies
        Table alTable =generateDetailInfoWithOneInfo("14. Allergies (if any):",240f,"Nil",860f);
        document.add(alTable);

        // 15. Past and existing illnesses (including chronic ailments and illnesses requiring medication):
        Paragraph  pei =

                new Paragraph("15. Past and existing illnesses (including chronic ailments and illnesses requiring medication):")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(10)
                .setFontColor(ColorConstants.BLACK);
        pei.setPaddingLeft(1);
        document.add(pei);
        pdfDoc.close();
    }

    /**
     * 添加 这种  A1 Personal Information
     * @param document
     * @param h4
     */
    private static void fillH4Title(Document document, String h4) {
        Paragraph headerH4 = null;
        try {
            headerH4 = new Paragraph(h4)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setBold()
                    .setFontSize(13)
                    .setFontColor(ColorConstants.BLACK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Div divA1 = new Div();
        divA1.add(headerH4);
        divA1.setMarginTop(8);
        divA1.setTextAlignment(TextAlignment.LEFT);
        document.add(divA1);
    }

    /**
     * 添加 (A) Profile of FDW"  这中标题
     * @param document
     * @Param  h3
     */
    private static void fillH3Title(Document document,String h3) {
        Paragraph headerH3 = null;
        try {
            headerH3 = new Paragraph(h3)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setBold()
                    .setFontSize(13)
                    .setFontColor(new DeviceRgb(45,112, 214));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Div divA = new Div();
        divA.add(headerH3);
        divA.setMarginTop(8);
        divA.setTextAlignment(TextAlignment.LEFT);
        document.add(divA);
    }

    private static Table generateDetailInfoWithTwoInfo(String title1, String title2, float title1Width, float title2Width, String value1, String value2, float value1Width, float value2Width) {
        List<String> title=new ArrayList<>();
        title.add(title1);
        title.add(title2);
        List<String> value=new ArrayList<>();
        value.add(value1);
        value.add(value2);
        List<Float> titleWidth=new ArrayList<>();
        titleWidth.add(title1Width);
        titleWidth.add(title2Width);
        List<Float> valueWidth=new ArrayList<>();
        valueWidth.add(value1Width);
        valueWidth.add(value2Width);
       return generateDetailInfo( title,value,titleWidth,valueWidth);
    }

    private static Table generateDetailInfoWithOneInfo(String title, float titleWidth, String value, float valueWith) {
        List<String> titleList=new ArrayList<>();
        titleList.add(title);
        List<String> valueList=new ArrayList<>();
        valueList.add(value);
        List<Float> titleWidthList=new ArrayList<>();
        titleWidthList.add(titleWidth);
        List<Float> valueWidthList=new ArrayList<>();
        valueWidthList.add(valueWith);
        Table table=generateDetailInfo( titleList,valueList,titleWidthList,valueWidthList);
        return table;

    }

    private static Table generateDetailInfo(List<String> title, List<String> value, List<Float> titleWidth,List<Float> valueWidth) {
        Table table=new Table(title.size()+value.size(),false);
        for(int i=0;i<title.size();i++){
            Cell titleCell=generateDetailTitleCell(title.get(i),titleWidth.get(i));
            table.addCell(titleCell);

            Cell valueCell=generateDetailValueCell(value.get(i),valueWidth.get(i));
            table.addCell(valueCell);
        }
        return table;
    }

    private static Cell generateDetailValueCell(String value, Float width) {

        Div div=new Div();
        div.setWidth(width/2-10);
        div.setBorderBottom(new SolidBorder(0.75f));
        div.setPaddingBottom(-6);
        try {
            //text.setBorderBottom(new SolidBorder(0.5f));
            Paragraph  valueP = new Paragraph(value)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setBold()
                    .setFontSize(10)
                    .setFontColor(ColorConstants.BLACK);
            div.add(valueP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cell valueCell=new Cell();
        valueCell.setWidth(width/2-20);
        valueCell.add(div);
        valueCell.setVerticalAlignment(VerticalAlignment.BOTTOM);
        valueCell.setTextAlignment(TextAlignment.CENTER);
        valueCell.setBorder(Border.NO_BORDER);
        return valueCell;
    }

    private static Cell generateDetailTitleCell(String title, Float width) {
        Paragraph titleP = null;
        try {
            titleP = new Paragraph(title)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(10)
                    .setFontColor(ColorConstants.BLACK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Cell titleCell=new Cell();
        titleCell.setWidth(width/2);
        titleCell.add(titleP);
        titleCell.setPaddingBottom(-2);
        titleCell.setVerticalAlignment(VerticalAlignment.BOTTOM);
        titleCell.setTextAlignment(TextAlignment.LEFT);
        titleCell.setBorder(Border.NO_BORDER);
        return titleCell;
    }
}
