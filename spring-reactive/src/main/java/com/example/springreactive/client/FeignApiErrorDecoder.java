package com.example.springreactive.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.netty.channel.DefaultAddressedEnvelope;

public class FeignApiErrorDecoder implements ErrorDecoder{
	
//	private final ErrorDecoder errorDecoder = new Default();
	
	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
