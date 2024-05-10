const { Message, MessageType } = require('./service_pb.js');
const { ChatServiceClient } = require('./service_grpc_web_pb.js');

const client = new ChatServiceClient('http://localhost:8080');

const message = new Message();
message.setMsgType(MessageType.REQUEST);
message.setSenderId('client');
message.setTargetId('server');
message.setMessage('Hello, this is a message!');

client.sendMessage(message, {}, (err, response) => {
  if (err) {
    console.error('Error:', err.message);
  } else {
    console.log('Response:', response.getMessage());
  }
});

const stream = client.receiveMessages(message, {});

stream.on('data', function(response) {
  console.log('Received message:', response.getMessage());
});

stream.on('status', function(status) {
  console.log('Stream status:', status);
});

stream.on('end', function() {
  console.log('Stream ended');
});

stream.on('error', function(err) {
  console.error('Error in stream:', err);
});