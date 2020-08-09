* [MongoDB--存储过程](#mongodb--存储过程)
  * [MongoDB存储过程](#mongodb存储过程)
  * [创建存储过程](#创建存储过程)
  * [查看存储过程](#查看存储过程)
  * [执行存储过程](#执行存储过程)


# MongoDB--存储过程

## MongoDB存储过程

MongoDB存储过程与传统关系型数据库的存储过程相同，将一个或多个命令保存成一段程序。调用时会根据程序中的制令进行操作。在MongoDB中国，存储过程使用`JavaScript`来写的，并且**<font color=red>存储在`system.js`这个特殊集合中</font>**。

## 创建存储过程

创建一个输入名称修改likes为1000的存储过程。

```shell
> db.system.js.insert({
	_id: "addLikesByName",
	value: function(param){
		return db.mycollection.update(
			{
				"name":param,
				$set: {likes:10000}
			}
		)
	}
});
```

加载存储过程

```
db.loadServerScripts();
```

创建完存储过程之后需要加载，否则MongoDB会报错。

## 查看存储过程

```
db.system.js.find().pretty();
```

## 执行存储过程

```
addLikesByName("zhangsan");
或
db.eval('addLikesByName("zhangsan")');
```

