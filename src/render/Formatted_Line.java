package render;

import java.util.ArrayList;
import java.util.List;

import render.visitor.Visitor;


public class Formatted_Line implements Formatted_Content{

	String text_align;
	List<Formatted_Text> text;

	public Formatted_Line() {
		super();
		this.text = new ArrayList<Formatted_Text>();
	}

	public String getText_align() {
		return text_align;
	}

	public void setText_align(String text_align) {
		this.text_align = text_align;
	}

	public List<Formatted_Text> getText() {
		return text;
	}

	public void setText(List<Formatted_Text> text) {
		this.text = text;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
