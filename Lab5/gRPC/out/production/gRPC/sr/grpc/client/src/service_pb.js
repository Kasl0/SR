// source: service.proto
/**
 * @fileoverview
 * @enhanceable
 * @suppress {missingRequire} reports error on implicit type usages.
 * @suppress {messageConventions} JS Compiler reports an error if a variable or
 *     field starts with 'MSG_' and isn't a translatable message.
 * @public
 */
// GENERATED CODE -- DO NOT EDIT!
/* eslint-disable */
// @ts-nocheck

var jspb = require('google-protobuf');
var goog = jspb;
var global =
    (typeof globalThis !== 'undefined' && globalThis) ||
    (typeof window !== 'undefined' && window) ||
    (typeof global !== 'undefined' && global) ||
    (typeof self !== 'undefined' && self) ||
    (function () { return this; }).call(null) ||
    Function('return this')();

goog.exportSymbol('proto.service.Message', null, global);
goog.exportSymbol('proto.service.MessageType', null, global);
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.service.Message = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.service.Message, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.service.Message.displayName = 'proto.service.Message';
}



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.service.Message.prototype.toObject = function(opt_includeInstance) {
  return proto.service.Message.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.service.Message} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.service.Message.toObject = function(includeInstance, msg) {
  var f, obj = {
    msgType: jspb.Message.getFieldWithDefault(msg, 1, 0),
    senderId: jspb.Message.getFieldWithDefault(msg, 2, ""),
    targetId: jspb.Message.getFieldWithDefault(msg, 3, ""),
    message: jspb.Message.getFieldWithDefault(msg, 4, "")
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.service.Message}
 */
proto.service.Message.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.service.Message;
  return proto.service.Message.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.service.Message} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.service.Message}
 */
proto.service.Message.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {!proto.service.MessageType} */ (reader.readEnum());
      msg.setMsgType(value);
      break;
    case 2:
      var value = /** @type {string} */ (reader.readString());
      msg.setSenderId(value);
      break;
    case 3:
      var value = /** @type {string} */ (reader.readString());
      msg.setTargetId(value);
      break;
    case 4:
      var value = /** @type {string} */ (reader.readString());
      msg.setMessage(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.service.Message.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.service.Message.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.service.Message} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.service.Message.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getMsgType();
  if (f !== 0.0) {
    writer.writeEnum(
      1,
      f
    );
  }
  f = message.getSenderId();
  if (f.length > 0) {
    writer.writeString(
      2,
      f
    );
  }
  f = message.getTargetId();
  if (f.length > 0) {
    writer.writeString(
      3,
      f
    );
  }
  f = message.getMessage();
  if (f.length > 0) {
    writer.writeString(
      4,
      f
    );
  }
};


/**
 * optional MessageType msg_type = 1;
 * @return {!proto.service.MessageType}
 */
proto.service.Message.prototype.getMsgType = function() {
  return /** @type {!proto.service.MessageType} */ (jspb.Message.getFieldWithDefault(this, 1, 0));
};


/**
 * @param {!proto.service.MessageType} value
 * @return {!proto.service.Message} returns this
 */
proto.service.Message.prototype.setMsgType = function(value) {
  return jspb.Message.setProto3EnumField(this, 1, value);
};


/**
 * optional string sender_id = 2;
 * @return {string}
 */
proto.service.Message.prototype.getSenderId = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 2, ""));
};


/**
 * @param {string} value
 * @return {!proto.service.Message} returns this
 */
proto.service.Message.prototype.setSenderId = function(value) {
  return jspb.Message.setProto3StringField(this, 2, value);
};


/**
 * optional string target_id = 3;
 * @return {string}
 */
proto.service.Message.prototype.getTargetId = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 3, ""));
};


/**
 * @param {string} value
 * @return {!proto.service.Message} returns this
 */
proto.service.Message.prototype.setTargetId = function(value) {
  return jspb.Message.setProto3StringField(this, 3, value);
};


/**
 * optional string message = 4;
 * @return {string}
 */
proto.service.Message.prototype.getMessage = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 4, ""));
};


/**
 * @param {string} value
 * @return {!proto.service.Message} returns this
 */
proto.service.Message.prototype.setMessage = function(value) {
  return jspb.Message.setProto3StringField(this, 4, value);
};


/**
 * @enum {number}
 */
proto.service.MessageType = {
  REQUEST: 0,
  RESPONSE: 1
};

goog.object.extend(exports, proto.service);
