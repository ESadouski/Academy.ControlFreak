package com.adform.academy.data.restclient.restexeption;

public class ClientOperationException extends Exception{
    public ClientOperationException(String msg){
        super("Operation was not complete! +\r\n" + msg);
    }
}
