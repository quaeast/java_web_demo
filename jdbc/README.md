# JDBC demo

这个 demo 最开始是想看看 Java JDBC 接口和驱动这种解耦合的设计方式以及 Maven 的 `<scope>runtime</scope>`

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

查看自动装载驱动的设置

```
META-INF
├── INDEX.LIST
├── INFO_BIN
├── INFO_SRC
├── LICENSE
├── MANIFEST.MF
├── README
├── gradle
│   └── incremental.annotation.processors
├── maven
│   └── com.google.protobuf
│       └── protobuf-java
│           ├── pom.properties
│           └── pom.xml
└── services
    ├── java.sql.Driver
    ├── javax.annotation.processing.Processor
    ├── lombok.core.LombokApp
    ├── lombok.core.PostCompilerTransformation
    ├── lombok.core.runtimeDependencies.RuntimeDependencyInfo
    ├── lombok.eclipse.EclipseASTVisitor
    ├── lombok.eclipse.EclipseAnnotationHandler
    ├── lombok.eclipse.handlers.EclipseSingularsRecipes$EclipseSingularizer
    ├── lombok.installer.IdeLocationProvider
    ├── lombok.javac.JavacASTVisitor
    ├── lombok.javac.JavacAnnotationHandler
    ├── lombok.javac.handlers.JavacSingularsRecipes$JavacSingularizer
    └── org.mapstruct.ap.spi.AstModifyingAnnotationProcessor

5 directories, 22 files

```

查看`META-INF/services/java.sql.Driver`，和[上文](https://www.baeldung.com/java-jdbc-loading-drivers)讲述相符合

```shell script
cat services/java.sql.Driver 
com.mysql.cj.jdbc.Driver%
``` 

### Spring boot plugin

本项目采用 spring boot 的构建工具。

值得注意的是，他的打包结果和传统的并不一样，应该是 Spring 对加载做了自己的处理，和 Java 的原生机制不同。


```
.
├── BOOT-INF
│   ├── classes
│   │   └── cn
│   │       └── quaeast
│   │           ├── Main.class
│   │           └── Person.class
│   ├── classpath.idx
│   ├── layers.idx
│   └── lib
│       ├── lombok-1.18.12.jar
│       ├── mysql-connector-java-8.0.19.jar
│       ├── protobuf-java-3.6.1.jar
│       └── spring-boot-jarmode-layertools-2.4.0.jar
├── META-INF
│   ├── MANIFEST.MF
│   └── maven
│       └── cn.quaeast
│           └── jdbc_demo
│               ├── pom.properties
│               └── pom.xml
└── org
    └── springframework
        └── boot
            └── loader
                ├── ClassPathIndexFile.class
                ├── ExecutableArchiveLauncher.class
                ├── JarLauncher.class
                ├── LaunchedURLClassLoader$DefinePackageCallType.class
                ├── LaunchedURLClassLoader$UseFastConnectionExceptionsEnumeration.class
                ├── LaunchedURLClassLoader.class
                ├── Launcher.class
                ├── MainMethodRunner.class
                ├── PropertiesLauncher$1.class
                ├── PropertiesLauncher$ArchiveEntryFilter.class
                ├── PropertiesLauncher$ClassPathArchives.class
                ├── PropertiesLauncher$PrefixMatchingArchiveFilter.class
                ├── PropertiesLauncher.class
                ├── WarLauncher.class
                ├── archive
                │   ├── Archive$Entry.class
                │   ├── Archive$EntryFilter.class
                │   ├── Archive.class
                │   ├── ExplodedArchive$AbstractIterator.class
                │   ├── ExplodedArchive$ArchiveIterator.class
                │   ├── ExplodedArchive$EntryIterator.class
                │   ├── ExplodedArchive$FileEntry.class
                │   ├── ExplodedArchive$SimpleJarFileArchive.class
                │   ├── ExplodedArchive.class
                │   ├── JarFileArchive$AbstractIterator.class
                │   ├── JarFileArchive$EntryIterator.class
                │   ├── JarFileArchive$JarFileEntry.class
                │   ├── JarFileArchive$NestedArchiveIterator.class
                │   └── JarFileArchive.class
                ├── data
                │   ├── RandomAccessData.class
                │   ├── RandomAccessDataFile$1.class
                │   ├── RandomAccessDataFile$DataInputStream.class
                │   ├── RandomAccessDataFile$FileAccess.class
                │   └── RandomAccessDataFile.class
                ├── jar
                │   ├── AbstractJarFile$JarFileType.class
                │   ├── AbstractJarFile.class
                │   ├── AsciiBytes.class
                │   ├── Bytes.class
                │   ├── CentralDirectoryEndRecord$1.class
                │   ├── CentralDirectoryEndRecord$Zip64End.class
                │   ├── CentralDirectoryEndRecord$Zip64Locator.class
                │   ├── CentralDirectoryEndRecord.class
                │   ├── CentralDirectoryFileHeader.class
                │   ├── CentralDirectoryParser.class
                │   ├── CentralDirectoryVisitor.class
                │   ├── FileHeader.class
                │   ├── Handler.class
                │   ├── JarEntry.class
                │   ├── JarEntryCertification.class
                │   ├── JarEntryFilter.class
                │   ├── JarFile$1.class
                │   ├── JarFile$JarEntryEnumeration.class
                │   ├── JarFile.class
                │   ├── JarFileEntries$1.class
                │   ├── JarFileEntries$EntryIterator.class
                │   ├── JarFileEntries.class
                │   ├── JarFileWrapper.class
                │   ├── JarURLConnection$1.class
                │   ├── JarURLConnection$JarEntryName.class
                │   ├── JarURLConnection.class
                │   ├── StringSequence.class
                │   └── ZipInflaterInputStream.class
                ├── jarmode
                │   ├── JarMode.class
                │   ├── JarModeLauncher.class
                │   └── TestJarMode.class
                └── util
                    └── SystemPropertyUtils.class

18 directories, 76 files

```
