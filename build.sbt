
organization := "fr.lcdp"
name := "lcdp-captcha-java"

version := "1.0.1-SNAPSHOT"
scalaVersion := "2.11.12"

lazy val `lcdp-captcha-java` = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq( cache , javaWs )

// disable using the Scala version in output paths and artifacts
crossPaths := false
