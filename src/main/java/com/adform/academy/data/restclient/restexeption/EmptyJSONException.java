package com.adform.academy.data.restclient.restexeption;

public class EmptyJSONException extends Exception {
    public EmptyJSONException(){
        super("Empty JSON line was get from REST");
    }
}
