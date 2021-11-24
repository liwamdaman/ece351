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

entity half_adder is port(
	A, B  : in bit;
	S, C  : out bit
);
end half_adder;
architecture half_adder_arch of half_adder is

begin
	S <= ( not ( ( A or B ) ) );
	C <= ( A and B );
end half_adder_arch;

entity four_bit_ripple_carry_adder is port(
	a0, b0, a1, b1, a2, b2, a3, b3, Cin  : in bit;
	sum0, sum1, sum2, sum3, Cout, V  : out bit
);
end four_bit_ripple_carry_adder;
architecture fouradder_structure of four_bit_ripple_carry_adder is
	signal c1, c2, c3, c4  : bit;
begin
	V <= ( ( c3 and ( not ( c4 ) ) ) or ( ( not ( c3 ) ) and c4 ) );
	Cout <= c4;
	sum0 <= ( ( ( ( a0 and ( not ( b0 ) ) ) or ( ( not ( a0 ) ) and b0 ) ) and ( not ( Cin ) ) ) or ( ( not ( ( ( a0 and ( not ( b0 ) ) ) or ( ( not ( a0 ) ) and b0 ) ) ) ) and Cin ) );
	c1 <= ( ( ( ( a0 and ( not ( b0 ) ) ) or ( ( not ( a0 ) ) and b0 ) ) and Cin ) or ( a0 and b0 ) );
	sum1 <= ( ( ( ( a1 and ( not ( b1 ) ) ) or ( ( not ( a1 ) ) and b1 ) ) and ( not ( c1 ) ) ) or ( ( not ( ( ( a1 and ( not ( b1 ) ) ) or ( ( not ( a1 ) ) and b1 ) ) ) ) and c1 ) );
	c2 <= ( ( ( ( a1 and ( not ( b1 ) ) ) or ( ( not ( a1 ) ) and b1 ) ) and c1 ) or ( a1 and b1 ) );
	sum2 <= ( ( ( ( a2 and ( not ( b2 ) ) ) or ( ( not ( a2 ) ) and b2 ) ) and ( not ( c2 ) ) ) or ( ( not ( ( ( a2 and ( not ( b2 ) ) ) or ( ( not ( a2 ) ) and b2 ) ) ) ) and c2 ) );
	c3 <= ( ( ( ( a2 and ( not ( b2 ) ) ) or ( ( not ( a2 ) ) and b2 ) ) and c2 ) or ( a2 and b2 ) );
	sum3 <= ( ( ( ( a3 and ( not ( b3 ) ) ) or ( ( not ( a3 ) ) and b3 ) ) and ( not ( c3 ) ) ) or ( ( not ( ( ( a3 and ( not ( b3 ) ) ) or ( ( not ( a3 ) ) and b3 ) ) ) ) and c3 ) );
	c4 <= ( ( ( ( a3 and ( not ( b3 ) ) ) or ( ( not ( a3 ) ) and b3 ) ) and c3 ) or ( a3 and b3 ) );
end fouradder_structure;


