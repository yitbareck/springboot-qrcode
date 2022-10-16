package com.manyahl.qrcode.controller;

import java.awt.image.BufferedImage;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.manyahl.qrcode.exception.InvalidRequestBodyExceprion;



@RestController
@RequestMapping(value="/api/v1/generatecode",produces = MediaType.IMAGE_PNG_VALUE)
public class QRCodeController {
	
	@PostMapping("/qr")
	public static BufferedImage generateQRCodeImage(@RequestBody Map<String,Object> requestBody) throws Exception {
		if(requestBody.containsKey("qrText")) {
			String barcodeText = requestBody.get("qrText").toString();
			QRCodeWriter barcodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
			return MatrixToImageWriter.toBufferedImage(bitMatrix);
		}
		throw new InvalidRequestBodyExceprion("Include qrText in your request bdoy");
		
	}
	
	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
	    return new BufferedImageHttpMessageConverter();
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(InvalidRequestBodyExceprion exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	

}
