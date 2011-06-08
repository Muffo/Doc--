package hierarchy.comments;

import doc.visitor.DocVisitor;

public class WebLinkElement extends CommentElement {
	
	private String url;
	
	public WebLinkElement(String url) {
		this.url = url;
		
//		if (this.url.charAt(0) == '[' && this.url.charAt(this.url.length()-1) == ']'){
//			this.url = this.url.substring(1, this.url.length()-1);
//		}
	}

	public String getUrl() {
		return url;
	}
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		return "[" + url + "]";
	}
}