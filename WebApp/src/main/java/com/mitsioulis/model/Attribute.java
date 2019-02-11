package com.mitsioulis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Attribute {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2") 
	@Column(name = "ATRR_ID")
	private String ATRR_ID;
	@Column(name = "ATTR_Name")
	private String ATTR_Name;
	@Column(name="ATTR_Value")
	private String ATTR_Value;
	
//	@ManyToMany(mappedBy="attributes")
//    private List<Employee> employees;
	
	
	public Attribute() {}


	public Attribute(String aTTR_Name, String aTTR_Value) {
		ATTR_Name = aTTR_Name;
		ATTR_Value = aTTR_Value;
	}


	public String getATRR_ID() {
		return ATRR_ID;
	}


	public void setATRR_ID(String aTRR_ID) {
		ATRR_ID = aTRR_ID;
	}


	public String getATTR_Name() {
		return ATTR_Name;
	}


	public void setATTR_Name(String aTTR_Name) {
		ATTR_Name = aTTR_Name;
	}


	public String getATTR_Value() {
		return ATTR_Value;
	}


	public void setATTR_Value(String aTTR_Value) {
		ATTR_Value = aTTR_Value;
	}


	@Override
	public String toString() {
		return "Attribute [ATRR_ID=" + ATRR_ID + ", ATTR_Name=" + ATTR_Name + ", ATTR_Value=" + ATTR_Value + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ATRR_ID == null) ? 0 : ATRR_ID.hashCode());
		result = prime * result + ((ATTR_Name == null) ? 0 : ATTR_Name.hashCode());
		result = prime * result + ((ATTR_Value == null) ? 0 : ATTR_Value.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (ATRR_ID == null) {
			if (other.ATRR_ID != null)
				return false;
		} else if (!ATRR_ID.equals(other.ATRR_ID))
			return false;
		if (ATTR_Name == null) {
			if (other.ATTR_Name != null)
				return false;
		} else if (!ATTR_Name.equals(other.ATTR_Name))
			return false;
		if (ATTR_Value == null) {
			if (other.ATTR_Value != null)
				return false;
		} else if (!ATTR_Value.equals(other.ATTR_Value))
			return false;
		return true;
	}

}
