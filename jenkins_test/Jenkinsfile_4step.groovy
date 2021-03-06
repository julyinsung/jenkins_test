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
   
   stage('Build') {
   input 'Do you approve build?'
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
         bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml -Dmaven.test.failure.ignore clean package/)
      }
   }
   
   stage('Junit') {
   input 'Do you approve Junit?'
      // Run the maven build
      //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
      bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml clean package/)
      junit '**/target/surefire-reports/TEST-*.xml'
   }
   
   stage('PMD') {
   input 'Do you approve PMD?'
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore jenkins_test\pom.xml clean package/)
         bat(/"${mvnHome}\bin\mvn" -f jenkins_test\pom.xml -Dmaven.test.failure.ignore clean package pmd:pmd/)
      }
   }
   
   stage('Deploy') {
   input 'Do you approve Deploy?'   
      milestone()
      echo "Result" 
      //junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
}
