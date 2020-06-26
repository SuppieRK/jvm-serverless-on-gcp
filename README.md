# Live Demo of Micronaut Application for Google Cloud Run

This demo is a part of my talk at [GDG DevParty 2020](https://gdg-devparty.ru/)

## Steps to work with this repository

### Login to your Google account with gcloud CLI

`gcloud auth login && gcloud auth configure-docker`

[See more details here on how to switch project](https://medium.com/google-cloud/setup-and-switch-between-google-cloud-projects-in-the-sdk-885c5000624c)

### Build and push image to Google Container Registry

`export PROJECT_ID=$(gcloud config get-value project) && docker build . -t gcr.io/$PROJECT_ID/demo-app && docker push gcr.io/$PROJECT_ID/demo-app`

### Process to deploy Docker image to Cloud Run

You can use either UI or [instructions from guide](https://cloud.google.com/run/docs/deploying)

### After work - do not forget to clean up resources

## Useful commands

#### Clean up Docker resources

`docker rm -f $(docker ps -aq) && docker system prune -a -f --volumes`

#### Build Docker image using JIB without GraalVM

`export PROJECT_ID=$(gcloud config get-value project) && chmod +x ./gradlew && time ./gradlew jibDockerBuild --image=gcr.io/$PROJECT_ID/demo-app:jib`

#### Build Docker image using Docker daemon with GraalVM

`export PROJECT_ID=$(gcloud config get-value project) && time docker build . -t gcr.io/$PROJECT_ID/demo-app:graal`

#### Compare images locally using [container-diff](https://github.com/GoogleContainerTools/container-diff)

`export PROJECT_ID=$(gcloud config get-value project) && container-diff diff daemon://gcr.io/$PROJECT_ID/demo-app:jib daemon://gcr.io/$PROJECT_ID/demo-app:graal --type=size --type=history`