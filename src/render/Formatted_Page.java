package render;

import java.util.ArrayList;
import java.util.List;

import render.visitor.Visitor;

public class Formatted_Page implements Page {

	List<Formatted_Content> lines = new ArrayList<Formatted_Content>();
	String pageTitle;

	public List<Formatted_Content> getLines() {
		return lines;
	}

	public void setLines(List<Formatted_Content> lines) {
		this.lines = lines;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
