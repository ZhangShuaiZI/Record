* [Mongo--游标](#mongo--游标)
  * [Next遍历游标](#next遍历游标)
  * [ForEach遍历游标](#foreach遍历游标)
  * [Limit限制结果数量](#limit限制结果数量)
  * [Skip限制返回记录的起点](#skip限制返回记录的起点)
  * [Sort排序结果](#sort排序结果)



# Mongo--游标

## Next遍历游标

`find()`命令并不直接返回结果，而是返回一个结果集的迭代器，即游标。使用像大多数数据库产品一样，MongoDB 也是用游标来循环处理每一条结果数据，具体语法如下：

```
> for( var c = db.t3.find(); c.hasNext(); ) {
... printjson( c.next());
... }
```

输出结果如下：

```
{ "_id" : ObjectId("4fb8e4838b2cb86417c9423a"), "age" : 1 }
{ "_id" : ObjectId("4fb8e4878b2cb86417c9423b"), "age" : 2 }
{ "_id" : ObjectId("4fb8e4898b2cb86417c9423c"), "age" : 3 }
{ "_id" : ObjectId("4fb8e48c8b2cb86417c9423d"), "age" : 4 }
{ "_id" : ObjectId("4fb8e48e8b2cb86417c9423e"), "age" : 5 }
```

## ForEach遍历游标

MongoDB 还有另一种方式来处理游标，即`forEach()`方法：

```
> db.t3.find().forEach( function(u) { printjson(u); } );
```

输出结果如下：

```
{ "_id" : ObjectId("4fb8e4838b2cb86417c9423a"), "age" : 1 }
{ "_id" : ObjectId("4fb8e4878b2cb86417c9423b"), "age" : 2 }
{ "_id" : ObjectId("4fb8e4898b2cb86417c9423c"), "age" : 3 }
{ "_id" : ObjectId("4fb8e48c8b2cb86417c9423d"), "age" : 4 }
```

## Limit限制结果数量

要是限制返回的记录数量，可在find()后使用limit()函数。例如，只返回3个结果，可以这样：

```
> db.c.find().limit(3)
```

要是匹配的结果不到3个，则返回匹配数量的结果。`limit()`的参数指定的是 返回结果数量的上限，而非下限。

查询users文档中2个记录：

```
db.users.find().limit(2);
```

## Skip限制返回记录的起点

从第3 条记录开始，返回5 条记录(limit 3, 5)

```
db.users.find().skip(3).limit(5);
```

举例如下:

C1 表的数据如下:

```
> db.c1.find()
{ "_id" : ObjectId("4fb5faaf6d0f9d8ea3fc91a8"), "name" : "Tony", "age" : 20 }
{ "_id" : ObjectId("4fb5fab96d0f9d8ea3fc91a9"), "name" : "Joe", "age" : 10 }
```

查询users 表的第2 条数据

```
> db.users.find().skip(1).limit(1)
```

## Sort排序结果

以年龄升序（asc）排列：

```
db.users.find().sort({age: 1});
```

以年龄降序（desc）排列：

```
db.users.find().sort({age: -1});
```

C1 表的数据如下:

```
> db.c1.find()
{ "_id" : ObjectId("4fb5faaf6d0f9d8ea3fc91a8"), "name" : "Tony", "age" : 20 }
{ "_id" : ObjectId("4fb5fab96d0f9d8ea3fc91a9"), "name" : "Joe", "age" : 10 }
```

查询c1 表按age 升序排列

```
> db.c1.find().sort({age: 1});
{ "_id" : ObjectId("4fb5fab96d0f9d8ea3fc91a9"), "name" : "Joe", "age" : 10 }
{ "_id" : ObjectId("4fb5faaf6d0f9d8ea3fc91a8"), "name" : "Tony", "age" : 20 }
```

第1 条是age=10 的，而后升序排列结果集。

查询users表按age 降序排列：

```
> db.users.find().sort({age: -1});
```
