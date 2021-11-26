entity OR_NOR is port(
	x, y  : in bit;
	F1, F2  : out bit
);
end OR_NOR;
architecture OR_NOR_arch of OR_NOR is

begin
	process ( x, y  ) 
		begin
			if ( ( ( not ( ( ( x and ( not ( '0' ) ) ) or ( ( not ( x ) ) and '0' ) ) ) ) and ( not ( ( ( y and ( not ( '0' ) ) ) or ( ( not ( y ) ) and '0' ) ) ) ) ) ) then
				F1 <= '0';
			else
				F1 <= '1';
			end if;
		end process;
	process ( x, y  ) 
		begin
			if ( ( ( not ( ( ( x and ( not ( '0' ) ) ) or ( ( not ( x ) ) and '0' ) ) ) ) and ( not ( ( ( y and ( not ( '0' ) ) ) or ( ( not ( y ) ) and '0' ) ) ) ) ) ) then
				F2 <= '1';
			else
				F2 <= '0';
			end if;
		end process;
end OR_NOR_arch;


