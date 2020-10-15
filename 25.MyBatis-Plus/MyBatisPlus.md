# MyBatisPlus

## 介绍

> 愿景
>
> 我们的愿景是成为 MyBatis 最好的搭档，就像 [魂斗罗](https://baomidou.com/img/contra.jpg) 中的 1P、2P，基友搭配，效率翻倍。

![image-20201010170005926](F:\工作\记录\25.MyBatis-Plus\MyBatisPlus.assets\image-20201010170005926.png)

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题

- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作

## 快速搭建

**1. 引入依赖**

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.2.0</version>
</dependency>
```

**使用mybatis-plus框架就可以不用使用通用Mapper框架了，应为mp有自己的通用方法。**

**2. 配置**

```yaml
server:
  port: 8081
spring:
  application:
    name: item-service
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/leyou?useSSL=false
    username: root
    password: Shuaizi521.
```

**3. 创建mapper**

创建Mapper继承BaseMapper方法。

```java
@Component
public interface StudentMapper extends BaseMapper<Student> {
}
```

**4. 配置扫描位置**
在启动类配置扫描路径。

```java
@MapperScan("com.leyou.item.mapper")
public class LyItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyItemApplication.class);
    }

}
```

**5.测试**

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test() {
        List<Student> students = studentMapper.selectList(null);
        // Assert.assertEquals(5, students.size());
        students.forEach(System.out::println);
    }

}
```

## MyBatis常用注解

1. id 默认为自增id
2. `@TableName("table_name")` 指定特殊表明
3. `@TableId()` 指定特殊名称id
4. `TableField("columnName")`

```java
@Data
@TableName("student")
public class Student {

    @TableId
    private Long sid;
    @TableField("username")
    private String username;
    private Integer age;
    private String sex;
    private Integer banji_id;

}
```

### 排除非表字段的三种方式

1. 添加关键字 `transient` 使用后该字段不参与序列化。

2. 静态变量方法 `static` 全类只有一份
3. `@TableFileId(exist=false)`

## 日志输出

```yaml
#打印日志
logging:
  level:
    root: warn #级别
    com.ocean.user.mapper: trace # 比debug级别还要低
  pattern:
    console:'%p%m%n' # 日志输出格式
```

## 常用语法

### #1 基本查询

#### **1.1 selectById()**

根据id查询。

```java
UserDo userDo = userMapper.selectById("6062c48ced714a4eaca977e00e504665");
System.out.println(userDo);
```

#### **1.2 selectBatchIds(List<>)**

根据id集合查询。

```java
List<String> list = Arrays.asList("6062c48ced714a4eaca977e00e504665", "admin");
List<UserDo> userDos = userMapper.selectBatchIds(list);
userDos.forEach(System.out::println);
```

#### **1.3 selectByMap(HashMap<K,V>)**

**根据Map进行查询**。map中的key和value对应着where中的条件。

`value为null的属性不会再被打印出来`

map表示表字段。

```java
HashMap<String,Object> map = new HashMap<>();
map.put("username","ZhangShuai");
List<UserDo> userDos = userMapper.selectByMap(map);
userDos.forEach(System.out::println);
```

#### **1.4 selectObjs(QueryWrapper<>)**

只返回第一列。  

```java
@Test
public void selectListByWrapperObjs() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    List<Object> objects = userMapper.selectObjs(queryWrapper); //只返回第一列
    objects.forEach(System.out::println);
}
```

#### **1.5 selectCount(QueryWrapper<>)**

记录条数

```java
@Test
public void selectListByWrapperCount() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    queryWrapper.likeRight("name", "王").or(qw->qw.lt("age", 40).gt("age", 40).isNotNull("email"));
    Integer integer = userMapper.selectCount(queryWrapper);
    System.out.println("总记录数：" + integer);
}
```

#### **1.6 selectOne(queryWrapper)**

只返回一条记录或者为空。

```java
@Test
public void selectListByWrapperOne() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    queryWrapper.likeRight("name", "王").or(qw->qw.lt("age", 40).gt("age", 40).isNotNull("email"));
    UserDo userDo = userMapper.selectOne(queryWrapper); // 只能查询一条记录 或者是为空
}
```

### #2 通过条件构造器进行查询

#### 2.1声明构造器

```java
// 方式1 
QueryWrapper<UserDo> userQueryWrapper = new QueryWrapper<>();
// 方式2
QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query()
```

#### 2.2 构造器中方法的使用

直接上例子。

`案例1`：实现where username like %sh% and age < 40

```java
 @Test
public void selectListByWrapper() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // where username like %sh% and age < 40
    queryWrapper.like("username", "sh").lt("age",40);
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例2`：实现where username like %sh% and age between 20 and 40 and email is not null

