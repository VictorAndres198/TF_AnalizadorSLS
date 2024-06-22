package tf.analizador;
import static tf.analizador.Tokens.*;

%%

%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ \t]+
newline=\r|\n|\r\n
comentario_linea = "--[^\\n]*"
comentario_multilinea = "\\{-[\\s\\S]*?-\\}"

%{
	private int indentLevel = 0;
    private int currentIndent = 0;
    private boolean newLine = true;
    public String lexeme;
%}

%%


{newline}               { currentIndent = 0; newLine = true; return Linea; }
{espacio}               { if (newLine) { currentIndent += yytext().length(); } }
{comentario_linea}      { /* Comentario de una línea */ }
{comentario_multilinea} { /* Comentario multilínea */ }
"//".*                  {}
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
"->"        			{ lexeme = yytext(); return Flecha_D; }
"<-"        			{ lexeme = yytext(); return Flecha_I; }
"::"        			{ lexeme = yytext(); return DosPuntos_Doble; }
","        				{ lexeme = yytext(); return Coma; }
">"    					{ lexeme = yytext(); return Mayor_que; }
">="    				{ lexeme = yytext(); return Mayor_igual_que; }
"<"    					{ lexeme = yytext(); return Menor_que; }
"<="    				{ lexeme = yytext(); return Menor_igual_que; }
"=="    				{ lexeme = yytext(); return Igual_a; }
"&&"    				{ lexeme = yytext(); return And; }
"||"    				{ lexeme = yytext(); return Or; }
"|"    					{ lexeme = yytext(); return Guarda; }
"$"    					{ lexeme = yytext(); return Dolar; }
"\\\\"  				{ lexeme = yytext(); return Lambda; }
"("    					{ lexeme = yytext(); return Parentesis_a; }
")"    					{ lexeme = yytext(); return Parentesis_c; }
"{"    					{ lexeme = yytext(); return Llave_a; }
"}"    					{ lexeme = yytext(); return Llave_c; }
"["    					{ lexeme = yytext(); return Corchete_a; }
"]"    					{ lexeme = yytext(); return Corchete_c; }
.                       { return ERROR; }
