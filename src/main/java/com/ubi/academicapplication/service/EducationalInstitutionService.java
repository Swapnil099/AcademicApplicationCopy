package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.regionDto.EIRegionMappingDto;
import com.ubi.academicapplication.dto.regionDto.EducationalRegionDto;
import com.ubi.academicapplication.dto.response.Response;

public interface EducationalInstitutionService {

	Response<EducationalInstitutionDto> addEducationalInstitution(EducationalInstitutionDto educationalInstitutionDto);

	Response<EducationalInstitutionDto> getSingleEducationalInstitution(int id);

	Response<EducationalInstitutionDto> getEducationalInstituteByName(String educationalInstitutionName);

	Response<List<EducationalInstitutionDto>> getAllEducationalInstitutions(Integer pageNumber, Integer pageSize);

	Response<EducationalInstitutionDto> deleteEducationalInstitution(int id);

	Response<EducationalInstitutionDto> updateEducationalInstitution(
			EducationalInstitutionDto educationalInstitutionDto);

	Response<EducationalRegionDto> addRegion(EIRegionMappingDto eIRegionMappingDto);

	Response<EducationalRegionDto> getEduInstwithRegion(int id);
	
	ByteArrayInputStream load();


}