package com.hnfealean.sport.model.file;

public class ImageFile {

	private String name;
	
	private boolean isDirectory=false;

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsDirectory() {
		return isDirectory;
	}

	public void setIsDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
}
