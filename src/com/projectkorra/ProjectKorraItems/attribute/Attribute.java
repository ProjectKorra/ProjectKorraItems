package com.projectkorra.ProjectKorraItems.attribute;

import java.util.ArrayList;

public class Attribute 
{	
	private String name;
	private String desc;
	private ArrayList<String> values;
	
	public Attribute(String name, String desc) {
		this.name = name;
		this.desc = desc;
		values = new ArrayList<String>();
	}
	
	public Attribute(String name) {
		this(name, "");
	}
	
	public Attribute() {
		this("", "");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	
	public static Attribute getAttribute(String name) {
		if(name == null)
			return null;
		for(Attribute att : AttributeList.attributes)
			if(att.getName().equalsIgnoreCase(name))
				return att;
		return null;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", desc=" + desc + ", values="
				+ values + "]";
	}
}
