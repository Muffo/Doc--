package hierarchy.classes;

import com.hp.gagawa.java.Node;

import doc.visitor.DocVisitor;

public class Data extends Member {

	private String array;


	public Data(String name, DataType type, VisibilityAttribute visibility, boolean isConst, boolean isStatic) {
		this.name = name;
		this.type = type;
		this.visibility = visibility;
		this.isConst = isConst;
		this.isStatic = isStatic;
		this.comments = null;
		this.array = null;
	}
	
	public Data(String name, DataType type ) {
		this(name, type, VisibilityAttribute.PROTECTED, false, false);
	}
	
	
	public String getArray() {
		return array;
	}

	public void addArray(String array) {
		if (this.array == null) {
			this.array = array;
		}
		else {
			this.array = this.array + array;
		}
	}
	
	public Node accept(DocVisitor v) {
		return v.visit(this);
	}
	
	
	public String toString() {
		StringBuffer res = new StringBuffer();
		
		if (getComments() != null && getComments().numElement() > 0) {
			res.append("\n/**\n" + getComments() + "*/\n");
		}
		
		if (isConst) res.append("const ");
		if (isStatic) res.append("static ");
		
		res.append(type + " ");
		res.append(name);
		if (array != null ){
			res.append("[" + array + "]");
		}
		
		return res.toString();
	}
	
}
