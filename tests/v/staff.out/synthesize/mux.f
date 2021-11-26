condition1 <= ( not ( ( MuxS and ( not '0' ) ) or ( ( not MuxS ) and '0' ) ) );
MuxO0 <= ( condition1 and ( MuxI0 ) ) or ( ( not condition1 ) and ( MuxI1 ) );
condition2 <= ( not ( ( MuxS and ( not '0' ) ) or ( ( not MuxS ) and '0' ) ) );
MuxO1 <= ( condition2 and ( MuxI2 ) ) or ( ( not condition2 ) and ( MuxI3 ) );

