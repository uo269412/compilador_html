package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Link_Content implements Ast {

	private List<Attribute> atributos;

	public Link_Content(List<Attribute> attributes) {
		this.atributos = attributes;
	}

	public List<Attribute> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Attribute> atributos) {
		this.atributos = atributos;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
