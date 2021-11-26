entity DanWuzHere is port(
	I11, I10, I9, I8, I7, I6, I5, I4, I3, I2, I1, I0, S, T  : in bit;
	O0, O1, O2, O3, O4, O5  : out bit
);
end DanWuzHere;
architecture behv1 of DanWuzHere is

begin
	process ( I5, I4, I3, I2, I1, I0, S, T  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '0' ) ) ) or ( ( not ( S ) ) and '0' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O0 <= I0;
				O1 <= I2;
				O2 <= I4;
			else
				O0 <= I1;
				O2 <= I5;
				O1 <= I3;
			end if;
		end process;
	process ( I11, I10, I9, I8, I7, I6, S, T  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O3 <= I6;
				O4 <= I8;
				O5 <= I10;
			else
				O3 <= I7;
				O4 <= I9;
				O5 <= I11;
			end if;
		end process;
end behv1;


