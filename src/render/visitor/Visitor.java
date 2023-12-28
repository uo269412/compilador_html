package render.visitor;

import render.Formatted_Block;
import render.Formatted_Line;
import render.Formatted_Page;
import render.Formatted_Text;

public interface Visitor {
	
	Object visit(Formatted_Page fp, Object param);
	Object visit(Formatted_Line fl, Object param);
	Object visit(Formatted_Text ft, Object param);
	Object visit(Formatted_Block formatted_Block, Object param);

}
