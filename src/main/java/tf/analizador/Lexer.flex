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

Double = ("."{D}+)|({D}+("."{D}*)?([eE][+-]?{D}+)?)
Bool = "true"|"True"|"False"|"false"
Char = \'[^'\\]\'
Char2 = \'\\[nrt'\"\\]\'
DPD= "::"
Int = ({D}+|"-"?{D}+)

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
"if"|"If"               { lexeme = yytext(); return If; }
"then"                  { lexeme = yytext(); return Then; }
"else"                  { lexeme = yytext(); return Else; }
"case"|"Case" 			{ lexeme = yytext(); return Case; }
"of"|"Of"               { lexeme = yytext(); return Of; }
"data"|"Data"           { lexeme = yytext(); return Data; }
"type"                  { lexeme = yytext(); return Type; }

"int"|"Int"
|"Integer"|"integer"
|{Int}					{ lexeme = yytext(); return Int; }

"double"|"Double"
|{Double}		        { lexeme = yytext(); return Double; }

"char"|"Char"
|{Char}|{Char2}         { lexeme = yytext(); return Char; }

"bool"|"Bool"
|{Bool}		            { lexeme = yytext(); return Bool; }

"="                     { lexeme = yytext(); return Igual; }
"+"                     { lexeme = yytext(); return Suma; }
"-"                     { lexeme = yytext(); return Resta; }
"*"                     { lexeme = yytext(); return Multiplicacion; }
"/"                     { lexeme = yytext(); return Division; }
"%"                     { lexeme = yytext(); return Modulo; }
"^"                     { lexeme = yytext(); return Potencia; }

"=>"					{ lexeme = yytext(); return Flecha_GD; }

"->"        			{ lexeme = yytext(); return Flecha_D; }
"<-"        			{ lexeme = yytext(); return Flecha_I; }

"::"|{DPD}     			{ lexeme = yytext(); return DosPuntos_Doble; }

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

\"[^\"]*\"     			{ lexeme = yytext(); return Cadena; }

{L}({L}|{D})*           { lexeme = yytext(); return Identificador; }

.                       { return ERROR; }