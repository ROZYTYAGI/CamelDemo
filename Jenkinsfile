#!groovy

pipeline {
  agent any
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
         myapp = docker.build("8979635092/test:${env.BUILD_ID}")
      }
    }
    stage('Docker Push') {
      agent any
      steps {
         docker.withRegistry('https://registry.hub.docker.com', 'dockerHub') {
                            myapp.push("latest")
                            myapp.push("${env.BUILD_ID}")
        }
      }
    }
    stage('Deploy to GKE') {
            steps{
                sh "sed -i 's/test:latest/test:${env.BUILD_ID}/g' mongoDemo.yml"
                step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'mongoDemo.yml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
            }
        }
  }
}
