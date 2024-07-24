pipeline {
    agent any
    parameters {
        choice(name: 'OS', choices: ['linux', 'darwin', 'windows', 'all'], description: 'Choose OS')
        choice(name: 'TARGETOSARCH', choices: ['amd64', 'arm64'], description: 'Choose architecture')
    }
    environment {
        REPO = 'https://github.com/flameflashy/kbot'
        BRANCH = 'main'
        REGISTRY = 'dmitriysokolov'
    }
    stages {
        stage('clone') {
            steps {
                echo 'Clone repo'
                git branch: "${BRANCH}", url: "${REPO}"
            }
        }
        stage('test') {
            steps {
                echo 'run test'
                sh 'make test'
            }
        }
        stage('build') {
            parallel {
                stage('Build Linux') {
                    when { expression { params.OS == 'linux' || params.OS == 'all' } }
                    steps {
                        echo 'Building for Linux'
                        sh 'make image TARGETOS=linux'
                    }
                }
                stage('Build Darwin') {
                    when { expression { params.OS == 'darwin' || params.OS == 'all' } }
                    steps {
                        echo 'Building for MacOs'
                        sh 'make image TARGETOS=macos'
                    }
                }
                stage('Build Windows') {
                    when { expression { params.OS == 'windows' || params.OS == 'all' } }
                    steps {
                        echo 'Building for Windows'
                        sh 'make image TARGETOS=windows'
                    }
                }
            }
        }
        stage('push') {
            parallel {
                stage('Push Linux to dockerhub') {
                    when { expression { params.OS == 'linux' || params.OS == 'all' } }
                    steps {
                        script {
                            docker.withRegistry('', 'dockerhub') {
                                sh 'make push TARGETOS=linux'
                            }
                        }
                    }
                }
                stage('Push Darwin to dockerhub') {
                    when { expression { params.OS == 'darwin' || params.OS == 'all' } }
                    steps {
                        script {
                            docker.withRegistry('', 'dockerhub') {
                                sh 'make push TARGETOS=macos'
                            }
                        }
                    }
                }
                stage('Push Windows to dockerhub') {
                    when { expression { params.OS == 'windows' || params.OS == 'all' } }
                    steps {
                        script {
                            docker.withRegistry('', 'dockerhub') {
                                sh 'make push TARGETOS=windows'
                            }
                        }
                    }
                }
            }
        }
    }
}
