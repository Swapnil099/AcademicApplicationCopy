package com.ubi.academicapplication.dto.school;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDto {

	private int schoolId;

	private int code;

	private String name;

	private String email;

	private int contact;

	private String address;

	private String type;

	private int strength;

	private String shift;

	private boolean exemptionFlag;

	private int vvnAccount;

	private int vvnFund;

}