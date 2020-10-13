
organization := "fr.lcdp"
name := "deadbolt-java-extension"

version := "1.0.0"
scalaVersion := "2.11.12"

lazy val `deadbolt-java-extension` = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  cache,
  javaWs,
"be.objectify" %% "deadbolt-java" % "2.5.8"
)