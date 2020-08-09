* [MongoDB--更新操作](#mongodb--更新操作)
  * [1. update()](#1-update)
  * [2. save()](#2-save)
  * [3. updateOne()](#3-updateone)
  * [4. updateMany()](#4-updatemany)
  * [5. replaceOne()](#5-replaceone)


# MongoDB--更新操作

常用的修改操作有`update()`和`save`，除此之外，还有`updateOne()`、`updateMany()`和`replaceOne()`。

## 1. update()

<FONT COLOR=RED SIZE=4>**语法：**</FONT>

```shell
db.collection.update(
	<query>,
	<update>,
	{
		upsert:<boolean>, #[false] ture没有匹配时会插入
		multi:<boolean>,  #[false] false只会修改一个，true修改所有符合条件的
		writeConcern:<document>,  #用来指定更新的排序规则
		collation:<document>, #更新数组中的特定元素
		arrayFilters:[<filterdocument1>,...]
	}
);
```

<FONT COLOR=RED SIZE=4>**示例：**</FONT>

1. 修改名字为zhangsan的likes值为1000。

```
db.collection.update(
	{
		"name":"zhangsan"
	},
	{
		$set:{
			"likes":1000
		}
	}
);
```

2. 将name为zhangsan并且数组ids为0000修改为2233。

```
db.collection.update(
	{ "name":"zhangsan" },
	{ $set: "ids.$[i]"."2233"},
	{ arrayFilters: [{"i":{$eq:"0000"}}]}
);
```

## 2. save()

`save()`不仅可以插入文档，还可以更新文档。与`update()`不同，`save()`在更新文档时<font color=red>**必须加上`_id`字段**</font>，从而通过`_id`字段对原文档进行覆盖式更新。

<FONT COLOR=RED SIZE=4>**示例：**</FONT>

```
db.comment.save({
	_id : ObjectId("4e5tn8s...347e4"),
	name : "zhangsan",
	likes : "1000"
});
```

## 3. updateOne()

只会更新一个。

```
db.collection.updateOne(
	{name:"zhangsan"},
	{$set:{likes:10000}}
);
```

## 4. updateMany()

更新多个。等通用update中的multi参数为true。

```
db.collection.updateMany(
	{name:"zhangsan"},
	{$set: {likes:10000}}
);
```

## 5. replaceOne()

类似save()方法，与之不同的是replace不需要`_id`，并且只更新一条数据。

```
db.comment.replaceOne(
	{producMode:""},
	{productMode:"Set"}
);
```

