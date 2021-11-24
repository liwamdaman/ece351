condition1 <= ( ( not ( ( OR_gatex and ( not '0' ) ) or ( ( not OR_gatex ) and '0' ) ) ) and ( not ( ( OR_gatey and ( not '0' ) ) or ( ( not OR_gatey ) and '0' ) ) ) );
OR_gatez <= ( condition1 and ( '0' ) ) or ( ( not condition1 ) and ( '1' ) );

