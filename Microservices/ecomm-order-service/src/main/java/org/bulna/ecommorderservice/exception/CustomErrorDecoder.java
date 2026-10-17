package org.bulna.ecommorderservice.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() == 404){
            return new RuntimeException("Product Not Found");
        }
        if(response.status() == 409){
            return new OutOfStockException();
        }
        return new RuntimeException("Generic Error: "+response.status());
    }
}
