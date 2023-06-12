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
    input_data = request.json['input_data']
    if input_data is None: return res.status(401).jsonify({'message':'need input data'})
    try:
        input_tensor = tf.convert_to_tensor(input_data, dtype=tf.float64)
        input_tensor = tf.reshape(input_tensor, [1, 4])
        output_tensor = model.predict(input_tensor)
        results = output_tensor.tolist()
        return res.status(200).jsonify(results, {'message':'Successfully'})
    except Exception as e:
        return res.statuts(500).jsonify({'error': 'Something went wrong'})

if __name__ == '__main__':
    app.run()
    port = int(os.environ.get("PORT", 8080))
    app.run(host='0.0.0.0', port=port)
