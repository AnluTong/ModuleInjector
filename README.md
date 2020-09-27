# ModuleInjector
解耦模块依赖，简单的模块能力发布，服务自动发现及依赖注入框架

## 理想
假定我们有A，B，C三个模块，三个模块各自输出自己的能力，三个模块又互相有一些依赖关系，如何在模块不互相依赖的情况下进行编码？

## 痛点
多模块化的项目中，常常会遇到模块依赖的问题，对模块边界理解不深，或者处理不当很有可能造成项目循环依赖，公共模块膨胀等棘手问题。当然我们可以通过谨慎的抽象公共接口并划分子模块等方式解决该类问题，但使用起来，可能并不太便利，我们还需要依赖一些依赖注入的机制实现抽象接口的注入。

理想的解决方案是，A模块发布A的接口，比如IAInterface，B模块发布B的接口，比如IBInterface，不管是A模块还是B模块，都能够自由的使用IAInterface和IBInterface，而两者不互相依赖，这里的自由包含我们不需要使用反射的手段，最好在写代码的阶段就能够发现个模块的接口，另外，通过接口能够获取被注入的实现。

## 灵感
gradle提供了一些打包期间自动化的手段，比如sourceSet，比如dependency，是否可以通过一些脚本，自动化的做一些工作？当然可以，ModuleInjector就干了两件事，一个是解耦模块依赖，便于依赖发布，另一个是实现了一个依赖注入。下面我们来看看ModuleInjector如何使用

## 极简配置
根目录下setting.gradle 最后一行增加

```
apply from: 'publish/SettingSetup.gradle'
```
根目录下build.gradle 最后一行增加

```
apply from: 'publish/ProjetSetup.gradle'
```

想要接入ModuleInjector的模块，只需要在自己的模块目录下创建/src/public文件夹。

以上就是配置需要的所有工作。

## 发布能力

子模块（甚至是主模块）在/src/public/java下发布自身接口类即可。其他模块想要使用别的模块公共能力也很简单，接入ModuleInjector即可。接入ModuleInjector之后，你会惊奇的发现，自己模块能够使用其他模块发布的接口，IDE有补齐提示，就像其他模块接口在自己模块一样，enjoy it！

## 依赖注入
上面我们实现了各模块间服务能力的自主发现及更加解耦的能力发布，接口能力的使用还依赖于依赖注入。我们实现了一个简单的依赖注入框架，需要你在使用前进行一个注入。我们将注入与模块能力发布进行了耦合（框架常干的事），只要接入ModuleInjector（创建/src/public），你就能使用依赖注入。

建议在架构中层级最低的位置(lowest level)，比如你可以在Application onCreate注入依赖

```
ModuleInjector.install(IAInterface.class, new LibAImpl())
```

之后，你就能通过接口，获取实现了

```
ModuleInjector.get(IAInterface.class).testA()
```

## 解构
有兴趣了解实现的同学可以看publish文件夹下脚本，服务发现主要是使用了sourceSets，另外，我们使用脚本创建了一个辅助工程public，将两个文件放到文件服务器中，就可以公共依赖了


