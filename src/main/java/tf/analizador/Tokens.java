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
    Linea,
    Flecha_D,  //->
    Flecha_I, // <-
    DosPuntos_Doble, //::
    Coma,
    Mayor_que,
    Mayor_igual_que,
    Menor_que,
    Menor_igual_que,
    Igual_a, // (==)
    And, // (&&)
    Or, //  (||)
    Guarda, // ( | )
    Dolar, // $
    Lambda, // (\)
    Parentesis_a,
    Parentesis_c,
    Llave_a,
    Llave_c,
    Corchete_a,
    Corchete_c,
    ERROR
}