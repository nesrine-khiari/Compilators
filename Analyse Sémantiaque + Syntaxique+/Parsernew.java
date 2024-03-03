import java.beans.Expression;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.lang.model.util.Elements;

public class Parsernew {

        public String[] LRGS = {
                        "Program'->Program",
                        "Program->Dec MainBlock",
                        "Dec->Identifier : DataType Init ;",
                        "DataType->Map",
                        "DataType->List",
                        "DataType->String",
                        "DataType->Int",
                        "DataType->Boolean",
                        "Init->~ InitValue ",
                        "InitValue->{ Elements }",
                        "InitValue->{ KeyValuePairs }",
                        "InitValue->Value",
                        "Value->Identifier",
                        "Value->IntLiteral",
                        "Value->StringLiteral",
                        "Elements->Value",
                        "Elements->Value , Elements",
                        "KeyValuePairs->KeyValuePair",
                        "KeyValuePairs->KeyValuePair , KeyValuePairs",
                        "KeyValuePair->Key : Value",
                        "Key->StringLiteral",
                        "Key->Identifier",
                        "MainBlock->[ Instruction ]",
                        "Instruction->Input Identifier ;",
                        "Instruction->Output Expression ;",
                        "Instruction->Identifier ~ Expression ;",
                        "Instruction->If Expression Then Instruction Else Instruction",
                        "Instruction->While Expression Do Instruction",
                        "Expression->Value",
                        "Expression->Value Operator Value",
                        "Expression->( Expression ) Operator Expression",
                        "Operator->+",
                        "Operator->-",
                        "Operator->*",
                        "Operator->/",
                        "Operator->=",
                        "Operator->#",
                        "Operator-><",
                        "Operator->>",
                        "Operator->And",
                        "Operator->Or",
                        
        };

