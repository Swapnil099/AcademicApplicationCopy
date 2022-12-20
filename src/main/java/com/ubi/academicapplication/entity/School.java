package com.ubi.academicapplication.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "School_Details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class School {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int schoolId;
	
	@Column(name = "schoolCode")
	private int code;
	
	@Column(name = "schoolName")
	private String name;
	
	@Column(name = "schoolEmail")
	private String email;
	
	@Column(name ="schoolContact")
	private int contact;
	
	@Column(name ="schoolAddress")
	private String address;
	
	@Column(name ="schoolType")
	private String type;
	
	private int strength;
	
	@Column(name ="schoolShift")
	private String shift;
	
	@Column(name = "exemptionFlag")
	private boolean exemptionFlag;

	@Column(name = "vvnAccount")
	private int vvnAccount;
	
	@Column(name = "vvnFund")
	private int vvnFund;

	

	@ManyToOne
	@JoinColumn(name="region_id",referencedColumnName="id" )
	private Region region;
	
	
	
	
	
	
	
}
