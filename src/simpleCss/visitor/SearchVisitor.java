package simpleCss.visitor;

import simpleCss.ast.AstCss;
import simpleCss.ast.Color;
import simpleCss.ast.Css_File;
import simpleCss.ast.Dl;
import simpleCss.ast.Etiqueta;
import simpleCss.ast.Font_Size;
import simpleCss.ast.Font_Style;
import simpleCss.ast.H1;
import simpleCss.ast.H2;
import simpleCss.ast.List_Style;
import simpleCss.ast.P;
import simpleCss.ast.Propiedad;
import simpleCss.ast.Text_Align;
import simpleCss.ast.Ul;

public class SearchVisitor implements Visitor {

	String ident;
	String label;

	@Override
	public Object visit(Color c, Object param) {
		if (label.equals("color")) {
			return c.getValor();
		}
		return null;
	}

	@Override
	public Object visit(Css_File c, Object param) {
		String valor = null;
		for (Etiqueta e : c.getTags()) {
			valor = (String) e.accept(this, param);
			if (valor != null) {
				return valor;
			}
		}
		return null;
	}

	@Override
	public Object visit(Font_Size f, Object param) {
		if (label.equals("font_size")) {
			return f.getValor();
		}
		return null;
	}

	@Override
	public Object visit(Font_Style f, Object param) {
		if (label.equals("font_style")) {
			return f.getValor();
		}
		return null;
	}

	@Override
	public Object visit(H1 h, Object param) {
		String valor = "";
		if (ident.equals("h1")) {
			for (Propiedad p : h.getPropiedades()) {
				valor = (String) p.accept(this, param);
				if (valor != null) {
					return valor;
				}
			}
		}
		return null;
	}

	@Override
	public Object visit(H2 h, Object param) {
		String valor = "";
		if (ident.equals("h2")) {
			for (Propiedad p : h.getPropiedades()) {
				valor = (String) p.accept(this, param);
				if (valor != null) {
					return valor;
				}
			}
		}
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		String valor = "";
		if (ident.equals("p")) {
			for (Propiedad p2 : p.getPropiedades()) {
				valor = (String) p2.accept(this, param);
				if (valor != null) {
					return valor;
				}
			}
		}
		return null;
	}

	@Override
	public Object visit(Text_Align t, Object param) {
		if (label.equals("text_align")) {
			return t.getValor();
		}
		return null;
	}

	public String search(String ident, String label, AstCss prog) {
		this.ident = ident;
		this.label = label;
		return (String) prog.accept(this, label);
	}

	@Override
	public Object visit(Ul ul, Object param) {
		String valor = "";
		if (ident.equals("ul")) {
			valor = (String) ul.getPropiedad().accept(this, param);
			if (valor != null) {
				return valor;
			}
		}
		return null;
	}

	@Override
	public Object visit(Dl dl, Object param) {
		String valor = "";
		if (ident.equals("dl")) {
			for (Propiedad p2 : dl.getPropiedades()) {
				valor = (String) p2.accept(this, param);
				if (valor != null) {
					return valor;
				}
			}
		}
		return null;
	}

	@Override
	public Object visit(List_Style list_Style, Object param) {
		if (label.equals("list-style-type")) {
			return list_Style.getValor();
		}
		return null;
	}

}
