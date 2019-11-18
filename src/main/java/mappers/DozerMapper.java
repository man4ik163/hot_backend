package mappers;

import dto.ChildDto;
import dto.ParentDto;
import model.Child;
import model.Parent;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DozerMapper implements SourceMapper {

    private final Mapper mapper;

    public DozerMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(
                DozerMapper.class.getResourceAsStream("/dozer-mapping.xml"));
        this.mapper = mapper;
    }

    @Override
    public Parent parentDtoToParent(ParentDto parentDto) {
        return mapper.map(parentDto, Parent.class);
    }

    @Override
    public ParentDto parentToParentDto(Parent parent) {
        return mapper.map(parent, ParentDto.class);
    }


    @Override
    public Child childDtoToChild(ChildDto childDto) {
        return null;
    }

    @Override
    public ChildDto childToChildDto(Child child) {
        return null;
    }

}
