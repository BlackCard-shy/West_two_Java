# West_Two_Java

## The third round(Covid-19)

1. 代码在Java_3.5\src\main\java中
2. 建表语句见下

```sql
 CREATE TABLE `库名`.`City`
 ( 
     `city_id` 			INT NOT NULL AUTO_INCREMENT,
     `Country_Id` 		INT,
     `city_name` 		VARCHAR(50),
     `city_lat` 		VARCHAR(20),
     `city_long` 		VARCHAR(30),
     `city_confirmed` 	INT,
     `city_recovered` 	INT,
     `city_deaths` 		INT,
     `city_updated` 	VARCHAR(60),
     PRIMARY KEY (`city_id`)
 )
 ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci; 
 
 CREATE TABLE `库名`.`countryy`
 ( 
     `Country_Id`			INT NOT NULL AUTO_INCREMENT COMMENT '主键',
     `Country` 				VARCHAR(50) COMMENT '国家名称',
     `Confirmed` 			INT, 
     `Recovered` 			INT,
     `Deaths` 				INT,
     `Population` 			INT,
     `Sq_km_area` 			INT,
     `Life_expectancy` 		VARCHAR(20), 
     `Elevation_in_meters` 	INT,
     `Continent` 			VARCHAR(20),
     `Abbreviation`	 		VARCHAR(20), 
     `Location` 			VARCHAR(20),
     `Iso` 					INT,
     `Capital_city` 		VARCHAR(30), 
     `Country_Lat` 			VARCHAR(30),
     `Country_Long` 		VARCHAR(30),
     `Country_Updated` 		VARCHAR(30), 
     PRIMARY KEY (`Country_Id`)
 )
 ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci; 
 
```

