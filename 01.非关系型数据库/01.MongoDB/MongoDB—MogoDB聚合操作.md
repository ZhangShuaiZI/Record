[TOC]



# MongoDB——MogoDB聚合操作

## MongDB的聚合操作

MongoDB的聚合操作是通过数据处理管道来实现的。一次操作可以通过多个管道来处理数据文档。使用管道是有顺序的，会依次将管道的结果传至下一个管道中继续处理。

<font size=1 color=red>聚合操作将安装以下数据进行讲解：</font>

```json
{
   _id: ObjectId(7df78ad8902c)
   title: 'MongoDB Overview', 
   description: 'MongoDB is no sql database',
   by_user: 'runoob.com',
   url: 'http://www.runoob.com',
   tags: ['mongodb', 'database', 'NoSQL'],
   likes: 100
},
{
   _id: ObjectId(7df78ad8902d)
   title: 'NoSQL Overview', 
   description: 'No sql database is very fast',
   by_user: 'runoob.com',
   url: 'http://www.runoob.com',
   tags: ['mongodb', 'database', 'NoSQL'],
   likes: 10
},
{
   _id: ObjectId(7df78ad8902e)
   title: 'Neo4j Overview', 
   description: 'Neo4j is no sql database',
   by_user: 'Neo4j',
   url: 'http://www.neo4j.com',
   tags: ['neo4j', 'database', 'NoSQL'],
   likes: 750
}
```

### 1. 管道1：分组$group

`$group`操作符主要作用是给文档中的特定字段进行分组，通常搭配`操作符`进行操作。

```json
// 查询分组后每个分组的数量
db.mycollection.aggregate([
    {
        $group: { _id: "$by_user", "sum":{$sum: 1}}
    }
])

// 数据结果
{
	"_id" : "runoob.com",
	"sum" : 2
},
{
	"_id" : "Neo4j",
	"sum" : 1
}
```

<font color=red>操作符：</font>

| 操作符    | 说明                                     |
| --------- | ---------------------------------------- |
| $sum      | group分组后，对指定文档数据进行求和。    |
| $avg      | 求平均数。                               |
| $first    | 同组文档，取出第一个文档。               |
| $last     | 同组文档，取出最后一个文档。             |
| $max      | 同组文档，取最大值。                     |
| $min      | 同组文档，取最小值。                     |
| $push     | 同组文档，以数组的形式显示指定字段。     |
| $addToSet | 同组文档，以数组的方式显示字段不重复值。 |

### 2. 管道2：显示$project

将显示的字段设置为1，不显示的字段设置为0。

```json
db.mycollection.aggregate([
        {
            $project: {"_id":1, "by_user":1}
        }
    ]);

```

输出结果

```json
// 返回结果
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac59"),
	"by_user" : "runoob.com"
},
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5a"),
	"by_user" : "runoob.com"
},
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5b"),
	"by_user" : "Neo4j"
}
```

**<font color=red>`$project`同样可以搭配`操作符`进行使用。</font>**

#### 1）截取指定的字符--$substr

```json
db.collection.aggregate([
    {
        "$project":
        {
            "id":0,
        	"title": {$substr:["$title",0,8]}
        }
    }
]);
```

#### 2）条件判断--$switch

```json
db.mycollection.aggregate([
        {
            $project: {"_id":1, "by_user":1, 
                "likes": {
                    $switch:{
                        branches:[
                            {case:{$gt: ["$likes",100]}, then:"likes>100"}, 
                            {case:{$eq: ["$likes",100]}, then:"likes=100"}
                        ], 
                        default: "likes<100"
                    }
                }
            }
        }
    ]);
```

**返回结果**

```json
/* 1 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac59"),
	"by_user" : "runoob.com",
	"likes" : "likes=100"
},

/* 2 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5a"),
	"by_user" : "runoob.com",
	"likes" : "likes<100"
},

/* 3 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5b"),
	"by_user" : "Neo4j",
	"likes" : "likes>100"
}
```

#### 3）查询指定字符在文档中的位置--$indexOfBytes

```json
db.mycollection.aggregate([
        {
            $project: {
                "_id":1,
                "by_user":1,
                "user": {
                    $indexOfBytes: ["$by_user", "com",0,10]
                }
            }
        }
    ]);
```

其中0,10为字符范围，也可以去掉。

返回结果：

