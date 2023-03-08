val scala_version = "3.2.2"

val akka_version = "2.7.0"

lazy val root = project
  .in(file("."))
  .settings(

    name := "psbp009",

    version := "0.0.1-SNAPSHOT",,

    scalaVersion := scala_version,

    libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akka_version

  )
