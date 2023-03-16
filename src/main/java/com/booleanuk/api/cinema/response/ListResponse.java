package com.booleanuk.api.cinema.response;

import java.util.List;

public class ListResponse<T> extends Response {
    private final List<T> data;

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ListResponse{" +
                "status=" + status +
                ", data='" + data + '\'' +
                '}';
    }
}
