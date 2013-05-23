package com.hnfealean.test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.hnfealean.sport.managers.product.BrandManager;
import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.managers.shopping.ProductAttributeManager;
import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.web.HTMLBuilder;
public class ExcelTest {
	public static ProductManager productManager;
	public static CategoryManager categoryManager;
	public static BrandManager brandManager;
	public static ProductAttributeManager productAttributeManager;
@BeforeClass
public static void setUpBeforeClass() throws Exception {
	
	try {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		productManager = (ProductManager)cxt.getBean("productManager");
		categoryManager = (CategoryManager)cxt.getBean("categoryManager");
		brandManager = (BrandManager)cxt.getBean("brandManager");
		productAttributeManager = (ProductAttributeManager) cxt.getBean("productAttributeManager");
		//zm = (ZoneManager)cxt.getBean("zoneManager");
		//countryManager=(CountryManager) cxt.getBean("countryManager");
	} catch (Exception e) {
		e.printStackTrace();
	}

	try {
		HSSFWorkbook wb = new HSSFWorkbook();
		//基于上面的WorkBook创建属于此WorkBook的Sheet，
		//3.0.1版在使用全角Sheet名的时候不必再setEncdoing了，个人感觉方便了许多。
		HSSFSheet st = wb.createSheet("测试页");
		//创建属于上面Sheet的Row，参数0可以是0～65535之间的任何一个，
		//注意，尽管参数是Int类型，但是Excel最多支持65536行
		HSSFRow row = st.createRow(0);
		//创建属于上面Row的Cell，参数0可以是0～255之间的任何一个，
		//同样，是因为Excel最大支持的列数为256列
		HSSFCell cell = row.createCell((short) 0);
		//设置此单元格的格式为文本，此句可以省略，Excel会自动识别。
		//其他还有几种常用的格式，请参考本文底部的补充部分。
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		//此处是3.0.1版的改进之处，上一版可以直接setCellValue("Hello, World!")，
		//但是在3.0.1里，被deprecated了。
		cell.setCellValue(new HSSFRichTextString("Hello, World!"));
		//创建一个文件输出流，指定到C盘根目录下（C盘都有吧？）
		//xls是Excel97-2003的标准扩展名，2007是xlsx，目前的POI能直接生产的还是xls格式，
		//如果此处把扩展名改成xlsx，在用Excel2007打开此文件时会报错。
		FileOutputStream writeFile = new FileOutputStream("c:/helloworld.xls");
		//把WorkBook写到流里
		wb.write(writeFile);
		//记得手动关闭流，官方文档已经做了特别说明，说POI不负责关闭用户打开的流。所以...
		writeFile.close();
		//上面就是创建一个新文档的简易代码，下面的例子是读取刚才创建的Excel并把读取到的内容显示在控制台上。


		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
@Test public void getStock() throws Exception{
	CsvReader read = new CsvReader("c:/Full-EP2010Oct30-1705.csv");
	read.readRecord();
	FileOutputStream outStream = new FileOutputStream(new File("c:/test.txt"));
	OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
	BufferedWriter sw = new BufferedWriter(writer);
	StringBuffer sb = new StringBuffer();

//	System.out.println(read.getRawRecord());
//	System.out.println(read.get("v_products_model"));
//	System.out.println(read.getColumnCount());
	String[] v = null ;
	int total=0;
	while(read.readRecord()){
		total++;
	v = read.getValues();
//	System.out.println(v.length);
//	for(int i=0;i<v.length-1;i++){
	
	/*	v_products_model
		v_products_image		-->可以删掉
		v_products_name_1
		v_products_description_1
		v_products_url_1	
		v_specials_price		->可以删掉
		v_specials_date_avail	->可以删掉
		v_specials_expires_date		->可以删掉
		v_products_price
		v_products_weight
		v_date_avail
		v_date_added	
		v_products_quantity
		v_manufacturers_name		->可以删掉
		v_categories_name_1
		v_categories_name_2
		v_categories_name_3	
		v_categories_name_4
		v_categories_name_5
		v_categories_name_6	
		v_categories_name_7
		v_tax_class_title
		v_status	
		v_metatags_products_name_status	
		v_metatags_title_status	
		v_metatags_model_status	
		v_metatags_price_status	
		v_metatags_title_tagline_status	
		v_metatags_title_1	
		v_metatags_keywords_1	
		v_metatags_description_1
*/
	if(productManager.checkExist(v[0])==0){
		Product p = new Product();
		p.setCode(v[0]);//货号	v_products_model
		if(v[1].length()>50)
			p.setName(v[1].substring(0, 49));//名称	v_products_name_1
		else{
			p.setName(v[1]);//名称	v_products_name_1
		}
		p.setDetail(v[2]);//详细描述		v_products_description_1
		if(v[10]==null||v[10].length()==0){
			p.setShtml_File_Name(v[1]);//文件名称	v_products_url_1
		}else
			p.setShtml_File_Name(v[3]);//文件名称	v_products_url_1
		p.setSellPrice(Float.valueOf(v[4]));//销售价	v_products_price
		p.setWeight(Integer.valueOf(v[5]));//重量		v_products_weight
	//	p.setExpireDate(Date.valueOf(v[6]));//失效日期		v_date_avail
	//	p.setCreateDate(Date.valueOf(v[7]));//添加日期		v_date_added
		p.setQuantity(Integer.valueOf(v[8]));//库存		v_products_quantity
		Category category = new Category(categoryManager.addEasyPopulate(v[9].trim()));
		p.setCategory(category);//分类		v_categories_name		对象
		p.setVisible(Boolean.valueOf(v[10]));//是否可见		v_status
		if(v[10]==null||v[10].length()==0){
			p.setTitleInPage(p.getName());
		}else{
		
		p.setTitleInPage(v[10]);//页面标题		v_title
		}
		if(v[11]==null||v[11].length()==0){
			p.setMeta_Description(p.getName());//meta描述	v_meta_description
		}else{
			p.setMeta_Description(v[11]);//meta描述	v_meta_description
		}
		if(v[12]==null||v[12].length()==0){
			p.setMeta_KeyWords(p.getName());//meta关键词		v_meta_keywords
		}else{
			p.setMeta_KeyWords(v[12]);//meta关键词		v_meta_keywords
		}
		//System.out.println(v[13]);
		//p.setBasePrice(Float.valueOf(v[13]));//底价，采购价		v_base_price
		p.setBasePrice(100.00f);//底价，采购价		v_base_price
	//	p.setMarketPrice(Float.valueOf(v[14]));//市场价		v_market_price
		p.setMarketPrice(200.f);//市场价		v_market_price
		Brand brand  = new Brand(brandManager.addEasyPopulate(v[15]));
		p.setBrand(brand);//品牌		v_brand		对象
		
		ImagesAndStyle productStyle = new ImagesAndStyle();
		productStyle.setImageUrl("google");
		
		productStyle.setName((new Date()).toString());
		//productStyle.setImageUrl("/images/doctemp.gif");
		
		//product.setBrand()
		//product.setProductStyles(productStyles)
		Set<ImagesAndStyle> style= new HashSet<ImagesAndStyle>();
		style.add(productStyle);
		p.setImagesAndStyles(style);
		p.setVisible(true);
		productManager.addProduct(p,brand.getId(),category.getId());
		List<Category> position = productManager.getPositon(p.getCategory().getTitleInPage4category());
		File saveDir = new File(ExcelTest.class.getResource("").getPath());
		System.out.println(ExcelTest.class.getResource("").getPath());
		HTMLBuilder.buildHtml(p, saveDir, position,"Product");
}else{
	Product p = productManager.findProduct(v[0]);
	List<Category> position = productManager.getPositon(p.getCategory().getTitleInPage4category());
	File saveDir = new File("C:/tomcat/webapps/angelinthebox/product");
//	System.out.println(ExcelTest.class.getResource("").getPath());
	HTMLBuilder.buildHtml(p, saveDir, position,"Product");
}
	//	productManager.addProduct(p);
	//	p.setAdditionInfo(null);
	//	p.setClickCount(clickCount);//浏览次数
		
	//	p.setCommend(commend);//是否推荐
	//	p.setImagesAndStyles(imagesAndStyles);//图片
	//	p.setManufacturers(manufacturers);//生产厂商
	//	p.setModel(model);//型号
	//	p.setOptions(options);//属性
	//	p.setSellCount(sellCount);//销售数量
	//	p.setSex(sex);//适合性别
	//	p.setSizes(sizes);//尺寸
		
		
		
		
	//		System.out.print(v[1]+"\t");
		//sb.append(v[i]+"\t");
	//	v["hell"];
//	}
	
//	System.out.print("\n");
//	sb.append("\n");
	}
	sw.write(sb.toString());
	sw.flush();
	sw.close();
	CsvWriter csvWriter = new CsvWriter("c:/testcsv.csv");
//	System.out.println(v.length+sb.toString());
//	
	csvWriter.writeRecord(sb.toString().split("\t"));
	csvWriter.flush();
	csvWriter.close();
/*	//read.get
//	while(read.getRawRecord()!=null)
	//	System.out.println(read.getValues().length);
	//指定要读取的文件，本例使用上面生成的helloworld.xls
	FileInputStream readFile = new FileInputStream("c:/helloworld.xls");
	//创建一个WorkBook，从指定的文件流中创建，即上面指定了的文件流
	HSSFWorkbook wb = new HSSFWorkbook(readFile);
	//获取名称为“测试页”的sheet
	//注意，如果不能确定具体的名称，可以用getSheetAt(int)方法取得Sheet
	HSSFSheet st = wb.getSheet("测试页");
	//获得第一行，同上，如果此行没有被创建过则抛出异常
	HSSFRow row = st.getRow(0);
	//获取第一个单元格，如果没有被创建过则抛出异常
	HSSFCell cell = row.getCell((short) 0);
	//把cell中的内容按字符串方式读取出来，并显示在控制台上
	//注意，getRichStringCellValue()方法是3.0.1新追加的，
	//老版本中的getStringCellValue()方法被deprecated了
	System.out.println(cell.getRichStringCellValue());
	//记得关闭流
	readFile.close();*/
}
}
