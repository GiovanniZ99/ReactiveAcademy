# BE4FE LIGHT ARCHETYPE

BEAR (Back End ARchitecture) is a back-end microservices architecture based on the **Spring Boot framework** written in **Java**.

The modularity underlying BEAR requires developers to structure their pages by creating different types of microservices according to the scope. Each microservice can be deployed on an openshift container in order to expose its synchronous/asynchronous interface. Each module is itself a maven module and is versioned in its own BitBucket repository.

• [BEAR Getting Started ](https://confluence.intesasanpaolo.com/display/DARWIN/BEAR+-+Getting+Started)

• [BEAR Technical Docs](https://confluence.intesasanpaolo.com/display/DARWIN/BEAR+-+Tech+Docs)

## Assumptions

To work with BEAR, it is assumed that you are already familiar with Spring, Spring Boot, Maven and some of the tools of the latest standards for Java Microservices development ( including the most common Design Patterns ).

## Environment Setup

Learn how to configure your work setup whether you are working from your local PC or from a POUN ( Virtual Desktop Workstation ) using the [BEAR Environment Setup](https://confluence.intesasanpaolo.com/display/DARWIN/BEAR+Environment+setup)

## Developer Guides

As an application framework built on top of Spring Boot, BEAR includes a collection of well-integrated libraries that cover a wide variety of features. The BEAR libraries include audit tracking, logs, monitoring, security, service-to-service communication, and more.
At the moment of writing the BEAR framework can only be used with **JDK 1.8**.

The documentation can be found at [BEAR Framework Overview](https://confluence.intesasanpaolo.com/display/DARWIN/BEAR+-+Overview)

The [Project Creation Page](https://darwin.sede.corp.sanpaoloimi.com/darwin/webapp/initializer-v1/application-setup) gives a guidance about the structure of a BEAR microservice and how to generate the scaffolding code for the component. In order to ease the work of creating a new microservices has benne created the Back End Initializer ( LINK ) that allows the developer to scaffold a new microservice using a simple web interface and the command line. BEAR provides a set of libraries of built-in Spring Components, known as "connectors".

Usage examples and documentation can be found at:

[BEAR SHOWCASE](https://confluence.intesasanpaolo.com/display/DARWIN/BEAR+-+Showcase)

## Tutorials

With the release of version 2.0, the BEAR framework is updated to **Spring Boot 2.5**, and all the connectors has been upgraded to the latest release of the drivers or clients provided by the vendors.

The official BEAR Tutorial can be found at: [BEAR TUTORIAL](https://confluence.intesasanpaolo.com/display/DARWIN/BEAR+Tutorial)

## Support

Depending on the needs, it's important to route requests to the correct **JIRA project** in order to contextualize the type of need and make the feedback more timely. Reports or requests relating to the use of BEAR or its components should be made at [JIRA BEARSUPP](https://jira.intesasanpaolo.com/secure/RapidBoard.jspa?rapidView=23&projectKey=BEARSUPP)