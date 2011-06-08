package hierarchy.comments;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import doc.visitor.DocVisitor;

public class Comments {
	
	List<CommentElement> commentElements;
	Map<String, Attribute> attributes;
	
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public Comments() {
		commentElements = new Vector<CommentElement>();
		attributes = new HashMap<String, Attribute>();
	}

	public void addElement(CommentElement com) {
		commentElements.add(com);
	}
	
	public int numElement() {
		return commentElements.size();
	}
	
	
	public void addAttribute(Attribute attr) {
		attributes.put(attr.getName(), attr);
	}
	
	
	public List<CommentElement> getCommentElements() {
		return commentElements;
	}
	
	public Attribute getAttribute(String attributeName) {
		if (!attributes.containsKey(attributeName)) {
			return null;
		}
		else
		{
			return attributes.get(attributeName);
		}
	}
	
	public String toString() {
		StringBuffer res = new StringBuffer();
		
		for (String key : attributes.keySet()) {
			res.append(attributes.get(key) + "\n");
		}
		
		
		for (CommentElement ce : commentElements) {
			res.append(ce +" ");
		}
		
		return res.toString();
	}
}
