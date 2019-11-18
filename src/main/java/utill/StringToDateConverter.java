package utill;

import java.text.ParseException;
import java.util.Date;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class StringToDateConverter extends CustomConverter<String, Date> {

    @Override
    public Date convert(String s, Type<? extends Date> type, MappingContext mappingContext) {
        try {
            return DateFormatter.stringToDate(s);
        } catch (ParseException e) {
            return null;
        }
    }
}
