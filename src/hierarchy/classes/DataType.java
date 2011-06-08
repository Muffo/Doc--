package hierarchy.classes;

import doc.visitor.DocVisitor;

public class DataType {
	private String name;
	
	public DataType(String name) {
		this.name = name;
	}
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		return name;
	}
}
