# SEMA
CC Division
- (CC) C282DSY0720 – Adiesty Puspa Novitasari – Universitas Negeri Malang
- (CC) C136DKX3870 – Muhammad Nurul Arifin– Universitas Amikom Yogyakarta
# This API we made to perform Machine Learning Model for Prediction Using Flask There is One Endpoint that will consume By the Mobile App
## Step to Build the API 
1. Install all the dependecies such as Library like flask etc
2. build the code
3. create route and endpoint
4. Test the code using postman
## Use API
1. Use the URL endpoint API
2. Set the Method POST
3. Send VIA Request.form or form-data(at postman body)
4. Input the key and number, ex: key: input_data Value:  [80.0, 90.0, 1.0, 1.0]
5. Get the response
## Deployment API, with Cloud Build for deployment on Cloud Run
**Before :**
- Go to https://cloud.google.com/ , register or login and create a new project.
- On the navigation at the left side or on the search bar, find or type "Storage Bucket" and click.
- Make your own bucket name and specified your region. If your bucket region same as your geographical location it will reduce the latency and lower your billing. 
- Make sure before that you've activated your Cloud Build API, Cloud Run API, Artifact Registry API also enable your billing project.
- Go to Cloud Build -> Settings -> Enable Cloud Run and Service Accounts.
- We'll use Cloud Build for activate cloudbuild.yaml for simplified the process until deployment to Cloud Run. We've to only type one command to cloud shell.
- We'll use Cloud Run, don't forget to create your own Dockerfile or use the same as our file.
- Make your own container repository on Artifact Registry. Specify the name of the repository, Docker and your preference region.

**Step :**
1. Go to activate chould shell, you will see the terminal.
2. Set your project-id, your region, and click authorize.

<gcloud config set project YOUR_PROJECT_ID>
<gcloud config set compute/region YOUR_REGION>
  
When prompted up showing click AUTHORIZE.
  
3. Copy this github repository-branch "CC" (the API that will be deployed located only on this branch)
4. After done
    + type "cd + 'name of a folder that contains cloned github files') 
    + <cd ( folder name ) = <cd SEMA>
5. You're now on SEMA directory.
6. Go to Cloud Shell Editor for build your own cloudbuild.yaml file. 
  
  + From our cloudbuild.yaml -> 'asia-southeast2-docker.pkg.dev/primeval-gear-384914/sekolahbersama/backendsema:latest'
    
    + asia-southeast2-docker.pkg.dev = artifact registry in asia-southeast2
    + primeval-gear-384914 = project ID
    + sekolahbersama = repository name in artifact registry
    + backendsema:latest = our cloud run service name
  
  You could modify this with your own project id, your own repository, also your ouwn cloud run service name.
  
7. Back to cloud shell terminal and type 
   <gcloud builds submit --gcs-source-staging-dir="gs://<your-bucket-name>/cloudbuild-custom" --config cloudbuild.yaml> 
    
   + note: 
     gs://<your-bucket-name> is the name of your bucket name you've been created before.
     
      + Press ENTER

8. Just waiting for around 15 minutes it will start build with step you've been specified in cloudbuild.yaml before.
   + If there's no error; Command will deliver status SUCCESS. And you've your own deployed link.
   + If there's  error, there's some additional info that you must know :
     
        + Maybe the port you've specified define at the app.py doesn't match with your Dockerfile or the way you write the PORT on app.py          file didn't meet google cloud requirements. 
        + Maybe your own cloudbuild.yaml config return status error.
        + or maybe your Dockerfile step, there's missing.

9. To verify your deployed API, you could type on the searchbar "Cloud Run" see your cloud run service-name. If there's green check, so that the deployment was running.
10. You could re-deploy for many times in Cloud Run and change the percentage of the traffic with both of (the latest and the last) and it doesn't affect the service-name and URL deployed. (the deployment service-name and URL deployed will be still the same)   
## API URL Endpoint
https://backendsema-x6blq7wjaa-et.a.run.app/api/predict 
## API Documentation 
https://app.swaggerhub.com/apis-docs/MNURULANU246/s-eko_lah_bersa_ma_se_ma/1.0.0#/predict/input_data 
## Tools We Use
- Visual Studio Code
- Postman
- Cloud Build 
- Cloud Storage for gs://semaasia and saved file from firebase storage too
- Firebase Auth for Authentication
- Firebase Firestore for database storage and set authorization regarding 'uid' Firebase Auth should 'match Firestore documentID'.
- Firebase Storage for save profile photo and saved url link to Firestore
- Cloud Run 
- SwaggerHub for UI API Documentations, Swagger Online Editor for edit API Documentations.
## Library We Use
- Flask
- Tensorflow
- CORS
