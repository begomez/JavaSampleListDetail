package test.udacity.com.contentaniminjava.model;


import java.io.Serializable;

/**
 * Created by bernatgomez on 18/7/17.
 */
public class ErrorModel implements Serializable {

    private String msg;

    public ErrorModel(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
