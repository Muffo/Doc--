package hierarchy.classes;

public enum VisibilityAttribute {
	PUBLIC, PRIVATE, PROTECTED;
	
	
	public String toString(){
		if (this == VisibilityAttribute.PUBLIC)
			return "public";
		if (this == VisibilityAttribute.PROTECTED)
			return "protected";
		if (this == VisibilityAttribute.PRIVATE)
			return "private";
		
		return "";
	}
}
