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