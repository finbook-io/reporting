package io.finbook.responses;

import java.util.Map;

public class ResponseStructure {

    private Map<String, Object> data;
    private String view;

    public ResponseStructure(Map<String, Object> data, String view) {
        this.data = data;
        this.view = view;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
