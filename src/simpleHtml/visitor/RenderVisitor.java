package simpleHtml.visitor;

import render.Formatted_Block;
import render.Formatted_Content;
import render.Formatted_Line;
import render.Formatted_Page;
import render.Formatted_Text;
import simpleCss.ast.AstCss;
import simpleCss.visitor.SearchVisitor;
import simpleHtml.ast.Ast;
import simpleHtml.ast.Attribute;
import simpleHtml.ast.Body_Content;
import simpleHtml.ast.Body_Data;
import simpleHtml.ast.Bold;
import simpleHtml.ast.Dl;
import simpleHtml.ast.Div;
import simpleHtml.ast.H1;
import simpleHtml.ast.H2;
import simpleHtml.ast.Header_Content;
import simpleHtml.ast.Html_File;
import simpleHtml.ast.Italic;
import simpleHtml.ast.Link_Content;
import simpleHtml.ast.P;
import simpleHtml.ast.Text;
import simpleHtml.ast.Text_Content;
import simpleHtml.ast.Title_Content;
import simpleHtml.ast.Ul;
import simpleHtml.ast.Underline;

public class RenderVisitor implements Visitor {

	Ast htmlAst;
	AstCss customCssAst;
	AstCss defaultCssAst;
	SearchVisitor sv;
	Formatted_Page fp;

	public RenderVisitor(Ast htmlAst, AstCss customCssAst, AstCss defaultCssAst) {
		this.fp = new Formatted_Page();
		this.htmlAst = htmlAst;
		this.customCssAst = customCssAst;
		this.defaultCssAst = defaultCssAst;
		this.sv = new SearchVisitor();
	}

	@Override
	public Object visit(Attribute attribute, Object param) {
		return null;
	}

	@Override
	public Object visit(Body_Content bc, Object param) {
		for (Body_Data bd : bc.getContenido()) {
			fp.getLines().add((Formatted_Content) bd.accept(this, param));
		}
		return null;
	}

	@Override
	public Object visit(Bold bold, Object param) {
		Formatted_Text text = new Formatted_Text();
		text.setFont_style("bold");
		String color = sv.search("p", "color", customCssAst);
		if (color == null) {
			color = sv.search("p", "color", defaultCssAst);
		}
		text.setColor(color);
		String font_size = sv.search("p", "font_size", customCssAst);
		if (font_size == null) {
			font_size = sv.search("p", "font_size", defaultCssAst);
		}
		if (font_size != null) {
			text.setFont_size(Double.parseDouble(font_size.split("px")[0]));
		}
		text.setText(bold.getText().toString());
		return text;
	}

	@Override
	public Object visit(H1 h1, Object param) {
		Formatted_Line line = new Formatted_Line();
		String foundValue = "";
		foundValue = sv.search("h1", "text_align", customCssAst);
		if (foundValue == null) {
			foundValue = sv.search("h1", "text_align", defaultCssAst);
		}
		line.setText_align(foundValue);

		for (Text_Content tc : h1.getContent()) {
			line.getText().add((Formatted_Text) tc.accept(this, "h1"));
		}
		return line;
	}

	@Override
	public Object visit(H2 h2, Object param) {
		Formatted_Line line = new Formatted_Line();
		String foundValue = "";
		foundValue = sv.search("h2", "text_align", customCssAst);
		if (foundValue == null) {
			foundValue = sv.search("h2", "text_align", defaultCssAst);
		}
		line.setText_align(foundValue);

		for (Text_Content tc : h2.getContent()) {
			line.getText().add((Formatted_Text) tc.accept(this, "h2"));
		}
		return line;
	}

	@Override
	public Object visit(Header_Content hc, Object param) {
		hc.getLink_content().accept(this, param);
		hc.getTitle_content().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Html_File hf, Object param) {
		hf.getHeader().accept(this, param);
		hf.getBody().accept(this, param);
		return fp;
	}

