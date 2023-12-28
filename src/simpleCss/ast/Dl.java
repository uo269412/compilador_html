package simpleCss.ast;

import java.util.ArrayList;
import java.util.List;

import simpleCss.visitor.Visitor;

public class Dl implements Etiqueta {

	private List<Propiedad> propiedades = new ArrayList<Propiedad>();

	public Dl(List<Propiedad> propiedades2) {
		this.propiedades = propiedades2;
	}


	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}


	public List<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}
	
	

}
