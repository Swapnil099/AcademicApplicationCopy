package com.ubi.academicapplication.dto.classdto;

import java.util.Set;

import com.ubi.academicapplication.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCreationDto {
	private String classCode;
	private String className;
	private int schoolId;
	private Set<Student> student;
}