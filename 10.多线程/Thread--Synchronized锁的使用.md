# Synchronized锁的使用

## 1. 介绍

## 2. 使用

### 1. 作用于示例方法（普通方法）

```java
public synchronized void run() {
    ...
}
```

### 2.作用于静态方法

锁静态方法，即为锁类。

```java
private synchronized static void fun(){

}
```

### 3. 作用于代码块

锁当前对象。

```java
synchronized(this){
    
}
```



锁xxx这个类。

```java
synchronized(xxx.class){
    
} 
```

synchronized后面的括号中是可以指定任意对象充当锁的，而零长度的byte数组对象创建起来将比任何对象都经济。

如：

```java
 private byte[] mBytes = new byte[0];
synchronized(mBytes){
    
}
```

