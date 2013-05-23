package com.hnfealean.sport.web.actions.product;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.csvreader.CsvReader;
import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.product.BrandManager;
import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.managers.shopping.ProductAttributeManager;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.ProductAttribute;
import com.hnfealean.sport.web.ChineseToPinyinUtil;
import com.hnfealean.sport.web.HTMLBuilder;
import com.hnfealean.sport.web.MD5;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.easypopulate.EasyPopulateForm;

public class EasyPopulateAction extends DispatchAction {
	private ProductManager productManager;
	private CategoryManager categoryManager;
	private BrandManager brandManager;
	private ProductAttributeManager productAttributeManager;

	public void setProductAttributeManager(
			ProductAttributeManager productAttributeManager) {
		this.productAttributeManager = productAttributeManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("index");
	}

	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EasyPopulateForm epf = (EasyPopulateForm) form;
		FormFile file = epf.getFile();
		if (file == null || file.getFileSize() == 0) {
			throw new SystemException("文件错误，不能为空！");
		}
		File csvFile = null;
		if (file.getFileName().toLowerCase().endsWith(".csv")) {
			String tempDirStr = request.getSession().getServletContext()
					.getRealPath("/".concat("temp"));
			File tempDir = new File(tempDirStr);
			if (!tempDir.exists())
				tempDir.mkdirs();// 如果目录不存在就创建
			csvFile = new File(tempDirStr, MD5.MD5Encode(
					(new Date()).toString()).concat(
					file.getFileName().toLowerCase()).concat(".csv"));
			FileOutputStream fos = new FileOutputStream(csvFile);
			fos.write(file.getFileData());
			fos.close();
		}
		if (epf.getType().equals(EasyPopulateForm.PRODUCT))
			uploadProduct(request, csvFile);
		else if (epf.getType().equals(EasyPopulateForm.CATEGORY))
			uploadCategory(request, csvFile);
		else if (epf.getType().equals(EasyPopulateForm.IMAGE))
			uploadImage(request, csvFile);
		else if (epf.getType().equals(EasyPopulateForm.ATTRIBUTE))
			uploadAttribute(request, csvFile);
		else if (epf.getType().equals(EasyPopulateForm.QUANTITY))
			uploadQuantity(request, csvFile);
		else if (epf.getType().equals(EasyPopulateForm.COMMEND))
			uploadCommend(request, csvFile);
		else if (epf.getType().equals(EasyPopulateForm.RELATED))
			uploadRelated(request, csvFile);
		// new CsvReader("c:/Full-EP2010Oct30-2210.csv");

