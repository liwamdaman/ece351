entity OR_gate_2 is port(
	x, y  : in bit;
	F  : out bit
);
end OR_gate_2;
architecture OR_gate_arch of OR_gate_2 is

begin
	F <= ( x or y );
end OR_gate_arch;

entity four_port_OR_gate_2 is port(
	a, b, c, d  : in bit;
	result  : out bit
);
end four_port_OR_gate_2;
architecture four_port_structure of four_port_OR_gate_2 is
	signal e, f  : bit;
begin
	result <= ( e or f );
	e <= ( a or b );
	f <= ( c or d );
end four_port_structure;

entity eight_port_OR_gate_2 is port(
	x0, x1, x2, x3, x4, x5, x6, x7  : in bit;
	y  : out bit
);
end eight_port_OR_gate_2;
architecture eight_port_structure of eight_port_OR_gate_2 is
	signal result1, result2, comp3_e, comp3_f, comp4_e, comp4_f  : bit;
begin
	y <= ( result1 or result2 );
	result1 <= ( comp3_e or comp3_f );
	comp3_e <= ( x0 or x1 );
	comp3_f <= ( x2 or x3 );
	result2 <= ( comp4_e or comp4_f );
	comp4_e <= ( x4 or x5 );
	comp4_f <= ( x6 or x7 );
end eight_port_structure;


