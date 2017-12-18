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

node {
   stage 'Build'
   input 'Do you approve build?'
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
node{
   stage('Junit') {
      milestone()
      echo "JUnit"
      // Run the maven build
      //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
      bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml clean package/)
      junit '**/target/surefire-reports/TEST-*.xml'
   }
   stage('PMD') {
      milestone()
      echo "PMD"
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
         bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml -Dmaven.test.failure.ignore clean package pmd:pmd/)
      }
   }
}

node{
   stage('Results') {
      milestone()
      echo "Result" 
      //junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
}