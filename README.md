# commit_modelizer

## How to run
### Dependencies
- Maven

```
sh init.sh
mvn clean compile
mvn exec:java -Dexec.mainClass=jp.nislab.App -Dexec.args="fetch"  # fetch buggy project repositories.
mvn exec:java -Dexec.mainClass=jp.nislab.App -Dexec.args="learn <repo-id>"  # make model of commit messages.
```

|repo-id|project name|
|-|-|
|math|[commons-math](https://github.com/apache/commons-math)|
|lang|[commons-lang](https://github.com/apache/commons-lang)|
|time|[joda-time](https://github.com/JodaOrg/joda-time)|
|closure|[closure-compiler](https://github.com/google/closure-compiler)|
|chart|[jfreechart](https://github.com/jfree/jfreechart)|
|mockito|[mockito](https://github.com/mockito/mockito)|