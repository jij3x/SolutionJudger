VOID = ":void"
INT = ":int"
DOUBLE = ":double"
TEXT = ":text"
STRING = ":string"
INT_ARR = "+array<:int>"
INT_VEC = "+vector<:int>"
INT_SLLIST = "+sllist<:int>"
INT_UDGRAPH = "+udgraph<:int>"
CHAR_ARR = "+array<:char>"
CHAR_2D_ARR = "+array<+array<:char>>"
STRING_ARR = "+array<:string>"
STRING_VEC = "+vector<:string>"

P_JAVA_T = "javaType"
P_SER = "serializer"
P_DES = "deserializer"

type_map = {
    VOID: {
        P_JAVA_T: "void"
    },
    INT: {
        P_JAVA_T: "int",
        P_SER: "serializeInt",
        P_DES: "deserializeInt"
    },
    DOUBLE: {
        P_JAVA_T: "double",
        P_SER: "serializeDouble",
        P_DES: "deserializeDouble"
    },
    TEXT: {
        P_JAVA_T: "String",
        P_SER: "serializeText",
        P_DES: "deserializeText"
    },
    STRING: {
        P_JAVA_T: "String",
        P_SER: "serializeString",
        P_DES: "deserializeString"
    },
    INT_ARR: {
        P_JAVA_T: "int[]",
        P_SER: "serializeIntArray",
        P_DES: "deserializeIntArray"
    },
    INT_VEC: {
        P_JAVA_T: "ArrayList<Integer>",
        P_SER: "serializeIntVector",
        P_DES: "deserializeIntVector"
    },
    CHAR_ARR: {
        P_JAVA_T: "char[]",
        P_SER: "serializeCharArray",
        P_DES: "deserializeCharArray"
    },
    CHAR_2D_ARR: {
        P_JAVA_T: "char[][]",
        P_SER: "serializeChar2DArray",
        P_DES: "deserializeChar2DArray"
    },
    STRING_ARR: {
        P_JAVA_T: "String[]",
        P_SER: "serializeStringArray",
        P_DES: "deserializeStringArray"
    },
    STRING_VEC: {
        P_JAVA_T: "List<String>",
        P_SER: "serializeStringVector",
        P_DES: "deserializeStringVector"
    },
    INT_SLLIST: {
        P_JAVA_T: "ListNode",
        P_SER: "serializeIntSLList",
        P_DES: "deserializeIntSLList"
    },
    INT_UDGRAPH: {
        P_JAVA_T: "UndirectedGraphNode",
        P_SER: "serializeIntUDGraph",
        P_DES: "deserializeIntUDGraph"
    }
}
