package org.rbc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class DateFormatValidator implements ConstraintValidator<CorrectDate, String> {

        @Override
        public boolean isValid(String date, ConstraintValidatorContext context) {
              if(date==null||date.isEmpty())
                      return false;
               String[] dateArr=date.split("-");
               if(dateArr.length!=2)
                       return false;
               try{
                       if(Integer.valueOf(dateArr[0])>12&&Integer.valueOf(dateArr[0])<1)
                               return false;
                       if(Integer.valueOf(dateArr[1])>31&&Integer.valueOf(dateArr[0])<1)
                               return false;
               }catch(NumberFormatException e){
                   return false;
               }
               return true;
        }
}
