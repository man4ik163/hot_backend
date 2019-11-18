package model;

import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import java.text.ParseException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import utill.DateFormatter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Child {

    @JMap
    private String name;

    @JMap
    private Long age;

    @JMap(value = "birthDay")
    private Date dateOfBirth;

    @JMapConversion(from = {"birthDay"}, to = {"dateOfBirth"})
    public Date conversion(String dateOfBirth) throws ParseException {
        return DateFormatter.stringToDate(dateOfBirth);
    }

}
