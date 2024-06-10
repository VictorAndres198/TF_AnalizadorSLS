package tf.analizador;
import static tf.analizador.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%

{espacio}               {}
"//".*                  {}
\n                      { return Linea; }
"let"                   { lexeme = yytext(); return Let; }
"in"                    { lexeme = yytext(); return In; }
"where"                 { lexeme = yytext(); return Where; }
"if"                    { lexeme = yytext(); return If; }
"then"                  { lexeme = yytext(); return Then; }
"else"                  { lexeme = yytext(); return Else; }
"case"                  { lexeme = yytext(); return Case; }
"of"                    { lexeme = yytext(); return Of; }
"data"                  { lexeme = yytext(); return Data; }
"type"                  { lexeme = yytext(); return Type; }
"int"                   { lexeme = yytext(); return Int; }
"double"                { lexeme = yytext(); return Double; }
"char"                  { lexeme = yytext(); return Char; }
"bool"                  { lexeme = yytext(); return Bool; }
"="                     { lexeme = yytext(); return Igual; }
"+"                     { lexeme = yytext(); return Suma; }
"-"                     { lexeme = yytext(); return Resta; }
"*"                     { lexeme = yytext(); return Multiplicacion; }
"/"                     { lexeme = yytext(); return Division; }
"%"                     { lexeme = yytext(); return Modulo; }
"^"                     { lexeme = yytext(); return Potencia; }
{L}({L}|{D})*           { lexeme = yytext(); return Identificador; }
("-"{D}+) | {D}+        { lexeme = yytext(); return Numero; }
.                       { return ERROR; }