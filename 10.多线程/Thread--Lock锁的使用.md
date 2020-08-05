# Lock锁的使用

## 1. 介绍

## 2. 使用

声明锁：

```java
private Lock lock = new ReentrantLock();
```

加锁：

```java
lock.lock();
```

解锁：

```java
lock.unlock();
```

