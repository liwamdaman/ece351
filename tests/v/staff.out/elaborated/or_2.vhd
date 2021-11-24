entity OR_gate_2 is port(
	x, y  : in bit;
	F  : out bit
);
end OR_gate_2;
architecture OR_gate_arch of OR_gate_2 is

begin
	F <= ( x or y );
end OR_gate_arch;


