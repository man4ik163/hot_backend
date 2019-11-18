package mappers;

import dto.ChildDto;
import dto.ParentDto;
import model.Child;
import model.Parent;

public interface SourceMapper {

    Parent parentDtoToParent(ParentDto parentDto);

    ParentDto parentToParentDto(Parent parent);

    Child childDtoToChild(ChildDto childDto);

    ChildDto childToChildDto(Child child);

}
