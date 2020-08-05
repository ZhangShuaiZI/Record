**Date**：2019-12-2  **Author**：Zhangshuai

[toc]

# Redis		

## 一、Redis数据类型

### 1. 通用指令

| 指令    | 描述                   | 示例                             |
| ------- | ---------------------- | -------------------------------- |
| Keys    | 获取符合规则的键名类别 | keys *                           |
| Exists  | 查看key是否存在        | exists key 存在返回1 不存在返回0 |
| Del     | 删除一个或多个键       | del name                         |
| Select  | 切换数据库             | select 1                         |
| Expires | 设置过期时间           | expires key 10(秒)               |
| TTL     | 查看过期时间           | ttl key                          |



### 2. String类型

| 指令        | 描述                                                         | 示例                            |
| :---------- | ------------------------------------------------------------ | ------------------------------- |
| SET         | 设置值                                                       | SET KEY str                     |
| GET         | 获取值                                                       | GET kEY                         |
| GETRANGE    | 返回 key 中字符串值的子字符                                  | GETRANGE   KEY 0 3              |
| GETSET      | 设置新值，获取旧值。                                         | GETSET KEY str                  |
| MGET        | 获取多个key对应值                                            | MGET key1 [key2]                |
| MSET        | 设置多个值                                                   | MSET key value1 [key2 value2]   |
| SETEX       | 将值 value 关联到 key ，<br />并将 key 的过期时间设为 seconds (以秒为单位)。 | SETEX name 10 zhangsan          |
| PSETEX      | 同上，以毫秒为单位。                                         | PSETEX name 1000 zhangsan       |
| SETNX       | 只有在key不存在的时候才能设置值。                            | SETNX name zhangsan             |
| SETRANGE    | SETRANGE key offset value<br />用 value 参数覆写给定 key 所储存的字符串值，<br />从偏移量 offset 开始。 |                                 |
| **STRLEN**  | 返回储存字符串长度                                           | STRLEN key                      |
| MSETNX      | 设置多个值，且不存在名不为存在。                             | MSETNX key1 value1[key2 value2] |
| INCR        | INCR key <br />数字增值1                                     | INCR count                      |
| INCRBY      | INCRBY key increment<br />数字增值increment                  | INCRBY count 1                  |
| INCRBYFLOAT | INCRBYFLOAT key increment<br />将 key 所储存的值加上给定的浮点增量值（increment） | INCRBYFLOAT count 1.2           |
| DECR        | DECR KEY<br />数字减一                                       | DECR count                      |
| DECRBY      | DECRBY key increment<br />key 所储存的值减去给定的减量值（decrement） | DECRBY count 1                  |
| APPEND      | APPEND key value <br />如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。 | APPEND name zz                  |

### 3. Hash字典

| 命令         | 描述                                                     | 示例                                  |
| :----------- | -------------------------------------------------------- | ------------------------------------- |
| HSET         | 将哈希表 key 中的字段 field 的值设为 value 。            | HSET key field value                  |
| HMSET        | 设置多个哈希值。                                         | HMSET KEY field1 value1 field2 value2 |
| HSETNX       | 如果为null则赋值，否则不修改。                           | HSETNX KEY field1 value               |
| HGET         | 查看哈希表key中，指定字段是否存在。                      | HGET KEY field1                       |
| HMGET        | 获取存储在哈希表中指定多个字段的值。                     | HMGET key field1 field2               |
| HGETALL      | 获取指定 key 的所有字段和值                              | HGETALL key                           |
| HDEL         | 删除指定field                                            | HDEL key field                        |
| HINCRBY      | 为哈希表 key 中的指定字段的整数值加上增量 increment 。   | HINCRBY Key field 2                   |
| HINCRBYFLOAT | 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。 | HINCRBYFLOAT Key FIeld 2.4            |
| HKEYS        | 获取所有哈希表的字段。                                   | HKEYS key                             |
| HLEN         | 获取哈希表中字段的数量。                                 |                                       |
| HVALS        | 获取所有给定字段的值.                                    |                                       |
| HEXISTS      | 查看是否存在                                             | HEXISTS key field                     |

### 4. List列表



**其他命令：**

