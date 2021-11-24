XNOR_testF <= ( not ( ( XNOR_testx and ( not XNOR_testy ) ) or ( ( not XNOR_testx ) and XNOR_testy ) ) );
XNOR_testZ <= ( not ( ( ( XNOR_testx and XNOR_testy ) and ( not ( not ( ( XNOR_testa and ( not XNOR_testb ) ) or ( ( not XNOR_testa ) and XNOR_testb ) ) ) ) ) or ( ( not ( XNOR_testx and XNOR_testy ) ) and ( not ( ( XNOR_testa and ( not XNOR_testb ) ) or ( ( not XNOR_testa ) and XNOR_testb ) ) ) ) ) );

