#Introduction 
Coap-master is backend for the master-ui project  ,  this is the  project for administrating coap system .  aim to show the metadatas , such as basic info of datasource and alert info .  also can provide the CURD operations for the configurable data , such as the alert source , alert group .
this project is depenpent on the coap-meta . which is data model for describing  data relationship of  coap system.

#Project  Structure
from the point of technical view , coap-master has following frameworks ,  the the spring boot  ,  spring mvc  , spring data jpa   ,  spring security and swagger .
spring boot  encapsulate  web container and other spring configs , let the project running just like executable java project .  spring mvc   provide restful api to coap-master-ui to complete the http request . spring security will check every request 's authentication  according to  the user 's role  , spring data jpa  help us to build and maintain  the relationship of the domain class  ,  and develop more efficiently  . and using swagger to provide the RESTful api   document for coap-master-ui developers ,  in order to coordinate the front-end and back-end .

----------
 - **Entry main files**
	 

 - **Java files**
	 the whole project is developed following the typical three level model . there are                controller  level . 
**com.accenture.coap.master.controller** contains all controllers.
	 serveice level
	  **com.accenture.coap.master.metadata.service** contains all services 
	  **com.accenture.coap.master.service** only contains the service for authentication&authorization 
	 repository level
	 **com.accenture.coap.master.metadata.repository** contains the repositories 
	 **com.accenture.coap.master.repository** contains the repositories only for 
	 authentication&authorization 
	  domain class 
	 **com.accenture.coap.master.metadata.domain.confdata** contains coap system configurable data domain class  
	 **com.accenture.coap.master.metadata.domain.metadata** contains coap system metadata domain class
	 other packages
	**com.accenture.coap.master.component** contains the java config file for spring boot.
	**com.accenture.coap.master.dto** contains the dto class  
	Test packages
	the test classes contains level test case of this project. some test class use the DBUnit  framework to  test . which are under  dbunit sub directory.
	 

 - **Config files**
the config file of spring boot is **application.properites** in class path.  all config items can be found  in spring boot reference  guard. 
the **import.sql** file  in class path contains the initial data  of  database . It will be inserted into database when coap-master startup. need note that the import.sql can't be renamed. otherwise the data can't be initialized .


 - **Profile files**
	 Now there are four environment exist , these are  
		 local  
		 dev              
		 stg
		 prd 
		 every environment have the according profile  file in the src/profiles
	 

#Build and Deploy
for local build and deploy

    build.bat local

for dev build and deploy

     build.bat dev
the build .bat script will call maven command to build project and call putty command to upload to server and restart the project.  one command to run them all.
#Reference
Spring boot
https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/

Spring data jpa
https://docs.spring.io/spring-data/jpa/docs/1.11.4.RELEASE/reference/html/

Swagger-ui
https://swagger.io/swagger-ui/

