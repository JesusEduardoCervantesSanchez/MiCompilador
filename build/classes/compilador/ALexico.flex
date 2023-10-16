package compilador;
import static compilador.Tokens.*;

%%

%class Lexer
%type Tokens
%line
%column
%{
   public String lexeme;
   DatosToken t = new DatosToken();
%}

terminadorDeLinea = \r|\n|\r\n
entradaDeCaracter = [^\r\n]
espacioEnBlanco = {terminadorDeLinea} | [ \t\f]

letra = [a-zA-ZñÑ_$á-źÁ-Ź]
digito = [0-9]
espacio = [ ]+
flotante = (-?[1-9][0-9]*\.[0-9]*[1-9])|(0\.0)|(-?[1-9][0-9]*\.0)|(-?[1-9][0-9]*\.[0-9]*[1-9][eE][-+][1-9][0-9]*)|(-?0\.[0-9]*[1-9][eE][-+][1-9][0-9]*)
entero = (0|-?[1-9][0-9]*)
num = {entero} | {flotante}

id = {letra}({letra}|{digito})*
%%

{espacioEnBlanco} { /* Ignorar */ }

"int" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return entero;}
"float" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return flotante;}
"char" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return caracter;}

{id} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return id;}

{num} {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return num;}

"=" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return Igual;}

"+" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return Suma;}
"-" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return Resta;}
"/" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return Division;}
"*" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return Producto;}

"(" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return AbreParentesis;}
")" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return CierraParentesis;}

"," {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return Coma;}
";" {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return PuntoComa;}

 . {t.numeroLinea=yyline; t.lexema=yytext(); lexeme=yytext(); return ERROR;}