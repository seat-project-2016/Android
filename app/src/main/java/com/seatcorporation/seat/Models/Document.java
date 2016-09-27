
package com.seatcorporation.seat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Document {

    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("proofname")
    @Expose
    private String proofname;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
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
     *     The proofname
     */
    public String getProofname() {
        return proofname;
    }

    /**
     * 
     * @param proofname
     *     The proofname
     */
    public void setProofname(String proofname) {
        this.proofname = proofname;
    }

}
