from flask import Flask
from flask import request, jsonify
from kafka import KafkaProducer
from .service.messageService import MessageService
import json

app = Flask(__name__)
app.config.from_pyfile("config.py")
messageService = MessageService()
kafka_host = "kafka"
kafka_port = "9092"
kafka_bootstrap_servers = f"{kafka_host}:{kafka_port}"
kafkaProducer = KafkaProducer(bootstrap_servers=kafka_bootstrap_servers,
                         value_serializer=lambda v: json.dumps(v).encode('utf-8'))

@app.route('/v1/ds/message', methods=['POST'])
def handle_message():
    user_id = request.headers.get('x-user-id')

    if not user_id:
        return jsonify({'error': 'x-user-id header is reuquired'})
    
    data = request.json.get('message')
    result = messageService.process_message(data)

    if result is not None:
        serialized = result.dict()
        serialized['user_id'] = user_id
        kafkaProducer.send('expense_service', serialized)
        return jsonify(serialized)
    else:
        return jsonify({"error": "Invalid message format"}), 400
    

@app.route('/healthCheckDs', methods = ['GET'])
def health_check():
    return 'OK'

if (__name__ == "__main__"):
    app.run(host="localhost", port = 8010, debug=True)