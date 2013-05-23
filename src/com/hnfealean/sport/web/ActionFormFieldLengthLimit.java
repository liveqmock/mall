package com.hnfealean.sport.web;

public class ActionFormFieldLengthLimit {

	/**
	 * CountryForm
	 */
	private int countryForm_cnName=20;
	private int countryForm_enName=20;
	private int countryForm_countries_iso_code_2=5;
	private int countryForm_countries_iso_code_3=5;
	private int zoneForm_code=3;
	private int zoneForm_name=30;
	/**
	 * ArticleForm
	 */
	private int articleForm_title=60;
	private int articleForm_keywords=60;
	private int articleForm_description=120;
	private int articleForm_url=60;
	private int articleForm_name=60;
	/**
	 * FrontArticleForm
	 */
	private int frontArticleForm_type=10;
	private int frontArticleForm_url=60;

	/**
	 * CommentForm
	 */
	private int commentForm_content = 2000;
	
	/**
	 * CouponActionForm
	 */
	private int counponActionForm_name=64;
	private int counponActionForm_id = 64;
	
	/**
	 * FrontCounponActionForm
	 */
	private int frontCounponActionForm_id=64;
	/**
	 * DeliverTypeActionForm
	 */
	private int deliverTypeActionForm_description=255;
	private int deliverTypeActionForm_name=50;
	
	/**
	 * DistributionActionForm
	 */
	private int distributionActionForm_distributionTemplateName=30;
	private int distributionActionForm_distributionTemplateDescription=255;
	private int distributionActionForm_deliverTypeName=50;
	private int distributionActionForm_zoneName=30;
	/**
	 * GlobalDeliverModuleActionForm
	 */
	private int globalDeliverModuleActionForm_globalDistributionTemplateName=255;
	private int globalDeliverModuleActionForm_zoneName=30;
	/**
	 * EasyPopulateForm
	 */
	/**none**/
	
	/**
	 * EmailActionForm
	 */
	private int emailActionForm_address=50;
	private int emailActionForm_smtpHost=50;
	private int emailActionForm_smtpPort=5;
	private int emailActionForm_username=50;
	private int emailActionForm_password=50;
	/**
	 * ImageFileActonForm
	 */
	private int imageFileActionForm_filePath=255;
	
	/**
	 * AlipayForm YeepayForm PaypalForm
	 */
	
	/**
	 * BrandActionForm
	 */
	private int brandActionForm_id=32;
	private int brandActionForm_logoUrl=100;
	private int brandActionForm_name=30;
	
	/**
	 * CategoryActionForm
	 */
	private int categoryActionForm_name=120;
	private int categoryActionForm_description=1000;
	private int categoryActionForm_meta_Description=255;
	private int categoryActionForm_meta_KeyWords=255;
	private int categoryActionForm_titleInPage4category=120;
	private int categoryActionForm_imageUrl=255;
	private int categoryActionForm_url=120;
	private int categoryActionForm_addedType=120;
	private int categoryActionForm_productIds=1000;
	
	/**
	 * CategoryAttributeForm
	 */
	private int categoryAttributeForm_name=20;
	private int categoryAttributeForm_value=30;
	private int categoryAttributeForm_filter=255;
	
}
