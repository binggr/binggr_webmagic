一个简单的webmagic小项目，主要通过整合webmagic、springboot对一些网页进行数据采集，然后将采集后的数据通过一定的过滤保存到redis、mysql数据、本地磁盘，以便为一些应用提供数据支持。



主要技术

sprinbgboot+webmagic+redis+mysql+jpa

springboot：整个项目的搭建

webmagic：数据的采集、解析、保存

redis、mysql：数据的存储

jpa: 用于数据库与应用的交互



框架组件图解

![image](http://code4craft.github.io/images/posts/webmagic.png)

WebMagic的四个组件（底层实现HttpClient+Jsoup）

#### Downloader

Downloader负责从互联网上下载页面，以便后续处理。WebMagic默认使用了[Apache HttpClient](http://hc.apache.org/index.html)作为下载工具。



#### PageProcessor

PageProcessor负责解析页面，抽取有用信息，以及发现新的链接。WebMagic使用[Jsoup](http://jsoup.org/)作为HTML解析工具，并基于其开发了解析XPath的工具[Xsoup](https://github.com/code4craft/xsoup)。

在这四个组件中，`PageProcessor`对于每个站点每个页面都不一样，是需要使用者定制的部分



#### Scheduler

Scheduler负责管理待抓取的URL，以及一些去重的工作。WebMagic默认提供了JDK的内存队列来管理URL，并用集合来进行去重。也支持使用Redis进行分布式管理。

除非项目有一些特殊的分布式需求，否则无需自己定制Scheduler。



#### Pipeline

Pipeline负责抽取结果的处理，包括计算、持久化到文件、数据库等。WebMagic默认提供了“输出到控制台”和“保存到文件”两种结果处理方案。

`Pipeline`定义了结果保存的方式，如果你要保存到指定数据库，则需要编写对应的Pipeline。对于一类需求一般只需编写一个`Pipeline`。



详细开发官方开发文档

http://webmagic.io/docs/zh/posts/ch1-overview/architecture.html




