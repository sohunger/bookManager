# bookManager
一个简单的个人博客项目

本项目主要技术栈：SpringBoot+MyBatis+MySQL+Shiro+Redis

1.使用Redis来缓存文章的信息，提高了请求的速度，同时完成了本周热文功能。
2.基于maven进行开发，并利用其继承特性管理共有jar包的依赖。
3.使用RabbitMq来解决系统之间的通信问题。
4.使用七牛云存储用户图像，减少对服务器内存的占用。
