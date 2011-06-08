package hierarchy.classes;

import java.util.List;
import java.util.Vector;

import doc.visitor.DocVisitor;

public class Function extends Member {

	List<Data> arguments;
	
	public Function(String name, DataType type, VisibilityAttribute visibility, boolean isConst, boolean isStatic) {
		this.name = name;
		this.type = type;
		this.visibility = visibility;
		this.isConst = isConst;
		this.isStatic = isStatic;
		this.arguments = new Vector<Data>();
		this.comments = null;
	}
	
	public Function(String name, DataType type ) {
		this(name, type, VisibilityAttribute.PROTECTED, false, false);
	}
	
	
	public List<Data> getArguments() {
		return arguments;
	}
	
	public void addArgument(Data arg) {
		arguments.add(arg);
	}
	
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String getArgumentsString() {
		StringBuffer res = new StringBuffer();
		
	
		if (arguments.size()  == 0) {
			return "";
		}
		
		for (Data d : arguments) {
			res.append(d +", ");
		}
		
		res.delete(res.length()-2, res.length()); //remove comma
		return res.toString();
	}
	
	public String toString() {
		StringBuffer res = new StringBuffer();
		
		if (getComments() != null && getComments().numElement() > 0) {
			res.append("\n/**\n" + getComments() + "*/\n");
		}
		
		if (isStatic) res.append("const ");
		if (isConst) res.append("static ");
		
		// constructors
		if (type != null) 
			res.append(type + " ");
		
		res.append(name);
		res.append("(");
	
		res.append(getArgumentsString());
		
		res.append(")");
		
		return res.toString();

	}

}
