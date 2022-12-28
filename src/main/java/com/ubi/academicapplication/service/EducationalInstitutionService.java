package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.regionDto.EIRegionMappingDto;
import com.ubi.academicapplication.dto.regionDto.EducationRegionGetDto;
import com.ubi.academicapplication.dto.regionDto.EducationalRegionDto;
import com.ubi.academicapplication.dto.response.Response;

public interface EducationalInstitutionService {

	Response<EducationalRegionDto> addEducationalInstitution(EducationalInstitutionDto educationalInstitutionDto);

	Response<EducationRegionGetDto> getEducationalInstituteByName(String educationalInstitutionName);

	Response<List<EducationRegionGetDto>> getAllEducationalInstitutions(Integer pageNumber, Integer pageSize);

	Response<EducationalInstitutionDto> deleteEducationalInstitution(int id);

	Response<EducationalRegionDto> updateEducationalInstitution(EducationalInstitutionDto educationalInstitutionDto);

	Response<EducationRegionGetDto> getEduInstwithRegion(int id);

	Response<List<EducationalInstitutionDto>> getEduInstwithSort(String field);

	ByteArrayInputStream load();

}
