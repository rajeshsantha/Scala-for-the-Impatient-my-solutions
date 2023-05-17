name := "PracticeScalaWorkspace"

version := "0.1"

scalaVersion := "2.13.8"
// For YAML
libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.1",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.1.1",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.1",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.1.1"
)
//For parsing application.conf
libraryDependencies += "com.typesafe" % "config" % "1.2.1"
