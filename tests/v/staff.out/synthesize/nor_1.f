condition1 <= ( ( not ( ( NOR_gatex and ( not '0' ) ) or ( ( not NOR_gatex ) and '0' ) ) ) and ( not ( ( NOR_gatey and ( not '0' ) ) or ( ( not NOR_gatey ) and '0' ) ) ) );
NOR_gateF <= ( condition1 and ( '1' ) ) or ( ( not condition1 ) and ( '0' ) );

