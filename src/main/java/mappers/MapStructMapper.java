package mappers;

import dto.ChildDto;
import dto.ParentDto;
import java.util.List;
import model.Child;
import model.Parent;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper extends SourceMapper {

    MapStructMapper MAPPER = Mappers.getMapper(MapStructMapper.class);

    @Override
    @Named(value = "parentDtoToParent")
    @Mapping(source = "birthDay", target = "dateOfBirth", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "child", target = "child", qualifiedByName = "childDtoToChild")
    @Mapping(source = "children", target = "children", qualifiedByName = "childDtoListToChildList")
    Parent parentDtoToParent(ParentDto parentDto);

    @Override
    @Named(value = "parentToParentDto")
    @Mapping(source = "dateOfBirth", target = "birthDay", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "child", target = "child", qualifiedByName = "childToChildDto")
    @Mapping(source = "children", target = "children", qualifiedByName = "childListToChildDtoList")
    ParentDto parentToParentDto(Parent parent);

    @Override
    @Named(value = "childDtoToChild")
    @Mapping(source = "birthDay", target = "dateOfBirth", dateFormat = "dd-MM-yyyy")
    Child childDtoToChild(ChildDto childDto);

    @Override
    @Named(value = "childToChildDto")
    @Mapping(source = "dateOfBirth", target = "birthDay", dateFormat = "dd-MM-yyyy")
    ChildDto childToChildDto(Child child);

    @Named(value = "childDtoListToChildList")
    @IterableMapping(qualifiedByName = "childDtoToChild")
    List<Child> childDtoListToChildList(List<ChildDto> childDtos);

    @Named(value = "childListToChildDtoList")
    @IterableMapping(qualifiedByName = "childToChildDto")
    List<ChildDto> childListToChildDtoList(List<Child> children);

}
