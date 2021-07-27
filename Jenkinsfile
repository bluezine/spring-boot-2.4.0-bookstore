pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/bluezine/spring-boot-2.4.0-bookstore.git', branch: 'main')
      }
    }

    stage('gradle') {
      steps {
        sh 'chmod +x ./gradlew'
        sh './gradlew build'
      }
    }

  }
}