import os
import requests
from flask import Flask, jsonify, request
from flask_cors import CORS
import tensorflow as tf

app = Flask(__name__)
CORS(app)
#model_url = 'https://storage.googleapis.com/mlmodelsave/model.json'
model_path = 'score_model.h5'
# if not os.path.exists(model_path):
#     response = requests.get(model_url)
#     with open(model_path, 'wb') as f:
#         f.write(response.content)

# Load the model
model = tf.keras.models.load_model(model_path)

@app.route('/api/predict', methods=['POST'])
def predict():
    input_data = request.json.get('input_data')
    if input_data is None or len(input_data) != 4:
        return jsonify({'message': 'input_data must contain 4 values'}), 400
    try:
        input_tensor = tf.convert_to_tensor([input_data], dtype=tf.float64)
        output_tensor = model.predict(input_tensor)
        results = output_tensor.tolist()
        return jsonify({'results': results}), 200
    except Exception as e:
        return jsonify({'error': 'Something went wrong'}), 500



if __name__ == '__main__':
    app.run(host='0.0.0.0', port=int(os.environ.get('PORT', 8080)))
