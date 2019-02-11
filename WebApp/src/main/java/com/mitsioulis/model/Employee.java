package com.mitsioulis.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mitsioulis.utils.GenerateSqlTimeStamp;

@Entity
@DynamicUpdate(value = true)
public class Employee {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="EMP_ID")
	private String EMP_ID;
	
	@Column(name="EMP_Name")
	private String EMP_Name;
	
	@Column(updatable=false)
	private Timestamp EMP_DateOfHire;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="emp_supervisor",insertable=true, nullable=true)
	private Employee EMP_supervisor;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "employeeattribute", 
			   joinColumns = { @JoinColumn(name = "EMPATTR_EmployeeID") }, 
			   inverseJoinColumns = { @JoinColumn(name = "EMPATTR_AttributeID") })
	@JsonBackReference
	@MapKey(name="ATRR_ID")
	private Map<String, Attribute> attributes = new HashMap<>();
	
	@PrePersist
	@Column(name = "EMP_Dateofhire")
	protected void onCreate() {
		EMP_DateOfHire = GenerateSqlTimeStamp.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		EMP_DateOfHire = this.getEMP_DateOfHire();
	}
	
	public Employee() {}


	public Employee(String eMP_Name,Employee eMP_supervisor) {
		EMP_supervisor = eMP_supervisor;
		EMP_Name = eMP_Name;
		
	}


	public String getEMP_ID() {
		return EMP_ID;
	}


	public void setEMP_ID(String eMP_ID) {
		EMP_ID = eMP_ID.toUpperCase();
	}


	public String getEMP_Name() {
		return EMP_Name;
	}


	public void setEMP_Name(String eMP_Name) {
		EMP_Name = eMP_Name;
	}


	public Timestamp getEMP_DateOfHire() {
		return EMP_DateOfHire;
	}


	public void setEMP_DateOfHire(Timestamp eMP_DateOfHire) {
		EMP_DateOfHire =  eMP_DateOfHire;
	}
	
	
	public Map<String, Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Attribute> attributes) {
		this.attributes = attributes;
	}

	public Employee getSupervisor() {
		return EMP_supervisor;
	}

	public void setSupervisor(Employee EMP_supervisor) {
		this.EMP_supervisor = EMP_supervisor;
	}


	@Override
	public String toString() {
		return "Employee [EMP_ID=" + EMP_ID + ", EMP_Name=" + EMP_Name + ", EMP_DateOfHire=" + EMP_DateOfHire
				+ ", supervisor=" + EMP_supervisor + ", attributes=" + attributes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EMP_DateOfHire == null) ? 0 : EMP_DateOfHire.hashCode());
		result = prime * result + ((EMP_ID == null) ? 0 : EMP_ID.hashCode());
		result = prime * result + ((EMP_Name == null) ? 0 : EMP_Name.hashCode());
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		//result = prime * result + ((subordinates == null) ? 0 : subordinates.hashCode());
		result = prime * result + ((EMP_supervisor == null) ? 0 : EMP_supervisor.hashCode());
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
		Employee other = (Employee) obj;
		if (EMP_DateOfHire == null) {
			if (other.EMP_DateOfHire != null)
				return false;
		} else if (!EMP_DateOfHire.equals(other.EMP_DateOfHire))
			return false;
		if (EMP_ID == null) {
			if (other.EMP_ID != null)
				return false;
		} else if (!EMP_ID.equals(other.EMP_ID))
			return false;
		if (EMP_Name == null) {
			if (other.EMP_Name != null)
				return false;
		} else if (!EMP_Name.equals(other.EMP_Name))
			return false;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (EMP_supervisor == null) {
			if (other.EMP_supervisor != null)
				return false;
		} else if (!EMP_supervisor.equals(other.EMP_supervisor))
			return false;
		return true;
	}

	

	
}
