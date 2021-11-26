entity DanWuzHere is port(
	J1, J0, I0, I1, I2, I3, S, T  : in bit;
	O0, O1, O2, O3, O4, O5, O6, O7, O8  : out bit
);
end DanWuzHere;
architecture behv1 of DanWuzHere is

begin
	process ( S, T, I0, I1  ) 
		begin
			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then
				O0 <= I0;
			else
				O0 <= I1;
			end if;
		end process;
	process ( S, T, I1, I2  ) 
		begin
			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then
				O1 <= I1;
			else
				O1 <= I2;
			end if;
		end process;
	process ( S, T, I0, I3, I1  ) 
		begin
			if ( ( not ( ( ( S and ( not ( T ) ) ) or ( ( not ( S ) ) and T ) ) ) ) ) then
				O2 <= ( I0 and I3 );
			else
				O2 <= ( I0 or I1 );
			end if;
		end process;
	process ( J1, J0  ) 
		begin
			O7 <= ( ( J0 and ( not ( J1 ) ) ) or ( ( not ( J0 ) ) and J1 ) );
			O8 <= ( not ( ( J1 and J0 ) ) );
		end process;
	process ( S, I0, I1  ) 
		begin
			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then
				O3 <= I0;
			else
				O3 <= I1;
			end if;
		end process;
	process ( S, I1, I2  ) 
		begin
			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then
				O4 <= I1;
			else
				O4 <= I2;
			end if;
		end process;
	process ( S, I2, I3, T  ) 
		begin
			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then
				O5 <= ( I2 and I3 );
			else
				O5 <= ( S or T );
			end if;
		end process;
	process ( S  ) 
		begin
			if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then
				O6 <= S;
			else
				O6 <= '0';
			end if;
		end process;
end behv1;


