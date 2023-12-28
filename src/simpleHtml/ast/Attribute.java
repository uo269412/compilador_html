package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Attribute implements Ast {

	private String atributo;
	private String cadena;

	public Attribute(String lexeme, String lexeme2) {
		this.atributo = lexeme;
		this.cadena = lexeme2;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