        String[][] tableSLR = {
                        { "etat/VT", "Identifier", ":", ";", "Map", "List", "String", "Int", "Boolean", "~", "{",
                                        "}", "IntLiteral", "StringLiteral", ",", "[", "]", "Input", "Output",
                                        "If", "Then", "Else", "While", "Do", "(", ")", "+", "-", "*", "/", "=", "#",
                                        "<", ">", "And'", "Or", "$", "Program'", "Program", "Dec", "DataType" , "Init", "InitValue", "Value", "Elements", "KeyValuePairs", "KeyValuePair", "Key", "MainBlock" , "Instruction", "Expression", "Operator"},
                                          
                                        { "0" , "s3", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "1", "2", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "1" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "acc", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "2" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s5", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "4", "err", "err", "err"},
                                        { "3" , "err", "s6", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "4" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r1", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "5" , "s10", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s8", "s9", "s11", "err", "err", "s12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "7", "err", "err"},
                                        { "6" , "err", "err", "err", "s14", "s15", "s16", "s17", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "13", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "7" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "8" , "s20", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "9" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s23", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "22", "err", "err", "err", "err", "err", "err", "21", "err"},
                                        { "10" , "err", "err", "err", "err", "err", "err", "err", "err", "s27", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "11" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s23", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "22", "err", "err", "err", "err", "err", "err", "28", "err"},
                                        { "12" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s23", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "22", "err", "err", "err", "err", "err", "err", "29", "err"},
                                        { "13" , "err", "err", "err", "err", "err", "err", "err", "err", "s31", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "30", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "14" , "err", "err", "err", "err", "err", "err", "err", "err", "r3", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "15" , "err", "err", "err", "err", "err", "err", "err", "err", "r4", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "16" , "err", "err", "err", "err", "err", "err", "err", "err", "r5", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "17" , "err", "err", "err", "err", "err", "err", "err", "err", "r6", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "18" , "err", "err", "err", "err", "err", "err", "err", "err", "r7", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "19" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r22", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "20" , "err", "err", "s32", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "21" , "err", "err", "s33", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "22" , "err", "err", "r28", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r28", "err", "err", "r28", "err", "r28", "s35", "s36", "s37", "s38", "s39", "s40", "s41", "s42", "s43", "s44", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "34"},
                                        { "23" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s23", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "22", "err", "err", "err", "err", "err", "err", "45", "err"},
                                        { "24" , "err", "err", "r12", "err", "err", "err", "err", "err", "err", "err", "r12", "err", "err", "r12", "err", "err", "err", "err", "err", "r12", "err", "err", "r12", "err", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "25" , "err", "err", "r13", "err", "err", "err", "err", "err", "err", "err", "r13", "err", "err", "r13", "err", "err", "err", "err", "err", "r13", "err", "err", "r13", "err", "r13", "r13", "r13", "r13", "r13", "r13", "r13", "r13", "r13", "r13", "r13", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "26" , "err", "err", "r14", "err", "err", "err", "err", "err", "err", "err", "r14", "err", "err", "r14", "err", "err", "err", "err", "err", "r14", "err", "err", "r14", "err", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "27" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s23", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "22", "err", "err", "err", "err", "err", "err", "46", "err"},
                                        { "28" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s47", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "29" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s48", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "30" , "err", "err", "s49", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "31" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "s51", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "50", "52", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "32" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r23", "err", "err", "err", "err", "r23", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "33" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r24", "err", "err", "err", "err", "r24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "34" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "53", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "35" , "r31", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r31", "r31", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r31", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "36" , "r32", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r32", "r32", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r32", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "37" , "r33", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r33", "r33", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r33", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "38" , "r34", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r34", "r34", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r34", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "39" , "r35", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r35", "r35", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r35", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "40" , "r36", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r36", "r36", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r36", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "41" , "r37", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r37", "r37", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r37", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "42" , "r38", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r38", "r38", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r38", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "43" , "r39", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r39", "r39", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r39", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "44" , "r40", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r40", "r40", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r40", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "45" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s54", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "46" , "err", "err", "s55", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "47" , "s10", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s8", "s9", "s11", "err", "err", "s12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "56", "err", "err"},
                                        { "48" , "s10", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s8", "s9", "s11", "err", "err", "s12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "57", "err", "err"},
                                        { "49" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r2", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "50" , "err", "err", "r8", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "51" , "s62", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s63", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "60", "58", "59", "61", "64", "err", "err", "err", "err"},
                                        { "52" , "err", "err", "r11", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "53" , "err", "err", "r29", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r29", "err", "err", "r29", "err", "r29", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "54" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s35", "s36", "s37", "s38", "s39", "s40", "s41", "s42", "s43", "s44", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "65"},
                                        { "55" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r25", "err", "err", "err", "err", "r25", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "56" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s66", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "57" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r27", "err", "err", "err", "err", "r27", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "58" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s67", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "59" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s68", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "60" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r15", "err", "err", "s69", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "61" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r17", "err", "err", "s70", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "62" , "err", "r21", "r12", "err", "err", "err", "err", "err", "err", "err", "r12", "err", "err", "r12", "err", "err", "err", "err", "err", "r12", "err", "err", "r12", "err", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "r12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "63" , "err", "r20", "r14", "err", "err", "err", "err", "err", "err", "err", "r14", "err", "err", "r14", "err", "err", "err", "err", "err", "r14", "err", "err", "r14", "err", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "r14", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "64" , "err", "s71", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "65" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s23", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "22", "err", "err", "err", "err", "err", "err", "72", "err"},
                                        { "66" , "s10", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s8", "s9", "s11", "err", "err", "s12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "73", "err", "err"},
                                        { "67" , "err", "err", "r9", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "68" , "err", "err", "r10", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "69" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "60", "74", "err", "err", "err", "err", "err", "err", "err"},
                                        { "70" , "s77", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s76", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "75", "61", "64", "err", "err", "err", "err"},
                                        { "71" , "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s25", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "78", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "72" , "err", "err", "r30", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r30", "err", "err", "r30", "err", "r30", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "73" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r26", "err", "err", "err", "err", "r26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "74" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r16", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "75" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r18", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "76" , "err", "r20", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "77" , "err", "r21", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},
                                        { "78" , "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r19", "err", "err", "r19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err"},              
        };

        public Stack<String> stackState = new Stack<>();
        // public Stack<String> sem = new Stack<String>();
        public Stack<String> analyse = new Stack<>();
        public HashMap<String, String> tabId = new HashMap<>();

        public Stack<String> stackSymbol = new Stack<>();
        String ch[] = { "Identifier", ":", "Map", "~","{",  "StringLiteral",":","IntLiteral",",","Identifier",":","StringLiteral", "}",  ";", "[", "If","(", "Identifier", "+", "Identifier",")","=","Identifier", "Then", "Output", "StringLiteral",";", "Else","Output","IntLiteral", ";", "]", "$"  };
        public String strInput;

        public String action = "";

        int index = 0;

        public Parsernew() {
        }

        public void analyzeSLnew() {

                action = "";
                index = 0;

                analyse.push("0");

                System.out.println("********pile     Entrée   Action			   ");
                this.AfficherSLR();

                while (index < ch.length)

                {
                        if (ch[index] == "Identifier") {
                        stackState.add("Identifier");
                        }
                        String s = analyse.peek();

                        String act = Action(s, ch[index]);
System.out.println(act);
                        if (Action(s, ch[index]).charAt(0) == 's') {

                                analyse.push(ch[index]);
                                analyse.push(Action(s, ch[index]).substring(1));

                                index++;
                                action = "shift";

                                AfficherSLR();
                        }
                        // Réduction
                        else if (Action(s, ch[index]).charAt(0) == 'r') {

                                String str = LRGS[Integer.parseInt(Action(s, ch[index]).substring(1))];
                                // Sementique(str);
                                // System.out.println("Semantique " + sem);
                                int pos = str.indexOf('>');

                                String tabparties[] = str.split("->");

                                String Partiegauche = tabparties[0];

                                String Partiedroite = tabparties[1];

                                String tabtoken[] = Partiedroite.split(" ");
                                int taillepile = tabtoken.length + tabtoken.length;
                                System.out.println(taillepile);
                                System.out.println(analyse.size());
                                for (int i = 0; i < taillepile; i++) {

                                        analyse.pop();

                                }
                                String sommetpile = analyse.peek();
                                analyse.push(Partiegauche);

                                analyse.push(Action(sommetpile, Partiegauche));

                                action = "reduce:" + str;
                                AfficherSLR();
                        }
                        // acceptation
                        else if (Action(s, ch[index]) == "acc") {
                                System.out.println("Analyse syntaxique validé : mot accepté");
                                // String resultatSemantique = sem.pop();
                                // if (resultatSemantique.equals("vide")) {
                                // sem.push("vide");
                                // System.out.println("Analyse sémantique validé ");
                                // System.out.println("Semantique " + sem);
                                // } else {
                                // System.out.println("Echec de l'analyse sémantique");

                                // }

                                break;
                        }

                        else
                        // erreur
                        {

                                System.out.println("texterreur" + Action(s, ch[index]) + s + ch[index] + index);
                                System.out.println("Echec d'analyse : mot n'est pas accepté");
                                // System.out.println("Semantique " + sem);
                                break;
                        }

                }

        }

        public String Action(String s, String a) {
                for (int i = 1; i < 80; i++)
                        if (tableSLR[i][0].equals(s))
                                for (int j = 1; j < 53; j++)
                                        if (tableSLR[0][j].equals(a))
                                                return tableSLR[i][j];
                return "err";
        }

        public void AfficherSLR() {
                // SLR

                String ss = "---";
                String ss1 = "---";
                int taillepile = analyse.size();
                int taillepilediv2 = taillepile / 2;
                for (int i = 0; i < taillepilediv2; i++)
                        ss = ss + "---";
                int tailleinput = ch.length;
                for (int i = 0; i < tailleinput; i++)
                        ss1 = ss1 + "---";

                strInput = "";
                for (int i = index; i < ch.length; i++)
                        strInput = strInput + ch[i];


                        System.out.print(analyse+"____");
                        System.out.print(strInput+"____");
                        System.out.print(action+"____");
                // System.out.printf("%s", analyse + "__");
                // System.out.printf("%s", strInput + "__");
                // System.out.printf("%s", action);
                System.out.println();
        }

        // public void AfficherSLRnew(String[] tt) {
        // // SLR

        // String ss = "-------";
        // String ss1 = "-------";
        // int taillepile = analyse.size();
        // int taillepilediv2 = taillepile / 2;
        // for (int i = 0; i < taillepilediv2; i++)
        // ss = ss + "-------";
        // int tailleinput = tt.length;
        // for (int i = 0; i < tailleinput; i++)
        // ss1 = ss1 + "-------";

        // strInput = "";
        // for (int i = index; i < tt.length; i++)
        // strInput = strInput + tt[i];

        // System.out.printf("%s", analyse + ss1);
        // System.out.printf("%s", strInput + ss);
        // System.out.printf("%s", action);
        // System.out.println();
        // }

        public void ouput() {

                System.out.println("**********Tableau SLR¨********");

                for (int i = 0; i < 80; i++) {
                        for (int j = 0; j < 53; j++) {
                                System.out.printf("%6s", tableSLR[i][j] + " ");
                        }
                        System.out.println();
                }
                System.out.println("**********Fin tableau SLR********");

        }

        // void Sementique(String regle) {
        // String temp1 = "";
        // String temp2 = "";
        // String temp3 = "";

        // if (regle.equals("S->{ Declaration D Corps I }")) {
        // temp1 = sem.pop(); // type de I
        // temp2 = sem.pop(); // type D
        // if (temp1.equals("vide") && temp2.equals("vide")) {
        // sem.push("vide");
        // } else {
        // sem.push("erreur");
        // }
        // } else if (regle.equals("D->id : T D")) {
        // temp3 = sem.pop();
        // temp2 = sem.pop();

        // if (temp3.equals("vide")) {

        // sem.push("vide");
        // }

        // else {
        // sem.push("erreur");
        // }
        // ;
        // }

        // else if (regle.equals("D->FinDec")) {
        // sem.push("vide");

        // }

        // else if (regle.equals("I->lire E I")) {
        // temp1 = sem.pop();
        // temp2 = sem.pop();
        // sem.push(temp1);
        // } else if (regle.equals("I->ecrire E I")) {
        // temp1 = sem.pop();
        // temp2 = sem.pop();
        // sem.push(temp1);
        // }

        // else if (regle.equals("I->id ~~ E I")) {
        // temp1 = sem.pop();
        // temp2 = sem.pop();
        // temp3 = sem.pop();
        // if (temp1.equals("vide")) {
        // String type1 = tabId.get(temp2);
        // String type2 = tabId.get(temp3);
        // if (type1.equals(type2)) {
        // sem.push("vide");
        // } else {
        // sem.push("erreur");
        // }
        // } else {
        // sem.push("erreur");
        // }
        // }

        // else if (regle.equals("I->si E alors I ")) {
        // temp1 = sem.pop();
        // temp2 = sem.pop();
        // if (temp2.equals("bolean") && temp1.equals("vide")) {
        // sem.push("vide");
        // } else {
        // sem.push("erreur");
        // }
        // }

        // else if (regle.equals("I->tantque E faire I")) {
        // temp1 = sem.pop();
        // temp2 = sem.pop();
        // if (temp2.equals("bolean") && temp1.equals("vide")) {
        // sem.push("vide");
        // } else {
        // sem.push("erreur");
        // }
        // }

        // else if (regle.equals("I->FinCorps")) {
        // sem.push("vide");
        // }

        // else if (regle.equals("E->id")) {
        // sem.push("vide");
        // }

        // else if (regle.equals("T->char")) {
        // sem.push("char");
        // }

        // else if (regle.equals("T->entier")) {
        // sem.push("entier");
        // } else if (regle.equals("T->bolean")) {
        // sem.push("bolean");
        // }

        // else if (regle.equals("E->id OPP id")) {
        // temp1 = sem.pop();
        // temp2 = sem.pop();
        // String type1 = tabId.get(temp1);
        // String type2 = tabId.get(temp2);
        // if (type1.equals(type2)) {
        // sem.push(type1);
        // } else {
        // sem.push("erreur");
        // }

        // } else {
        // sem.push("erreur");
        // }

        // }

}
