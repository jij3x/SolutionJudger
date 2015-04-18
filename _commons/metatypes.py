VOID = ":void"
INT = ":int"
DOUBLE = ":double"
TEXT = ":text"
STRING = ":string"
MUTABLE_STRING = ":mutable_string"
INT_ARR = "+array<:int>"
INT_2D_ARR = "+array<+array<:int>>"
INT_VEC = "+vector<:int>"
INT_SLLIST = "+sllist<:int>"
INT_UDGRAPH = "+udgraph<:int>"
CHAR_ARR = "+array<:char>"
CHAR_2D_ARR = "+array<+array<:char>>"
BOOL = ":bool"
STRING_ARR = "+array<:string>"
STRING_VEC = "+vector<:string>"
STRING_2D_VEC = "+vector<+vector<:string>>"
STRING_SET = "+set<:string>"
INT_BINARYTREE = "+binarytree<:int>"

NOCHANGE = "nochange"
COL_CHANGEABLE = "colChangeable"

P_JAVA_T = "javaType"
P_C_T = "cType"
P_CPP_T = "cplusplusType"
P_CS_T = "csharpType"

P_SER = "serializer"
P_DES = "deserializer"
P_OFLTR = "outputFilter"

type_map = {
    VOID: {
        P_JAVA_T: "void"
    },
    BOOL: {
        P_JAVA_T: "boolean",
        P_SER: "serializeBool",
        P_DES: "deserializeBool",
        P_OFLTR: "noFilter"
    },
    INT: {
        P_JAVA_T: "int",
        P_SER: "serializeInt",
        P_DES: "deserializeInt",
        P_OFLTR: "noFilter"
    },
    DOUBLE: {
        P_JAVA_T: "double",
        P_SER: "serializeDouble",
        P_DES: "deserializeDouble",
        P_OFLTR: "noFilter"
    },
    TEXT: {
        P_JAVA_T: "String",
        P_SER: "serializeText",
        P_DES: "deserializeText",
        P_OFLTR: "noFilter"
    },
    MUTABLE_STRING: {
        P_JAVA_T: "StringBuilder",
        P_C_T: "char *",
        P_CPP_T: "string&",
        P_CS_T: "ref string",
        P_SER: "serializeString",
        P_DES: "deserializeString",
        P_OFLTR: "noFilter"
    },
    STRING: {
        P_JAVA_T: "String",
        P_SER: "serializeString",
        P_DES: "deserializeString",
        P_OFLTR: "noFilter"
    },
    INT_ARR: {
        P_JAVA_T: "int[]",
        P_SER: "serializeIntArray",
        P_DES: "deserializeIntArray",
        P_OFLTR: "noFilter"
    },
    INT_2D_ARR: {
        P_JAVA_T: "int[][]",
        P_SER: "serializeInt2DArray",
        P_DES: "deserializeInt2DArray",
        P_OFLTR: "noFilter"
    },
    INT_VEC: {
        P_JAVA_T: "List<Integer>",
        P_SER: "serializeIntVector",
        P_DES: "deserializeIntVector",
        P_OFLTR: "noFilter"
    },
    CHAR_ARR: {
        P_JAVA_T: "char[]",
        P_SER: "serializeCharArray",
        P_DES: "deserializeCharArray",
        P_OFLTR: "noFilter"
    },
    CHAR_2D_ARR: {
        P_JAVA_T: "char[][]",
        P_SER: "serializeChar2DArray",
        P_DES: "deserializeChar2DArray",
        P_OFLTR: "noFilter"
    },
    STRING_ARR: {
        P_JAVA_T: "String[]",
        P_SER: "serializeStringArray",
        P_DES: "deserializeStringArray"
    },
    STRING_VEC: {
        P_JAVA_T: "List<String>",
        P_SER: "serializeStringVector",
        P_DES: "deserializeStringVector",
        P_OFLTR: "noFilter"
    },
    STRING_2D_VEC: {
        P_JAVA_T: "List<List<String>>",
        P_SER: "serializeString2DVector",
        P_DES: "deserializeString2DVector",
        P_OFLTR: "noFilter"
    },
    STRING_SET: {
        P_JAVA_T: "Set<String>",
        P_SER: "serializeStringSet",
        P_DES: "deserializeStringSet",
        P_OFLTR: "noFilter"
    },
    INT_SLLIST: {
        P_JAVA_T: "ListNode",
        P_SER: "serializeIntSLList",
        P_DES: "deserializeIntSLList",
        P_OFLTR: "noFilter"
    },
    INT_UDGRAPH: {
        P_JAVA_T: "UndirectedGraphNode",
        P_SER: "serializeIntUDGraph",
        P_DES: "deserializeIntUDGraph",
        P_OFLTR: "udgraphFilter"
    },
    INT_BINARYTREE: {
        P_JAVA_T: "TreeNode",
        P_SER: "serializeIntBinaryTree",
        P_DES: "deserializeIntBinaryTree",
        P_OFLTR: "noFilter"
    }
}
