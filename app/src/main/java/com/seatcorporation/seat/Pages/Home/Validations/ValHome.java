package com.seatcorporation.seat.Pages.Home.Validations;

import com.seatcorporation.seat.Models.ItemObjects;

import java.util.LinkedList;

/**
 * Created by Devrath on 27-09-2016.
 */

public class ValHome {

    public ValHome(){
    }

    public boolean isProofNotAdded(LinkedList<ItemObjects> mData) {
        //Check if any of data is not present
        for (int pos = 0; pos < mData.size(); pos++) {
            if (mData.get(pos).isDataAdded() == false) {
                return true;
            }
        }
        return false;
    }

    public String whichProofNotPresent(LinkedList<ItemObjects> mData) {
        //Check if any of data is not present
        for (int pos = 0; pos < mData.size(); pos++) {
            if (mData.get(pos).isDataAdded() == false) {
                return mData.get(pos).getName();
            }
        }
        return "";
    }




}
