import AssemblyKeys._

import com.typesafe.startscript.StartScriptPlugin

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

name := "site"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.1"

sbtVersion := "0.11.0"

organization := "usescala"


seq(assemblySettings: _*)

libraryDependencies ++= Seq(
   "net.databinder" %% "unfiltered-filter" % "0.5.0",
   "net.databinder" %% "unfiltered-jetty" % "0.5.0",
   "net.databinder" %% "unfiltered-scalatest" % "0.5.0" % "test",
   "net.databinder" %% "dispatch-http" % "0.8.5",
   "net.databinder" %% "unfiltered-scalate" % "0.5.0",
   "org.fusesource.scalate" % "scalate-core" % "1.5.2",
   "ch.qos.logback" % "logback-classic" % "0.9.26",
   "org.slf4j" % "slf4j-jdk14" % "1.6.1",
   "org.scalatest" % "scalatest_2.9.1" % "1.6.1",
   "org.scala-tools.time" %% "time" % "0.5"
) ++ Seq( // local testing
  "javax.servlet" % "servlet-api" % "2.5" % "provided"
)

resolvers ++= Seq(
 "jboss" at  "https://repository.jboss.org/nexus/content/groups/public/"
)
