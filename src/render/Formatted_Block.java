package render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import render.visitor.Visitor;

public class Formatted_Block implements Formatted_Content {

	Map<String, String> propiedades;
	List<Formatted_Content> text;

	public Formatted_Block() {
		this.propiedades = new HashMap<>();
		this.text = new ArrayList<Formatted_Content>();
	}

	public Map<String, String> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Map<String, String> propiedades) {
		this.propiedades = propiedades;
	}

	public List<Formatted_Content> getText() {
		return text;
	}

	public void setText(List<Formatted_Content> text) {
		this.text = text;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
