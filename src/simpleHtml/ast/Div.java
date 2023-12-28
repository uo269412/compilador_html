package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Div implements Body_Data {

	private List<Body_Data> contenido;

	public Div(List<Body_Data> bd) {
		this.contenido = bd;
	}

	public List<Body_Data> getContenido() {
		return contenido;
	}

	public void setContenido(List<Body_Data> contenido) {
		this.contenido = contenido;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
