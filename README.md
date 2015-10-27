greeter: Native Hibernate (No JPA)
==================================

This application is based on WildFly 10 Quickstart Greeter
https://github.com/wildfly/quickstart/tree/10.x/greeter

Native hibernate is used instead of JPA. Note that JPA annotations are used for mapping of User entity.

Changes made
------------

* Removed all two UserDao implementations
* Created new UserDao implementation that uses native Hibernate approach
* Altered UserDao.java interface so that createUser returns User object instead of null
  * this is required to display user ID properly
* Altered CreateController.java so that newly created user object contains ID
* Created new HibernateUtil.java class for Hibernate's sessionFactory creation in native way
  * also table USERS is created and initialized here
* Removed JPA stuff
  * persistence.xml
  * EntityManager setup from Resources.java
* Created jboss-deployment-structure.xml to use WildFly's org.hibernate module
* Added hibernate-core & javassist dependencies to pom.xml
* Used WildFly 10.0.0.CR3 BOMs in pom.xml and used Maven Central only
* Changed name to hibernate-greeter in pom.xml
* Removed import.sql because you can't use hbm2ddl if using native Hibernate with container managed transactions (JTA)
* Updated README.md file :)

Orignal README.md:

greeter: Demonstrates CDI, JPA, JTA, EJB, and JSF
========================
Author: Pete Muir  
Level: Beginner  
Technologies: CDI, JSF, JPA, EJB, JTA  
Summary: The `greeter` quickstart demonstrates the use of *CDI*, *JPA*, *JTA*, *EJB* and *JSF* in WildFly.  
Target Product: WildFly  
Source: <https://github.com/wildfly/quickstart/>  

What is it?
-----------

The `greeter` quickstart demonstrates the use of *CDI*, *JPA*, *JTA*, *EJB* and *JSF* in Red Hat JBoss Enterprise Application Platform.

When you deploy this example, two users are automatically created for you:  `emuster` and `jdoe`. This data is located in the `src/main/resources/import.sql file`.

To test this example:

1. Enter a name in the `username` field and click on `Greet!`.
2. If you enter a username that is not in the database, you get a message `No such user exists!`.
3. If you enter a valid username, you get a message "Hello, " followed by the user's first and last name.
4. To create a new user, click the `Add a new user` link. Enter the username, first name, and last name and then click `Add User`. The user is added and a message displays the new user id number.
5. Click on the `Greet a user!` link to return to the `Greet!` page.


_Note: This quickstart uses the H2 database included with Red Hat JBoss Enterprise Application Platform 7. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment!_

_Note: This quickstart uses a `*-ds.xml` datasource configuration file for convenience and ease of database configuration. These files are deprecated in WildFly and should not be used in a production environment. Instead, you should configure the datasource using the Management CLI or Management Console. Datasource configuration is documented in the [Administration and Configuration Guide](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/) for Red Hat JBoss Enterprise Application Platform._


System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later. See [Configure Maven for WildFly 7](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN_JBOSS_EAP7.md#configure-maven-to-build-and-deploy-the-quickstarts) to make sure you are configured correctly for testing the quickstarts.


Use of WILDFLY_HOME
---------------

In the following instructions, replace `WILDFLY_HOME` with the actual path to your WildFly installation. The installation path is described in detail here: [Use of WILDFLY_HOME and JBOSS_HOME Variables](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_OF_EAP7_HOME.md#use-of-eap_home-and-jboss_home-variables).


Start the WildFly Server
-------------------------

1. Open a command prompt and navigate to the root of the WildFly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/standalone.sh
        For Windows: WILDFLY_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/wildfly-greeter.war` to the running instance of the server.


Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/jboss-greeter>. 


Undeploy the Archive
--------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Server Log: Expected warnings and errors
-----------------------------------

_Note:_ You will see the following warnings in the server log. You can ignore these warnings.

    WFLYJCA0091: -ds.xml file deployments are deprecated. Support may be removed in a future version.

    HHH000431: Unable to determine H2 database version, certain features may not work


Run the Quickstart in Red Hat JBoss Developer Studio or Eclipse
-------------------------------------
You can also start the server and deploy the quickstarts or run the Arquillian tests from Eclipse using JBoss tools. For general information about how to import a quickstart, add a WildFly server, and build and deploy a quickstart, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_JBDS.md#use-jboss-developer-studio-or-eclipse-to-run-the-quickstarts) 


Debug the Application
------------------------------------

If you want to debug the source code of any library in the project, run the following command to pull the source into your local repository. The IDE should then detect it.

        mvn dependency:sources


