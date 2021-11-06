JObjects objects() {
	JObjects json = new JObjects();
	json.append(object());
	while(!lexer.inspect(EOF)) {
		lexer.consume(",");
		json.append(object());
	}
	lexer.consume(EOF);

	return json;
}

JObject object() {
	JObject jObject = JSONObject();

	lexer.consume("{");
	pair(jObject);
	while(!lexer.inspect("}")) {
		lexer.consume(",");
		pair(jObject);
	}
	lexer.consume("}");

	return jObject;
}

void pair(jObject) {
	String jsonString = json_string();
	lexer.consume(":");
	Value val = value();
	jObject.addPair(jsonString, val);
}

Value value() {
	Value val;
	if (lexer.inspectJNum()) {
		val = new JNum(lexer.consumeJNum());
	} else if (lexer.inspectJBool()) {
		val = new JBoolean(lexer.consumeJBool());
	} else if (lexer.inspect("\"")) {
		val = new JString(json_string());
	} else {
		throw new RuntimeException("invalid value");
	}
	return val;
}

String json_string() {
	lexer.consume("\"");
	String jsonString = lexer.consumeJString();
	lexer.consume("\"");
	return jsonString;
}

