package com.ubi.academicapplication.dto.classdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {
	private Long classId;
	private String classCode;
	private String className;
}
