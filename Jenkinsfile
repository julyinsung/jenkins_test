pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        bat '/"${mvnHome}\\bin\\mvn" -f jenkins_test\\pom.xml clean package/'
      }
    }
  }
}