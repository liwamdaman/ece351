condition2 <= ( not ( ( ( IfIfS and ( not ( '1' ) ) ) or ( ( not ( IfIfS ) ) and '1' ) ) ) );
IfIfO1 <= ( ( condition2 and IfIfI2 ) or ( ( not ( condition2 ) ) and IfIfI3 ) );
condition1 <= ( not ( ( ( IfIfS and ( not ( '0' ) ) ) or ( ( not ( IfIfS ) ) and '0' ) ) ) );
IfIfO0 <= ( ( condition1 and IfIfI0 ) or ( ( not ( condition1 ) ) and IfIfI1 ) );
