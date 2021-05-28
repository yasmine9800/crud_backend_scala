version := "1.0.0"
name := "openapi-scala-akka-http-server"
organization := "io.swagger"
scalaVersion := "2.12.6"
lazy val AkkaHttpVersion = "10.2.1"
lazy val AkkaVersion = "2.6.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.10",
  "com.typesafe.akka" %% "akka-stream" % "2.5.26",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10",
)
