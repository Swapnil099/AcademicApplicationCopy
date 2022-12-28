package com.ubi.academicapplication.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.classdto.ClassStudentDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.ClassMapper;
import com.ubi.academicapplication.mapper.SchoolMapper;
import com.ubi.academicapplication.mapper.StudentMapper;
import com.ubi.academicapplication.repository.ClassRepository;
import com.ubi.academicapplication.repository.SchoolRepository;
import com.ubi.academicapplication.repository.StudentRepository;

@Service
public class ClassServiceImpl implements ClassService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClassMapper classMapper;
	
	@Autowired
	private SchoolMapper schoolMapper;

	@Autowired
	private StudentMapper studentMapper;

	public Response<ClassStudentDto> addClassDetails(ClassDto classDto) {

		Result<ClassStudentDto> res = new Result<>();
		Response<ClassStudentDto> response = new Response<>();
		
		ClassDetail className = classRepository.getClassByclassName(classDto.getClassName());
		ClassDetail classCode = classRepository.getClassByclassCode(classDto.getClassCode());
//		if (tempClass.isPresent()) {
//			throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
//					HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), res);
//		}

		if (className != null) {
			throw new CustomException(HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),
					HttpStatusCode.RESOURCE_ALREADY_EXISTS, HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(), res);
		}
		if (classCode != null) {
			throw new CustomException(HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),
					HttpStatusCode.RESOURCE_ALREADY_EXISTS, HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(), res);
		}
		
		ClassDetail classDetail=new ClassDetail();
		classDetail.setClassId(classDto.getClassId());
		classDetail.setClassName(classDto.getClassName());
		classDetail.setClassCode(classDto.getClassCode());
		classDetail.setSchool(schoolRepository.getReferenceById(classDto.getSchoolId()));
		classDetail.setStudents(new HashSet<>());
		for(Long student: classDto.getStudentId())
		{
			Student student1=studentRepository.getReferenceById(student);
			if(student1!=null)
			{
				classDetail.getStudents().add(student1);
			}
		}
		
		ClassDetail savedClass=classRepository.save(classDetail);
		ClassStudentDto classStudentDto=classMapper.toStudentDto(savedClass);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<ClassStudentDto>(classStudentDto));
		return response;
	}

	public Response<List<ClassStudentDto>> getClassDetails(Integer PageNumber, Integer PageSize) {

		Result<List<ClassStudentDto>> allClasses = new Result<>();
		Pageable pageing = PageRequest.of(PageNumber, PageSize);
		Response<List<ClassStudentDto>> getListofClasses = new Response<List<ClassStudentDto>>();

		Page<ClassDetail> classList = this.classRepository.findAll(pageing);
		List<ClassStudentDto> classDto =new ArrayList();
		
		for(ClassDetail classDetail:classList)
		{
			ClassStudentDto classStudentDto=new ClassStudentDto();
			classStudentDto.setClassDto(classMapper.entityToDto(classDetail));
			classStudentDto.setSchoolDto(schoolMapper.entityToDto(classDetail.getSchool()));
			//Set<Student> s1=classDetail.getStudents();
		    Set<StudentDto> studentDto=classDetail.getStudents().stream()
		    		.map(students -> studentMapper.entityToDto(students)).collect(Collectors.toSet());
		    classStudentDto.setStudentDto(studentDto);
		    classDto.add(classStudentDto);
		}
		if (classList.isEmpty()) {
		throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
				HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), allClasses);
		}
		
		allClasses.setData(classDto);
		getListofClasses.setStatusCode(HttpStatusCode.CLASS_RETREIVED_SUCCESSFULLY.getCode());
		getListofClasses.setMessage(HttpStatusCode.CLASS_RETREIVED_SUCCESSFULLY.getMessage());
		getListofClasses.setResult(allClasses);
		return getListofClasses;
	}

	public Response<ClassStudentDto> getClassById(Long classid) {

		Response<ClassStudentDto> getClass = new Response<>();
		Optional<ClassDetail> classDetail = this.classRepository.findById(classid);
		Result<ClassStudentDto> classResult = new Result<>();
		if (!classDetail.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_CLASS_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_CLASS_MATCH_WITH_ID, HttpStatusCode.NO_CLASS_MATCH_WITH_ID.getMessage(),
					classResult);
		}
		
		ClassStudentDto classStudentDto=new ClassStudentDto();
		classStudentDto.setClassDto(classMapper.entityToDto(classDetail.get()));
		classStudentDto.setSchoolDto(schoolMapper.entityToDto(classDetail.get().getSchool()));
		Set<StudentDto> studentDto=classDetail.get().getStudents().stream()
	    		.map(students -> studentMapper.entityToDto(students)).collect(Collectors.toSet());
	    classStudentDto.setStudentDto(studentDto);
	    ;
		classResult.setData(classStudentDto);
		getClass.setStatusCode(HttpStatusCode.CLASS_RETREIVED_SUCCESSFULLY.getCode());
		getClass.setMessage(HttpStatusCode.CLASS_RETREIVED_SUCCESSFULLY.getMessage());
		getClass.setResult(classResult);
		return getClass;
	}

