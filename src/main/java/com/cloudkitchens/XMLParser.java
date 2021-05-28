package com.cloudkitchens;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/*
# <a>
#   <b>
#     <c>foo<d> <d></c>
#     <c></c>
#   </b>
#   <d>blah</d>
# </a>
#
# * Each call to tokenzer.nextToken() returns:
#  * {
#  *   value: 'a',
#  *   type: 'BEGIN'
#  * }
#  * {
#  *   value: 'b',
#  *   type: 'BEGIN'
#  * }
#  * {
#  *   value: 'c',
#  *   type: 'BEGIN'
#  * }
#  * {
#  *   value: 'foo',
#  *   type: 'TEXT'
#  * }
#  * {
#  *   value: 'c',
#  *   type: 'END'
#  * }
#  * {
#  *   value: 'c',
#  *   type: 'BEGIN'
#  * }
#  * {
#  *   value: 'c',
#  *   type: 'END'
#  * }
#  * {
#  *   value: 'b',
#  *   type: 'END'
#  * }
#  * {
#  *   value: 'd',
#  *   type: 'BEGIN'
#  * }
#  * {
#  *   value: 'blah',
#  *   type: 'TEXT'
#  * }
#  * {
#  *   value: 'd',
#  *   type: 'END'
#  * }
#  * {
#  *   value: 'a',
#  *   type: 'END'
#  * }
#
 */

class Tokenizer {
  List<Token> tokens;
  int idx = 0;

  void setTokens(List<Token> tokens) {
    this.tokens = tokens;
    idx = 0;
  }

  Token nextToken() {
    if (idx < tokens.size()) {
      return tokens.get(idx++);
    }

    return null;
  }
}

class Token {
  String value;
  TokenType type;

  Token(String value, TokenType type) {
    this.value = value;
    this.type = type;
  }
  String value() {
    return value;
  }
  TokenType type() {
    return type;
  }
}

enum TokenType {
  BEGIN,
  END,
  TEXT,
}


class XMLNode {
  String name;
  String text;
  List<XMLNode> children = new LinkedList<>();
  boolean isValid;
}

class XMLDocument {
  XMLNode root;

  public XMLDocument(XMLNode root) {
    this.root = root;
  }
}

public class XMLParser {
  
  public static XMLDocument parse(Tokenizer tokenizer) {
    Stack<XMLNode> stack = new Stack<>();
    stack.push(new XMLNode());
    do {
      Token t = tokenizer.nextToken();
      if (t == null) break;
      
      if (t.type() == TokenType.BEGIN) {
        XMLNode node = new XMLNode();
        node.name = t.value();
        stack.peek().children.add(node);
        stack.push(node);
      } else if (t.type() == TokenType.END) {
        XMLNode node = stack.peek();
        if (!t.value().equals(node.name)) {
          System.out.println("invalid doc, " + node.name +" is not close correctly.");
          break;
        }
        stack.pop();
      } else if (t.type() == TokenType.TEXT) {
        XMLNode node = stack.peek();
        node.text = t.value();
      }
    } while (true);

    if (stack.size() != 1 && stack.peek().children.size() != 1) {
      System.out.println("xml input is not valid, stack size is: " + stack.size());
    }
    return new XMLDocument(stack.peek().children.get(0));
  }


  public static void main(String[] args) {
    Tokenizer tokenizer = new Tokenizer();
    List<Token> list = new LinkedList<>();
    list.add(new Token("a", TokenType.BEGIN));
//    list.add(new Token("b", TokenType.BEGIN));
//    list.add(new Token("c", TokenType.BEGIN));
//    list.add(new Token("foo", TokenType.TEXT));
//    list.add(new Token("c", TokenType.END));
//    list.add(new Token("c", TokenType.BEGIN));
//    list.add(new Token("c", TokenType.END));
//    list.add(new Token("b", TokenType.END));
//    list.add(new Token("d", TokenType.BEGIN));
//    list.add(new Token("blah", TokenType.TEXT));
//    list.add(new Token("d", TokenType.END));
    list.add(new Token("a", TokenType.END));
    tokenizer.setTokens(list);

    XMLDocument doc = XMLParser.parse(tokenizer);
    System.out.println("");
  }

}

