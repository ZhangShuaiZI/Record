package cglib;

/**
 * <p>Title: Person</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/14 0014 15:43
 */
public class Person {

    private String name;
    private Integer age;

    public Person(){}

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName(Integer id){
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
