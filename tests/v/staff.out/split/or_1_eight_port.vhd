entity OR_gate is port(
	x, y  : in bit;
	z  : out bit
);
end OR_gate;
architecture OR_gate_arch of OR_gate is

begin
	process ( x, y  ) 
		begin
			if ( ( ( not ( ( ( x and ( not ( '0' ) ) ) or ( ( not ( x ) ) and '0' ) ) ) ) and ( not ( ( ( y and ( not ( '0' ) ) ) or ( ( not ( y ) ) and '0' ) ) ) ) ) ) then
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
	process ( e, f  ) 
		begin
			result <= ( e or f );
		end process;
	process ( a, b  ) 
		begin
			if ( ( ( not ( ( ( a and ( not ( '0' ) ) ) or ( ( not ( a ) ) and '0' ) ) ) ) and ( not ( ( ( b and ( not ( '0' ) ) ) or ( ( not ( b ) ) and '0' ) ) ) ) ) ) then
				e <= '0';
			else
				e <= '1';
			end if;
		end process;
	process ( c, d  ) 
		begin
			if ( ( ( not ( ( ( c and ( not ( '0' ) ) ) or ( ( not ( c ) ) and '0' ) ) ) ) and ( not ( ( ( d and ( not ( '0' ) ) ) or ( ( not ( d ) ) and '0' ) ) ) ) ) ) then
				f <= '0';
			else
				f <= '1';
			end if;
		end process;
end four_port_structure;

entity eight_port_OR_gate is port(
	x0, x1, x2, x3, x4, x5, x6, x7  : in bit;
	y  : out bit
);
end eight_port_OR_gate;
architecture eight_port_structure of eight_port_OR_gate is
	signal result1, result2, comp3_e, comp3_f, comp4_e, comp4_f  : bit;
begin
	process ( result1, result2  ) 
		begin
			y <= ( result1 or result2 );
		end process;
	process ( comp3_e, comp3_f  ) 
		begin
			result1 <= ( comp3_e or comp3_f );
		end process;
	process ( x0, x1  ) 
		begin
			if ( ( ( not ( ( ( x0 and ( not ( '0' ) ) ) or ( ( not ( x0 ) ) and '0' ) ) ) ) and ( not ( ( ( x1 and ( not ( '0' ) ) ) or ( ( not ( x1 ) ) and '0' ) ) ) ) ) ) then
				comp3_e <= '0';
			else
				comp3_e <= '1';
			end if;
		end process;
	process ( x2, x3  ) 
		begin
			if ( ( ( not ( ( ( x2 and ( not ( '0' ) ) ) or ( ( not ( x2 ) ) and '0' ) ) ) ) and ( not ( ( ( x3 and ( not ( '0' ) ) ) or ( ( not ( x3 ) ) and '0' ) ) ) ) ) ) then
				comp3_f <= '0';
			else
				comp3_f <= '1';
			end if;
		end process;
	process ( comp4_e, comp4_f  ) 
		begin
			result2 <= ( comp4_e or comp4_f );
		end process;
	process ( x4, x5  ) 
		begin
			if ( ( ( not ( ( ( x4 and ( not ( '0' ) ) ) or ( ( not ( x4 ) ) and '0' ) ) ) ) and ( not ( ( ( x5 and ( not ( '0' ) ) ) or ( ( not ( x5 ) ) and '0' ) ) ) ) ) ) then
				comp4_e <= '0';
			else
				comp4_e <= '1';
			end if;
		end process;
	process ( x6, x7  ) 
		begin
			if ( ( ( not ( ( ( x6 and ( not ( '0' ) ) ) or ( ( not ( x6 ) ) and '0' ) ) ) ) and ( not ( ( ( x7 and ( not ( '0' ) ) ) or ( ( not ( x7 ) ) and '0' ) ) ) ) ) ) then
				comp4_f <= '0';
			else
				comp4_f <= '1';
			end if;
		end process;
end eight_port_structure;


