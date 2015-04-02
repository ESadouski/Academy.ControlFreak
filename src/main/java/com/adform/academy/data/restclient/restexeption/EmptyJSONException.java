package com.adform.academy.data.restclient.restexeption;

/**
 * Created by teran on 02.04.15.
 */
public class EmptyJSONException extends Exception {
    public EmptyJSONException(){
        super("Empty JSON line was get from REST");
    }
}
