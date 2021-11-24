entity full_adder is port(
	A, B, Cin  : in bit;
	S, Cout  : out bit
);
end full_adder;
architecture full_adder_arch of full_adder is

begin
	S <= ( ( ( ( A and ( not ( B ) ) ) or ( ( not ( A ) ) and B ) ) and ( not ( Cin ) ) ) or ( ( not ( ( ( A and ( not ( B ) ) ) or ( ( not ( A ) ) and B ) ) ) ) and Cin ) );
	Cout <= ( ( ( ( A and ( not ( B ) ) ) or ( ( not ( A ) ) and B ) ) and Cin ) or ( A and B ) );
end full_adder_arch;


