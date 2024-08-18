java基础
== 和equals比较
栈： 栈中可以存储基本类型的数据和引用类型的地址。特点: 先进后出，一般空间比较小，存取速度较快。

堆： 堆中可以存储引用类型的数据（对象和数组）。特点: 空间比较大，存储速度相对较慢。

方法区： 方法区中可以 存储字符串常量池、静态数据、代码和类的元数据。
● == 对比的是栈中的值，基本数据类型是变量值，引用类型是堆中内存对象的地址
抽象类和接口的区别
● 定义的关键字不同。
● 子类继承或实现关键字不同。
● 类型扩展不同：抽象类是单继承，而接口是多继承。
● 方法访问控制符：抽象类无限制，只是抽象类中的抽象方法不能被 private 修饰;而接口有限制，接口默认的是 public 控制符。
● 属性方法控制符：抽象类无限制，而接口有限制，接口默认的是 public 控制符。
● 方法实现不同：抽象类中的普通方法必须有实现，抽象方法必须没有实现;而接口中普通方法不能有实现，但在 JDK 8 中的 static 和 defualt 方法必须有实现。
● 静态代码块的使用不同：抽象类可以有静态代码块，而接口不能有。
集合
ArrayList和LinkedList区别
ArrayList：基于动态数组，是一块连续的内存空间，适合下标访问（随机访问）
（一个元素所占据的内存长度乘以下标位置）
● 扩容机制：因为数组长度固定，超出长度存数据需要新建数组，然后将老数组的数据拷贝到新数组，如果不是尾部插入数据还会涉及到元素的移动（往后复制一份，插入新元素），使用尾插法并指定初始容量可以极大提升性能，甚至超过LinkedList（需要创建大量的node对象）
● ArrayList初始化大小为10,扩容规则:扩容后的大小= 原始大小*1.5
LinkedList：基于链表，可以存储在分散的内存中，适合做数据插入及删除操作，不适合查询，需要逐一遍历
遍历LinkedList 必须使用iterator 不能使用for循环，因为每次for循环体内通过get（i）取得某一元素时都需要对list重新遍历，性能消耗极大。
另外不要试图使用indexOf等返回元素索引，并利用其进行遍历，使用indexOf对list进行了遍历，当结果为空时会遍历整个列表

Hashmap
存放非键值对，非线程安全，key和value都允许存储空值，只能存在一个空key和多个空value，
初始容量16，加入到12的时候会进行扩容，每次扩容2倍
阈值=默认为16*负载因子0.75

Hashmap的实现原理


泛型

反射
JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制

● 反射就是Spring 实现IOC的基础
IOC：依赖注入，依赖注入的好处就是：如果你的程序中new一个类是需要动态new的，那么得到这个需要new的类的名称之后，就可以动态new出来。IOC就像工厂方法+反射动态生成对象。
枚举

I/O

注解

多线程
进程
线程
创建线程
继承thread类
实现Runnable接口
实现Callable接口
创建线程池
线程的生命周期
● 新建
● 就绪（可运行）
● 阻塞
● 等待
● 计时等待
● 死亡
sleep 、wait
wait释放cpu 同时释放锁
sleep释放cpu 不释放锁
线程安全
synchronize
锁升级
偏向锁、轻量级锁、重量级锁
一旦出现其他线程竞争资源，偏向锁就会被撤销

synchronized锁升级实际上是把本来的悲观锁变成了 在一定条件下 使用无锁(同样线程获取相同资源的偏向锁)，以及使用乐观(自旋锁 cas)和一定条件下悲观(重量级锁)的形式。
偏向锁:适用于单线程适用锁的情况
轻量级锁: 适用于竞争较不激烈的情况(这和乐观锁的使用范围类似)
重量级锁: 适用于竞争激烈的情况
ConcurrentHashMap
Java8中的ConcurrentHashmap是由Node数组+链表/红黑树组成
concurrentHashMap的初始化通过自旋和CAS操作完成。
ReentrantLock
可重入锁
读写锁
volatile
保证可见性
禁止指令重排序
线程池
参数：
1.corePoolsize 指定了线程池里的线程数量，核心线程池大小
2.maximumPoolsize 指定了线程池里的最大线程数量
3.keepAliveTime 当线程池线程数量大于corePoolSize时候，多出来的空闲线程，多长时间会被销毁。
4.unit 时间单位。TimeUnit
5.workQueue 任务队列，用于存放提交但是尚未被执行的任务
我们可以选择如下几种:
ArrayBlockingQueue: 基于数组结构的有界阻塞队列，FIFO。
。 LinkedBlockingQueue: 基于链表结构的有界阻塞队列，FIFO.
。SynchronousQueue: 不存储元素的阻塞队列，每个插入操作都必须等待一个移出操作，反之亦然。
o PriorityBlockingQueue: 具有优先级别的阻塞队列。
6.threadFactory 线程工厂，用于创建线程，一般可以用默认的
7.handler 拒绝策略，所谓拒绝策略，是指将任务添加到线程池中时，线程池拒绝该任务所采取的相应策略。
什么时候拒绝?当向线程池中提交任务时，如果此时线程池中的线程已经饱和了，而且阻塞队列也已经满了，则线程池会选择一种拒绝策略来处理该任务，该任务会交给RejectedExecutionHandler 处理
应用场景
JUC并发工具包用过哪些
Countdownlatch
JVM
类的加载过程


