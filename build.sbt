
organization := "fr.lcdp"
name := "deadbolt-java-extension"

version := "2.0.0"
scalaVersion := "2.13.1"

crossScalaVersions := Seq("2.12.10", "2.13.1")

lazy val `deadbolt-java-extension` = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaWs,
"be.objectify" %% "deadbolt-java" % "2.8.1"
)