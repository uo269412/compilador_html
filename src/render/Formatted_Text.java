package render;

import render.visitor.Visitor;

public class Formatted_Text implements Page {

	String color;
	double font_size;
	String font_style;
	double metrics = -1;
	String text;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getFont_size() {
		return font_size;
	}

	public void setFont_size(double font_size) {
		this.font_size = font_size;
	}

	public String getFont_style() {
		return font_style;
	}

	public void setFont_style(String font_style) {
		this.font_style = font_style;
	}

	public double getMetrics() {
		if (text != null) {
			metrics = text.length() * font_size;
		}
		return metrics;
	}

	public void setMetrics(double metrics) {
		this.metrics = metrics;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