| 制令      | 描述                                                         | 示例                                  |
| :-------- | :----------------------------------------------------------- | ------------------------------------- |
| LPUSH     | 左添加                                                       | LPUSH  Key value1 value2 value3       |
| RPUSH     | 右添加                                                       | RPUSH Key value1 value2 value3        |
| LPOP      | 左移除                                                       | LPOP Key                              |
| RPOP      | 右移除                                                       | RPOP key                              |
| BLPOP     | 移出并获取列表的第一个元素， <br />如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |                                       |
| BRPOP     | 移出并获取列表的最后一个元素， <br />如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |                                       |
| BRPOP     | 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 | BRPOPLPUSH source destination timeout |
| LINDEX    | 通过索引获取列表中的元素                                     | LINDEX key index                      |
| LINSERT   | 在列表的元素前或者后插入元素                                 | LINSERT key BEFORE\|AFTER pivot value |
| LLEN      | 获取列表长度                                                 | LLEN key                              |
| LRANGE    | 获取列表指定范围内的元素                                     | LRANGE key 0 -1                       |
| LREM      | 移除列表元素[` >0`从前向后;`<0`从后向前 = 所有]              | LREM key count value                  |
| LSET      | 通过索引设置列表元素的值                                     | LSET key index value                  |
| LTRIM     | 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 | LTRIM key start stop                  |
| RPOPLPUSH | 移除列表的最后一个元素，并将该元素添加到另一个列表并返回     |                                       |
| RPUSHHX   | 为已存在的列表添加值，右添加                                 |                                       |
| LPUSHHX   | 为已存在的列表添加值，左添加                                 |                                       |



### 03.Set（集合）

| 命令         | 描述                                                | 示例                                |
| ------------ | --------------------------------------------------- | ----------------------------------- |
| SADD         | 添加                                                | SADD key member1 [member2]          |
| SREM         | 移除集合中一个或多个成员                            | SREM key member1 [member2]          |
| SCARD        | 返回集合的成员数                                    | SCARD key                           |
| SDIFF        | 返回给定集合的差值                                  | SDIFF key1 key2                     |
| SDIFFSTORE   | 返回给定所有集合的差集并存储在 destination 中       | SDIFFSTORE destination key1 [key2]  |
| INSERT       | 返回给定所有集合的交集                              | SINTER key1 [key2]                  |
| INSERTSTORE  | 返回给定所有集合的交集并存储在 destination 中       | SINTERSTORE destination key1 [key2] |
| UNION        | 返回所有给定集合的并集                              | SUNION key1 key2                    |
| UNIONSTORE   | 所有给定集合的并集存储在 destination 集合中         | SUNIONSTORE destination key1 key2   |
| SISMEMBER    | 判断 member 元素是否是集合 key 的成员               | SISMEMBER key member                |
| SMEMBER      | 返回集合中的所有成员                                | SMEMBERS key                        |
| SMOVE        | 将 member 元素从 source 集合移动到 destination 集合 | SMOVE source destination member     |
| SPOP         | 移除并返回集合中的一个随机元素                      | SPOP key                            |
| SRANGEMEMBER | 返回集合中一个或多个随机数                          | SRANDMEMBER key [count]             |
| SSCAN        | 迭代集合中的元素                                    | SSCAN key match v*                  |

### zset(sorted set：有序集合)

