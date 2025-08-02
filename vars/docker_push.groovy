def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    def credentials = config.credentials ?: 'docker-hub-credentials'
    
    echo "Pushing Docker image: ${imageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_USERNAME',
        passwordVariable: 'DOCKER_PASSWORD'
    )]) {
        sh """
                    docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}
                    docker tag ${imageName}:${imageTag} ${DOCKER_USERNAME}/${imageName}:${imageTag}
                    docker push ${DOCKER_USERNAME}/${imageName}:${imageTag}
                    echo 'Image pushed to Docker Hub successfully'
        """
    }
}


  