```java
@Test
public void selectListByWrapper2() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // where username like %sh% and age between 20 and 40 and email is not null
    queryWrapper.like("name","sh").between("age", 20,40).isNotNull("email");
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例3`：实现where name like '王%' or age >= 40 order by age desc, id asc

```java
@Test
public void selectListByWrapper3() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // name like '王%' or age >= 40 order by age desc, id asc
    queryWrapper.likeRight("name","sh").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例4`：date_formate(create_time,'%Y-%m-%d') = 2019-02-14 and `manager_id in (select id from user where name like '王%')`

```java
@Test
public void selectListByWrapper4() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // date_formate(create_time,'%Y-%m-%d') = 2019-02-14 and manager_id in (select id from user where name like '王%')
    queryWrapper.apply("date_formate(create_time,'%Y-%m-%d')={0}", "2019-02-14")
        .inSql("manager_id", "select id from user where name like '王%'"); // 子查询
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例5`：where name like 'sh%' and (age < 40 or email is not null)    `使用了lamada表达式。`

```java
@Test
public void selectListByWrapper5() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // where name like 'sh%' and (age < 40 or email is not null)
   	queryWrapper.likeRight("name", "sh").and(wp->(wp.lt("age", 40).or().isNotNull("email")));
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例6`：name like '王%' or (age < 40 and age > 20 and email is not null)

```java
@Test
public void selectListByWrapper6() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // name like '王%' or (age < 40 and age > 20 and email is not null)
   	queryWrapper.likeRight("name", "王").or(wp->wp.lt("age", 40).gt("age", 20).isNotNull("email");
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例7`：where (age < 40 or email is not null) and name like 'Wang%'

```java
@Test
    public void selectListByWrapper7() {
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
        //  (age < 40 or email is not null) and name like 'Wang%'
        queryWrapper.nested(wq->wq.lt("age", 40).or().isNotNull("email")).likeRight("name","wang");
        List<UserDo> userDos = userMapper.selectList(queryWrapper);
        userDos.forEach(System.out::println);
    }
```

`案例8`：//  age in (33,34,35)

```java
@Test
public void selectListByWrapper8() {
    //  QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    //  age in (33,34,35)
    queryWrapper.in("age", Arrays.asList(33, 34, 35));
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例9`：limit

```java
@Test
public void selectListByWrapper9() {
    //        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    //  limit
    // 使用last 有sql注入的风险
    queryWrapper.in("age", Arrays.asList(33, 34, 35)).last("limit 1");
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例10`：展示部分列  select id, name where username like %sh% and age < 40

```java
@Test
public void selectListByWrapperSupper() {
    //QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // select id, name where username like %sh% and age < 40
    queryWrapper.select("id", "name").like("username", "sh").lt("age",40);
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例10.5`：展示部分列 select id, name, age, gender where username like %sh% and age < 40

```java
@Test
public void selectListByWrapperSupper2() {
    //  QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // select id, name, age, gender where username like %sh% and age < 40
    queryWrapper.select(UserDo.class, info->!info.getColumn().equals("create_time")&&!info.getColumn().equals("manager_id"))
        .like("username", "sh").lt("age",40);
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);
}
```

`案例11`： allEq用法1

```java
public void selectListByWrapperSupperAllEq() {
        //QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "zhangsan");
        params.put("age", "10");
        queryWrapper.allEq(params);
        List<UserDo> userDos = userMapper.selectList(queryWrapper);
        userDos.forEach(System.out::println);
    }
```

`案例12`：allEq用法2

allEq中添加了一个过滤器，如下面的是key不等于username

所以他打印出来的日志是：

`SELECT id,password,create_time,update_time,username FROM sys_user WHERE status='0' AND (password = ?)`

```java
@Test
public void selectListByWrapperSupperAllEq2() {
    //QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    HashMap<String, Object> params = new HashMap<>();
    params.put("username", "shuaizizhang");
    params.put("password", "00000000");
    queryWrapper.allEq((k,v)->!k.equals("username"), params); // k v 表示 key value
    List<UserDo> userDos = userMapper.selectList(queryWrapper);
    userDos.forEach(System.out::println);

}
```

















**根据QueryWrapper进行查询。**

```java
@Test
public void selectListByWrapperMaps2() {
    QueryWrapper<UserDo> queryWrapper = Wrappers.<UserDo>query();
    // 等同于：select avg(age) avg_age,min(age) min_age, max(age) max_age from user group by manage_id having sum(age) < 500
    queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("manage_id").having("sum(age)<{0}", 500);
    List<Map<String, Object>> mapList = userMapper.selectMaps(queryWrapper);
}
```