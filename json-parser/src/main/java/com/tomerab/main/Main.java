package com.tomerab.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.tomerab.ast.JsonObject;
import com.tomerab.exceptions.JsonSyntaxError;
import com.tomerab.lexer.JsonLexer;
import com.tomerab.parser.JsonParser;
import com.tomerab.visitor.JsonPrettyPrintVisitor;
import com.tomerab.visitor.JsonVisitor;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder inputBuilder = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        inputBuilder.append(line).append("\n");
      }

      String jsonInput = inputBuilder.toString();

      JsonLexer jsonLexer = new JsonLexer(jsonInput);
      JsonParser jsonParser = new JsonParser(jsonLexer);
      JsonObject obj = jsonParser.parse();
      JsonVisitor visitor = new JsonPrettyPrintVisitor(4);

      obj.accept(visitor);

      System.out.println();
    } catch (JsonSyntaxError e) {
      System.out.println("JsonSyntaxError: " + e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
