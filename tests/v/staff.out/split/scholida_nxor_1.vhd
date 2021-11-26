entity XNOR_test is port(
	a, b, x, y  : in bit;
	F, Z  : out bit
);
end XNOR_test;
architecture XNOR_test_arch of XNOR_test is

begin
	process ( a, b, x, y  ) 
		begin
			F <= ( not ( ( ( x and ( not ( y ) ) ) or ( ( not ( x ) ) and y ) ) ) );
			Z <= ( not ( ( ( ( x and y ) and ( not ( ( not ( ( ( a and ( not ( b ) ) ) or ( ( not ( a ) ) and b ) ) ) ) ) ) ) or ( ( not ( ( x and y ) ) ) and ( not ( ( ( a and ( not ( b ) ) ) or ( ( not ( a ) ) and b ) ) ) ) ) ) ) );
		end process;
end XNOR_test_arch;


