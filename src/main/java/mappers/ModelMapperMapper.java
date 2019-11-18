package mappers;

import dto.ChildDto;
import dto.ParentDto;
import java.text.ParseException;
import java.util.Date;
import model.Child;
import model.Parent;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import utill.DateFormatter;

public class ModelMapperMapper implements SourceMapper {

    private ModelMapper parentModelMapper = new ModelMapper();

    public ModelMapperMapper() {

        parentModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<String, Date> stringToDateConverter = context -> {
            try {
                return DateFormatter.stringToDate(context.getSource());
            } catch (ParseException e) {
                return null;
            }
        };
        parentModelMapper.addConverter(stringToDateConverter);

        Converter<Date, String> dateToStringConverter = context -> DateFormatter.dateToString(context.getSource());
        parentModelMapper.addConverter(dateToStringConverter);

        parentModelMapper.addMappings(new PropertyMap<Parent, ParentDto>() {
            @Override
            protected void configure() {
                using(dateToStringConverter).map(source.getDateOfBirth(), destination.getBirthDay());
                using(dateToStringConverter).map(source.getChild().getDateOfBirth(), destination.getChild().getBirthDay());
                map(source.getChildren(), destination.getChildren());
            }
        });
        parentModelMapper.addMappings(new PropertyMap<ParentDto, Parent>() {
            @Override
            protected void configure() {
                using(stringToDateConverter).map(source.getBirthDay(), destination.getDateOfBirth());
                using(stringToDateConverter).map(source.getChild().getBirthDay(), destination.getChild().getDateOfBirth());
                map(source.getChildren(), destination.getChildren());
            }
        });

        parentModelMapper.addMappings(new PropertyMap<Child, ChildDto>() {
            @Override
            protected void configure() {
                using(dateToStringConverter).map(source.getDateOfBirth(), destination.getBirthDay());
            }
        });
        parentModelMapper.addMappings(new PropertyMap<ChildDto, Child>() {
            @Override
            protected void configure() {
                using(stringToDateConverter).map(source.getBirthDay(), destination.getDateOfBirth());
            }
        });
    }

    @Override
    public Parent parentDtoToParent(ParentDto parentDto) {
        return parentModelMapper.map(parentDto, Parent.class);
    }

    @Override
    public ParentDto parentToParentDto(Parent parent) {
        return parentModelMapper.map(parent, ParentDto.class);
    }

    @Override
    public Child childDtoToChild(ChildDto childDto) {
        return parentModelMapper.map(childDto, Child.class);
    }

    @Override
    public ChildDto childToChildDto(Child child) {
        return parentModelMapper.map(child, ChildDto.class);
    }

}
