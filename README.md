
# SEMA
CC Division
- (CC) C282DSY0720 – Adiesty Puspa Novitasari – Universitas Negeri Malang
- (CC) C136DKX3870 – Muhammad Nurul Arifin– Universitas Amikom Yogyakarta
# This API we made to perform Machine Learning Model for Prediction Using Flask There is One Endpoint that will consume By the Mobile App
## Before deploy
- We'll use cloud run, don't forget to create Dockerfile or use the same as our file.
- Create a new project.
- Make a storage bucket for cloud build in your preference region (it will reduce the cost).
- Why use container?, container in container registry also will rewrite on your cloud storage in multi region) if you don't specify using cloudbuild.yaml 
- Make sure before that you've activated your Cloud Build API, Cloud Run API  also and enable your billing project.
- We'll use Cloud Build API for one button type deployment to Deploy on Cloud Run.
1. Go to cloud console, by using command.
2. Set your project by your preference region. Like..
3. Copy this github link 
4. After done
Type cd go to the folder that contains github files
<Cd ( folder name )>
Example cd SEMA
5. You now on SEMA directory.
6. Go to Cloud Code Editor for build your own cloudbuild.yaml
 Or use our file at github.
7. Back to cloud console (command) and type gcloud build submit config cloudbuild.yaml 
8. Just waiting for around 15 minutes. If there's  error, there's some add info that u must know
-Maybe the port you've define
-maybe your own cloudbuild.yaml config
-or maybe your dockerfile
9. If there's no error; Command will deliver status SUCCESS. And you've your own link.
10. 10. Go to your services by type Cloud Run in search bar or the navigation. Select your service-name that you've been specified before.
11. You could re deploy for many times in Cloud Run and change the percentage of the traffic with both of (the latest and the last) - the deployment service with the same service name and the same final url result too.
## API Documentation 
https://app.swaggerhub.com/apis-docs/MNURULANU246/s-eko_lah_bersa_ma_se_ma/1.0.0#/predict/input_data 
## Tools We Use
- VSCODE
- Postman
- Cloud Build
- Cloud Storage
- Firebase Firestore
- Cloud Run
- Swagger
## Library We Use
- Flask
- Tensorflow
- CORS
