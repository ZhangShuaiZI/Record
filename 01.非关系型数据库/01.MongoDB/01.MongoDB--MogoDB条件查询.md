[TOC]

# MongoDB

## MongoDB查询

> 查询可使用语句`find()/findOne()`

### 1. 条件查询

#### 1）比较操作符

| 操作符 | 说明       |
| ------ | ---------- |
| $eq    | 相等       |
| $ne    | 不相等     |
| $gt    | 大于       |
| $gte   | 大于等于   |
| $lt    | 小于       |
| $lte   | 小于等于   |
| $in    | 在数组内   |
| $nin   | 不在数组内 |

<font size=2 color=red>**举例：1.参照likes大于等于100的数据：**</font>

```json
db.mycollection.findOne(
    {
        "likes": {
            $gte: 100
        }
    }
)
```

<font size=2 color=red>**举例：2.查询包含以下数组的数据：**</font>

```json
db.mycollection.find(
    {
        "by_user": {
            $in: ["admin", "runoob.com"]
        }
    }    
)
```



#### 2）逻辑操作符

| 操作符 | 说明 |
| ------ | ---- |
| $and   | 并且 |
| $or    | 或   |
| $nor   | 非或 |
| $not   | 非   |

<font size=2 color=red>**举例：And操作：**</font>

```json
db.mycollection.find(
    {
        $and: [
            {"likes": {$gte: 100}},
            {"by_user": {$in: ["runoob.com"]}}
        ]
    }    
)
```

其他操作类似。

#### 3）元素操作符

元素操作符是指，使用在字段元素的操作符。

| 操作符  | 说明                                                         |
| ------- | ------------------------------------------------------------ |
| $exists | 字段是否存在数据，如过为true则表示数据须符合该字段存在的条件。false则恰恰相反。 |
| $type   | 返回符合要求数据类型的数据。                                 |

<font size=2 color=red>**举例：$exists操作：**</font>

<font size=2 color=red>**举例：$type操作：**</font>

```json
db.mycollection.find(
    {
        $and: [
            {"likes": {$exists: true}},
            {"likes": {$type: "double"}}
        ]
    }    
)
```

#### 4）评估操作符

| 操作符 | 说明                         |
| ------ | ---------------------------- |
| $expr  | 两个字段进行比较符合条件的。 |
| $mod   | 符合条件目标列除x等于y       |

<font size=2 color=red>**举例1：查询出列num1大于列num2的数据** </font>

```json
db.mycollection.find(
    {
        $expr : {
            $gt : ["$num1","$num2"]
        }
    }    
);
```

<font size=2 color=red>**举例2：查询出列likes除5余0的数据** </font>

```json
db.mycollection.find(
	{
        "$likes":{
            $mod : [5,0]
        }
    }
);
```

