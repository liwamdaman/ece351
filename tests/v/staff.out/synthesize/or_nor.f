condition1 <= ( ( not ( ( OR_NORx and ( not '0' ) ) or ( ( not OR_NORx ) and '0' ) ) ) and ( not ( ( OR_NORy and ( not '0' ) ) or ( ( not OR_NORy ) and '0' ) ) ) );
OR_NORF1 <= ( condition1 and ( '0' ) ) or ( ( not condition1 ) and ( '1' ) );
condition2 <= ( ( not ( ( OR_NORx and ( not '0' ) ) or ( ( not OR_NORx ) and '0' ) ) ) and ( not ( ( OR_NORy and ( not '0' ) ) or ( ( not OR_NORy ) and '0' ) ) ) );
OR_NORF2 <= ( condition2 and ( '1' ) ) or ( ( not condition2 ) and ( '0' ) );

