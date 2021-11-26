entity NOR_gate is port(
	x, y  : in bit;
	F  : out bit
);
end NOR_gate;
architecture NOR_gate_arch of NOR_gate is

begin
	process ( x, y  ) 
		begin
			if ( ( ( not ( ( ( x and ( not ( '0' ) ) ) or ( ( not ( x ) ) and '0' ) ) ) ) and ( not ( ( ( y and ( not ( '0' ) ) ) or ( ( not ( y ) ) and '0' ) ) ) ) ) ) then
				F <= '1';
			else
				F <= '0';
			end if;
		end process;
end NOR_gate_arch;


