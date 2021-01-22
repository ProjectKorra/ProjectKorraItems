package com.projectkorra.items.attribute;

import com.projectkorra.projectkorra.Element;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Attribute {
	private String name;
	private String desc;
	private ArrayList<String> values;
	private Element element;
	private double duration, time;
	private int benefit;

	/**
	 * Creates a new Attribute with a specific name, description, element, and
	 * benefit.
	 * 
	 * @param name the name of the Attribute
	 * @param desc a description of the Attribute
	 * @param element if the element belongs to a specific element, this can be
	 *            null
	 * @param benefit either -1 or 1, if increasing this attribute causes the
	 *            player to be better off then the benefit should be 1, else -1.
	 */
	public Attribute(String name, String desc, Element element, int benefit) {
		this.name = name;
		this.desc = desc;
		this.element = element;
		this.benefit = benefit;
		this.duration = 0;
		this.time = 0;
		values = new ArrayList<String>();
	}

	/**
	 * Copies the details from another Attribute into this Attribute.
	 * 
	 * @param other the Attribute to copy
	 */
	public Attribute(Attribute other) {
		this.name = other.name;
		this.desc = other.desc;
		this.time = other.time;
		this.duration = other.duration;
		this.element = other.element;
		this.benefit = other.benefit;
		this.values = new ArrayList<String>();
		for (String str : other.values)
			this.values.add(new String(str));
	}

	public Attribute(String name, String desc, Element element) {
		this(name, desc, element, 1);
	}

	public Attribute(String name, String desc) {
		this(name, desc, null);
	}

	public Attribute(String name) {
		this(name, "");
	}

	public Attribute() {
		this("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTime() {
		return time;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public int getBenefit() {
		return benefit;
	}

	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
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

	/**
	 * Attempts to parse the attribute value into a double
	 * 
	 * @return the value or 0 the value couldn't be parsed
	 */
	public double getValueAsDouble() {
		if (values.size() == 0)
			return 0;
		try {
			return Double.parseDouble(values.get(0));
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}

	/**
	 * Sets the value of this attribute to a double.
	 * 
	 * @param val the value of the attribute
	 */
	public void setValues(double val) {
		this.values = new ArrayList<String>();
		values.add(val + "");
	}

	/**
	 * Gets an attribute from the list of attributes if the name matches an
	 * existing attribute.
	 * 
	 * @param name the name of the attribute
	 * @return an Attribute or null if none was found
	 */
	public static Attribute getAttribute(String name) {
		if (name == null)
			return null;
		for (Attribute att : AttributeList.ATTRIBUTES)
			if (att.getName().equalsIgnoreCase(name))
				return att;
		return null;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", desc=" + desc + ", values=" + values + ", time=" + time + "]";
	}

	/**
	 * Returns if this Attribute's name matches name, AND the value of this
	 * Attribute is not 0.
	 * 
	 * @param name the name of the attribute
	 * @return true if names are equal and it's value isn't 0
	 */
	public boolean getBooleanValue(String name) {
		if (!this.name.equalsIgnoreCase(name))
			return false;
		try {
			boolean val = Integer.parseInt(this.values.get(0)) != 0;
			return val;
		}
		catch (Exception e) {
		}
		return false;
	}

	/**
	 * Checks if an attribute exists in a map, and if it does makes sure that
	 * the value is not 0.
	 * 
	 * @param name name of the Attribute
	 * @param map containing attribute names and values
	 * @return true if name is in map and it's value isn't 0
	 */
	public static boolean getBooleanValue(String name, ConcurrentHashMap<String, Double> map) {
		boolean val = map.containsKey(name);
		if (val) {
			try {
				val = map.get(name).intValue() != 0;
			}
			catch (Exception e) {
			}
		}
		return val;
	}
}
