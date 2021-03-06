RAW = ":raw"
VOID = ":void"
INT = ":int"
UINT = ":uint"
DOUBLE = ":double"
DOUBLE_VEC = "+vector<:double>"
TEXT = ":text"
STRING = ":string"
MUTABLE_STRING = ":mutable_string"
INT_ARR = "+array<:int>"
INT_2D_ARR = "+array<+array<:int>>"
INT_VEC = "+vector<:int>"
INT_2D_VEC = "+vector<+vector<:int>>"
INT_SLLIST = "+sllist<:int>"
INT_SLLIST_VEC = "+vector<+sllist<:int>>"
INT_SLRLIST = "+slrlist<:int>"
INT_UDGRAPH = "+udgraph<:int>"
CHAR_ARR = "+array<:char>"
CHAR_2D_ARR = "+array<+array<:char>>"
BOOL = ":bool"
BOOL_VEC = "+vector<:bool>"
STRING_ARR = "+array<:string>"
STRING_VEC = "+vector<:string>"
STRING_2D_VEC = "+vector<+vector<:string>>"
STRING_SET = "+set<:string>"
INT_BINARYTREE = "+binarytree<:int>"
INT_BINARYTREE_VEC = "+vector<+binarytree<:int>>"
INT_LBINARYTREE = "+lbinarytree<:int>"
INT_POINT = ":ipoint"
INT_POINT_ARR = "+array<:ipoint>"
INT_INTERVAL = ":iinterval"
INT_INTERVAL_VEC = "+vector<:iinterval>"

NOCHANGE = "nochange"

P_JAVA_T = "javaType"
P_C_T = "cType"
P_CPP_T = "cppType"
P_CS_T = "csType"

P_SER = "serializer"
P_DES = "deserializer"
P_OFLTR = "outputFilter"

type_map = {
    RAW: {
        P_JAVA_T: "StreamTokenizer",
        P_DES: "getStreamToken"
    },
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
    UINT: {
        P_JAVA_T: "int",
        P_SER: "serializeUnsignedInt",
        P_DES: "deserializeUnsignedInt"
    },
    DOUBLE: {
        P_JAVA_T: "double",
        P_SER: "serializeDouble",
        P_DES: "deserializeDouble"
    },
    DOUBLE_VEC: {
        P_JAVA_T: "List<Double>",
        P_SER: "serializeDoubleVector",
        P_DES: "deserializeDoubleVector"
    },
    TEXT: {
        P_JAVA_T: "String",
        P_SER: "serializeText",
        P_DES: "deserializeText"
    },
    MUTABLE_STRING: {
        P_JAVA_T: "StringBuilder",
        P_C_T: "char *",
        P_CPP_T: "string&",
        P_CS_T: "ref string",
        P_SER: "serializeMutableString",
        P_DES: "deserializeMutableString"
    },
    STRING: {
        P_JAVA_T: "String",
        P_SER: "serializeString",
        P_DES: "deserializeString"
    },
    BOOL_VEC: {
        P_JAVA_T: "List<Boolean>",
        P_SER: "serializeBoolVector",
        P_DES: "deserializeBoolVector"
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
    INT_2D_VEC: {
        P_JAVA_T: "List<List<Integer>>",
        P_SER: "serializeInt2DVector",
        P_DES: "deserializeInt2DVector"
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
        P_DES: "deserializeIntSLList",
        P_OFLTR: "sllist_filter"
    },
    INT_SLLIST_VEC: {
        P_JAVA_T: "List<ListNode>",
        P_SER: "serializeIntSLListVector",
        P_DES: "deserializeIntSLListVector",
        P_OFLTR: "sllistvector_filter"
    },
    INT_SLRLIST: {
        P_JAVA_T: "RandomListNode",
        P_SER: "serializeIntSLRList",
        P_DES: "deserializeIntSLRList",
        P_OFLTR: "slrlist_filter"
    },
    INT_UDGRAPH: {
        P_JAVA_T: "UndirectedGraphNode",
        P_SER: "serializeIntUDGraph",
        P_DES: "deserializeIntUDGraph",
        P_OFLTR: "udgraph_filter"
    },
    INT_BINARYTREE: {
        P_JAVA_T: "TreeNode",
        P_SER: "serializeIntBinaryTree",
        P_DES: "deserializeIntBinaryTree",
        P_OFLTR: "binarytree_filter"
    },
    INT_BINARYTREE_VEC: {
        P_JAVA_T: "List<TreeNode>",
        P_SER: "serializeIntBinaryTreeVector",
        P_DES: "deserializeIntBinaryTreeVector",
        P_OFLTR: "binarytreevector_filter"
    },
    INT_LBINARYTREE: {
        P_JAVA_T: "TreeLinkNode",
        P_SER: "serializeIntLinkedBinaryTree",
        P_DES: "deserializeIntLinkedBinaryTree",
        P_OFLTR: "binarytree_filter"
    },
    INT_POINT: {
        P_JAVA_T: "Point",
        P_SER: "serializeIPoint",
        P_DES: "deserializeIPoint",
        P_OFLTR: "pair_filter"
    },
    INT_POINT_ARR: {
        P_JAVA_T: "Point[]",
        P_SER: "serializeIPointArray",
        P_DES: "deserializeIPointArray",
        P_OFLTR: "pairvector_filter"
    },
    INT_INTERVAL: {
        P_JAVA_T: "Interval",
        P_SER: "serializeIInterval",
        P_DES: "deserializeIInterval",
        P_OFLTR: "pair_filter"
    },
    INT_INTERVAL_VEC: {
        P_JAVA_T: "List<Interval>",
        P_SER: "serializeIIntervalVector",
        P_DES: "deserializeIIntervalVector",
        P_OFLTR: "pairvector_filter"
    }
}