框架
spring核心注解
● @Required.
● @Autowired
● @configuration
SpringMvc 核心注解
● @RequestMapping  
启动类3个注解
SpringBootApplication
● SpringBootConfiguration
● EnableAutoConfiguration
● ComponentScan
SpringBoot 和Spring的区别

● 创建了独立的spring应用
● 嵌入了tomcat
● 尽可能的自动配置spring应用
● springboot 提交了spring运行的默认配置
SpringBoot 自动配置的原理

● EnableAutoConfiguration
● ComponentScan
● Import


Spring 的生命周期
实例化：在Java应用程序中，Bean对象是通过new关键字或者反射机制来实例化的。在这个阶段，Bean对象被创建，并分配了内存空间。
设置属性(Bean注入和装配)
初始化：当Bean对象被创建后，需要进行初始化，包括设置属性值、执行一些初始化操作等。在Spring框架中，Bean的初始化可以通过配置文件中的init-method属性进行指定。
使用：在Bean初始化之后，它就可以被应用程序使用了。在使用过程中，Bean可能会调用其他对象的方法，从而导致其他Bean对象被实例化和初始化。
销毁：当Be
yml 命令怎么映射的

先去加载spring.factories这个文件里有 mybatis的自动配置类的权限定类名，然后通过反射实例化这个自动配置类。在自动配置类中加载数据库的配置信息，以及包扫码路径等，
mybatis 执行流程
MyBatis的执行流程可以分为以下几个主要步骤：
1.  加载配置文件：MyBatis首先会从配置文件（mybatis-config.xml ）和Java代码的注解中加载SQL的配置信息。这些信息包括传入参数映射配置、执行的SQL语句、结果映射配置等，形成一个或多个MappedStatement对象，并存储在内存中。
2.  创建SqlSessionFactory：通过解析配置文件，构造会话工厂获取SqlSessionFactory。这个过程涉及到配置文件的解析和映射文件的加载与解析。
3.  创建SqlSession：SqlSessionFactory用于创建SqlSession实例。每次调用openSession方法都会返回一个新的SqlSession实例。
4.  执行SQL：通过SqlSession对象，可以执行各种数据库操作，如查询、插入、更新和删除等。这些操作通常是通过Mapper接口来实现的。
5.  解析SQL：在执行SQL之前，MyBatis会对SQL语句进行解析，将其转换为内部表示形式。这一步骤确保了SQL语句的正确性和效率。
6.  执行SQL：解析后的SQL语句会被执行。这一步骤可能涉及到不同类型的Executor，例如SIMPLE、REUSE和BATCH等。
7.  结果映射：执行SQL后，MyBatis会将查询结果映射到Java对象中。这一过程涉及到结果集的处理和参数的绑定。
8.  返回结果：最后，MyBatis会将处理后的结果返回给调用者，完成整个执行流程。
    总结来说，MyBatis的执行流程包括加载配置文件、创建SqlSessionFactory、创建SqlSession、解析SQL、执行SQL、结果映射和返回结果等步骤。每一步都有其特定的功能和作用，确保了MyBatis能够高效且安全地执行数据库操作。
    数据库
    乐观锁和悲观锁的区别
    乐观锁和悲观锁是两种解决并发场景下数据竞争的思想。乐观锁假设数据不会同时被修改，不上锁，而是在更新时检查是否被修改；悲观锁则认为数据可能同时被修改，直接上锁，其他操作需要等待解锁。
    索引设计原则
    ● 索引最左匹配原则
    ● 为经常需要排序、分组操作的字段建立索引
    ● 为常作为查询条件的字段建立索引
    ● 限制索引的数目
    事务失效
    ● 方法不是public的
    ● 事务方法所在的类没有被加载到Spring IOC容器中
    ● 异常没有被抛出
    ● 熟悉rollbackFor设置错误
    sex 字段能不能做索引
    不适合加索引，区分度不高
    MySQL
    innodb 支持事务和外键
    表设计
    命名规范
    ● 驼峰命名或下划线命名
    ● 主键索引名为pk_字段名;唯一索引名为uk_字段名;普通索引名为idx_字段名
    字段类型选择
