plugins     {
    java 
  application
 }
repositories     { mavenCentral(  ) }
dependencies { testImplementation  ("org.junit.jupiter  junit-jupiter:5.10.0   ") }
 application { mainClass.set("Main")}
      tasks.withType<Jar>{
duplicatesStrategy   = DuplicatesStrategy.EXCLUDE
    manifest{ attributes["Main-Class"  ] = "Main" }
    from(configurations.runtimeClasspath.get().map      { if (it.isDirectory) it else zipTree(it) })


    }
