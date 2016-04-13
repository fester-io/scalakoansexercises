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
  "-Xlog-reflective-calls",
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

val ScalaTestVersion = "+"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % ScalaTestVersion % Test,
	"org.scalatest" %% "scalatest-matchers" % ScalaTestVersion % Test)
