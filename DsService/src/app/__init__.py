from flask import Flask
from flask import request, jsonify
from kafka import KafkaProducer
from service.messageService import MessageService
import json

app = Flask(__name__)
app.config.from_pyfile("config.py")
messageService = MessageService()
kafkaProducer = KafkaProducer(bootstrap_servers=['192.168.84.130:9092'],
                         value_serializer=lambda v: json.dumps(v).encode('utf-8'))

@app.route('/v1/ds/message', methods=['POST'])
def handle_message():
    data = request.json.get('message')
    result = messageService.process_message(data)
    serialized = result.json()
    kafkaProducer.send('expense_service', serialized)
    return jsonify(result)

if (__name__ == "__main__"):
    app.run(host="localhost", port = 8000, debug=True)