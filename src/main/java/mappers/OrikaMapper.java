package mappers;

import dto.ChildDto;
import dto.ParentDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import model.Child;
import model.Parent;
import utill.StringToDateConverter;

public class OrikaMapper implements SourceMapper {

    private MapperFacade mapperParentFacade;
    private MapperFacade mapperChildFacade;

    public OrikaMapper() {
        MapperFactory mapperParentFactory = new DefaultMapperFactory
                .Builder().mapNulls(false).build();

        mapperParentFactory.getConverterFactory().registerConverter(new StringToDateConverter());

        MapperFactory mapperChildFactory = new DefaultMapperFactory
                .Builder().mapNulls(false).build();

        mapperChildFactory.getConverterFactory().registerConverter(new StringToDateConverter());

        mapperParentFactory.classMap(Parent.class, ParentDto.class)
                .field("dateOfBirth", "birthDay").byDefault().register();

        mapperParentFacade = mapperParentFactory.getMapperFacade();

        mapperChildFactory.classMap(Child.class, ChildDto.class)
                .field("dateOfBirth", "birthDay").byDefault().register();

        mapperChildFacade = mapperParentFactory.getMapperFacade();
    }

    @Override
    public Parent parentDtoToParent(ParentDto parentDto) {
        return mapperParentFacade.map(parentDto, Parent.class);
    }

    @Override
    public ParentDto parentToParentDto(Parent parent) {
        return mapperParentFacade.map(parent, ParentDto.class);
    }

    @Override
    public Child childDtoToChild(ChildDto childDto) {
        return mapperChildFacade.map(childDto, Child.class);
    }

    @Override
    public ChildDto childToChildDto(Child child) {
        return mapperChildFacade.map(child, ChildDto.class);
    }
}
