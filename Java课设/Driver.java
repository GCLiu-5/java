package classend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Driver {
	public static void main (String[] args) throws BiffException, IOException, WriterException, DocumentException{
		ArrayList<ExpressWaybill> expList = new ArrayList<ExpressWaybill>();//������ݵ���
		
		expList = getExpList();
		creatOrderNum(expList);
		makeAll(expList);
		
		System.out.println(expList);
	}
	public static ArrayList<String> readExc() throws BiffException, IOException{//��ȡexc��Ϣ
		//����workbook
		Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\Administrator\\eclipse-workspace\\java_one\\java\\src\\classend\\�����ļ�.xls"));
		//��ȡ��һ��������
		Sheet sheet = workbook.getSheet(0);
		
		ArrayList<String> infList = new ArrayList<String>();//���������ַ�����
		String inf = "";//����������Ϣ
		
		for (int i = 1; i < sheet.getRows(); i++) {  //��
			for (int j = 0; j < sheet.getColumns(); j++) { //��
				Cell cell = sheet.getCell(j,i);
				inf += cell.getContents()+"\n"; 
			}
			infList.add(inf);
			inf = "";
		}
		workbook.close();
		return infList;
	}
	public static ArrayList<ExpressWaybill> getExpList() throws BiffException, IOException {//�õ�ȫ����ݵ���
		ArrayList<String> infList = new ArrayList<String>();//���������ַ�����
		ArrayList<ExpressWaybill> expList = new ArrayList<ExpressWaybill>();//������ݵ���
		
		infList = readExc();
		
		for (int i = 0; i < infList.size(); i++) {
			expList.add(getExpressWaybill(infList.get(i)));
		}
		return expList;
	}
	public static ExpressWaybill getExpressWaybill(String inf) {//�õ�������ݵ���Ϣ
		ExpressWaybill exp = null;
		
		Pattern pattern = Pattern.compile("(.*)\\n(.*)\\n(.*)\\n(.*)\\n(.*)\\n(.*)\\n(.*)");
		Matcher matcher = pattern.matcher(inf);
		if (matcher.find()) {
			exp = new ExpressWaybill(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6), matcher.group(7));
		}
		
		return exp;
	}
	public static void creatOrderNum(ArrayList<ExpressWaybill> expList) {//����������
		for (int i = 0; i < expList.size(); i++) {
			String randomNum = "";//�����
			for (int j = 0; j < 3; j++) {
				int num = (int)(Math.random()*10);
				randomNum += num+"";
			}
			Pattern pattern = Pattern.compile("-");
			Matcher matcher = pattern.matcher(expList.get(i).getDate());
			
			String orderNum = matcher.replaceAll("")+randomNum;//������
			
			expList.get(i).setOrderNum(orderNum);
		}
	}
	public static File makeQRCode(ExpressWaybill exp) throws WriterException, IOException {
		String content = ""; //��ά������
		String path = "C:\\Users\\Administrator\\eclipse-workspace\\java_one\\java\\src\\classend";//��ŵ�ַ
	     
	    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
	     
	    Map hints = new HashMap();
	    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	    
	    content = exp.toString();
	    String fileName = exp.getOrderNum()+".jpg";
	    BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200,hints);
	    File file = new File(path,fileName);
	    
	    MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
	    
	    return file;
	}
	public static void makeAll(ArrayList<ExpressWaybill> expList) throws WriterException, IOException, DocumentException {
		for (int i = 0; i < expList.size(); i++) {
			jpgToPdf(makeQRCode(expList.get(i)));
			//makeQRCode(expList.get(i));
		}
	}
	public static void jpgToPdf(File file) throws DocumentException, MalformedURLException, IOException {
		com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 50, 50, 50, 50);
		String fileName = file.getName();
		fileName = fileName.replaceAll(".jpg", "");
		String path = "C:\\Users\\Administrator\\eclipse-workspace\\java_one\\java\\src\\classend\\"+fileName+".pdf";
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(path));
		document.open();
		
		Image image = Image.getInstance(file.getPath());
		document.add(image);
		
		document.close();
		writer.close();
		
	}
}
