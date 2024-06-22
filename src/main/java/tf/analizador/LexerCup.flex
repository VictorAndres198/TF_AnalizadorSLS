package tf.analizador;

import java_cup.runtime.Symbol;

%%

%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

L=[a-zA-Z_]+
D=[0-9]+
espacio=[ \t]+
newline=\r|\n|\r\n
comentario_linea = "--[^\\n]*"
comentario_multilinea = "\\{-[\\s\\S]*?-\\}"


%{
	private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }

    private int indentLevel = 0;
    private int currentIndent = 0;
    private boolean newLine = true;
%}

%%

{newline}               { currentIndent = 0; newLine = true; return new Symbol(sym.Linea, yychar, yyline, yytext());; }
{espacio}               { if (newLine) { currentIndent += yytext().length(); } }
{comentario_linea}      { /* Comentario de una línea */ }
{comentario_multilinea} { /* Comentario multilínea */ }
"let"                   {return new Symbol(sym.Let, yychar, yyline, yytext());}
"in"                    {return new Symbol(sym.In, yychar, yyline, yytext());}
"where"                 {return new Symbol(sym.Where, yychar, yyline, yytext());}
"if"                    {return new Symbol(sym.If, yychar, yyline, yytext());}
"then"                  {return new Symbol(sym.Then, yychar, yyline, yytext());}
"else"                  {return new Symbol(sym.Else, yychar, yyline, yytext());}
"case"                  {return new Symbol(sym.Case, yychar, yyline, yytext());}
"of"                    {return new Symbol(sym.Of, yychar, yyline, yytext());}
"data"                  {return new Symbol(sym.Data, yychar, yyline, yytext());}
"type"                  {return new Symbol(sym.Type, yychar, yyline, yytext());}
"int"                   {return new Symbol(sym.Int, yychar, yyline, yytext());}
"double"                {return new Symbol(sym.Double, yychar, yyline, yytext());}
"char"                  {return new Symbol(sym.Char, yychar, yyline, yytext());}
"bool"                  {return new Symbol(sym.Bool, yychar, yyline, yytext());}
"="                     {return new Symbol(sym.Igual, yychar, yyline, yytext());}
"+"                     {return new Symbol(sym.Suma, yychar, yyline, yytext());}
"-"                     {return new Symbol(sym.Resta, yychar, yyline, yytext());}
"*"                     {return new Symbol(sym.Multiplicacion , yychar, yyline, yytext());}
"/"                     {return new Symbol(sym.Division , yychar, yyline, yytext());}
"%"                     {return new Symbol(sym.Modulo , yychar, yyline, yytext());}
"^"                     {return new Symbol(sym.Potencia , yychar, yyline, yytext());}
{L}({L}|{D})*           {return new Symbol(sym.Identificador , yychar, yyline, yytext());}
("-"{D}+) | {D}+        {return new Symbol(sym.Numero , yychar, yyline, yytext());}
"->"        			{return new Symbol(sym.Flecha_D , yychar, yyline, yytext());}
"<-"        			{return new Symbol(sym.Flecha_I , yychar, yyline, yytext());}
"::"        			{return new Symbol(sym.DosPuntos_Doble , yychar, yyline, yytext());}
","        				{return new Symbol(sym.Coma , yychar, yyline, yytext());}
">"    					{return new Symbol(sym.Mayor_que , yychar, yyline, yytext());}
">="    				{return new Symbol(sym.Mayor_igual_que , yychar, yyline, yytext());}
"<"    					{return new Symbol(sym.Menor_que , yychar, yyline, yytext());}
"<="    				{return new Symbol(sym.Menor_igual_que , yychar, yyline, yytext());}
"=="    				{return new Symbol(sym.Igual_a , yychar, yyline, yytext());}
"&&"    				{return new Symbol(sym.And , yychar, yyline, yytext());}
"||"    				{return new Symbol(sym.Or , yychar, yyline, yytext());}
"|"    					{return new Symbol(sym.Guarda , yychar, yyline, yytext());}
"$"    					{return new Symbol(sym.Dolar , yychar, yyline, yytext());}
"\\\\"  				{return new Symbol(sym.Lambda , yychar, yyline, yytext());}
"("    					{return new Symbol(sym.Parentesis_a , yychar, yyline, yytext());}
")"    					{return new Symbol(sym.Parentesis_c , yychar, yyline, yytext());}
"{"    					{return new Symbol(sym.Llave_a , yychar, yyline, yytext());}
"}"    					{return new Symbol(sym.Llave_c , yychar, yyline, yytext());}
"["    					{return new Symbol(sym.Corchete_a , yychar, yyline, yytext());}
"]"    					{return new Symbol(sym.Corchete_c , yychar, yyline, yytext());}
"\\{[ \t]*" 			{ indentLevel = currentIndent; newLine = false; return new Symbol(sym.Llave_a , yychar, yyline, yytext()); }
"[ \t]*\\}" 			{ indentLevel = 0; newLine = true; return new Symbol(sym.Llave_c , yychar, yyline, yytext()); }
. 						{return new Symbol(sym.ERROR, yychar, yyline, yytext());}


