package mappers;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import dto.ChildDto;
import dto.ParentDto;
import model.Child;
import model.Parent;

public class JMapperMapper implements SourceMapper {

    private JMapper<ParentDto, Parent> parentToParentDto;
    private JMapper<Parent, ParentDto> parentDtoToParent;
    private JMapper<ChildDto, Child> childToChildDto;
    private JMapper<Child, ChildDto> childDtoToChild;

    public JMapperMapper() {
        JMapperAPI apiParent = new JMapperAPI()
                .add(JMapperAPI.mappedClass(Parent.class));
        parentToParentDto = new JMapper<>(ParentDto.class, Parent.class, apiParent);

        JMapperAPI apiParentDto = new JMapperAPI()
                .add(JMapperAPI.mappedClass(ParentDto.class));
        parentDtoToParent = new JMapper<>(Parent.class, ParentDto.class, apiParentDto);

        JMapperAPI apiChild = new JMapperAPI()
                .add(JMapperAPI.mappedClass(Child.class));
        childToChildDto = new JMapper<>(ChildDto.class, Child.class, apiChild);

        JMapperAPI apiChildDto = new JMapperAPI()
                .add(JMapperAPI.mappedClass(ChildDto.class));
        childDtoToChild = new JMapper<>(Child.class, ChildDto.class, apiChildDto);
    }

    @Override
    public Parent parentDtoToParent(ParentDto parentDto) {
        return parentDtoToParent.getDestination(parentDto);
    }

    @Override
    public ParentDto parentToParentDto(Parent parent) {
        return parentToParentDto.getDestination(parent);
    }

    @Override
    public Child childDtoToChild(ChildDto childDto) {
        return childDtoToChild.getDestination(childDto);
    }

    @Override
    public ChildDto childToChildDto(Child child) {
        return childToChildDto.getDestination(child);
    }
}
