package dto;

import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import utill.DateFormatter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ChildDto {

    @JMap
    private String name;

    @JMap
    private Long age;

    @JMap(value = "dateOfBirth")
    private String birthDay;

    @JMapConversion(from = {"dateOfBirth"}, to = {"birthDay"})
    public String conversion(Date birthDay) {
        return DateFormatter.dateToString(birthDay);
    }
}
