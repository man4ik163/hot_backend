package test_mapper;

import dto.ChildDto;
import dto.ParentDto;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import mappers.DozerMapper;
import mappers.EmpiricMapper;
import mappers.JMapperMapper;
import mappers.MapStructMapper;
import mappers.ModelMapperMapper;
import mappers.OrikaMapper;
import model.Child;
import model.Parent;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import utill.DateFormatter;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.SECONDS)
public class TestMapper {

    private Parent parent;
    private ParentDto parentDto;
    private Child child;
    private ChildDto childDto;

    private EmpiricMapper empiricMapper = new EmpiricMapper();
    private DozerMapper dozerMapper = new DozerMapper();
    private ModelMapperMapper modelMapper = new ModelMapperMapper();
    private JMapperMapper jMapper = new JMapperMapper();
    private OrikaMapper orikaMapper = new OrikaMapper();

    @Setup
    public void prepare() {
        //Child
        child = new Child();
        child.setAge(12L);
        child.setName("Luba");
        child.setDateOfBirth(new Date());
        //ChildDto
        childDto = new ChildDto();
        childDto.setAge(12L);
        childDto.setName("Luba");
        childDto.setBirthDay(DateFormatter.dateToString(new Date()));
        //Parent
        parent = new Parent();
        parent.setAge(37L);
        parent.setName("Ivan");
        parent.setDateOfBirth(new Date());
        parent.setChild(child);
        parent.setChildren(getChildrenList());
        //ParentDto
        parentDto = new ParentDto();
        parentDto.setAge(37L);
        parentDto.setName("Ivan");
        parentDto.setBirthDay(DateFormatter.dateToString(new Date()));
        parentDto.setChild(childDto);
        parentDto.setChildren(getChildrenDtoList());
    }

    private List<Child> getChildrenList() {
        List<Child> childrenForList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Child child = new Child();
            child.setAge((long) i);
            child.setName("Luba" + i);
            child.setDateOfBirth(new Date());
            childrenForList.add(child);
        }
        return childrenForList;
    }

    private List<ChildDto> getChildrenDtoList() {
        List<ChildDto> childrenForList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            ChildDto childDto = new ChildDto();
            childDto.setAge((long) i);
            childDto.setName("Luba" + i);
            childDto.setBirthDay(DateFormatter.dateToString(new Date()));
            childrenForList.add(childDto);
        }
        return childrenForList;
    }

    /**
     * Empiric
     */
    @Benchmark
    public ParentDto testEmpiricMapperParentToParentDto() {
        return empiricMapper.parentToParentDto(parent);
    }

    @Benchmark
    public Parent testEmpiricMapperParentDtoToParent() throws ParseException {
        return empiricMapper.parentDtoToParent(parentDto);
    }

    /**
     * MapStruct
     */
    @Benchmark
    public Parent testMapStructMapperParentToParentDto() {
        return MapStructMapper.MAPPER.parentDtoToParent(parentDto);
    }

    @Benchmark
    public ParentDto testMapStructMapperParentDtoToParent() {
        return MapStructMapper.MAPPER.parentToParentDto(parent);
    }

    /**
     * Dozer
     */
    @Benchmark
    public ParentDto testDozerMapperParentToParentDto() {
        return dozerMapper.parentToParentDto(parent);
    }

    @Benchmark
    public Parent testDozerMapperParentDtoToParent() {
        return dozerMapper.parentDtoToParent(parentDto);
    }

    /**
     * ModelMapper
     */
    @Benchmark
    public ParentDto testModelMapperParentToParentDto() {
        return modelMapper.parentToParentDto(parent);
    }

    @Benchmark
    public Parent testModelMapperParentDtoToParent() {
        return modelMapper.parentDtoToParent(parentDto);
    }

    /**
     * JMapper
     */
    @Benchmark
    public ParentDto testJMapperParentToParentDto() {
        return jMapper.parentToParentDto(parent);
    }

    @Benchmark
    public Parent testJMapperParentDtoToParent() {
        return jMapper.parentDtoToParent(parentDto);
    }

    /**
     * Orika
     */
    @Benchmark
    public ParentDto testOrikaParentToParentDto() {
        return orikaMapper.parentToParentDto(parent);
    }

    @Benchmark
    public Parent testOrikaParentDtoToParent() {
        return orikaMapper.parentDtoToParent(parentDto);
    }

}
