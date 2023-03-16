package com.booleanuk.api.cinema.response;

public class DataResponse<T> extends Response {
    private final T data;

    public DataResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "DataResponse{" +
                "status=" + status +
                ", data='" + data + '\'' +
                '}';
    }
}
