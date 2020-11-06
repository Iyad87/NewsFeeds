
## News feeds web application  
### Technologies
 - Java 1.8
 - Spring-Boot framework
 - Maven 
 - SQL

### How to set up 
 * Clone the repository
 ```
https://github.com/Iyad87/assignmentNewsFeeds.git
```

* Create sql database called feeds

```
     CREATE DATABASE feeds;
```



### How  run the application

```
You can Run from your IDE 

```

### Or type this from the root of the project

```
mvn spring-boot:run

```




### Get Data from Database using GraphQL

```
http://localhost:8080/graphiql

```


### Then write these query to retrieve the data:

```
{
  feed {
    title
    description
    pubdate
    image
  }
}
```

## Get Data from Database using @REST Controller

```
http://localhost:8080/feeds

``` 

### Or by Feed ID 


```
http://localhost:8080/feeds/1

``` 

  





