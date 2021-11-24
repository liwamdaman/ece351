entity half_adder is port(
	A, B  : in bit;
	S, C  : out bit
);
end half_adder;
architecture half_adder_arch of half_adder is

begin
	S <= ( not ( ( A or B ) ) );
	C <= (A and B);
end half_adder_arch;


