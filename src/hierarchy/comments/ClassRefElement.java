package hierarchy.comments;

import doc.visitor.DocVisitor;

public class ClassRefElement extends CommentElement {
	
	private String className;
	private String anchor;

	public ClassRefElement(String className) {
		this.className = className;
		
//		if (this.className.charAt(0) == '#'){
//			this.className = this.className.substring(1);
//		}
		
		this.anchor = null;
	}

	public String getClassName() {
		return className;
	}
	
	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		if (anchor == null) {
			return "#" + className;
		}
		else
		{
			return "#" + className + "#" + anchor;
		}
		
	}
	
	
}