package com.safwa.zwadatkom.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;




public class VerifyOtpModel implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    private final static long serialVersionUID = 2841087114966693085L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data implements Serializable
    {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("message")
        @Expose
        private String message;
        private final static long serialVersionUID = 8932241382216149173L;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}