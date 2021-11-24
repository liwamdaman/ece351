entity Mux is port(
	I3, I2, I1, I0, S  : in bit;
	O0, O1  : out bit
);
end Mux;
architecture behv1 of Mux is

begin
	process ( I3, I2, I1, I0, S  ) 
		begin
			if ( ( not ( ( ( S and ( not ( '0' ) ) ) or ( ( not ( S ) ) and '0' ) ) ) ) ) then
				O0 <= I0;
				O1 <= I2;
			else
				O0 <= I1;
				O1 <= I3;
			end if;
		end process;
end behv1;


