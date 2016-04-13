name := "Scala Koans"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions ++= Vector(
  "-deprecation",
  "-feature",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-encoding", "UTF-8",
  "-target:jvm-1.8",
  "-unchecked",
  "-Xexperimental",
  "-Yopt:l:classpath")


javacOptions ++= Vector(
  "-encoding", "UTF-8",
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked",
  "-Xlint:deprecation")

parallelExecution in Test := false
fork := true

logLevel := Level.Info
traceLevel := -1
showSuccess := false
showTiming := false

val ScalaTestVersion = "+"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest-core" % ScalaTestVersion % Test,
	"org.scalatest" %% "scalatest-funsuite" % ScalaTestVersion % Test,
	"org.scalatest" %% "scalatest-junit" % ScalaTestVersion % Test,
	"org.scalatest" %% "scalatest-flatspec" % ScalaTestVersion % Test,
	"org.scalatest" %% "scalatest-wordspec" % ScalaTestVersion % Test,
	"org.scalatest" %% "scalatest-matchers" % ScalaTestVersion % Test)
