package com.grocify.storemgmt.validator;


import com.grocify.commonlibs.model.request.StoreDetailRequest;
import com.grocify.storemgmt.exception.InvalidRequestException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UpdateStoreRequestValidation {
    public void validationUpdateRequest(StoreDetailRequest obj){
        if(obj.getUserId()==0){
            throw new InvalidRequestException("this is must be present");
        }
        if(!StringUtils.hasText(obj.getStoreName())){
            throw new InvalidRequestException("Name not be black");
        }
        if(!StringUtils.hasText(obj.getStoreAddress())){
            throw new InvalidRequestException("address not be black");
        }
        if(StringUtils.hasText(obj.getGstNo())) {

            String regex = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(obj.getGstNo());

            if (!matcher.matches()) {
                throw new InvalidRequestException("\n" +
                        "    It should be 15 characters long.\n" +
                        "    The first 2 characters should be a number.\n" +
                        "    The next 10 characters should be the PAN number of the taxpayer.\n" +
                        "    The 13th character (entity code) should be a number from 1-9 or an alphabet.\n" +
                        "    The 14th character should be Z.\n" +
                        "    The 15th character should be an alphabet or a number.\n");
            }
        }else {
            throw new InvalidRequestException("gst number not be blank");
        }






    }

}
