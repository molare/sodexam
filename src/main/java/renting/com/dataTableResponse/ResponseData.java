package renting.com.dataTableResponse;


import renting.com.entities.Bien;
import renting.com.entities.BienAttachment;

import java.util.List;

/**
 * Created by MORY on 20/03/2019.
 */
public class ResponseData {
    private boolean status;
    private String message;
    private Object data;
    private Object firstData;
    private Object secondData;
    private Object thirtyData;
    private Bien bien;
    private List<BienAttachment> bienAttachment;

    public ResponseData(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ResponseData(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseData(boolean status, Object data, String message, List<BienAttachment> bienAttachment) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.bienAttachment = bienAttachment;
    }

    public ResponseData(boolean status, Bien bien, Object data) {
        this.status = status;
        this.bien = bien;
        this.data = data;
    }

  /*public ResponseData(boolean status, Object data, Object secondData, Object thirtyData) {
        this.status = status;
        this.data = data;
        this.secondData = secondData;
        this.thirtyData = thirtyData;
    }*/

    public ResponseData(boolean status, Object firstData, Object secondData, Object thirtyData) {
        this.status = status;
        this.firstData = firstData;
        this.secondData = secondData;
        this.thirtyData = thirtyData;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public Object getSecondData() {
        return secondData;
    }

    public void setSecondData(Object secondData) {
        this.secondData = secondData;
    }

    public Object getThirtyData() {
        return thirtyData;
    }

    public void setThirtyData(Object thirtyData) {
        this.thirtyData = thirtyData;
    }

    public Object getFirstData() {
        return firstData;
    }

    public void setFirstData(Object firstData) {
        this.firstData = firstData;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public List<BienAttachment> getBienAttachment() {
        return bienAttachment;
    }

    public void setBienAttachment(List<BienAttachment> bienAttachment) {
        this.bienAttachment = bienAttachment;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