1. 尽可能选择占用存储空间小的字段类型，在满足正常业务需求的情况下，从小到大，往上选。
2. 如果字符串长度固定，或者差别不大，可以选择char类型。如果字符串长度差别较大，可以选择varchar类型。
3. 是否字段，可以选择bit类型。
4. 枚举字段，可以选择tinyint类型。
5. 主键字段，可以选择bigint类型。
6. 金额字段，可以选择decimal类型。
7. 时间字段，可以选择timestamp或datetime类型。
   字段默认值not null
   索引设计原则
   ● 根据查询条件进行选择（高频使用）
   ● 区分度高的字段优先
   ● 不要建立过多的索引（索引底层实现与二叉树相关，过多的索引会导致写入速度变慢）
   ● 联合索引优化（满足最左匹配原则）
   SQL优化
   ● 用EXPLAIN 分析SQL运行情况
   ● 避免使用select*from语句，避免全表扫描
   ● 使用索引来提高查询速度，避免造成索引失效的写法
   ● 使用limit 1来限制查询结果只有一条记录
   ● 用union all 代替union，union会多一次过滤
   ● 插入数据过多，考虑批量插入
   ● 使用联合索引，要满足最左匹配原则
   ● 对查询进行优化，考虑在where及order by涉及的列上建立索引
   ● 避免在where子句中进行表达式操作，会导致索引失效

事务
索引
● B+树（多路搜索树）
ACID
原子性
一致性
隔离性
持久性
事务的隔离级别
读未提交、读已提交、可重复读（默认）、串行化

事务的传播机制
REQUIRED
● REQUIRED是默认的事务传播行为。如果当前存在事务，那么该方法将会在该事务中运行；如果当前没有事务，那么它会启动一个新的事务。
● 事务传播机制是通过 TransactionInterceptor 拦截器来实现的。TransactionInterceptor 是一个 AOP 拦截器，它拦截方法调用，并在方法调用之前和之后启动和提交事务。
接口幂等性
● 同一个接口，多次发出同一个请求，必须保证操作只执行一次
处理：
前端按钮置灰
使用Post/Redirect/Get模式 提交重定向页面
token机制
token+redis实现接口幂等性
● 前端访问订单页，后端生成唯一token 存到redis，前端提交信息之后，后端拿到token ，再去redis里面删除key为token的数据，删除成功 生成订单，删除失败，提示重复提交订单
IdempotentUtil
String token = UUID.randomUUID().toString();
jedis.setex(token, 60*5, "processed");
// 设置Redis的key，过期时间为5分钟

IdempotentHandler
if (jedis.exists(token)) {
System.out.println("Duplicate request. Token: " + token);
return false; // 请求已处理过
}
// 处理请求逻辑，例如保存数据等
jedis.setex(token, 60*5, "processed"); // 将 Token 存入 Redis，并设置过期时间
System.out.println("Request processed successfully. Token: " + token);
在上面的代码中，IdempotentUtil 类负责生成 Token 并将 Token 存入 Redis，并设置过期时间；IdempotentHandler 类负责处理请求时检查 Token 是否已存在于 Redis 中，如果存在则表示重复请求，否则处理请求并将 Token 存入 Redis。

通过这种方式，可以确保每个 Token 只能被处理一次，从而实现接口的幂等性。
SSO

用户登录状态的存储与校验
用户登录成功后，生成AuthToken交给客户端保存，若是浏览器，就保存在Cookie中，若是手机App就保存在App本地缓存中。当用户浏览需要登录页面时，客户端将AuthToken提交给SSO服务校验登录状态/获取用户登录信息
对于登录信息存储，建议采用Redis，使用Redis集群存储登录信息，既可保证高可用，又可线性扩产，同时也可让SSO服务满足负载均衡/可伸缩的需求
对象	说明
AuthToken	直接使用UUID/GUID即可，若有验证AuthToken合法性需求，可将UserName + 时间戳加密生成，服务端解密之后验证合法性
登陆信息	通常将UserId、UserName缓存起来
登出
清除 AuthToken

