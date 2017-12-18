pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        bat '/"${mvnHome}\\bin\\mvn"  clean package/'
      }
    }
  }
}