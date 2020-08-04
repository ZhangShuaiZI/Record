* [MongoDB--删除操作](#mongodb--删除操作)
  * [1. remove()](#1-remove)
  * [2. deleteOne()](#2-deleteone)
  * [3. deleteMany()](#3-deletemany)


# MongoDB--删除操作

## 1. remove()

<font color=red>语法格式:</font>

```shell
db.collection.remove(
	<query>,
	{
		justOne: <boolean>, #默认[false] true:只删除一个 false:删除所有
		writeConcern>: <document>,
		collation: <document>
	}
);
```

## 2. deleteOne()

只删除一个。

```
db.collection.deleteOne({
	"name":"zhangsan"
});
```

## 3. deleteMany()

删除多个。

```
db.collection.deleteMany("
	"name":""
");
```

