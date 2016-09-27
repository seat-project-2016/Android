
package com.seatcorporation.seat.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterData {

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("os_type")
    @Expose
    private String osType;
    @SerializedName("documents")
    @Expose
    private List<Document> documents = new ArrayList<Document>();

    /**
     * 
     * @return
     *     The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     *     The phone_number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 
     * @param deviceId
     *     The device_id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 
     * @return
     *     The osType
     */
    public String getOsType() {
        return osType;
    }

    /**
     * 
     * @param osType
     *     The os_type
     */
    public void setOsType(String osType) {
        this.osType = osType;
    }

    /**
     * 
     * @return
     *     The documents
     */
    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * 
     * @param documents
     *     The documents
     */
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}
