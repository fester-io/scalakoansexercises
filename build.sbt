name := "Scala Koans"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

val ScalaTestVersion = "+"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % ScalaTestVersion % Test,
	"org.scalatest" %% "scalatest-matchers" % ScalaTestVersion % Test
)
