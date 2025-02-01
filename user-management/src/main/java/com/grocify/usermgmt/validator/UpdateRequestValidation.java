package com.grocify.usermgmt.validator;

import com.grocify.commonlibs.model.request.UserProfileUpdateRequest;
import com.grocify.usermgmt.exception.InvalidRequestException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UpdateRequestValidation {
    public void validationUpdateRequest(UserProfileUpdateRequest userProfileUpdateRequest) {
        if (StringUtils.hasText(userProfileUpdateRequest.getMobileNo())) {

            String regex = "^(\\+91)?[6-9]\\d{9}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(userProfileUpdateRequest.getMobileNo());

            if (!matcher.matches()) {
                throw new InvalidRequestException("Numbers that start with +91 (optional) followed by a valid number starting with 6-9 \n" +
                        " Numbers that start directly with a digit between 6 and 9, followed by 9 digits.");
            }

        } else {
            throw new InvalidRequestException("Mobile number not be blank");
        }

        if (!StringUtils.hasText(userProfileUpdateRequest.getLastName())) {
            throw new InvalidRequestException("last Name not be black");
        }
        if (!StringUtils.hasText(userProfileUpdateRequest.getFirstName())) {
            throw new InvalidRequestException("first Name not be black");
        }
        if (!StringUtils.hasText(userProfileUpdateRequest.getAddress())) {
            throw new InvalidRequestException("address not be black");
        }
    }

}
