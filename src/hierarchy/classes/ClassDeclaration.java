package hierarchy.classes;

import hierarchy.comments.Comments;

import java.util.*;

import doc.visitor.DocVisitor;

public class ClassDeclaration {
	private String name;
	
	private List<Function> functions;
	private List<Function> constructors;
	private List<Function> destructors;
	private List<Function> operators;
	private List<Data> datas;
	private List<String> friends;
	private List<String> parents;
	
	private Comments comments;
	
	public ClassDeclaration(String name) {
		this.name = name;
		this.functions = new Vector<Function>();
		this.constructors = new Vector<Function>();
		this.destructors = new Vector<Function>();
		this.operators = new Vector<Function>();
		this.datas = new Vector<Data>();
		this.parents = new Vector<String>();
		this.friends = new Vector<String>();
	}
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String getName() {
		return name;
	}
	
	public List<Function> getFunctions() {
		return functions;
	}
	
	public void addFunction(Function f) {
		functions.add(f);
	}
	
	
	public List<Data> getDatas() {
		return datas;
	}
	
	public void addData(Data d) {
		datas.add(d);
	}
	
	public List<Function> getOperators() {
		return operators;
	}
	
	public void addOperator(Function o) {
		operators.add(o);
	}
	
	public List<Function> getConstructors() {
		return constructors;
	}
	
	public void addConstructor(Function c) {
		constructors.add(c);
	}
	
	public List<Function> getDestructors() {
		return destructors;
	}
	
	public void addDestructor(Function d) {
		destructors.add(d);
	}
	
	public List<String> getParents() {
		return parents;
	}
	
	public void addParent(String p) {
		parents.add(p);
	}
	
	
	public List<String> getFriends() {
		return friends;
	}
	
	public void addFriend(String p) {
		friends.add(p);
	}
	
	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}
	
	public boolean hasMember(String name) {
		
		for (Function f : functions ) {
			if (f.getName().equals(name)) 
				return true;
		}
		
		
		for (Data d : datas ) {
			if (d.getName().equals(name)) 
				return true;
		}
		
		return false;
	}
	
	public String toString() {
		
		StringBuffer res = new StringBuffer();
		
		res.append("class " + name + ": ");
		for (String p : parents) {
			res.append(p +", ");
		}
		
		if (res.charAt(res.length()-2) == ',')
			res.delete(res.length()-2, res.length()); //remove comma
		
		res.append(" {\n");
		
		for (String f : friends) {
			res.append("friend class " + f +";\n");
		}
		
		for (Data d : datas) {
			res.append(d +";\n");
		}
		
		for (Function c : constructors) {
			res.append(c +";\n");
		}
		
		for (Function d : destructors) {
			res.append(d +";\n");
		}
		
		for (Function f : functions) {
			res.append(f +";\n");
		}
		
		for (Function o : operators) {
			res.append(o +";\n");
		}
		
		res.append("}");
		
		
		return res.toString();
		
	}
}
