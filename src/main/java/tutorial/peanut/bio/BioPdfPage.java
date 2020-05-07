package tutorial.peanut.bio;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.IOException;

public class BioPdfPage {
  public  static PdfPage addBioPage(PdfDocument pdfDoc,Integer pageNum,Document document) throws IOException {
      String  title="19(IPW)   2201395-ID-ES";
      String foot1="Peanut Technology Pte.Ltd.   LC No.19C9926    Tel: +65 69709425";
      String foot2="170 Upper Bukit Timah Road #01-42, Bukit Timah Shopping Centre, Singapore, 588179";
      String pageSufi="A";
      Integer pageLRTrim=24;  //页面的左右边距
      Integer pageTopTrim=56; //页面上边距
      Integer titleRightLength=162; // 页眉右侧文本长度
      Integer pageTopTxtSize=14;     //页脚文本字号大小
      Integer titleLeftImgWith=110; // 页眉左侧图片的相对宽度 这个和图片的原始大小有关
      Integer pageBottomTrim=16;    //页面下边距
      Integer pageFootTxtSize=8;     //页脚文本字号大小
      Integer pageNumSize=10;        //页码文本大小
      String  titleImgPath="src/main/resources/my/logo_login.png"; //图片路径


      ImageData img= ImageDataFactory.create(titleImgPath);
      PdfPage page= pdfDoc.addNewPage(pageNum);

      Rectangle pageSize = page.getPageSize();
      PdfCanvas canvas = new PdfCanvas(page);
      canvas.addImage(img,pageLRTrim,pageSize.getTop() - pageTopTrim,titleLeftImgWith,false);
      canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), pageTopTxtSize)
              .moveText(pageSize.getWidth()-titleRightLength-pageLRTrim, pageSize.getTop() - pageTopTrim+10)
              .showText(title)
              .endText();
      //Draw footer line
            /*canvas.setStrokeColor(ColorConstants.BLACK)
                    .setLineWidth(.2f)
                    .moveTo(pageSize.getWidth() / 2 - 30, 20)
                    .lineTo(pageSize.getWidth() / 2 + 30, 20).stroke();*/
      //Draw page left footer
      canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), pageFootTxtSize)
              .moveText(pageLRTrim, pageBottomTrim+pageFootTxtSize)
              .showText(foot1)
              .endText();
      canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), pageFootTxtSize)
              .moveText(pageLRTrim, pageBottomTrim)
              .showText(foot2)
              .endText();
      //Draw page number
      canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), pageNumSize)
              .moveText(pageSize.getWidth()-pageLRTrim-10, pageBottomTrim)
              .showText(pageSufi+pageNum)
              .endText();
      //Draw watermark
      Paragraph p = new Paragraph("PEANUT JOB").setFontSize(60);
      canvas.saveState();
      PdfExtGState gs1 = new PdfExtGState().setFillOpacity(0.2f);
      canvas.setExtGState(gs1);

      document.showTextAligned(p,
              pageSize.getWidth() / 2, pageSize.getHeight() / 2,
              pdfDoc.getPageNumber(page),
              TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
      canvas.restoreState();

      return page;
  }

}