```json
/* 1 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac59"),
	"by_user" : "runoob.com",
	"user" : 7
},

/* 2 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5a"),
	"by_user" : "runoob.com",
	"user" : 7
},

/* 3 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5b"),
	"by_user" : "Neo4j",
	"user" : -1
}
```

#### 4）计算字符串的长度--$strLenBytes

```json
db.mycollection.aggregate([
        {
            $project: {
                "_id":1,
                "by_user":1,
                "user": {
                    $strLenBytes: "$by_user"
                }
            }
        }
    ]);
```

**返回结果**

```json
db.mycollection.aggregate([
        {
            $project: {
                "_id":1,
                "by_user":1,
                "user": {
                    $strLenBytes: "$by_user"
                }
            }
        }
    ]);
```

#### 5）比较字符串间的大小--$strcasecmp

比较字符串之间的大小：

* 结果为1：表示第一个字符串<font color=red>大于</font>第二个字符串
* 结果为0：表示第一个字符串<font color=red>等于</font>第二个字符串
* 结果为-1：表示第一个字符串<font color=red>小于</font>第二个字符串

```json
db.mycollection.aggregate([
        {
            $project: {
                "_id":1,
                "by_user":1,
                "user": {
                    $strcasecmp: ["$by_user","nsssssssssssss"]
                }
            }
        }
    ]);
```

输出结果

```json
/* 1 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac59"),
	"by_user" : "runoob.com",
	"user" : 1
},

/* 2 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5a"),
	"by_user" : "runoob.com",
	"user" : 1
},

/* 3 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5b"),
	"by_user" : "Neo4j",
	"user" : -1
}
```

#### 6）大小写转换--$toLower $toUpper

```json
db.mycollection.aggregate([
    {
        $project: {"_id":1, "by_user":1,
            "toCase": {
                $toUpper: "$by_user"
            },
            "toLowerCase": {
                $toLower: "$by_user"
            }
        }
    }
]);
```

#### 7）字符串合并--$concat

```json
db.mycollection.aggregate([
    {
        $project: {"_id":1, "by_user":1,
            "concat": {
                $concat: ["$by_user","123"]
            }
        }
    }
]);
```

#### 8）字符串拆分--$split

```json
db.mycollection.aggregate([
        {$project: {"_id":1, "by_user":1, "split": {
            $split: ["$by_user", "."]
        }}}
    ]).pretty()
```

**输出结果**

```json
/* 1 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac59"),
	"by_user" : "runoob.com",
	"split" : [
		"runoob",
		"com"
	]
},

/* 2 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5a"),
	"by_user" : "runoob.com",
	"split" : [
		"runoob",
		"com"
	]
},

/* 3 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5b"),
	"by_user" : "Neo4j",
	"split" : [
		"Neo4j"
	]
}
```

#### 9）加减乘除运算

* `Add`
* `Subtract`
* `Multiply`
* `Mod`

```json
db.mycollection.aggregate([
        {
            $project: {"_id":1, "likes":1,
                "add": {$add: ["$likes","$likes"]},
                "subtract": {$subtract: ["$likes","$likes"]},
                "multiply": {$multiply: ["$likes", 2]},
                "mod": {$mod: ["$likes", "$likes"]}
            }
        }
    ]);
```

**输出结果**

```json
/* 1 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac59"),
	"likes" : 100,
	"add" : 200,
	"subtract" : 0,
	"multiply" : 200,
	"mod" : 0
},

/* 2 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5a"),
	"likes" : 10,
	"add" : 20,
	"subtract" : 0,
	"multiply" : 20,
	"mod" : 0
},

/* 3 createdAt:2020/8/2 下午6:13:45*/
{
	"_id" : ObjectId("5f2691d914b00c091cc1ac5b"),
	"likes" : 750,
	"add" : 1500,
	"subtract" : 0,
	"multiply" : 1500,
	"mod" : 0
}
```

#### 10）时间相关：

* `$year`：返回年份
* `$month`：返回月份
* `$week`：返回一年内的第几周
* `$hour`：返回小时
* `$minute`：返回分钟
* `$second`：返回秒
* `$millisecond`：返回毫秒
* `$dayOfYear`：一年的第几天
* `$dayOfMonth`：一月的第几天
* `$dayOfWeek`:一星期的第几天

### 3.管道3：筛选$match



