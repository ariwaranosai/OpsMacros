name := "opsmacros"


lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  version := "0.0.1",
  scalacOptions ++= Seq (
    "-language:implicitConversions",
    "-language:existentials",
    "-language:postfixOps",
    "-Xlint",
    "-deprecation",
    "-Xfatal-warnings",
    "-feature",
    "-deprecation"
  ),
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )
)

lazy val opsmacros = (project in file("."))
  .settings(commonSettings)
  .settings(
    scalacOptions ++= Seq(
      "-Ymacro-debug-lite"
    )
  )

lazy val benchmark = (project in file("benchmark"))
  .settings(commonSettings)
  .dependsOn(opsmacros)
  .settings(
    resolvers += "Sonatype OSS Snapshots" at
      "https://oss.sonatype.org/content/repositories/releases",
    libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.7",
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    parallelExecution in Test := false
  )