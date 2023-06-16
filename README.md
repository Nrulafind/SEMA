# SEMA
ML Division
- (ML) M136DKY3785 - Indah Susilowati
- (ML) M040DKY4406 - Clarisa Tri Handayani

# Step in Building 
For this application we using linier regression model to predict the final score. 

## Data Preprocessing 
- Data Colecting, to make the machine learning model, we need to collect the data that needed to make a score prediction. we collecting the data from kaggle : https://www.kaggle.com/datasets/rkiattisak/student-performance-in-mathematics
- Data Transformation, after collecting the data, we transform that data so it can be used to modelling the machine learning. using one hot encoding we transform 'gender' column to 'M' and 'F' column
![image](https://github.com/Nrulafind/SEMA/assets/83949360/44177f4b-2928-4e07-8c3b-5f72967073aa)

- Data Reduction, droping down some column that doesn't needed to making the prediction. to perform the modeling we only using data that contain numbers
![image](https://github.com/Nrulafind/SEMA/assets/83949360/1fea5f37-35cb-4f07-bd22-83136a588b54)

## Modeling the Machine Learning
- Spliting Data, after preprocessing the data. we split the data to train data and test data
![image](https://github.com/Nrulafind/SEMA/assets/83949360/c7a247e3-6587-4081-ba07-667e43815fbc)

- Model Architecture, one of initial part of linier regression model. this part is where the model is constructed. the architecture of this model is consist of one layer Dense with linier activation function.
- Training Model, this part is where data trained to the model that has been constructed. to training the data we using 200 epoch and a loss function msle. 
- Import Model, after find the best trained model we import the model to h5 format so it can be get into deployment part
