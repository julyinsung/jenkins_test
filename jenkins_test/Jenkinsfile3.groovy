def mvnHome
node {
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/julyinsung/jenkins_test.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'maven'
   }
}

input 'Do you approve build?'
node {
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
         bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml -Dmaven.test.failure.ignore clean package/)
      }
   }
}

input 'Do you approve Junit & PMD?'
node{
   stage('Junit') {
      // Run the maven build
      //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
      bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml clean package/)
      junit '**/target/surefire-reports/TEST-*.xml'
   }
   stage('PMD') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
         bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml -Dmaven.test.failure.ignore clean package pmd:pmd/)
      }
   }
}

input 'Do you approve Deploy?'
node{
   stage('Deploy') {
      milestone()
      echo "Result" 
      //junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
}