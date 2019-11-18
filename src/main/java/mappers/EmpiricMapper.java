package mappers;

import dto.ChildDto;
import dto.ParentDto;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Child;
import model.Parent;
import utill.DateFormatter;

public class EmpiricMapper {

    public Parent parentDtoToParent(ParentDto parentDto) throws ParseException {
        if (parentDto == null) {
            return null;
        }
        Parent parent = new Parent();
        parent.setName(parentDto.getName());
        parent.setAge(parentDto.getAge());
        parent.setDateOfBirth(DateFormatter.stringToDate(parentDto.getBirthDay()));
        parent.setChild(childDtoToChild(parentDto.getChild()));
        parent.setChildren(listChildDtoToListChild(parentDto.getChildren()));
        return parent;
    }

    public ParentDto parentToParentDto(Parent parent) {
        if (parent == null) {
            return null;
        }
        ParentDto parentDto = new ParentDto();
        parentDto.setName(parent.getName());
        parentDto.setAge(parent.getAge());
        parentDto.setBirthDay(DateFormatter.dateToString(parent.getDateOfBirth()));
        parentDto.setChild(childToChildDto(parent.getChild()));
        parentDto.setChildren(listChildToListChildDto(parent.getChildren()));
        return parentDto;
    }

    private Child childDtoToChild(ChildDto childDto) throws ParseException {
        if (childDto == null) {
            return null;
        }
        Child child = new Child();
        child.setName(childDto.getName());
        child.setAge(childDto.getAge());
        child.setDateOfBirth(DateFormatter.stringToDate(childDto.getBirthDay()));
        return child;
    }

    private ChildDto childToChildDto(Child child) {
        if (child == null) {
            return null;
        }
        ChildDto childDto = new ChildDto();
        childDto.setName(child.getName());
        childDto.setAge(child.getAge());
        childDto.setBirthDay(DateFormatter.dateToString(child.getDateOfBirth()));
        return childDto;
    }

    private List<ChildDto> listChildToListChildDto(List<Child> children) {
        if (children == null) {
            return null;
        }
        if (children.isEmpty()) {
            return Collections.emptyList();
        }
        List<ChildDto> childrenDto = new ArrayList<>();
        for (Child child : children) {
            if (child == null) {
                continue;
            }
            ChildDto childDto = new ChildDto();
            childDto.setName(child.getName());
            childDto.setAge(child.getAge());
            childDto.setBirthDay(DateFormatter.dateToString(child.getDateOfBirth()));
            childrenDto.add(childDto);
        }
        return childrenDto;
    }

    private List<Child> listChildDtoToListChild(List<ChildDto> children) throws ParseException {
        if (children == null) {
            return null;
        }
        if (children.isEmpty()) {
            return Collections.emptyList();
        }
        List<Child> childrenResult = new ArrayList<>();
        for (ChildDto childDto : children) {
            if (childDto == null) {
                continue;
            }
            Child child = new Child();
            child.setName(childDto.getName());
            child.setAge(childDto.getAge());
            child.setDateOfBirth(DateFormatter.stringToDate(childDto.getBirthDay()));
            childrenResult.add(child);
        }
        return childrenResult;
    }

}
