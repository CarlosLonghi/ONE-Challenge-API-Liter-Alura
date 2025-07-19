package com.one.challenge.literalura.service;

public interface IDataConvert {
    <T> T getData(String json, Class<T> tClass);
}
