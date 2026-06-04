pipeline {

    // Run on any available Jenkins agent
    agent any

    // ── Tool configuration ─────────────────────────────────────────
    tools {
        maven 'Maven-3.9'   // Must match the name in Jenkins → Tools config
    }

    // ── Environment variables ──────────────────────────────────────
    environment {
        APP_NAME    = 'employee-management-app'
        JAR_NAME    = 'my-project-application-1.0.0.jar'
        JAVA_OPTS   = '-Xmx512m'
    }

    // ── Pipeline stages ────────────────────────────────────────────
    stages {

        // STAGE 1: Checkout code from GitHub
        stage('Checkout') {
            steps {
                echo "=========================================="
                echo " Stage 1: Checkout from GitHub"
                echo "=========================================="
                checkout scm
                echo "Code checkout complete!"
                echo "Branch: ${env.GIT_BRANCH}"
                echo "Commit: ${env.GIT_COMMIT}"
            }
        }

        // STAGE 2: Build - Compile the source code
        stage('Build') {
            steps {
                echo "=========================================="
                echo " Stage 2: Build - Compiling source code"
                echo "=========================================="
                bat 'mvn clean compile -DskipTests'
                echo "Build complete!"
            }
        }

        // STAGE 3: Test - Run all unit tests
        stage('Test') {
            steps {
                echo "=========================================="
                echo " Stage 3: Test - Running unit tests"
                echo "=========================================="
                bat 'mvn test'
                echo "Tests complete!"
            }
            post {
                always {
                    // Publish JUnit test results
                    junit allowEmptyResults: true,
                          testResults: '**/target/surefire-reports/*.xml'
                    echo "Test results published!"
                }
                success {
                    echo "All tests passed!"
                }
                failure {
                    echo "Some tests failed! Check test results above."
                }
            }
        }

        // STAGE 4: Package - Create the JAR file
        stage('Package') {
            steps {
                echo "=========================================="
                echo " Stage 4: Package - Creating JAR file"
                echo "=========================================="
                bat 'mvn package -DskipTests'
                echo "JAR created: target/${env.JAR_NAME}"
            }
            post {
                success {
                    // Archive the JAR artifact
                    archiveArtifacts artifacts: 'target/*.jar',
                                     allowEmptyArchive: true,
                                     fingerprint: true
                    echo "JAR archived successfully!"
                }
            }
        }

        // STAGE 5: Build Info - Print summary
        stage('Build Summary') {
            steps {
                echo "=========================================="
                echo " Stage 5: Build Summary"
                echo "=========================================="
                echo "App Name    : ${env.APP_NAME}"
                echo "Build Number: ${env.BUILD_NUMBER}"
                echo "Build URL   : ${env.BUILD_URL}"
                echo "Workspace   : ${env.WORKSPACE}"
                echo "=========================================="
                echo " Pipeline completed successfully!"
                echo "=========================================="
            }
        }
    }

    // ── Post-pipeline actions ──────────────────────────────────────
    post {
        success {
            echo "BUILD SUCCESSFUL - Employee Management App is ready!"
        }
        failure {
            echo "BUILD FAILED - Check console output for errors."
        }
        always {
            echo "Pipeline finished. Build #${env.BUILD_NUMBER} complete."
        }
    }
}