消息队列
消息堆积怎么处理
1. 如果仅仅是 Consumer消费速度落后于消息生产的速度的话，可以考虑采用扩容消费者群组的方式。
2. 如果积压比较严重，积压了上百万、上千万的消息。
   ● a. 修复现有 Consumer问题，并将其停掉。
   ● b. 重新创建一个容量更太的 topic，例如 patition是原来的 10倍，临时建立好原来10倍的 queue数量。
   ● c. 重新写一个临时 Consumer 程序，消费原来积压的队列。该 Consumer不做任何耗时的操作，将消息均匀写入新创建的队列里。
   ● d. 征用 10 倍的机器来部署 已经修复好的 consumer 程序，每一批 consumer 消费一个临时 queue的数据。这种做法相当于临时将 queue资源和 consumer资源扩大 10 倍，以正常的 10 倍速度来消费数据。
   ● e. 消息积压解决后，恢复原有架构。
3. 如果消息已经丢失
   由于有的消息队列有过期失效机制，造成了大量的消息丢失。这种情况下只能将丢失的那批数据，写个临时程序，一点一点的查出来，然后重新灌入 MQ 里面去。
4. 消息队列快写满了
   如果消息积压在 MQ 里，很长时间都没有处理掉，此时导致 MQ快些满了，这个是时候还有别的办法吗？没有，谁让第二个方案执行的太慢了，临时写程序，接入数据来消费，消费一个丢弃一个，都不要了。快速消费掉所有的消息，然后走第三个方案，补数据。
   MQ怎么保证消息不丢失
   ● 持久化机制：消息队列会将消息持久化存储，即使在消息被消费之前，也能确保消息的可靠性和持久性。通常，MQ的消息持久化机制会将消息存储在硬盘或数据库中，而不是只存储在内存中。这样，即使在应用程序或MQ服务器重启之后，消息仍然可以被恢复。
   ● 刷盘机制：RocketMQ等消息队列支持同步刷盘和异步刷盘两种方式。同步刷盘模式下，消息写入磁盘时会等待磁盘的写入完成才返回写入成功的响应，确保消息在Broker宕机时不会丢失。异步刷盘模式下，消息写入磁盘后立即返回写入成功的响应，但不等待磁盘写入完成，这种方式可能会增加消息丢失的风险，但在性能上有所优势。
   ● ACK机制：在消息队列中，生产者发送消息后，Broker会返回ACK确认信号，表示消息已成功发送。如果Broker未收到ACK确认信号，则会尝试重新发送消息，直到收到确认。这种机制有助于确保消息在传输过程中不会丢失。
   ● 重试和死信队列：当MQ消费异常时，可以采取重试机制，尝试重新消费消息。可以设置最大重试次数，如果超过最大重试次数仍然失败，可以将消息放入死信队列等待后续处理。
   ● 分布式事务：对于需要确保消息和数据库事务一致性的场景，可以使用分布式事务来确保消息不丢失。例如，在RocketMQ中，可以通过事务消息的方式，确保消息发送和数据库操作在同一个本地事务中完成，从而确保两者的一致性。
   ● 监控和告警：对MQ的运行状态进行监控，当发现异常或潜在的问题时，及时触发告警通知相关人员进行处理。这有助于及时发现并解决可能导致消息丢失的问题。
   MQ怎么保证消息不重复消费
   ● 幂等性（生成一个全局唯一的ID）
   ● 分布式锁
   ● 消息消费确认机制
   ● 消息过期设置

Redis
Redis的基本类型和应用场景
● string
● list
● set
● zset
● hash
Redis的寻址方式
微服务
微服务有什么优势
分布式
分布式锁
zk和Redis锁
基于Redis的分布式锁
● 主从切换的情况下可能出现多客户端获取锁的情况
● Lua脚本在单机上具有原子性，主从同步不具有原子性
基于Zookeeper的分布式锁
● 需要引入Zookeeper集群，比较重量级
● 分布式锁的可重入力度只能是节点级别
Redis的锁 Redisson
超时重试、看门狗
分布式事务
2PC
3PC
TCC
Saga事务
本地消息表
MQ事务消息
Linux
服务器很慢怎么排查原因
ping、telnet、ssh、 tcpdump、netstack、pstack
Nginx
nginx怎么配置反向代理
nginx的配置文件在 /usr/local/nginx/conf 下的  nginx.conf（修改过配置文件，记得重启nginx服务）

面试点
网页一次访问的全流程
1. 将域名解析为IP地址
2. 与目标主机建立TCP连接 (三次握手)
3. 发送与接受数据
4. 与目标主机断开TCP连接 (四次挥手)
   文件夹很大怎么遍历