package simpleCss.ast;

import simpleCss.visitor.Visitor;

public class Font_Style implements Propiedad {

	private String valor;

	public Font_Style(String valor2) {
		this.valor = valor2;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
