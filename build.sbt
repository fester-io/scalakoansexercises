import sbt.Keys._

name := "Scala Koans"


lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
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
    "-Yopt:l:classpath"),
  javacOptions ++= Vector(
    "-encoding", "UTF-8",
    "-source", "1.8",
    "-target", "1.8",
    "-Xlint:unchecked",
    "-Xlint:deprecation"),
  updateOptions := updateOptions.value.withCachedResolution(true))

val ScalaTestVersion = "+"
val AkkaVersion = "2.4.+"

lazy val root = Project(
  id = "scala-koans",
  base = file(".")).
  settings(commonSettings: _*).
  settings(
    //@formatter:off
    libraryDependencies ++= Seq(
      "org.scalatest"           %% "scalatest-core"         % ScalaTestVersion % Test,
      "org.scalatest"           %% "scalatest-funsuite"     % ScalaTestVersion % Test,
      "org.scalatest"           %% "scalatest-junit"        % ScalaTestVersion % Test,
      "org.scalatest"           %% "scalatest-flatspec"     % ScalaTestVersion % Test,
      "org.scalatest"           %% "scalatest-wordspec"     % ScalaTestVersion % Test,
      "org.scalatest"           %% "scalatest-featurespec"  % ScalaTestVersion % Test,
      "org.scalatest"           %% "scalatest-propspec"     % ScalaTestVersion % Test,
      "org.scalatest"           %% "scalatest-matchers"     % ScalaTestVersion % Test,

      "com.typesafe.akka"       %% "akka-testkit"           % AkkaVersion % Test,
      "com.typesafe.akka"       %% "akka-stream-testkit"    % AkkaVersion % Test,
      "com.typesafe.akka"       %% "akka-http-testkit"      % AkkaVersion % Test),
    //@formatter:on
    parallelExecution in Test := false,
    fork := true,
    logLevel := Level.Info,
    traceLevel := -1,
    showSuccess := false,
    showTiming := false).
  dependsOn(macroSub)

lazy val macroSub = Project(
  id = "macro",
  base = file("macro")).
  settings(commonSettings: _*).
  settings(
    scalacOptions += "-language:experimental.macros",
    libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _))
