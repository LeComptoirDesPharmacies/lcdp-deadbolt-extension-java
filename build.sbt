
organization := "fr.lcdp"
name := "lcdp-deadbolt-extension-java"

version := "1.0.0"
scalaVersion := "2.11.12"

lazy val `lcdp-deadbolt-extension-java` = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  cache,
  javaWs,
"be.objectify" %% "deadbolt-java" % "2.5.8"
)