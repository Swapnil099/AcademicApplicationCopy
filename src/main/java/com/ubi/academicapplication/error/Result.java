package com.ubi.academicapplication.error;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 2405172041950251807L;
	private T data;
}
