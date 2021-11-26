entity OR_gate is port(
	x, y  : in bit;
	z  : out bit
);
end OR_gate;
architecture OR_gate_arch of OR_gate is

begin
	process ( x, y  ) 
		begin
			if ( (( not ( ( ( x and ( not ( '0' ) ) ) or ( ( not ( x ) ) and '0' ) ) ) ) and ( not ( ( ( y and ( not ( '0' ) ) ) or ( ( not ( y ) ) and '0' ) ) ) )) ) then
				z <= '0';
			else
				z <= '1';
			end if;
		end process;
end OR_gate_arch;

entity four_port_OR_gate is port(
	a, b, c, d  : in bit;
	result  : out bit
);
end four_port_OR_gate;
architecture four_port_structure of four_port_OR_gate is
	signal e, f  : bit;
begin
	OR1 : entity work.OR_gate port map( a, b, e );
	OR2 : entity work.OR_gate port map( c, d, f );
	process ( e, f  ) 
		begin
			result <= (e or f);
		end process;
end four_port_structure;

entity eight_port_OR_gate is port(
	x0, x1, x2, x3, x4, x5, x6, x7  : in bit;
	y  : out bit
);
end eight_port_OR_gate;
architecture eight_port_structure of eight_port_OR_gate is
	signal result1, result2  : bit;
begin
	OR1 : entity work.four_port_OR_gate port map( x0, x1, x2, x3, result1 );
	OR2 : entity work.four_port_OR_gate port map( x4, x5, x6, x7, result2 );
	process ( result1, result2  ) 
		begin
			y <= (result1 or result2);
		end process;
end eight_port_structure;


