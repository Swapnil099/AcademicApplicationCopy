package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Payment;
import com.ubi.academicapplication.entity.Student;

public interface PaymentService {

	Response<Payment> makePayment(Payment payment);

	Response<Payment> getSingle(int id);

	Response<List<Payment>> getAllPayment();
	
	Response<Payment> updatePay(Payment pay);

	public Response<Payment> deletePayment(int id);

	
//	Response<PaymentDto> makePayment(PaymentDto payment);
//
//	PaymentDto getSingleUser(int id);
//
//	List<PaymentDto> getAllUser();
//
//	PaymentDto updatePay(PaymentDto payDto);

}