		return mapping.findForward("uploadsuccess");
	}

	private boolean uploadCategory(HttpServletRequest request, File file)
			throws Exception {
		CsvReader read = new CsvReader(file.getAbsolutePath());// request.getSession().getServletContext().getRealPath("/".concat("temp/")).concat(file.getFileName().toLowerCase()));
		read.readRecord();
		String[] v = null;
		int total = 0;
		while (read.readRecord()) {
			try {
				total++;
				v = read.getValues();
				if (v[0] != null)
					v[0] = v[0].length() >= 120 ? v[0].substring(0, 119) : v[0];
				if (v[1] != null)
					v[1] = v[1].length() >= 120 ? v[1].substring(0, 119) : v[1];
				if (v[2] != null)
					v[2] = v[2].length() >= 255 ? v[2].substring(0, 254) : v[2];
				if (v[4] != null)
					v[4] = v[0].length() >= 255 ? v[4].substring(0, 254) : v[4];
				if (v[5] != null)
					v[5] = v[5].length() >= 255 ? v[5].substring(0, 254) : v[4];
				if (v[6] != null)
					v[6] = v[6].length() >= 120 ? v[6].substring(0, 119) : v[6];
				if (v[7] != null)
					v[7] = v[7].length() >= 120 ? v[7].substring(0, 119) : v[7];

				if (categoryManager.checkExist(v[0])) {// 存在此分类 v[0]
														// v_category_name
					Category c = categoryManager.getCategoryByName(v[0]);
					c.setName(v[0]); // v[0] v_name
					c.setParent(new Category(v[1])); // v[1] v_parent_name
					c.setImageUrl(v[2]); // v[2] v_category_image
					c.setDescription(v[3]); // v[3] v_detail
					c.setMeta_KeyWords(v[4]); // v[4] v_meta_keywords
					c.setMeta_Description(v[5]); // v[5] v_meta_description
					c.setTitleInPage4category(v[6]); // v[6] v_title
					c.setUrl(v[7]); // v[7] v_url
					// c.setParent(parent)
					if (v[2] == null || v[2].length() == 0) {
						c.setParent(null);
					} else {
						c.setParent(categoryManager.addEasyCategory(v[2]));
					}
					categoryManager.updateCategory(c, c.getId());
				} else {
					Category c = new Category();
					c.setName(v[0]); // v[0] v_name
					c.setImageUrl(v[1]); // v[1] v_category_image
					c.setDescription(v[3]); // v[3] v_detail
					c.setMeta_KeyWords(v[4]); // v[4] v_meta_keywords
					c.setMeta_Description(v[5]); // v[5] v_meta_description
					c.setTitleInPage4category(v[6]); // v[6] v_title

					if (v[7] == null || v[7].length() == 0) {
						c.setUrl(WebUtil.generateURL(v[0])); // v[7] v_url
					} else {
						c.setUrl(WebUtil.generateURL(v[7])); // v[7] v_url
					}
					// c.setParent(parent)
					if (v[2] == null || v[2].length() == 0) {
						c.setParent(null);
					} else {
						c.setParent(categoryManager.addEasyCategory(v[2]));
					}
					if (c.getParent() != null)
						categoryManager.addCategory(c, c.getParent().getId());
					else {
						categoryManager.addCategory(c, 0);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}
		}
		return true;
	}

	private boolean uploadProduct(HttpServletRequest request, File file)
			throws Exception {
		// InputStream is =null;

		CsvReader read = new CsvReader(file.getAbsolutePath(), ',', Charset
				.forName("gbk"));// request.getSession().getServletContext().getRealPath("/".concat("temp/")).concat(file.getFileName().toLowerCase()));
		read.readRecord();
		String[] v = null;
		int total = 0;
		while (read.readRecord()) {
			try {
				total++;
				v = read.getValues();
				if(v==null||v.length!=18) continue;
//				if (v[1] != null)
//					v[1] = v[1].length() >= 120 ? v[1].substring(0, 119) : v[1];
//				if (v[3] != null)
//					v[3] = v[3].length() >= 120 ? v[3].substring(0, 119) : v[3];
//				if (v[9] != null)
//					v[9] = v[9].length() >= 120 ? v[9].substring(0, 119) : v[9];
//				if (v[11] != null)
//					v[11] = v[11].length() >= 255 ? v[11].substring(0, 254)
//							: v[11];
//				if (v[12] != null)
//					v[12] = v[12].length() >= 255 ? v[12].substring(0, 254)
//							: v[12];
//				if (v[13] != null)
//					v[13] = v[13].length() >= 255 ? v[13].substring(0, 254)
//							: v[13];
//				if (v[17] != null)
//					v[17] = v[17].length() >= 255 ? v[17].substring(0, 254)
//							: v[17];
				if (productManager.checkExist(v[0]) == 0) {
					Product p = new Product();
					p.setCode(v[0]);// 货号 v_products_model
					p.setName(v[1]);// 名称 v_products_name_1
					p.setDetail(v[2]);// 详细描述 v_products_description_1
					if (v[3] == null || v[3].length() == 0) {
						
						p.setShtml_File_Name(ChineseToPinyinUtil.getFullSpell(WebUtil.generateURL(v[1])));// 文件名称
															// v_products_url_1
					} else {
						
						p.setShtml_File_Name(ChineseToPinyinUtil.getFullSpell(WebUtil.generateURL(v[3])));// 文件名称
															// v_products_url_1
					}
					try {
						p.setSellPrice(Float.valueOf(v[4]));// 销售价 v_products_price
						p.setWeight(Integer.valueOf(v[5]));// 重量 v_products_weight
						// p.setExpireDate(Date.valueOf(v[6]));//失效日期 v_date_avail
						// p.setCreateDate(Date.valueOf(v[7]));//添加日期 v_date_added
						p.setBasePrice(Float.valueOf(v[14]));// 底价，采购价 v_base_price
						p.setMarketPrice(Float.valueOf(v[15]));// 市场价 v_market_price 

						p.setQuantity(Integer.valueOf(v[8]));// 库存
																// v_products_quantity
						p.setVisible(Boolean.valueOf(v[10]));// 是否可见 v_status
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					Category category = new Category(categoryManager
							.addEasyPopulate(v[9].trim()));
					p.setCategory(category);// 分类 v_categories_name 对象
				
					if (v[11] == null || v[11].length() == 0) {
						p.setTitleInPage(p.getName());
					} else {
						p.setTitleInPage(v[11]);// 页面标题 v_title
					}
					if (v[12] == null || v[12].length() == 0) {
						p.setMeta_Description(p.getName());// meta描述
															// v_meta_description
					} else {
						p.setMeta_Description(v[12]);// meta描述
														// v_meta_description
					}
					if (v[13] == null || v[13].length() == 0) {
						p.setMeta_KeyWords(p.getName());// meta关键词
														// v_meta_keywords
					} else {
						p.setMeta_KeyWords(v[13]);// meta关键词 v_meta_keywords
					}
					// System.out.println(v[13]);
					// p.setBasePrice(Float.valueOf(v[13]));//底价，采购价
					// v_base_price
					// p.setMarketPrice(200.f);//市场价 v_market_price
					Brand brand = new Brand(brandManager.addEasyPopulate(v[16]));
					p.setBrand(brand);// 品牌 v_brand 对象

					ImagesAndStyle productStyle = new ImagesAndStyle();
					productStyle.setImageUrl(v[17]);

					productStyle.setName((new Date()).toString());
					Set<ImagesAndStyle> style = new HashSet<ImagesAndStyle>();
					style.add(productStyle);
					p.setImagesAndStyles(style);
					p.setVisible(true);
					productManager.addProduct(p, brand.getId(), category
							.getId());
					List<Category> position = productManager.getPositon(p
							.getCategory().getTitleInPage4category());
					File saveDir = new File(request.getSession()
							.getServletContext().getRealPath("/product"));

					HTMLBuilder.buildHtml(p, saveDir, position,
							ConstantString.PRODUCT);
				} else {
					Product p = productManager.findProduct(v[0]);
					// p.setShtml_File_Name(java.net.URLEncoder.encode(p.getShtml_File_Name()));
					p.setShtml_File_Name(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(p
							.getShtml_File_Name())));
					// p.setShtml_File_Name(p.getShtml_File_Name().replaceAll(" ",
					// "_"));
					productManager.updateProduct(p, p.getId());
					List<Category> position = productManager.getPositon(p
							.getCategory().getTitleInPage4category());
					File saveDir = new File(request.getSession()
							.getServletContext().getRealPath("/product"));
					// System.out.println(ExcelTest.class.getResource("").getPath());
					HTMLBuilder.buildHtml(p, saveDir, position,
							ConstantString.PRODUCT);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}

		}
		return true;
	}

	private boolean uploadImage(HttpServletRequest request, File file)
			throws Exception {
		CsvReader read = new CsvReader(file.getAbsolutePath());
		// request.getSession().getServletContext().getRealPath("/".concat("temp/")).concat(file.getFileName().toLowerCase()));
		read.readRecord();
		String[] v = null;
		int total = 0;
		while (read.readRecord()) {
			try {
				total++;
				v = read.getValues();
				if (v[1] != null)
					v[1] = v[1].length() >= 120 ? v[1].substring(0, 119) : v[1];
				if (v[2] != null)
					v[2] = v[2].length() >= 255 ? v[2].substring(0, 254)
							: v[2];
				if (v[3] != null)
					v[3] = v[3].length() >= 50 ? v[3].substring(0, 49) : v[3];
				int productId = productManager.checkExist(Integer.valueOf(v[0]));
				if (productId == 0) {

				} else {
					// productManager.findProduct(v[0])
					if (productManager.checkImage(productId, v[2]) == false) {
						ImagesAndStyle image = new ImagesAndStyle();
						image.setImageUrl(v[2]);// v[2] v_image_url
						image.setName(v[3]); // v[3] v_image_name
						image.setProduct(new Product(productId)); //
						image.setVisible(true);
						productManager.addImagesAndStyle(image);
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		return true;
	}

	private boolean uploadAttribute(HttpServletRequest request, File file)
			throws Exception {
		CsvReader read = new CsvReader(file.getAbsolutePath());
		// request.getSession().getServletContext().getRealPath("/".concat("temp/")).concat(file.getFileName().toLowerCase()));
		read.readRecord();
		String[] v = null;
		int total = 0;
		while (read.readRecord()) {
			total++;
			v = read.getValues();
			int productId = productManager.checkExist(v[0]);
			if (productId == 0) {

			} else {
				// ProductAttribute attri = new ProductAttribute();
				int attributeId = productManager
						.updateCheckExistAttribute(v[2]);
				if (attributeId > 0) {
					// v[2] v_attribute_name
					int optionId = productManager.checkExistAttributeOption(
							v[3], productId, attributeId);
					if (optionId == 0) {// 没有此属性,新建
						AttributeOption option = new AttributeOption();
						option.setProduct(new Product(productId));
						option.setAttribute(new ProductAttribute(attributeId));
						option.setValue(v[3]); // v[3] v_attribute_value
						productManager.addAttributeOption(option);
					} else {// 已有属性，更新
						/*
						 * AttributeOption option = new AttributeOption();
						 * option.setProduct(new Product(productId));
						 * option.setId(optionId); option.setAttribute(new
						 * ProductAttribute(attributeId));
						 * option.setValue(v[3]);
						 */
					}
				}
			}
		}
		return true;
	}

	private boolean uploadQuantity(HttpServletRequest request, File file)
			throws Exception {
		CsvReader read = new CsvReader(file.getAbsolutePath());
		// request.getSession().getServletContext().getRealPath("/".concat("temp/")).concat(file.getFileName().toLowerCase()));
		read.readRecord();
		String[] v = null;
		int total = 0;
		while (read.readRecord()) {
			total++;
			v = read.getValues();
			int productId = productManager.checkExist(v[0]); // v[0]
																// v_product_model
			if (productId == 0) {

			} else {
				productManager.updateQuantity(productId, Integer.valueOf(v[2]));// v[2]
																				// v_quantity
			}
		}
		return true;
	}

	private boolean uploadCommend(HttpServletRequest request, File file)
			throws Exception {
		return true;
	}

	private boolean uploadRelated(HttpServletRequest request, File file)
			throws Exception {
		return true;
	}
}