	@Override
	public Object visit(Italic i, Object param) {
		Formatted_Text text = new Formatted_Text();
		text.setFont_style("italic");
		String color = sv.search("p", "color", customCssAst);
		if (color == null) {
			color = sv.search("p", "color", defaultCssAst);
		}
		text.setColor(color);
		String font_size = sv.search("p", "font_size", customCssAst);
		if (font_size == null) {
			font_size = sv.search("p", "font_size", defaultCssAst);
		}
		if (font_size != null) {
			text.setFont_size(Double.parseDouble(font_size.split("px")[0]));
		}
		text.setText(i.getText().toString());
		return text;
	}

	@Override
	public Object visit(Link_Content lc, Object param) {
		for (Attribute a : lc.getAtributos()) {
			a.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		Formatted_Line line = new Formatted_Line();
		String foundValue = "";
		foundValue = sv.search("p", "text_align", customCssAst);
		if (foundValue == null) {
			foundValue = sv.search("p", "text_align", defaultCssAst);
		}
		line.setText_align(foundValue);

		for (Text_Content tc : p.getContent()) {
			line.getText().add((Formatted_Text) tc.accept(this, "p"));
		}
		return line;
	}

	@Override
	public Object visit(Text t, Object param) {
		Formatted_Text text = new Formatted_Text();
		String font_style = sv.search((String) param, "font_style", customCssAst);
		if (font_style == null) {
			font_style = sv.search((String) param, "font_style", defaultCssAst);
		}
		text.setFont_style(font_style);
		String color = sv.search((String) param, "color", customCssAst);
		if (color == null) {
			color = sv.search((String) param, "color", defaultCssAst);
		}
		text.setColor(color);
		String font_size = sv.search((String) param, "font_size", customCssAst);
		if (font_size == null) {
			font_size = sv.search((String) param, "font_size", defaultCssAst);
		}
		if (font_size != null) {
			text.setFont_size(Double.parseDouble(font_size.split("px")[0]));
		}

		text.setText(t.getText().toString());
		return text;
	}

	@Override
	public Object visit(Title_Content tc, Object param) {
		fp.setPageTitle(tc.getText().toString());
		return null;
	}

	@Override
	public Object visit(Underline u, Object param) {
		Formatted_Text text = new Formatted_Text();
		text.setFont_style("underline");
		String color = sv.search("p", "color", customCssAst);
		if (color == null) {
			color = sv.search("p", "color", defaultCssAst);
		}
		text.setColor(color);
		String font_size = sv.search("p", "font_size", customCssAst);
		if (font_size == null) {
			font_size = sv.search("p", "font_size", defaultCssAst);
		}
		if (font_size != null) {
			text.setFont_size(Double.parseDouble(font_size.split("px")[0]));
		}
		text.setText(u.getText().toString());
		return text;
	}

	@Override
	public Object visit(Div div, Object param) {
		Formatted_Block block = new Formatted_Block();
		for (Body_Data bd : div.getContenido()) {
			block.getText().add((Formatted_Content) bd.accept(this, param));
		}
		return block;
	}

	@Override
	public Object visit(Dl li, Object param) {
		Formatted_Line line = new Formatted_Line();
		String foundValue = "";
		foundValue = sv.search("dl", "text_align", customCssAst);
		if (foundValue == null) {
			foundValue = sv.search("dl", "text_align", defaultCssAst);
		}
		line.setText_align(foundValue);

		for (Text_Content tc : li.getContent()) {
			line.getText().add((Formatted_Text) tc.accept(this, "dl"));
		}
		return line;
	}

	@Override
	public Object visit(Ul ul, Object param) {
		Formatted_Block block = new Formatted_Block();
		String foundValue = "";
		foundValue = sv.search("ul", "list-style-type", customCssAst);
		if (foundValue == null) {
			foundValue = sv.search("ul", "list-style-type", defaultCssAst);
		}
		
		block.getPropiedades().put("List Style", foundValue);
		for (Dl dl : ul.getContent()) {
			block.getText().add((Formatted_Line) dl.accept(this, param));
		}
		return block;
	}

	public Formatted_Page render() {
		return (Formatted_Page) this.htmlAst.accept(this, null);
	}

}
