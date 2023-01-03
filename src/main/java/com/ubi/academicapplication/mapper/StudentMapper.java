package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.dto.student.StudentVerifyDto;
import com.ubi.academicapplication.entity.Student;

@Component
public class StudentMapper {
	ModelMapper modelMapper = new ModelMapper();

	// entity to DTO Mapping
	public StudentDto entityToDto(Student student) {
		 	StudentDto studentDto=modelMapper.map(student, StudentDto.class);
			studentDto.setStudentId(student.getStudentId());
		 	studentDto.setCategory(student.getCategory());
		 	studentDto.setCurrentStatus(student.getCurrentStatus());
		 	studentDto.setDateOfBirth(student.getDateOfBirth());
		 	studentDto.setFatherName(student.getFatherName());
		 	studentDto.setFatherOccupation(student.getFatherName());
		 	studentDto.setGender(student.getGender());
		 	studentDto.setIsActivate(student.getIsActivate());
		 	studentDto.setJoiningDate(student.getJoiningDate());
		 	studentDto.setMinority(student.getMinority());
		 	studentDto.setMotherName(student.getMotherName());
		 	studentDto.setMotherOccupation(student.getMotherOccupation());
		 	studentDto.setStatus(student.getStatus());
		 	studentDto.setStudentName(student.getStudentName());
		 	studentDto.setStudentStatus(student.isStudentStatus());
		 	studentDto.setVerifiedByPrincipal(student.getVerifiedByPrincipal());
		 	
		 	studentDto.setVerifiedByTeacher(student.isVerifiedByTeacher());
		    studentDto.setClassId(student.getClassDetail().getClassId());
		    return studentDto;
		}
	
	public StudentVerifyDto entityToDtoId(StudentVerifyDto student) {
		StudentVerifyDto studentDto=modelMapper.map(student, StudentVerifyDto.class);
		studentDto.setStudentId(student.getStudentId());
		return studentDto;
	}
	
	public StudentVerifyDto entityToDtoIds(StudentVerifyDto student) {
		StudentVerifyDto studentDto=modelMapper.map(student, StudentVerifyDto.class);
		studentDto.setStudentId(student.getStudentId());
		return studentDto;
	}
	
	public List<StudentDto> entitiesToDtos(List<Student> student) {
		return student.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	}
	
	// DTO to entity Mapping
	public Student dtoToEntity(StudentDto studentDto) {
		return modelMapper.map(studentDto, Student.class);
	}

	public List<Student> dtosToEntities(List<StudentDto> studentDTOs) {
		return studentDTOs.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
	}

	public Set<StudentDto> entitiesToDto(List<Student> list) {
		return list.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toSet());
	}

}