| 命令             | 说明                                                         | 示例                                       |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------ |
| ZADD             | 有序集合添加一个或多个成员，或者更新已存在成员的分数         | ZADD key score1 member1 [score2 member2]   |
| ZCARD            | 获取有序集合的成员数                                         | ZCARD key                                  |
| ZCOUNT           | 计算在有序集合中指定区域分数的成员数                         | ZCOUNT key min max                         |
| ZINCRBY          | 有序集合中对指定成员的分数加上增量 increment                 | ZINCRBY key increment member               |
| ZINTERSTORE      | 计算给定的一个或多个有序集的**交集**并将结果集存储在新的有序集合 key 中 | ZINTERSTORE destination numkeys key1[key2] |
| ZLEXCOUNT        | 在有序集合中计算指定字典区间内成员数量                       | ZLEXCOUNT key [member1 [member2            |
| ZRANGE           | 通过索引区间返回有序集合指定区间内的成员                     | ZRANGE key start stop                      |
| ZRANGEBYLEX      | 通过字典区间返回有序集合的成员                               | ZRANGELEX key min max                      |
| ZRANGEBYSCORE    | 通过分数返回有序集合指定区间内的成员                         | ZRANGEBYSCORE key min max                  |
| ZRANK            | 返回有序集合中指定成员的索引排名                             | ZRANK key member                           |
| ZREM             | 移除一个元素                                                 | ZREM key member1 [member2]                 |
| ZREMRANGEBYLEX   | 移除有序集合中给定的字典区间的所有成员                       |                                            |
| ZREMRANGEBYRANK  | 移除有序集合中给定的排名区间的所有成员                       |                                            |
| ZREMRANGEBYSCORE | 移除有序集合中给定的分数区间的所有成员                       |                                            |
| ZREVRANGE        | 返回有序集中指定区间内的成员，通过索引，分数从高到低         |                                            |
| ZREVRANGEBYSCORE | 返回有序集中指定分数区间内的成员，分数从高到低排序           |                                            |
| ZREVRANK         | 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序 |                                            |
| ZSOCRE           | 返回有序集中，成员的分数值                                   |                                            |
| ZUNIONSTORE      | 计算给定的一个或多个有序集的并集，并存储在新的 key 中        | ZUNIONSTORE destination key                |
| ZSCAN            | 迭代有序集合中的元素（包括元素成员和元素分值）               | ZSCAN key 0 match "R*"                     |

## Redis命令

Redis 命令用于在 redis 服务上执行操作。

要在 redis 服务上执行命令需要一个 redis 客户端。Redis 客户端在我们之前下载的的 redis 的安装包中。

Redis 客户端的基本语法为：

```
$redis-cli
redis 127.0.0.1:6379>
redis 127.0.0.1:6379> PING

PONG
```

### 在远程服务上执行命令

```
$ redis-cli -h host -p port -a password
```

```
$redis-cli -h 127.0.0.1 -p 6379 -a "mypass"
redis 127.0.0.1:6379>
redis 127.0.0.1:6379> PING

PONG
```

### Redis keys 命令

下表给出了与 Redis 键相关的基本命令：

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [DEL key](https://www.runoob.com/redis/keys-del.html) <br />该命令用于在 key 存在时删除 key。 |
| 2    | [DUMP key](https://www.runoob.com/redis/keys-dump.html)  <br />序列化给定 key ，并返回被序列化的值。 |
| 3    | [EXISTS key](https://www.runoob.com/redis/keys-exists.html)  <br />检查给定 key 是否存在。 |
| 4    | [EXPIRE key](https://www.runoob.com/redis/keys-expire.html) <br />seconds 为给定 key 设置过期时间，以秒计。 |
| 5    | [EXPIREAT key timestamp](https://www.runoob.com/redis/keys-expireat.html)  <br />EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。 |
| 6    | [PEXPIRE key milliseconds](https://www.runoob.com/redis/keys-pexpire.html)  <br />设置 key 的过期时间以毫秒计。 |
| 7    | [PEXPIREAT key milliseconds-timestamp](https://www.runoob.com/redis/keys-pexpireat.html)  <br />设置 key 过期时间的时间戳(unix timestamp) 以毫秒计 |
| 8    | [KEYS pattern](https://www.runoob.com/redis/keys-keys.html)  <br />查找所有符合给定模式( pattern)的 key 。 |
| 9    | [MOVE key db](https://www.runoob.com/redis/keys-move.html)  <br />将当前数据库的 key 移动到给定的数据库 db 当中。 |
| 10   | [PERSIST key](https://www.runoob.com/redis/keys-persist.html)  <br />移除 key 的过期时间，key 将持久保持。 |
| 11   | [PTTL key](https://www.runoob.com/redis/keys-pttl.html)  <br />以毫秒为单位返回 key 的剩余的过期时间。 |
| 12   | [TTL key](https://www.runoob.com/redis/keys-ttl.html)  <br />以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。 |
| 13   | [RANDOMKEY](https://www.runoob.com/redis/keys-randomkey.html)  <br />从当前数据库中随机返回一个 key 。 |
| 14   | [RENAME key newkey](https://www.runoob.com/redis/keys-rename.html)  <br />修改 key 的名称 |
| 15   | [RENAMENX key newkey](https://www.runoob.com/redis/keys-renamenx.html)  <br />仅当 newkey 不存在时，将 key 改名为 newkey 。 |
| 16   | [TYPE key](https://www.runoob.com/redis/keys-type.html)  <br />返回 key 所储存的值的类型。 |

