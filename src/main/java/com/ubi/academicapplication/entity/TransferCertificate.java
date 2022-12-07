package com.ubi.academicapplication.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="transfer_certificate")
 
public class TransferCertificate {
	
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private int id;

@NotNull

@DateTimeFormat(pattern="dd/MM/yyyy")
private Date dateOfIssue;


}


	