//	public Response<ClassDto> deleteClassById(Long id) {
//		Result<ClassDto> res = new Result<>();
//		res.setData(null);
//		Optional<ClassDetail> classes = classRepository.findById(id);
//		if (!classes.isPresent()) {
//			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
//					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
//		}
//
//		/*
//		 * for(School newSchools:classes.get().getSchool()) {
//		 * newSchools.getClassDetail().remove(classes.get());
//		 * schoolRepository.save(newSchools); }
//		 */
//
//
//		
//		//classes.get().setSchool(new HashSet<>());
////		classRepository.save(classes.get());
//		
//		List<Student> stds = classes.get().getStudents();
//		classRepository.deleteById(id);
//		
//		// retain students to DB
//		stds.stream()
//		.forEach(std -> {
//			std.setClassDetail(null);
//			studentRepository.save(std);
//		});		
////		studentRepository.saveAll(stds);	
//		
//		// classes.get().setSchool(new HashSet<>());
//		classRepository.save(classes.get());
//		classRepository.deleteById(id);
//
//		Response<ClassDto> response = new Response<>();
//		response.setMessage(HttpStatusCode.CLASS_DELETED_SUCCESSFULLY.getMessage());
//		response.setStatusCode(HttpStatusCode.CLASS_DELETED_SUCCESSFULLY.getCode());
//		response.setResult(new Result<ClassDto>(classMapper.entityToDto(classes.get())));
//		return response;
//	}
//
//	public Response<ClassDto> updateClassDetails(ClassDto classDetail) {
//		Result<ClassDto> res = new Result<>();
//
//		res.setData(null);
//		Optional<ClassDetail> existingClassContainer = classRepository.findById(classDetail.getClassId());
//		if (!existingClassContainer.isPresent()) {
//			throw new CustomException(HttpStatusCode.NO_CLASS_FOUND.getCode(), HttpStatusCode.NO_CLASS_FOUND,
//					HttpStatusCode.NO_CLASS_FOUND.getMessage(), res);
//		}
//		ClassDto existingClass = classMapper.entityToDto(existingClassContainer.get());
//		existingClass.setClassCode(classDetail.getClassCode());
//		existingClass.setClassName(classDetail.getClassName());
//
//		ClassDetail updateClass = classRepository.save(classMapper.dtoToEntity(existingClass));
//		Response<ClassDto> response = new Response<>();
//		response.setMessage(HttpStatusCode.CLASS_UPDATED.getMessage());
//		response.setStatusCode(HttpStatusCode.CLASS_UPDATED.getCode());
//		response.setResult(new Result<>(classMapper.entityToDto(updateClass)));
//		return response;
//	}
//
	@Override
	public Response<ClassStudentDto> getClassByName(String className) {

		Response<ClassStudentDto> getClass = new Response<ClassStudentDto>();
		ClassDetail classDetail = this.classRepository.getClassByclassName(className);
		Result<ClassStudentDto> classResult = new Result<>();
		if (classDetail == null) {
			throw new CustomException(HttpStatusCode.CLASS_NOT_FOUND.getCode(), HttpStatusCode.CLASS_NOT_FOUND,
					HttpStatusCode.CLASS_NOT_FOUND.getMessage(), classResult);
		}
		ClassStudentDto classStudentDto=new ClassStudentDto();
		classStudentDto.setClassDto(classMapper.entityToDto(classDetail));
		classStudentDto.setSchoolDto(schoolMapper.entityToDto(classDetail.getSchool()));
		Set<StudentDto> studentDto=classDetail.getStudents().stream()
	    		.map(students -> studentMapper.entityToDto(students)).collect(Collectors.toSet());
	    classStudentDto.setStudentDto(studentDto);
	    classResult.setData(classStudentDto);
		getClass.setStatusCode(HttpStatusCode.CLASS_RETREIVED_SUCCESSFULLY.getCode());
		getClass.setMessage(HttpStatusCode.CLASS_RETREIVED_SUCCESSFULLY.getMessage());
		getClass.setResult(classResult);
		return getClass;
	}
//
//	@Override
//	public Response<List<StudentDto>> getClasswithStudent(Long id) {
//		Response<List<StudentDto>> response = new Response<>();
//		Result<List<StudentDto>> res = new Result<>();
//		Optional<ClassDetail> classDetail = this.classRepository.findById(id);
//
//		if (!classDetail.isPresent()) {
//			throw new CustomException(HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getCode(),
//					HttpStatusCode.NO_STUDENT_MATCH_WITH_ID, HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getMessage(), res);
//		}
//
//		ClassDetail classDetailss = classDetail.get();
//
//		List<Student> student = classDetailss.getStudents();
//
//		response.setResult(res);
//
//		res.setData(studentMapper.entitiesToDtos(student));
//
//		response.setStatusCode(HttpStatusCode.STUDENT_RETRIVED_SUCCESSFULLY.getCode());
//		response.setMessage(HttpStatusCode.STUDENT_RETRIVED_SUCCESSFULLY.getMessage());
//		return response;
//	}
//
//	@Override
//	public Response<List<ClassDto>> getClasswithSort(String field) {
//
//		Result<List<ClassDto>> allClassResult = new Result<>();
//
//		Response<List<ClassDto>> getListofClasses = new Response<>();
//
//		List<ClassDetail> list = this.classRepository.findAll(Sort.by(Sort.Direction.ASC, field));
//		List<ClassDto> classDtos = classMapper.entitiesToDtos(list);
//
//		if (list.size() == 0) {
//			throw new CustomException(HttpStatusCode.NO_CLASS_FOUND.getCode(), HttpStatusCode.NO_CLASS_FOUND,
//					HttpStatusCode.NO_CLASS_FOUND.getMessage(), allClassResult);
//		}
//		allClassResult.setData(classDtos);
//		getListofClasses.setStatusCode(HttpStatusCode.CLASS_RETRIVED_SUCCESSFULLY.getCode());
//		getListofClasses.setMessage(HttpStatusCode.CLASS_RETRIVED_SUCCESSFULLY.getMessage());
//		getListofClasses.setResult(allClassResult);
//		return getListofClasses;
//	}
//
//	@Override
//	public ByteArrayInputStream load() {
//		List<ClassDetail> classd = classRepository.findAll();
//		ByteArrayInputStream out = ClassCsvHelper.classCSV(classd);
//		return out;
//	}
}
