entity full_adder is port(
	A, B, Cin  : in bit;
	S, Cout  : out bit
);
end full_adder;
architecture full_adder_arch of full_adder is

begin
	S <= ((( not ( ( ( A and ( not ( B ) ) ) or ( ( not ( A ) ) and B ) ) ) ) and Cin) or (((( not ( A ) ) and B) or (( not ( B ) ) and A)) and ( not ( Cin ) )));
	Cout <= ((((( not ( A ) ) and B) or (( not ( B ) ) and A)) and Cin) or (A and B));
end full_adder_arch;


