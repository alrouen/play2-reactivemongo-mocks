name := "play2-reactivemongo-mocks"

val mocksVersion = s"${sys.props.getOrElse("build.majorMinor", "0.3")}.${sys.props.getOrElse("build.version", "SNAPSHOT")}"

val targetPlayReactiveMongoVersion = "0.11.6.play24"

version := targetPlayReactiveMongoVersion + "_" + mocksVersion

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.11.7")

publishArtifact in (Compile, packageDoc) := false

organization := "org.freesp"

libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "org.mockito"           %   "mockito-all"           % "1.10.19",
    "org.reactivemongo"     %%  "play2-reactivemongo"   % targetPlayReactiveMongoVersion,
    "com.typesafe.play"     %% "play"                   % "2.4.3"       % "provided",
    "org.specs2"            %% "specs2"                 % "2.3.13"
)

resolvers ++= Seq(  "oss-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
				    "oss-releases"  at "https://oss.sonatype.org/content/repositories/releases",
				    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")

jacoco.settings

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

scalariformSettings



