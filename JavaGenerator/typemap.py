VOID = ":void"
INT = ":int"
DOUBLE = ":double"
TEXT = ":text"
STRING = ":string"
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

P_JAVA_T = "javaType"
P_SER = "serializer"
P_DES = "deserializer"

type_map = {
    VOID: {
        P_JAVA_T: "void"
    },
    BOOL: {
        P_JAVA_T: "boolean",
        P_SER: "serializeBool",
        P_DES: "deserializeBool"
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
    INT_2D_ARR: {
        P_JAVA_T: "int[][]",
        P_SER: "serializeInt2DArray",
        P_DES: "deserializeInt2DArray"
    },
    INT_VEC: {
        P_JAVA_T: "List<Integer>",
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
    STRING_2D_VEC: {
        P_JAVA_T: "List<List<String>>",
        P_SER: "serializeString2DVector",
        P_DES: "deserializeString2DVector"
    },
    STRING_SET: {
        P_JAVA_T: "Set<String>",
        P_SER: "serializeStringSet",
        P_DES: "deserializeStringSet"
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
    },
    INT_BINARYTREE: {
        P_JAVA_T: "TreeNode",
        P_SER: "serializeIntBinaryTree",
        P_DES: "deserializeIntBinaryTree"
    }
}
