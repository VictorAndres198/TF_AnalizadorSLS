package tf.analizador;

public enum Tokens {
    Let,
    In,
    Where,
    If,
    Then,
    Else,
    Case,
    Of,
    Data,
    Type,
    Int,
    Double,
    Char,
    Bool,
    Igual,
    Suma,
    Resta,
    Multiplicacion,
    Division,
    Modulo,
    Potencia,
    Identificador,
    Numero,
    ERROR,
    Linea, 
    /*pendiente agregar:
    ->
    ::
    logic operators(== , <, >, <=, >=)
    bool operators(&& ||)
    guarda ( | ) 
    para input de cadenas ($)
    brackets ( (, ), [, ], {, }  )
    para funcion lambda (\)
    coma(,)
    comentario(-- sdsdsd)
    */
}