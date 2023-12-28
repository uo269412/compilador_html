package simpleCss.visitor;

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

public class PrintVisitor implements Visitor{

	@Override
	public Object visit(Color c, Object param) {
		System.out.println("		------COLOR------");
		System.out.println("			" + c.getValor());
		System.out.println("		------/COLOR-----");
		return null;
	}

	@Override
	public Object visit(Css_File c, Object param) {
		System.out.println("------CSS------");
		for (Etiqueta e : c.getTags()) {
			e.accept(this, param);
		}
		System.out.println("------/CSS-----");
		return null;
	}

	@Override
	public Object visit(Font_Size f, Object param) {
		System.out.println("		------FONT_SIZE------");
		System.out.println("			" + f.getValor());
		System.out.println("		------/FONT_SIZE-----");
		return null;
	}

	@Override
	public Object visit(Font_Style f, Object param) {
		System.out.println("		------FONT_STYLE------");
		System.out.println("			" + f.getValor());
		System.out.println("		------/FONT_STYLE-----");
		return null;
	}

	@Override
	public Object visit(H1 h, Object param) {
		System.out.println("	------H1------");
		for (Propiedad p : h.getPropiedades()) {
			p.accept(this, param);
		}
		System.out.println("	------/H1-----");
		return null;
	}

	@Override
	public Object visit(H2 h, Object param) {
		System.out.println("	------H2------");
		for (Propiedad p : h.getPropiedades()) {
			p.accept(this, param);
		}
		System.out.println("	------/H2-----");
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		System.out.println("	------P-------");
		for (Propiedad p2 : p.getPropiedades()) {
			p2.accept(this, param);
		}
		System.out.println("	------/P-----");
		return null;
	}

	@Override
	public Object visit(Text_Align t, Object param) {
		System.out.println("		------TEXT_ALIGN------");
		System.out.println("			" + t.getValor());
		System.out.println("		------/TEXT_ALIGN-----");
		return null;
	}

	@Override
	public Object visit(Ul ul, Object param) {
		System.out.println("	------UL-------");
		ul.getPropiedad().accept(this, param);
		System.out.println("	------/UL-----");
		return null;
	}

	@Override
	public Object visit(Dl dl, Object param) {
		System.out.println("		------DL-------");
		for (Propiedad p2 : dl.getPropiedades()) {
			p2.accept(this, param);
		}
		System.out.println("		------/DL-----");
		return null;
	}

	@Override
	public Object visit(List_Style list_Style, Object param) {
		System.out.println("		------LIST_STYLE------");
		System.out.println("			" + list_Style.getValor());
		System.out.println("		------/LIST_STYLE-----");
		return null;
	}

}
