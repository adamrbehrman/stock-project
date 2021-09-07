
name := "StocksProject2"
version := "0.1"
scalaVersion := "2.12.14"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.1.2" 
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.2" 

assemblyJarName in assembly := "StocksProject2-fatjar-0.1.jar"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

enablePlugins(GitVersioning, GitBranchPrompt)

git.baseVersion := "1.0"



