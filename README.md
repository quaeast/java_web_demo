# JDBC demo

这个 demo 最开始是想看看 Java JDBC 接口和驱动这种解耦合的设计方式以及 Maven 的 <scope>runtime</scope>

后来发现我还不会用 Maven package 可执行 jar 包，就顺便写上了

## JDBC 连接 [ref](https://www.baeldung.com/java-jdbc-loading-drivers)

JDBC driver 现在可以隐式加载，不需要显式声明。

## maven 可执行 jar 包 [ref](https://www.baeldung.com/executable-jar-with-maven)

有三种方法可供选择 

### 手动

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <executions>
        <execution>
            <id>copy-dependencies</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <outputDirectory>
                    ${project.build.directory}/libs
                </outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
```

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <addClasspath>true</addClasspath>
                <classpathPrefix>libs/</classpathPrefix>
                <mainClass>
                    cn.quaeast.Main
                </mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

这样做并没有实际把依赖 jar 包导入，只是给 target.jar 设置了路径，你如果移动了位置，他还是不好使的

### Apache Maven Assembly Plugin

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
            <configuration>
                <archive>
                <manifest>
                    <mainClass>
                        cn.quaeast.Main
                    </mainClass>
                </manifest>
                </archive>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Spring boot plugin

本项目采用，推荐

