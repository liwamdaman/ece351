condition2 <= ( not ( ( ( IfIf2S and ( not ( '0' ) ) ) or ( ( not ( IfIf2S ) ) and '0' ) ) ) );
IfIf2O1 <= ( ( condition2 and IfIf2I2 ) or ( ( not ( condition2 ) ) and IfIf2I3 ) );
condition1 <= ( not ( ( ( IfIf2S and ( not ( '0' ) ) ) or ( ( not ( IfIf2S ) ) and '0' ) ) ) );
IfIf2O0 <= ( ( condition1 and IfIf2I0 ) or ( ( not ( condition1 ) ) and IfIf2I1 ) );
condition3 <= ( not ( ( ( IfIf2S and ( not ( '1' ) ) ) or ( ( not ( IfIf2S ) ) and '1' ) ) ) );
IfIf2O2 <= ( ( condition3 and IfIf2I4 ) or ( ( not ( condition3 ) ) and IfIf2I5 ) );
condition4 <= ( not ( ( ( IfIf2S and ( not ( '1' ) ) ) or ( ( not ( IfIf2S ) ) and '1' ) ) ) );
IfIf2O3 <= ( ( condition4 and IfIf2I5 ) or ( ( not ( condition4 ) ) and IfIf2I3 ) );
