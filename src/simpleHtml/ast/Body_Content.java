package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Body_Content implements Ast {

	private List<Body_Data> contenido;

	public Body_Content(List<Body_Data> bd) {
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
