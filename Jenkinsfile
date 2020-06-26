#!groovy

pipeline {
  agent any
    stages {
    stage('Maven Install') {
      agent {
        docker {
          image 'maven:3.5.0'
        }
      }
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Docker Build') {
      agent any
      steps {
         sh 'docker build -t 8979635092/test:latest .'
      }
    }
    stage('Docker Push') {
      agent any
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
          sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh 'docker push 8979635092/test:latest'
        }
      }
    }
     stage('Apply Kubernetes Files') {
      steps {
          withKubeConfig([credentialsId: 'gke']) {
          sh 'cat mongoDemo.yml | sed "s/latest/latest/g" | kubectl apply -f -'
         
        }
      }
  }
  }
}
