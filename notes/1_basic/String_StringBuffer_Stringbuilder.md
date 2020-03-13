## String, StringBuffer和StringBuilder的区别

String是Java中基础且重要的类，并且String也是Immutable类的典型实现，被声明为final class，除了hash这个属性其它属性都声明为final,因为它的不可变性，所以例如拼接字符串时候会产生很多无用的中间对象，如果频繁的进行这样的操作对性能有所影响。

StringBuffer就是为了解决大量拼接字符串时产生很多中间对象问题而提供的一个类，提供append和add方法，可以将字符串添加到已有序列的末尾或指定位置，它的本质是一个线程安全的可修改的字符序列，把所有修改数据的方法都加上了synchronized。但是保证了线程安全是需要性能的代价的。

在很多情况下我们的字符串拼接操作不需要线程安全，这时候StringBuilder登场了，StringBuilder是JDK1.5发布的，它和StringBuffer本质上没什么区别，就是去掉了保证线程安全的那部分，减少了开销。

StringBuffer 和 StringBuilder 二者都继承了 AbstractStringBuilder ，底层都是利用可修改的char数组(JDK 9 以后是 byte数组)。

所以如果我们有大量的字符串拼接，如果能预知大小的话最好在new StringBuffer 或者StringBuilder 的时候设置好capacity，避免多次扩容的开销。扩容要抛弃原有数组，还要进行数组拷贝创建新的数组。

我们平日开发通常情况下少量的字符串拼接其实没太必要担心，例如

String str = "aa"+"bb"+"cc";

像这种没有变量的字符串，编译阶段就直接合成"aabbcc"了，然后看字符串常量池（下面会说到常量池）里有没有，有也直接引用，没有就在常量池中生成，返回引用。

如果是带变量的，其实影响也不大，JVM会帮我们优化了。

> 1、在字符串不经常发生变化的业务场景优先使用String(代码更清晰简洁)。如常量的声明，少量的字符串操作(拼接，删除等)。
2、在单线程情况下，如有大量的字符串操作情况，应该使用StringBuilder来操作字符串。不能使用String"+"来拼接而是使用，避免产生大量无用的中间对象，耗费空间且执行效率低下（新建对象、回收对象花费大量时间）。如JSON的封装等。
3、在多线程情况下，如有大量的字符串操作情况，应该使用StringBuffer。如HTTP参数解析和封装等。

参考：
https://github.com/h2pl/Java-Tutorial/blob/master/docs/java/basic/3%E3%80%81string%E5%92%8C%E5%8C%85%E8%A3%85%E7%B1%BB.md