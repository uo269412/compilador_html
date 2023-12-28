package simpleCss.ast;

import java.util.ArrayList;
import java.util.List;

import simpleCss.visitor.Visitor;

public class Css_File implements AstCss {

	List<Etiqueta> tags = new ArrayList<Etiqueta>();

	public Css_File(List<Etiqueta> etiquetas) {
		this.tags = etiquetas;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public List<Etiqueta> getTags() {
		return tags;
	}

	public void setTags(List<Etiqueta> tags) {
		this.tags = tags;
	}

}
