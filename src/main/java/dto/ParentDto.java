package dto;

import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import utill.DateFormatter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ParentDto {

    @JMap
    private String name;

    @JMap
    private Long age;

    @JMap(value = "dateOfBirth")
    private String birthDay;

    @JMap
    private ChildDto child;

    @JMap
    private List<ChildDto> children;

    @JMapConversion(from = {"dateOfBirth"}, to = {"birthDay"})
    public String conversion(Date birthDay) {
        return DateFormatter.dateToString(birthDay);
    }
}
