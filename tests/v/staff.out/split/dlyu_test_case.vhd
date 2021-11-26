entity DanWuzHere is port(
	I11, I10, I9, I8, I7, I6, I5, I4, I3, I2, I1, I0, S, T  : in bit;
	O0, O1, O2, O3, O4, O5  : out bit
);
end DanWuzHere;
architecture behv1 of DanWuzHere is

begin
	process ( S, T, I0, I1  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '0' ) ) ) or ( ( not ( S ) ) and '0' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O0 <= I0;
			else
				O0 <= I1;
			end if;
		end process;
	process ( S, T, I2, I3  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '0' ) ) ) or ( ( not ( S ) ) and '0' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O1 <= I2;
			else
				O1 <= I3;
			end if;
		end process;
	process ( S, T, I4, I5  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '0' ) ) ) or ( ( not ( S ) ) and '0' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O2 <= I4;
			else
				O2 <= I5;
			end if;
		end process;
	process ( S, T, I6, I7  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O3 <= I6;
			else
				O3 <= I7;
			end if;
		end process;
	process ( S, T, I8, I9  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O4 <= I8;
			else
				O4 <= I9;
			end if;
		end process;
	process ( S, T, I10, I11  ) 
		begin
			if ( ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) and ( not ( ( ( T and ( not ( '1' ) ) ) or ( ( not ( T ) ) and '1' ) ) ) ) ) ) then
				O5 <= I10;
			else
				O5 <= I11;
			end if;
		end process;
end behv1;


