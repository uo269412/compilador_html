package simpleCss.ast;

import simpleCss.visitor.Visitor;

public class Ul implements Etiqueta {

	private List_Style propiedad;

	public Ul(List_Style propiedades) {
		this.propiedad = propiedades;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public List_Style getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(List_Style propiedad) {
		this.propiedad = propiedad;
	}

}
