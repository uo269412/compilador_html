package render.visitor;

import java.util.Map;

import render.Formatted_Block;
import render.Formatted_Content;
import render.Formatted_Line;
import render.Formatted_Page;
import render.Formatted_Text;

public class PageVisitor implements Visitor {

	@Override
	public Object visit(Formatted_Page fp, Object param) {
		System.out.println("PAGE: " + fp.getPageTitle());
		for (Formatted_Content fl : fp.getLines()) {
			fl.accept(this, fp.getLines().indexOf(fl) + 1);
		}
		return null;
	}

	@Override
	public Object visit(Formatted_Line fl, Object param) {
		String tabs = "\t";
		String valuePrint = ": " + param +" ";
		if (param == null) {
			tabs += "\t";
			valuePrint = " ";
		}
		System.out.println(tabs + "(LINE" + valuePrint + "| TEXT-ALIGN: " + fl.getText_align() + ")");
		for (Formatted_Text ft : fl.getText()) {
			ft.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Formatted_Text ft, Object param) {
		String tabs = "\t\t";
		if (param == null) {
			tabs += "\t";
		}
		System.out.println(tabs + "(TEXT: " + ft.getText() + " | COLOR : " + ft.getColor() + ", STYLE: "
				+ ft.getFont_style() + ", SIZE: " + ft.getFont_size() + ", METRICS: " + ft.getMetrics() + ")");

		return null;
	}

	@Override
	public Object visit(Formatted_Block fb, Object param) {
		String propiedades = getPropiedades(fb);
		String tabs = "\t";
		String valuePrint = "";
		if (param == null) {
			tabs += "\t";
		} else {
			valuePrint = ": " + (int) param;
		}
		System.out.println(tabs + "(BLOCK" + valuePrint + propiedades + ")");
		for (Formatted_Content fl : fb.getText()) {
			fl.accept(this, null);
		}
		return null;
	}

	private String getPropiedades(Formatted_Block formatted_Block) {
		StringBuilder result = new StringBuilder();
		for (Map.Entry<String, String> entry : formatted_Block.getPropiedades().entrySet()) {
			result.append(entry.getKey().toUpperCase()).append(": ").append(entry.getValue()).append(", ");
		}
		if (result.length() > 0) {
			result.setLength(result.length() - 2);
			return " | " + result.toString();
		}
		return result.toString();
	}
}
