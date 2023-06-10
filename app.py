import os
import requests
from flask import Flask, jsonify, request
import tensorflow as tf

app = Flask(__name__)
#model_url = 'https://storage.googleapis.com/mlmodelsave/model.json'
model_path = 'score_model.h5'
# if not os.path.exists(model_path):
#     response = requests.get(model_url)
#     with open(model_path, 'wb') as f:
#         f.write(response.content)

# Load the model
model = tf.keras.models.load_model(model_path)

@app.route('/predict', methods=['POST'])
def predict():
    input_data = request.json['input_data']
    input_tensor = tf.convert_to_tensor(input_data, dtype=tf.float64)
    input_tensor = tf.reshape(input_tensor, [1, 4])
    output_tensor = model.predict(input_tensor)
    results = output_tensor.tolist()
    return jsonify(results)

if __name__ == '__main__':
    app.run()
