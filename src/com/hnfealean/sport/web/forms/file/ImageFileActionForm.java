package com.hnfealean.sport.web.forms.file;

import org.apache.struts.action.ActionForm;

public class ImageFileActionForm extends ActionForm {

	private String filePath;
	private int outputHeight;
	private int outputWidth;

	public int getOutputHeight() {
		return outputHeight;
	}

	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}

	public int getOutputWidth() {
		return outputWidth;
	}

	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
