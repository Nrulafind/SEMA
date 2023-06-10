from flask import flask, jsonify, request
import tensorflow as tf

app = Flask(__name__)

model = tf.keras.models.load_model('https://storage.googleapis.com/mlmodelsave/model.json')

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
