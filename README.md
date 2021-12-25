## :zap:​ 运行环境
JDK 1.8 </br>
MySQL 8.0

## :zap:​ 前言
本次课程设计的选题为飞机大战游戏设计，主要意在实现用我方飞机来消灭敌机的功能，检验了作者基本的代码逻辑能力和对面向对象程序设计的理解。</br>
在课程设计中，采用了**IO流**来读取游戏规则；使用**MySQL**数据库来存储每一局的得分，并使用**Java Swing**中的JTable类来展示得分排行榜；我方飞机与敌机等游戏内物体的设计，采用了面向对象的程序设计思想，将飞机、子弹等封装成类，继承自一个飞行道具的父类；使用了**List**泛型数据结构，来对同一类的物体进行了统一的管理；所有的场景提供图形化界面设计；在每次场景的绘制当中，使用了多线程技术，来自动绘制页面。</br>
通过设计游戏通关，游戏失败，游戏重新启动等多种不同的情况，对游戏进行充分的测试，结果基本符合了飞机大战游戏的功能，并取得了良好的游戏体验。

## :zap:​ 功能结构图

此次飞机大战的设计与实现包含了游戏胜利分数记录功能，我方飞机与敌机交互功能，我方飞机与敌方Boss交互功能，我方子弹与敌方飞机的交互功能，键盘操控我方飞机功能，敌方子弹与我方飞机交互功能，排行榜查询功能，游戏难度切换功能，游戏规则查询功能等。由此，我将软件分成游戏子系统和主页面模块。</br>
游戏子系统包含了游戏对象（各个游戏对象的抽象父类），我方飞机对象，敌机对象，我方子弹对象，敌方子弹对象，爆炸对象，Boss对象，场景对象。通过获取玩家键盘事件，完成对飞机的移动。
主页面模块我采用了三张logo的图片，设置变量及线程来使三张图片进行自动切换，达到了文字的动态效果。在主页面中，可以通过菜单栏的排行榜来查询得分排名，可以通过模式中的简单模式和困难模式，来改变游戏难度。同时提供了一个退出游戏结束进程的按钮。菜单栏具备了游戏规则查询功能，以及作者信息查询功能。</br>
根据以上描述，我们可以得到如下系统模块图：</br>
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane1.png)

## :zap:​ 游戏界面展示 

### 主页面
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane2.png)

### 游戏中
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane3.png)

### 排行榜查看
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane4.png)

### 规则说明
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane5.png)

### 游戏成功
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane8.png)

### 游戏失败
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane7.png)

### 模式调整
![function](https://github.com/CodeSlogan/planewar/blob/main/screenshots/plane9.png)
