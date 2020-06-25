#!groovy

pipeline {
  agent none
   environment {
        PROJECT_ID = 'fiery-bay-277309'
        CLUSTER_NAME = 'mongodemo'
        LOCATION = '/home/tyagirozy4'
        CREDENTIALS_ID = 'gke'
    }
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
    stage('Deploy to GKE') {
            steps{
                sh "sed -i 's/mongodemo:latest/mongodemo:${env.BUILD_ID}/g' mongoDemo.yml"
                step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'mongoDemo.yml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
            }
        }
  }
}
