pipeline {
	agent any
	environment {
		DOCKER_BUILDKIT=1
	}
	stages {
		stage('Clone'){
			steps {
				echo "Clone the branch from GIT"
			}
		}
		stage('Run'){
			steps{
				echo 'Build the Docker image"
				script{
					dockerImage = docker.build("bavodenys/python_kivy_snake", "./Snake")
				}
			}
		}
		stage('Test'){
			steps{
				echo "Test"
			}	
		}	
		stage('Push'){
			steps{
				script{
					withDockerRegistery([credentialsId: "dockerhub_id", url:""]){
					dockerImage.push()
					}
				}
			}
		}
	}
	post{
		always{
			echo "Execute always"
		}
		success{
			echo "Success!!"
		}
		failure{
			echo "Failure!!"
		}
	
	}
}
	
