package model;

public class Announcement {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private String title;
	private String text;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Announcement(String title, String text) {
		super();
		this.title = title;
		this.text = text;
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Announcement [title=" + title + ", text=" + text + "]";
	}
}
